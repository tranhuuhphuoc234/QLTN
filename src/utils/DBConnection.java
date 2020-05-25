package utils;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.sql.*;

public class DBConnection<T> {
    private String urlConnection = "jdbc:sqlserver://localhost:1433;databaseName=QLTN;user=sa;password=123456";

    public boolean Create(T item) {
        try (Connection con = DriverManager.getConnection(urlConnection)) {
            Class<?> classInfo = item.getClass();
            String className = classInfo.getName();
            int lastIndxDot = className.lastIndexOf(".");
            String tableName = className.substring(lastIndxDot + 1);
            String query = "INSERT INTO " + tableName + "(";
            Field[] fields = classInfo.getFields();
            for (Field columnName : fields
            ) {
                query += columnName.getName() + ",";
            }
            query = query.substring(0, query.length() - 1);
            query += ") VALUES(";
            for (Field fieldItem : fields
            ) {
                if (fieldItem.getType().equals(String.class)) {
                    query += "N'" + fieldItem.get(item) + "',";

                } else if (fieldItem.getType().equals(Timestamp.class)) {
                    query += "'" + fieldItem.get(item) + "',";
                } else if (fieldItem.getType().equals(int.class)) {
                    query += fieldItem.get(item) + ",";
                } else {
                    query += "convert(VARBINARY(max),'" + fieldItem.get(item) + "'),";
                }

            }
            query = query.substring(0, query.length() - 1);
            query += ")";
            System.out.println(query);
            PreparedStatement pstmt = con.prepareStatement(query);
            int check = pstmt.executeUpdate();
            if (check == 1) {
                return true;
            }


        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean check(String check, String value) {
        try (Connection con = DriverManager.getConnection(urlConnection)) {
            Statement stmt = con.createStatement();
            String query = "";
            if (check.equals("checkUser")) {
                query = "SELECT USERNAME FROM USERS WHERE USERNAME ='" + value + "'";
            }
            else if (check.equals("checkRelativeIdCard")) {
                query = "SELECT relativeidcard FROM relative WHERE relativeidcard ='" + value + "'";
            }
            else if (check.equals("checkPrisonerId"))
            {
                query ="checkprisonerid "+value;
            }
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();


        }
        return false;
    }

    public int callProc(String procName, String value) {
        try (Connection con = DriverManager.getConnection(urlConnection)) {
            String query = procName + " '" + value + "'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getLocation(String tableName,String columnValue ){
        try(Connection con = DriverManager.getConnection(urlConnection))
        {
            Statement stmt = con.createStatement();
            String query = "";
            if ( tableName.equals("country"))
            {
                query = "findcountrybycity N'"+columnValue+"'";
            }
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                return rs.getString(1);
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

    return null;
    }


    public int getLastId() {
        try (Connection con = DriverManager.getConnection(urlConnection)) {
            Statement stmt = con.createStatement();
            String query = "SELECT TOP 1 prisonerid FROM prisoner ORDER BY prisonerid DESC ";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getAllName(String tableName) {
        try (Connection con = DriverManager.getConnection(urlConnection)) {
            Statement stmt = con.createStatement();
            String columnName = tableName + "name";
            String query = "SELECT DISTINCT " + columnName + " FROM " + tableName;
            ResultSet rs = stmt.executeQuery(query);
            String allName = "";
            while (rs.next()) {
                allName += rs.getString(columnName) + ",";
            }
            allName = allName.substring(0, allName.length() - 1); //loai bo dau phay cuoi
            return allName;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }


    public int getColumnID(String tableName, String columnValue) {
        try (Connection con = DriverManager.getConnection(urlConnection)) {
            Statement stmt = con.createStatement();
            String columnID = tableName + "id";
            String columnName = tableName + "name";
            String query ="";
            if (!tableName.equals("relative")) {
                query = "SELECT " + columnID + " FROM " + tableName + " WHERE " + columnName + " = N'" + columnValue + "'";
            }
            else{
                query ="SELECT " +columnID +" FROM " + tableName + " WHERE " + tableName+"idcard ='" +columnValue+"'";
            }
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public boolean updatePrisoner(String priosnerId, String relativeid){
        try(Connection con = DriverManager.getConnection(urlConnection); Statement stmt = con.createStatement())
        {
            String query = "updateprisoner"+ priosnerId + ","+relativeid;
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next())
            {
                return true;
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    public DefaultTableModel findRelative(String idCard) {
        try (Connection con = DriverManager.getConnection(urlConnection); Statement stmt = con.createStatement()) {
            String query ="findrelativebyidcard '"+ idCard+"'";
            ResultSet rs = stmt.executeQuery(query);
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int col)/// lam bang k chinh sua dc
                {
                    return false;
                }
            };
            model.addColumn("Id Card");
            model.addColumn("Name");
            model.addColumn("Age");
            model.addColumn("Phone");
            model.addColumn("Address");
            model.addColumn("City");
            model.addColumn("Country");
            model.addColumn("Relationship");
            while (rs.next()){
                String id = rs.getString("relativeidcard");
                String name = rs.getString("relativename");
                Integer age = rs.getInt("relativeage");
                String phone = rs.getString("relativephone");
                String address = rs.getString("relativeaddress");
                String city = rs.getString("cityname");
                String country = rs.getString("countryname");
                String relationship = rs.getString("relationship");
                model.addRow(new Object[]{id,name,age,phone,address,city,country,relationship});
            }
            System.out.println(query);
            return model;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public DefaultTableModel findPrisoner(String idCard) {
        try (Connection con = DriverManager.getConnection(urlConnection);Statement stmt = con.createStatement();) {
            String query = "findprisonerbyidcard '"+idCard+"'";
            ResultSet rs = stmt.executeQuery(query);
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int col)/// lam bang k chinh sua dc
                {
                    return false;
                }
            };
            model.addColumn("Id Card");
            model.addColumn("Name");
            model.addColumn("Age");
            model.addColumn("Gender");
            model.addColumn("Date of Birth");
            model.addColumn("Date of Arrest");
            model.addColumn("Date of Release");
            model.addColumn("Crime");
            model.addColumn("Punishment");
            model.addColumn("Danger level");
            model.addColumn("Cell room");
            while (rs.next()) {
                String id = rs.getString("prisoneridcard");
                String name = rs.getString("prisonername");
                Integer age = rs.getInt("prisonerage");
                String gender = rs.getString("gender");
                String DoB = rs.getString("dateofbirth");
                String DoA = rs.getString("dateofarrest");
                String DoR = rs.getString("dateofrelease");
                String crime = rs.getString("crimename");
                String punishment = rs.getString("punishmentname");
                Integer danger = rs.getInt("dangerlevel");
                String cell = rs.getString("cellroomname");
                model.addRow(new Object[]{id, name, age, gender, DoB, DoA, DoR, crime, punishment, danger,cell});
            }
            System.out.println(query);
            return model;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

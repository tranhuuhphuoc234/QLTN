package utils;

import java.lang.reflect.Field;
import java.sql.*;

public class DBConnection<T> {
    private String urlConnection = "jdbc:sqlserver://localhost:1433;databaseName=QLMS;user=sa;password=123456";

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
                if (!fieldItem.getType().equals(int.class))
                    query += "'" + fieldItem.get(item) + "',";
                else {
                    query += fieldItem.get(item) + ",";
                }
            }
            query = query.substring(0, query.length() - 1);
            query += ")";
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

    public boolean checkUser(String userName) {
        try (Connection con = DriverManager.getConnection(urlConnection)) {
            Statement stmt = con.createStatement();
            String query = "SELECT USERNAME FROM USERS WHERE USERNAME ='" + userName + "'";
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public int getLastId(){
        try (Connection con = DriverManager.getConnection(urlConnection)){
            Statement stmt = con.createStatement();
            String query = "SELECT TOP 1 prisonerid FROM prisoner ORDER BY prisonerid DESC ";
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()){
                return rs.getInt(1);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

}


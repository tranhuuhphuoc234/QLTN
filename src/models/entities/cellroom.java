package models.entities;

public class cellroom {
    public String cellroomname;
    public int cellroomid,cellroomtype;

    public cellroom(String cellroomname, int cellroomtype, int cellroomid) {
        this.cellroomname = cellroomname;
        this.cellroomtype = cellroomtype;
        this.cellroomid = cellroomid;
    }
    public cellroom(){
        cellroomid = 0;
        cellroomname = "";
        cellroomtype = 0;
    }

    public String getCellroomname() {
        return cellroomname;
    }

    public void setCellroomname(String cellroomname) {
        this.cellroomname = cellroomname;
    }

    public int getCellroomtype() {
        return cellroomtype;
    }

    public void setCellroomtype(int cellroomtype) {
        this.cellroomtype = cellroomtype;
    }

    public int getCellroomid() {
        return cellroomid;
    }

    public void setCellroomid(int cellroomid) {
        this.cellroomid = cellroomid;
    }
}

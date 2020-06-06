package models.entities;

import java.sql.Timestamp;

public class VisitsChedule {
    public int prisonerid;
    public String visitorid;
    public Timestamp visitdate;

    public VisitsChedule(int prisonerid, String visitorid, Timestamp visitdate) {
        this.prisonerid = prisonerid;
        this.visitorid = visitorid;
        this.visitdate = visitdate;
    }
    public VisitsChedule(){
        prisonerid = 0;
        visitorid = "";
        visitdate = null;
    }

    public int getPrisonerid() {
        return prisonerid;
    }

    public void setPrisonerid(int prisonerid) {
        this.prisonerid = prisonerid;
    }

    public String getVisitorid() {
        return visitorid;
    }

    public void setVisitorid(String visitorid) {
        this.visitorid = visitorid;
    }

    public Timestamp getVisitdate() {
        return visitdate;
    }

    public void setVisitdate(Timestamp visitdate) {
        this.visitdate = visitdate;
    }
}

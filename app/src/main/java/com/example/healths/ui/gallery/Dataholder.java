package com.example.healths.ui.gallery;

public class Dataholder
{
String sym,predicDis,Datetime,USERID;

public Dataholder(String sym,String predicDis,String Datetime,String USERID)
{
    this.sym=sym;
    this.predicDis=predicDis;
    this.Datetime=Datetime;
    this.USERID=USERID;
}

    public String getDatetime() {
        return Datetime;
    }

    public void setDatetime(String datetime) {
        Datetime = datetime;
    }

    public String getSym() {
        return sym;
    }

    public void setSym(String sym) {
        this.sym = sym;
    }

    public String getPredicDis() {
        return predicDis;
    }

    public void setPredicDis(String predicDis) {
        this.predicDis = predicDis;
    }

    public String getUSERID() {
        return USERID;
    }

    public void setUSERID(String USERID) {
        this.USERID = USERID;
    }
}

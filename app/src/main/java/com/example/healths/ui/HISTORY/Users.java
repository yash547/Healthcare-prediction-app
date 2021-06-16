package com.example.healths.ui.HISTORY;
public class Users {

    String sym,predicDis,datetime,userid;

//    public String getDatetime() {
//        return Datetime;
//    }
//
//
//    public String getSym() {
//        return sym;
//
//    }
//
//    public String getPredicDis() {
//        return predicDis;
//    }
    public Users()
    {

    }
 public Users(String sym,String predicDis,String datetime,String userid)
{
    this.sym=sym;
    this.predicDis=predicDis;
    this.datetime=datetime;
    this.userid=userid;
}
    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {

       this.datetime = datetime;
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
//
    public String getUSERID() {
        return userid;
    }
//
    public void setUSERID(String USERID) {
        this.userid = USERID;
    }
}
package com.shms.shms_android.model;

public class Date {

    public static String getStringDate(int year, int mon, int day) {
        return getMonth(mon) + " " + day + " " + year;
    }

    private static String getMonth(int mon) {
        switch (mon) {
            case 2:
                return "Feb";
            case 3:
                return "Mar";
            case 4:
                return "Apr";
            case 5:
                return "May";
            case 6:
                return "Jun";
            case 7:
                return "Jul";
            case 8:
                return "Aug";
            case 9:
                return "Sep";
            case 10:
                return "Oct";
            case 11:
                return "Nov";
            case 12:
                return "Dec";
            case 1:
            default:
                return "Jan";
        }

    }

    public static String getDatabaseDate(int year, int mon, int day) {
        String m = ""+mon;
        if(m.length() == 1)
            m = "0"+m;
        String d = ""+day;
        if(d.length() == 1)
            d = "0"+d;
        return ""+year+"-"+m+"-"+d;
    }
}

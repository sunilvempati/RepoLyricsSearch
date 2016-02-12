package com.sunil.test.musicsearchapp.Utilities;


public class Utils {


    public static String getDuration(Integer trackTimeMillis) {

        int timeConstant=60;
        int SECOND=1000;
        int MINUTE=timeConstant*SECOND;
        int HOUR=timeConstant*MINUTE;
        //hh:mm
        String strText = "00:00";
        if (trackTimeMillis > MINUTE)
            {
                int mins = trackTimeMillis / MINUTE;
                int hours=0;
                if(mins>timeConstant){
                    hours=mins/timeConstant;
                    mins=mins%timeConstant;
                }
                String strhours=""+hours;
                String strMins=""+mins;
                if(hours<10)
                    strhours="0"+hours;
                if(mins<10)
                    strMins="0"+mins;
                strText=strhours+":"+strMins;
        }
        return  strText;
    }
}

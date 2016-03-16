package com.klepka.kamil.silentapp;

/**
 * Created by Kamil on 2016-03-16.
 */
public class SilentList {

    public  String id;
    public String TimeText;
    public String DayText;
    public String img_url;

    public SilentList (String p_TimeText,String p_DayText,String p_img_url)
    {
        TimeText = p_TimeText;
        DayText = p_DayText;
        img_url = p_img_url;
    }
}

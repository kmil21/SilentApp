package com.klepka.kamil.silentapp;

/**
 * Created by Kamil on 2016-03-30.
 */
public class SilentInfo {

    private int StartHour,EndHour,StartMin,EndMin;
    private boolean[] Days = new boolean[7];

    public SilentInfo (int StartHour,int EndHour, int StartMin,int EndMin,boolean[] Days)
    {
        this.Days=Days;
        this.StartHour = StartHour;
        this.EndHour = EndHour;
        this.StartMin = StartMin;
        this.EndMin = EndMin;
    }

    public int getStartHour ()
    {
        return this.StartHour;
    }
    public int getEndHour()
    {
        return this.EndHour;
    }
    public int getStartMin()
    {
        return StartMin;
    }
    public int getEndMin()
    {
        return EndMin;
    }
    public boolean[] getDays()
    {
        return this.Days;
    }
    public void setStartHour(int Hour)
    {
        StartHour = Hour;
    }
    public void setEndtHour(int Hour)
    {
        EndHour = Hour;
    }
    public void setStartMin(int Min)
    {
        StartMin= Min;
    }
    public void setEndMin(int Min)
    {
        EndMin = Min;
    }
    public void setDays(boolean[] days)
    {
        this.Days = days;
    }
}

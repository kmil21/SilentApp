package com.klepka.kamil.silentapp;


import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.touchboarder.weekdaysbuttons.WeekdaysDataItem;
import com.touchboarder.weekdaysbuttons.WeekdaysDataSource;
import com.touchboarder.weekdaysbuttons.WeekdaysDrawableProvider;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Kamil on 2016-03-09.
 */
public class SilentPicker extends AppCompatActivity implements WeekdaysDataSource.Callback {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private boolean is24Hour = true,VibOn=false;
    private WeekdaysDataSource weekdaysDataSource;
    private float LargeScaleX = 1.5f,LargeScaleY = 1.5f,NormalScaleX =1f,NormalScaleY=1f;
    private float LargeTxt = 23f,NormalTxt=18f;
    private TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_silent);
        NestedScrollView scrollView = (NestedScrollView) findViewById(R.id.nest_scrollview);
        scrollView.setFillViewport(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Sprawdzić oprogramowywanie tego guzika

        TimePicker start = (TimePicker) findViewById(R.id.timePickerStart);
        start.setIs24HourView(is24Hour);
        TimePicker end = (TimePicker) findViewById(R.id.timePickerEnd);
       end.setIs24HourView(is24Hour);
/*
        int screenSize = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;

        String toastMsg;
        switch(screenSize) {
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                toastMsg = "Large screen";

                break;
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                toastMsg = "Normal screen";

                break;
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                toastMsg = "Small screen";
                break;
            default:
                toastMsg = "Screen size is neither large, normal or small";
        }
        */

        if (savedInstanceState != null) {
            // Restore the weekdaysDataSource state, save a reference to weekdaysDataSource.

            weekdaysDataSource = WeekdaysDataSource.restoreState("wds1", savedInstanceState, this, callback1, null);
        }
        else {
            // No previous state, first creation.
            setupWeekdaysButtons();

        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        if (weekdaysDataSource != null)
        {
            weekdaysDataSource.saveState("wds1",outState);
        }
    }
    public void checkAll(View v) {
        switch (v.getId()) {
            case R.id.check_all:
                weekdaysDataSource.selectAll(((CheckBox) v).isChecked());
                break;

        }
    }
    public void checkFill(View v) {
        switch (v.getId()) {
            case R.id.check_fill:
                weekdaysDataSource.setFillWidth(((CheckBox) v).isChecked());
                break;
        }
    }

    public void setVibr (View v)
    {

    }
    public void setupWeekdaysButtons() {
        weekdaysDataSource  = new WeekdaysDataSource(this, R.id.weekdays_stub)
                .setDrawableType(WeekdaysDrawableProvider.MW_ROUND_RECT)
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setFontBaseSize(16)
                .setFontTypeFace(Typeface.MONOSPACE)
                .setUnselectedColorRes(R.color.colorPrimaryLight)
                .setTextColorUnselectedRes(R.color.colorSecondaryText)
                .setTextColorSelectedRes(R.color.black)
                .setNumberOfLetters(3)
                .setFillWidth(true)

                             .start(callback1);
    }

    private WeekdaysDataSource.Callback callback1 = new WeekdaysDataSource.Callback() {
        @Override
        public void onWeekdaysItemClicked(int attachId,WeekdaysDataItem item) {
            CheckBox checkBox = (CheckBox) findViewById(R.id.check_all);
            if (checkBox != null) checkBox.setChecked(weekdaysDataSource.isAllDaysSelected());

            Calendar calendar = Calendar.getInstance();
            int today = calendar.get(Calendar.DAY_OF_WEEK);
            if(item.getCalendarDayId()==today&&item.isSelected())
                Toast.makeText(SilentPicker.this,"Carpe diem",Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onWeekdaysSelected(int attachId,ArrayList<WeekdaysDataItem> items) {

        }
    };

    @Override
    public void onWeekdaysItemClicked(int attachId,WeekdaysDataItem item) {


        //Filter on the attached id if there is multiple weekdays data sources
        if(attachId==R.id.weekdays_recycler_view){
            Calendar calendar = Calendar.getInstance();
            int today = calendar.get(Calendar.DAY_OF_WEEK);
            if(item.getCalendarDayId()==today&&item.isSelected())
                Toast.makeText(SilentPicker.this,"Carpe diem",Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onWeekdaysSelected(int attachId,ArrayList<WeekdaysDataItem> items) {
//       String selectedDays=getSelectedDaysFromWeekdaysData(items);
//        if(!TextUtils.isEmpty(selectedDays))
//            showSnackbarShort(selectedDays);
    }
    public void TimeSilentSet (View view)
    {
        finish();
    }


}

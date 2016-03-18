package com.klepka.kamil.silentapp;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.touchboarder.weekdaysbuttons.WeekdaysDataItem;
import com.touchboarder.weekdaysbuttons.WeekdaysDataSource;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    WeekdaysDataSource weekdaysDataSource;
    Toolbar toolbar,toolbar2;
    Intent intent;
    ListView listView;
    private   ArrayList<RowItem> list = new ArrayList<RowItem>();;
    private ListViewAdapter adapter;
    Context context ;
    private String days = "P\t\tW\t\tŚ\t\tC\t\tP\t\tS\t\tN";
    private String time = "From: \t12:30 \n\n\t\t\tTo: \t15:00";
    String TestID;
    private Boolean[] activeAlarms= new Boolean[9] ;
    private int SilentID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



       toolbar2=(Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);
       // getSupportActionBar().setIcon(R.drawable.ic_add_alarm_black_48dp);
        intent = new Intent(this, SilentPicker.class);


        // getSupportActionBar().setIcon(R.drawable.ic_add_alarm_black_48dp);
        intent = new Intent(this, SilentPicker.class);
        toolbar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivityForResult(intent, 1);
                addItems();
            }
        });


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // startActivityForResult(new Intent(this, SilentPicker.class), 1);



        Spannable span = new SpannableString(days);

        for ( int i = 0, len = days.length(); i < len; i++ ){
            span.setSpan(new ForegroundColorSpan(getRandomColor()), i, i+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }


            listView = (ListView) findViewById(R.id.SilentsList);

            adapter = new ListViewAdapter(this, R.layout.column_row, list);

            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Toast.makeText(getApplicationContext(),
                    //    parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                    TestID = Integer.toString(position);
                }
            });


    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            list = (ArrayList<RowItem>) savedInstanceState.getSerializable("List");
            ListViewAdapter adapter = new ListViewAdapter(this,R.layout.column_row,list );
            listView = (ListView) findViewById(R.id.SilentsList);
            listView.setAdapter(adapter);

        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putSerializable("List", list);
        super.onSaveInstanceState(outState);
        outState.putParcelable("ListV", listView.onSaveInstanceState());

    }

    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
    public void addItems() {
        silentsList();
        adapter.notifyDataSetChanged();
    }
    public void deleteItems() {
        list.remove(2);
        adapter.notifyDataSetChanged();
    }

    private int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    private void silentsList()
    {

        RowItem items = new RowItem(R.drawable.ic_volume_up_black_18dp,time,SelectedDays(days) );
        activeAlarms[SilentID] = true;
        list.add(items);
        SilentID++;
    }

    private  Spannable SelectedDays(String days)
    {
        Spannable span = new SpannableString(days);

        for ( int i = 0, len = days.length(); i < len; i++ ){

            span.setSpan(new ForegroundColorSpan(getRandomColor()), i, i+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return span;
    }

    public void ActiveSilent(View view)
    {
        ImageView butn = (ImageView) view;

        Integer resource = (Integer)butn.getTag(R.id.ItemImgSrc);
        Integer tmp =(Integer) butn.getTag(R.id.ItemPosition);
        TestID = Integer.toString((Integer) butn.getTag(R.id.ItemPosition));
        if (resource == R.drawable.ic_volume_up_black_18dp) {
            butn.setImageResource(R.drawable.ic_volume_off_black_18dp);
            butn.setTag(R.id.ItemImgSrc, R.drawable.ic_volume_off_black_18dp);
            Toast.makeText(MainActivity.this,"Element klikniety:"+TestID,Toast.LENGTH_SHORT).show();
            RowItem row = list.get(tmp);
            row.setImageId(R.drawable.ic_volume_off_black_18dp);
            list.set(tmp,row);
            activeAlarms[tmp] = false;
        }
        else
        {
            butn.setImageResource(R.drawable.ic_volume_up_black_18dp);
            butn.setTag(R.id.ItemImgSrc,R.drawable.ic_volume_up_black_18dp);
            Toast.makeText(MainActivity.this,"Element klikniety:"+TestID,Toast.LENGTH_SHORT).show();
            RowItem row = list.get(tmp);
            row.setImageId(R.drawable.ic_volume_up_black_18dp);
            list.set(tmp, row);
            activeAlarms[tmp] = true;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

       getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
                Toast.makeText(MainActivity.this,"Brak nowego alarmu",Toast.LENGTH_SHORT).show();
            }

        }
    }

}

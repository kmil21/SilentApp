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
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.touchboarder.weekdaysbuttons.WeekdaysDataItem;
import com.touchboarder.weekdaysbuttons.WeekdaysDataSource;

import java.io.Serializable;
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
   // private String days5 = "P\t\tW\t\tŚ\t\tC\t\tP\t\tS\t\tN";
    private String days;
    //private String time = "From: \t12:30 \n\n\t\t\tTo: \t15:00";
    String TestID;
    private Boolean[] activeAlarms= new Boolean[9] ;
    private int SilentID = 0;
    private int currentposition;
    private ArrayList<SilentInfo> SilentSettings = new ArrayList<SilentInfo>() ;

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
                startActivityForResult(intent, 1);
                //addItems();
            }
        });

    days = getResources().getString(R.string.DaysOfWeek);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // startActivityForResult(new Intent(this, SilentPicker.class), 1);




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


          registerForContextMenu(listView);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        Log.d("Test", "Wykonuje sie z tagie :" + v.getTag());

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        currentposition = info.position;
        menu.add(0, v.getId(),0, getString(R.string.edit));
        menu.add(0, v.getId(), 0, getString(R.string.remove));


    }



    private void editSilent(int SilentId) {
        Log.d("Edit", "Wywołano edycje alarmu " + Integer.toString(SilentId));
    }
    @Override
    public boolean onContextItemSelected(MenuItem item)
    {


        if (item.getTitle() == getString( R.string.edit)){editSilent(currentposition);}
        else if(item.getTitle() == getString( R.string.remove)){deleteItems(currentposition);}
        else {return false;}

        return true;
    }



    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
    public void addItems(SilentInfo setInforamtion) {

        String Times ="From: \t";
        Times+= Integer.toString(setInforamtion.getStartHour())+":"+Integer.toString(setInforamtion.getStartMin())+"\n\n\t\t\tTo:\t";
        Times+=Integer.toString(setInforamtion.getEndHour())+":"+Integer.toString(setInforamtion.getEndMin());

        //private String time = "From: \t12:30 \n\n\t\t\tTo: \t15:00";
        RowItem items = new RowItem(R.drawable.ic_volume_up_black_18dp,Times,SelectedDays(days,setInforamtion.getDays()) );
        //activeAlarms[SilentID] = true;
        list.add(items);
       // SilentID++;
        //silentsList();
        adapter.notifyDataSetChanged();
        Log.d("Test", "Wykonuje sie");
    }

    public void deleteItems(int Dposition) {
        list.remove(Dposition);
        adapter.notifyDataSetChanged();
    }

    private int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
    private int getActivColor(){

        return Color.parseColor("#39FF14");
    }

    private  Spannable SelectedDays(String days,boolean[] DaysActiv)
    {
        Spannable span = new SpannableString(days);
        int k= 0;
        // #39FF14 kolor zielony
        for ( int i = 0, len = days.length(); i < len; i++ ){

            if (days.charAt(i) != '\t')
            {
               // Log.d("Tutaj","Jestem");
                if (DaysActiv[k]) {
                    span.setSpan(new ForegroundColorSpan(getActivColor()), i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    k++;
                    Log.d("Tutaj","Jestem "+ Integer.toString(k));
                }
                else
                    k++;
            }
            else {
                span.setSpan(new ForegroundColorSpan(Color.BLACK), i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
               // Log.d("Tutaj", "Jestem 22");
            }
        }
        for (int i= 0;i<span.length();i++)
        {

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
                boolean[] DaySet = new boolean[7];
                String[] result= new String[11];
                result[0] = data.getStringExtra("StartHour");
                result[1] = data.getStringExtra("StartMin");
                result[2] = data.getStringExtra("EndHour");
                result[3] = data.getStringExtra("EndMin");
                for (int i = 4;i<=10;i++)
                {
                    result[i]= data.getStringExtra("Day"+Integer.toString(i-3));
                    if(result[i].equals("1"))
                    {
                        DaySet[i-4] = true;
                       // Log.d("Tutaj","Jestem");
                    }
                    else
                        DaySet[i-4] = false;
                   // Log.d("Tutaj","Nie powinienem być");
                }
                String DaysTest = "";
                Log.d("Godzina Start ",result[0]+":"+result[1]);
                Log.d("Godzina Koniec ",result[2]+":"+result[3]);
                for(int i=0;i<7;i++)
                {
                    DaysTest += result[i+4] + " , ";
                }

                Log.d("Dni ", DaysTest);

                SilentInfo set = new SilentInfo(Integer.parseInt(result[0]),Integer.parseInt(result[2]),Integer.parseInt(result[1]),Integer.parseInt(result[3]),DaySet);
                addItems(set);

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
                Toast.makeText(MainActivity.this,"Brak nowego alarmu",Toast.LENGTH_SHORT).show();
            }

        }
    }

}

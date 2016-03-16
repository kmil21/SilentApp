package com.klepka.kamil.silentapp;

import static com.klepka.kamil.silentapp.Constants.FIRST_COLUMN;
import static com.klepka.kamil.silentapp.Constants.SECOND_COLUMN;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.touchboarder.weekdaysbuttons.WeekdaysDataItem;
import com.touchboarder.weekdaysbuttons.WeekdaysDataSource;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    WeekdaysDataSource weekdaysDataSource;
    Toolbar toolbar,toolbar2;
    Intent intent;
    ListView listView;
    private   ArrayList<RowItem> list;
    Context context ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



       toolbar2=(Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);
       getSupportActionBar().setTitle("Dodaj nowy");
       // getSupportActionBar().setIcon(R.drawable.ic_add_alarm_black_48dp);
        intent = new Intent(this, SilentPicker.class);


        // getSupportActionBar().setIcon(R.drawable.ic_add_alarm_black_48dp);
        intent = new Intent(this, SilentPicker.class);
        toolbar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(intent, 1);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // startActivityForResult(new Intent(this, SilentPicker.class), 1);

        RowItem RowData[] = new RowItem[]
                {
                        new RowItem(R.drawable.ic_volume_off_black_18dp,"Dupa", "Cyce"),
        new RowItem(R.drawable.ic_volume_off_black_18dp,"Dupa2", "Cyce"),
        new RowItem(R.drawable.ic_volume_off_black_18dp,"Dupa3", "Cyce")
                };
        Log.d("Krok 1", "tutaj");
        listView=(ListView) findViewById(R.id.SilentsList);
      //  silentsList();
        ListViewAdapter adapter=new ListViewAdapter(this,R.layout.column_row,RowData);
        Log.d("Krok 2", "tutaj");
        listView.setAdapter(adapter);

    }


    private void silentsList()
    {
        int id = R.drawable.ic_volume_off_black_48dp;
        list =new ArrayList<RowItem>();
        RowItem items = new RowItem(R.drawable.ic_volume_off_black_48dp,"Dupa", "Cyce");
        list.add(items);
       /* HashMap<String,String> temp = new HashMap<String,String>();
            temp.put(FIRST_COLUMN,"colored Notebook");
            temp.put(SECOND_COLUMN,"By Nevneet");

*/

        RowItem items2 = new RowItem(R.drawable.ic_volume_off_black_48dp,"Dupa2", "Cyce2");
        list.add(items2);
        RowItem items3 = new RowItem(R.drawable.ic_volume_off_black_48dp,"Dupa3", "Cyce3");
        list.add(items3);
        RowItem items4 = new RowItem(R.drawable.ic_volume_off_black_48dp,"Dupa4", "Cyce4");
        list.add(items4);
        //list.add(items);

    }

    public void AddNewSilent(View view)
    {

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
            }

        }
    }

}

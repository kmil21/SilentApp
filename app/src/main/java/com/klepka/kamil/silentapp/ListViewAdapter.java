package com.klepka.kamil.silentapp;

import static com.klepka.kamil.silentapp.Constants.FIRST_COLUMN;
import static com.klepka.kamil.silentapp.Constants.SECOND_COLUMN;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Kamil Klepka on 2016-03-16.
 */
public class ListViewAdapter extends ArrayAdapter<RowItem>{

    public ArrayList<HashMap<String, String>> list;
    Activity activity;
    List<RowItem> rowItems;
    Context context;
    int layoutResourceId;
    RowItem data[] = null;

    public  ListViewAdapter(Context context,int layoutResourceId, RowItem[] data)
    {

        super(context,layoutResourceId,data);
        this.layoutResourceId =layoutResourceId;
       // this.activity = activity;
      this.context = context;
        this.data =data;
        Log.d("Krok 3", "tutaj");
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    private class  ViewHolder{
    TextView txtTime;
    TextView txtDay;
    ImageView imgVolum;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       ViewHolder holder = null;

        Log.d("Krok 4", "tutaj");

        if(convertView == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView=inflater.inflate(layoutResourceId,parent,false);
            holder =new ViewHolder();
            holder.txtTime = (TextView) convertView.findViewById(R.id.TextTime);
            holder.txtDay = (TextView) convertView.findViewById(R.id.TextDay);
            holder.imgVolum = (ImageView) convertView.findViewById(R.id.activAlarm);
            convertView.setTag(holder);
            Log.d("dupa","tutaj");
        }
        else {
            holder=(ViewHolder) convertView.getTag();
        }

        //RowItem rowItem = (RowItem) getItem(position);

        RowItem Item = data[position];
        /*
        HashMap<String, String> map=list.get(position);
        holder.txtTime.setText(map.get(FIRST_COLUMN));
        holder.txtTime.setText(map.get(SECOND_COLUMN));
        holder.imgVolum.setImageResource(R.drawable.ic_volume_up_black_48dp);
        */
        holder.txtTime.setText(Item.getDesc());
        holder.txtTime.setText(Item.getTitle());
        holder.imgVolum.setImageResource(Item.getImageId());
        return convertView;
    }
}

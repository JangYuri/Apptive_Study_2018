package com.example.yuri.myapplication;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by yuri on 2018. 4. 28..
 */

public class FragmentTab1 extends Fragment {

    int i=0;

    private DBOpenHelper mDbOpenHelper;
    String sort = "name";

    ListViewAdapter mAdapter;

    static ArrayList<ListViewItem> arrayData;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);

        ListView listView= view.findViewById(R.id.list_friends_section);


        //header setting
        View header = getLayoutInflater().inflate(R.layout.list_header, null, false) ;
        ImageView header_pPic_me = header.findViewById(R.id.list_item_pPic_me);
        TextView header_name_me = header.findViewById(R.id.list_item_name_me);
        TextView header_message_me = header.findViewById(R.id.list_item_message_me);

        Bitmap bitmap = BitmapFactory.decodeResource(header.getResources(), R.drawable.thanos);
        RoundedBitmapDrawable roundB = RoundedBitmapDrawableFactory.create(header.getResources(),bitmap);
        roundB.setCircular(true);
        header_pPic_me.setImageDrawable(roundB);
        header_name_me.setText("짱유");
        header_message_me.setText("으아ㅏ아아아ㅏ아");


        ImageView header_pPic_mark = header.findViewById(R.id.list_item_pPic_mark);
        TextView header_name_mark = header.findViewById(R.id.list_item_name_mark);
        TextView header_message_mark = header.findViewById(R.id.list_item_message_mark);

        bitmap = BitmapFactory.decodeResource(header.getResources(), R.drawable.thanos);
        roundB = RoundedBitmapDrawableFactory.create(header.getResources(),bitmap);
        roundB.setCircular(true);
        header_pPic_mark.setImageDrawable(roundB);
        header_name_mark.setText("타노스");
        header_message_mark.setText("우주 최강");


        ImageView header_pPic_recom = header.findViewById(R.id.list_item_pPic_recom);
        TextView header_name_recom = header.findViewById(R.id.list_item_name_recom);
        TextView header_message_recom = header.findViewById(R.id.list_item_message_recom);

        bitmap = BitmapFactory.decodeResource(header.getResources(), R.drawable.ryan);
        roundB = RoundedBitmapDrawableFactory.create(header.getResources(),bitmap);
        roundB.setCircular(true);
        header_pPic_recom.setImageDrawable(roundB);
        header_name_recom.setText("새로운 친구를 만나보세요!");
        header_message_recom.setText("44 >");


        ImageView header_pPic_group = header.findViewById(R.id.list_item_pPic_group);
        TextView header_name_group = header.findViewById(R.id.list_item_name_group);

        bitmap = BitmapFactory.decodeResource(header.getResources(), R.drawable.plus_friends);
        roundB = RoundedBitmapDrawableFactory.create(header.getResources(),bitmap);
        roundB.setCircular(true);
        header_pPic_group.setImageDrawable(roundB);
        header_name_group.setText("플러스친구");



        listView.addHeaderView(header);


        mDbOpenHelper = new DBOpenHelper(this.getContext());
        mDbOpenHelper.open();
        mDbOpenHelper.create();
        mDbOpenHelper.deleteAllColumns();

        mDbOpenHelper.insertColumn(R.drawable.ppic_01, "13학번", "ㅋㅋ");
        mDbOpenHelper.insertColumn(R.drawable.ppic_02, "14학번", "ㅇㅇ");
        mDbOpenHelper.insertColumn(R.drawable.ppic_03, "네즈", "ㅎㅇㅎㅇ");
        mDbOpenHelper.insertColumn(R.drawable.ppic_04, "쪙", "ㅍㅍㅍㅍ");
        for (int i=0; i<20; i++) {
            mDbOpenHelper.insertColumn(R.drawable.basic_pic, "누구 "+(i++), "");
        }



        arrayData =  new ArrayList<>();
        showDatabase(sort);

        mAdapter = new ListViewAdapter(getContext(), R.layout.friends_list_item_layout, arrayData);
        listView.setAdapter(mAdapter);
        return view;
    }
    @Override
    public void onDestroy() {
        mDbOpenHelper.close();
        super.onDestroy();
    }

    public void showDatabase(String sort){
        Cursor iCursor = mDbOpenHelper.sortColumn(sort);
        Log.e("showDatabase", "DB Size: " + iCursor.getCount());

        while(iCursor.moveToNext()){
            ListViewItem data = new ListViewItem(
                    iCursor.getInt(iCursor.getColumnIndex("userimage")),
                    iCursor.getString(iCursor.getColumnIndex("name")),
                    iCursor.getString(iCursor.getColumnIndex("message"))
            );
            arrayData.add(data);
        }
        iCursor.close();
    }


}

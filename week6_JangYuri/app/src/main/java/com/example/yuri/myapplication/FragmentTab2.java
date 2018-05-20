package com.example.yuri.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by yuri on 2018. 4. 28..
 */

public class FragmentTab2 extends Fragment {

    private DBOpenHelper_Room mDbOpenHelper;
    String sort = "time";

    ListViewAdapter2 mAdapter;
    static ArrayList<ListViewItem> arrayData;

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab2, container, false);

        ListView listView = view.findViewById(R.id.list_chattingRoom);


        mDbOpenHelper = new DBOpenHelper_Room(this.getContext());
        mDbOpenHelper.open();
        mDbOpenHelper.create();
        mDbOpenHelper.deleteAllColumns();


        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");

        mDbOpenHelper.insertColumn(R.drawable.ppic_01, "1415", "asdf", formatter.format(date));
        mDbOpenHelper.insertColumn(R.drawable.ppic_02, "2222", "rtw",  formatter.format(date));
        mDbOpenHelper.insertColumn(R.drawable.ppic_03, "3333", "ert",  formatter.format(date));
        mDbOpenHelper.insertColumn(R.drawable.ppic_04, "ee33", "wrt",  formatter.format(date));

        arrayData =  new ArrayList<>();
        showDatabase(sort);

        mAdapter = new ListViewAdapter2(getContext(), R.layout.chatting_list_item_layout, arrayData);
        listView.setAdapter(mAdapter);
//        ArrayList<ListViewItem> friendsList = new ArrayList<>();
//
//        for(int i=0; i<20; i++) {
//            friendsList.add(new ListViewItem(R.drawable.basic_pic, "구사몬들..", "피크닉가쟝!~!", "3:38"));
//        }
//
//
//        ListViewAdapter2 adapter = new ListViewAdapter2(getContext(), R.layout.chatting_list_item_layout, friendsList);
//        listView.setAdapter(adapter);

        return view;
    }
    @Override
    public void onDestroy() {
        mDbOpenHelper.close();
        super.onDestroy();
    }

    public void showDatabase(String sort){
        Cursor iCursor = mDbOpenHelper.sortColumn(sort);
        Log.d("showDatabase", "DB Size: " + iCursor.getCount());

        while(iCursor.moveToNext()){
            ListViewItem data = new ListViewItem(
                    iCursor.getInt(iCursor.getColumnIndex("userimage")),
                    iCursor.getString(iCursor.getColumnIndex("chatname")),
                    iCursor.getString(iCursor.getColumnIndex("lastwords")),
                    iCursor.getString(iCursor.getColumnIndex("time"))
            );
            arrayData.add(data);
        }
        iCursor.close();
    }

}

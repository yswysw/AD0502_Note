package com.example.ad0502_note.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.ad0502_note.adapter.MyListViewAdapter;
import com.example.ad0502_note.dbutils.Note;
import com.example.ad0502_note.dbutils.NoteDBUtil;
import com.example.ad0502_note.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton write_note;
    ArrayList<Note> list = new ArrayList<>();
//    private FloatingActionButton search;
    private SwipeMenuListView mSwipeMenuListView;
    private MyListViewAdapter myListViewAdapter;
    private SwipeMenuCreator mSwipeMenuCreator;
    private int index11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        //从数据库获得资源所有数据。
        list = NoteDBUtil.getNoteList(MainActivity.this);
        initMyAdapter();

    }

    private void initMyAdapter() {
        myListViewAdapter = new MyListViewAdapter(MainActivity.this, list);
        mSwipeMenuListView.setAdapter(myListViewAdapter);
    }



    private void initViews() {

        write_note = (FloatingActionButton) findViewById(R.id.write_note);
        write_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddActivity.class);
                startActivity(i);
                finish();
            }
        });

//        search = (FloatingActionButton) findViewById(R.id.search);
//        search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, SearchActivity.class);
//                startActivity(i);
//                finish();
//            }
//        });


        SlideListView();


        mSwipeMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note n = list.get(position);
                Intent i = new Intent(MainActivity.this, ShowItemActivity.class);
                i.putExtra("id", n.getId());
                i.putExtra("title", n.getTitle());
//                Log.d("aaa", "onItemClick: " + n.getTitle());
                i.putExtra("type", n.getType());
                i.putExtra("content", n.getContent());
                startActivityForResult(i, 1001);
                //finish();//不需要finish:
            }
        });
    }

    private void SlideListView() {

        mSwipeMenuListView = (SwipeMenuListView) findViewById(R.id.listView);
        mSwipeMenuCreator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(120);
                // set item title
                openItem.setTitle("X");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);

                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(200);

                // set a icon
                deleteItem.setIcon(R.mipmap.ic_launcher);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        // set creator
        mSwipeMenuListView.setMenuCreator(mSwipeMenuCreator);
        mSwipeMenuListView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);

        mSwipeMenuListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {
            @Override
            public void onSwipeStart(int index1) {
                index11 = index1;
                Toast.makeText(MainActivity.this, "" + index1, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipeEnd(int position) {


            }
        });

        mSwipeMenuListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open
                        break;
                    case 1:
                        // delete
                        //删除让数据库把对应的数据的isDel字段更改为1.
                        NoteDBUtil.Update(MainActivity.this, list.get(index11).getId());
                        list = NoteDBUtil.getNoteList(MainActivity.this);//这个方法我始终是一个list并且按时清空
                        //list.remove(index11);
                        Log.i("aaaaaaaaaaa", "onMenuItemClick: " + list.size());

                        myListViewAdapter.notifyDataSetChanged();
                        Log.i("aaaaaaaaaaa", "onMenuItemClick: " + list.size());


                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });


    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//        myListViewAdapter.notifyDataSetChanged();
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1001:
                if (resultCode == RESULT_OK) {
                    list = NoteDBUtil.getNoteList(MainActivity.this);
//                    initMyAdapter();
                    myListViewAdapter.notifyDataSetChanged();
                }
        }
    }
}

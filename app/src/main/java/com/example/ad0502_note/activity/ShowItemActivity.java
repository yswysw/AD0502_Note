package com.example.ad0502_note.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.ad0502_note.dbutils.NoteDBUtil;
import com.example.ad0502_note.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ShowItemActivity extends AppCompatActivity {
    private Spinner spinner;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;

    private EditText change_title;
    private EditText change_content;
    private Button Chnage_Note_Ok;
    private String value;
    private String str_type;
    private String type;
    private int id;
    private FloatingActionButton return_to_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_item);

        initViews();
        //让选项框的值是我传过来的。
        initSpinner();
        //获得类型
        getTypeSpinner();
    }

    private void initViews() {
        change_title = (EditText) findViewById(R.id.change_title);
        change_content = (EditText) findViewById(R.id.change_content);
        Chnage_Note_Ok = (Button) findViewById(R.id.change_btn);
        Chnage_Note_Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShowItemActivity.this, MainActivity.class);
                String title = change_title.getText().toString().trim();
                if (str_type == null) {
                    type = value;
                } else {
                    type = str_type;
                }
                String content = change_content.getText().toString().trim();

                //获得更改时间
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                java.util.Date time = new java.util.Date();
                String modifydate = sdf.format(time);
                Log.i("modifydate", modifydate);
                NoteDBUtil.Change_message(ShowItemActivity.this, id, title, type, content, modifydate);

                setResult(RESULT_OK, i);
                finish();
            }
        });

        return_to_main = (FloatingActionButton) findViewById(R.id.return_to_main);
        return_to_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ShowItemActivity.this, MainActivity.class);
                //startActivity(i);细节:共享同一个requestCode和同一个RESULT_OK。
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }


    private void initSpinner() {

        spinner = (Spinner) findViewById(R.id.change_spinner_type);


        //数据
        data_list = new ArrayList<String>();
        data_list.add("null");
        data_list.add("学习");
        data_list.add("生活");
        data_list.add("旅游");
        data_list.add("约会");

        //适配器
        arr_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(arr_adapter);


        if (getIntent() == null)
            return;
        id = getIntent().getIntExtra("id", 0);
        String title = getIntent().getStringExtra("title");
        if(!title.isEmpty()){
            change_title.setText("");
            Toast.makeText(this, "这里没有执行", Toast.LENGTH_SHORT).show();
        }else {
            change_title.setText(title);
        }
        value = getIntent().getStringExtra("type");
        setSpinnerItemSelectedByValue(spinner, value);
        String content = getIntent().getStringExtra("content");
        change_content.setText(content);

    }

    public static void setSpinnerItemSelectedByValue(Spinner spinner, String value) {

        SpinnerAdapter apsAdapter = spinner.getAdapter(); //得到SpinnerAdapter对象
        int k = apsAdapter.getCount();
        for (int i = 0; i < k; i++) {
            if (value.equals(apsAdapter.getItem(i).toString())) {
                spinner.setSelection(i, true);// 默认选中项
                break;
            }
        }
    }


    private void getTypeSpinner() {
//        spinner_type.setSelection(0, false);//网上搜索到解决没有点击spinner也会执行点击事件的措施.

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //拿到被选择项的值
                str_type = (String) spinner.getSelectedItem();
                Log.i("aaaaa", str_type);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });


    }


}

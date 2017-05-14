package com.example.ad0502_note.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ad0502_note.R;
import com.example.ad0502_note.dbutils.NoteDBUtil;

import java.text.SimpleDateFormat;

public class AddActivity extends AppCompatActivity {
    private EditText edt_title;
    private Spinner spinner_type;
    private String str_type;
    private EditText edt_content;
    private Button btn_to_db;
    private FloatingActionButton return_to_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initViews();
        //获得类型
        getTypeSpinner();
    }

    private void initViews() {
        edt_title = (EditText) findViewById(R.id.edt_title);
        spinner_type = (Spinner) findViewById(R.id.spinner_type);
        edt_content = (EditText) findViewById(R.id.edt_content);
        btn_to_db = (Button) findViewById(R.id.btn_to_db);

        btn_to_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获得标题
                String title = edt_title.getText().toString().trim();
                if(title.isEmpty()){
                    title = "null";
                }
                String type = str_type;
                if(type.isEmpty()){
                    type = (String)spinner_type.getItemAtPosition(0);
                    Log.d("aaaaa", "onClick: " + type);
                }
                //获得内容
                String content = edt_content.getText().toString().trim();
                //获得创建时间
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                java.util.Date time = new java.util.Date();
                String createdate = sdf.format(time);

                boolean isInsert = NoteDBUtil.addNote(AddActivity.this, title, type, content, createdate);
                Toast.makeText(AddActivity.this, ""+isInsert, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(AddActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });


        return_to_main = (FloatingActionButton) findViewById(R.id.return_to_main);
        return_to_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(AddActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }


    private void getTypeSpinner() {
//        spinner_type.setSelection(0, false);//网上搜索到解决没有点击spinner也会执行点击事件的措施.

                spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view,
                                               int position, long id) {
                        //拿到被选择项的值
                        str_type = (String) spinner_type.getSelectedItem();
                        Log.i("aaaaa", str_type);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // TODO Auto-generated method stub

                    }
                });


    }

}

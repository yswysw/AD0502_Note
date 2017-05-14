package com.example.ad0502_note.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ad0502_note.R;

public class SearchActivity extends AppCompatActivity {
    private Button btn_start_time;
    private Button btn_end_time;
    private FloatingActionButton return_to_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initViews();
    }

    private void initViews() {
        btn_start_time = (Button) findViewById(R.id.btn_start_time);
        btn_start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SearchActivity.this,SelectTimeActivity.class);
                startActivityForResult(i,2001);
            }
        });

        btn_end_time = (Button) findViewById(R.id.btn_end_time);
        btn_end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SearchActivity.this,SelectTimeActivity.class);
                startActivityForResult(i,2002);
            }
        });

        return_to_main = (FloatingActionButton) findViewById(R.id.return_to_main);
        return_to_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SearchActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}

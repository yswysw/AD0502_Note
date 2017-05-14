package com.example.ad0502_note.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.example.ad0502_note.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

public class SelectTimeActivity extends AppCompatActivity {
    private MaterialCalendarView mMaterialCalendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time);

        initViews();
    }

    private void initViews() {
        mMaterialCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        mMaterialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Intent i = new Intent(SelectTimeActivity.this,SearchActivity.class);
                startActivity(i);
            }
        });
    }
}

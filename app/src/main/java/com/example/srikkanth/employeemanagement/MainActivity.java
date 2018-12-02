package com.example.srikkanth.employeemanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button AddRecordButton;
    private Button EditRecordButton;
    private Button ViewAllRecordsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddRecordButton=findViewById(R.id.Add_Record_Button);
        EditRecordButton=findViewById(R.id.Edit_Record_Button);
        ViewAllRecordsButton=findViewById(R.id.View_All_Record_button);
        AddRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,PostActivity.class);
                startActivity(intent);
            }
        });
        EditRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,EditActivity.class);
                startActivity(intent);
            }
        });
        ViewAllRecordsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ViewActivity.class);
                startActivity(intent);
            }
        });
    }
}

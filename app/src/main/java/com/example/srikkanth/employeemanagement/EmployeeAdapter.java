package com.example.srikkanth.employeemanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class EmployeeAdapter extends ArrayAdapter<EmployeeRecord> {
    private Context context;
    private int ResourceLayout;
    private TextView EmployeeIdTextView,FirstNameTextView,LastNameTextView,GenderTextView,AgeTextView,EmailTextView,ContactTextView;
    public EmployeeAdapter(Context context, int resource, List<EmployeeRecord> employeeRecords) {
        super(context, resource, employeeRecords);
        this.context = context;
        this.ResourceLayout = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            v = vi.inflate(ResourceLayout, null);
        }
        init(v);
        EmployeeRecord employeeRecord = (EmployeeRecord) getItem(position);
        EmployeeIdTextView.setText(employeeRecord.getId());
        FirstNameTextView.setText(employeeRecord.getFirstName());
        LastNameTextView.setText(employeeRecord.getLastName());
        GenderTextView.setText(employeeRecord.getGender());
        AgeTextView.setText(employeeRecord.getAge());
        EmailTextView.setText(employeeRecord.getEmail());
        ContactTextView.setText(employeeRecord.getContact());
        return v;
    }
    private void init(View V){
        EmployeeIdTextView=V.findViewById(R.id.id_text_view);
        FirstNameTextView=V.findViewById(R.id.firstname_text_view);
        LastNameTextView=V.findViewById(R.id.lastname_text_view);
        GenderTextView=V.findViewById(R.id.gender_text_view);
        AgeTextView=V.findViewById(R.id.age_text_view);
        EmailTextView=V.findViewById(R.id.email_text_view);
        ContactTextView=V.findViewById(R.id.contact_text_view);
    }
}
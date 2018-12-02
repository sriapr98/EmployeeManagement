package com.example.srikkanth.employeemanagement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class EditActivity extends AppCompatActivity {
    private EditText EmployeeIdEditText;
    private Button ViewEmployeeButton,DeleteEmployeeButton;
    private TextView FirstNameTextView,LastNameTextView,AgeTextView,GenderTextView,EmailTextView,ContactTextView;
    private LinearLayout DisplayDetailsLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        init();
        ViewEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DisplayDetailsLinearLayout.setVisibility(View.GONE);
                    VolleyPost("View");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        DeleteEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DisplayDetailsLinearLayout.setVisibility(View.GONE);
                    VolleyPost("Delete");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void init(){
        EmployeeIdEditText=findViewById(R.id.Id_Edit_Text);
        ViewEmployeeButton=findViewById(R.id.View_Employee_Button);
        DeleteEmployeeButton=findViewById(R.id.Delete_Employee_Button);
        DisplayDetailsLinearLayout=findViewById(R.id.RESPONSE_LINEAR_LAYOUT);
        FirstNameTextView=findViewById(R.id.Display_FirstName_Text_View);
        LastNameTextView=findViewById(R.id.Display_LastName_Text_View);
        GenderTextView=findViewById(R.id.Display_Gender_Text_View);
        AgeTextView=findViewById(R.id.Display_Age_Text_View);
        EmailTextView=findViewById(R.id.Display_Email_Text_View);
        ContactTextView=findViewById(R.id.Display_Contact_Text_View);
    }
    public void VolleyPost(final String action) throws JSONException {
        String ip_address=getString(R.string.Ip_address);
        String Url="http://"+ip_address+"/edit.php?Action="+action+"&Id="+EmployeeIdEditText.getText().toString();
        StringRequest postRequest = new StringRequest(Request.Method.GET, Url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        if(action.equals("View") && !response.equals("No Record Found")) {
                                Gson g = new Gson();
                                Log.e("Response",response);
                                EmployeeRecord employeeRecord = g.fromJson(response, EmployeeRecord.class);
                                DisplayDetailsLinearLayout.setVisibility(View.VISIBLE);
                                FirstNameTextView.setText(employeeRecord.getFirstName());
                                LastNameTextView.setText(employeeRecord.getLastName());
                                GenderTextView.setText(employeeRecord.getGender());
                                AgeTextView.setText(employeeRecord.getAge());
                                EmailTextView.setText(employeeRecord.getEmail());
                                ContactTextView.setText(employeeRecord.getContact());
                            }
                        else{
                            Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(postRequest);

    }
}

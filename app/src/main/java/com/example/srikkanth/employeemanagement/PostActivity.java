package com.example.srikkanth.employeemanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class PostActivity extends AppCompatActivity {
    private EditText EmployeeIdEditText,FirstNameEditText,LastNameEditText,GenderEditText,AgeEditText,EmailEditText,ContactEditText;
    private Button AddRecordButton,UpdateRecordButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        init();
        AddRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    VolleyPost("Add");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        UpdateRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    VolleyPost("Update");
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }
    private void init(){
        EmployeeIdEditText=findViewById(R.id.employee_id_edit_text);
        FirstNameEditText=findViewById(R.id.first_name_edit_text);
        LastNameEditText=findViewById(R.id.last_name_edit_text);
        GenderEditText=findViewById(R.id.gender_edit_text);
        AgeEditText=findViewById(R.id.age_edit_text);
        EmailEditText=findViewById(R.id.email_edit_text);
        ContactEditText=findViewById(R.id.contact_edit_text);
        AddRecordButton=findViewById(R.id.Add_Record_Button_Post);
        UpdateRecordButton=findViewById(R.id.Update_Record_Button_Post);
    }
    public void VolleyPost(String action) throws JSONException {
            Log.e("Action",action);
            String ip_address=getString(R.string.Ip_address);
            String Url="http://"+ip_address+"/addupdate.php";
            JSONObject jsonObject=new JSONObject();
            jsonObject=populate(action);
            final String requestBody=jsonObject.toString();
            StringRequest postRequest = new StringRequest(Request.Method.POST, Url,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            // response
                            Log.e("Response", response);
                            Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(),error.getCause().toString(),Toast.LENGTH_LONG).show();
                        }
                    }
                    )
                    {
                        @Override
                        public String getBodyContentType() {
                            return String.format("application/json; charset=utf-8");
                        }
                        @Override
                        public byte[] getBody() throws AuthFailureError {
                            try {
                                return requestBody == null ? null : requestBody.getBytes("utf-8");
                            } catch (UnsupportedEncodingException uee) {
                                return null;
                            }
                        }
                    };
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(postRequest);

        }
    public JSONObject populate(String action) throws JSONException {

        JSONObject  obj = new JSONObject();
        obj.put("Action",action);
        obj.put("Employeeid",EmployeeIdEditText.getText().toString());
        obj.put("FirstName",FirstNameEditText.getText().toString());
        obj.put("LastName",LastNameEditText.getText().toString());
        obj.put("Gender",GenderEditText.getText().toString());
        obj.put("Age",AgeEditText.getText().toString());
        obj.put("Email",EmailEditText.getText().toString());
        obj.put("Contact",ContactEditText.getText().toString());
        return obj;
    }

}

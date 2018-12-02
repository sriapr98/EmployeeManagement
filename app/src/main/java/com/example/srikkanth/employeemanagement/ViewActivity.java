package com.example.srikkanth.employeemanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {
    private ListView employeesListView;
    private EmployeeAdapter employeeAdapter;
    private ArrayList<EmployeeRecord> employeeRecords;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        employeesListView=findViewById(R.id.employees_List_View);
        employeeAdapter=new EmployeeAdapter(this,R.layout.employee_item,employeeRecords);
        employeeRecords=new ArrayList<>();
        try {
            VolleyPost();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void VolleyPost() throws JSONException {
        String ip_address=getString(R.string.Ip_address);
        String Url="http://"+ip_address+"/viewall.php";
        JSONObject jsonObject=new JSONObject();
        final String requestBody=jsonObject.toString();
        StringRequest postRequest = new StringRequest(Request.Method.POST, Url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                            Gson g = new Gson();
                            Log.e("Response",response);
                            JsonArray jsonArray=new JsonParser().parse(response).getAsJsonArray();
                            int i;
                            Log.e("Size",String.valueOf(jsonArray.size()));
                            for(i=0;i<jsonArray.size();i++) {
                                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                                String id=jsonObject.get("id").getAsString();
                                String firstname=jsonObject.get("firstname").getAsString();
                                String lastname=jsonObject.get("lastname").getAsString();
                                String gender=jsonObject.get("gender").getAsString();
                                String age=jsonObject.get("age").getAsString();
                                String email=jsonObject.get("email").getAsString();
                                String contact=jsonObject.get("contact").getAsString();
                                EmployeeRecord employeeRecord = new EmployeeRecord(id,firstname,lastname,gender,age,email,contact);
                                employeeRecords.add(employeeRecord);
                            }
                            Log.e("ListSize",String.valueOf(employeeRecords.size()));
                        employeeAdapter=new EmployeeAdapter(ViewActivity.this,R.layout.employee_item,employeeRecords);
                        employeesListView.setAdapter(employeeAdapter);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
        ) {
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
}

package com.example.sanjeev.giet;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
public class Register extends AppCompatActivity
{
    Button reg_button;
    Button refresh_button;
    TextView login;
    EditText FN,LN,CID,EID,PD,CONPD,MN;
    Spinner BRA,SEM;
    String fn,ln,bra,sem,cid,eid,pd,conpd,mn,br,se;
    String reg_url="https://pasko.000webhostapp.com/mainproject_addinfo.php";
    ArrayAdapter<CharSequence> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        reg_button= (Button) findViewById(R.id.btn_submit);
        refresh_button=findViewById(R.id.btn_refresh);
        login=findViewById(R.id.tv_alt_login);
        FN=(EditText) findViewById(R.id.et_fn);
        LN=(EditText) findViewById(R.id.et_ln);
        BRA=(Spinner) findViewById(R.id.et_bra);
        SEM=(Spinner) findViewById(R.id.et_sem);
        CID=(EditText) findViewById(R.id.et_cid);
        EID=(EditText) findViewById(R.id.et_eid);
        PD=(EditText) findViewById(R.id.et_pass);
        CONPD=(EditText) findViewById(R.id.et_conpass);
        MN=(EditText) findViewById(R.id.et_mn);

        adapter=ArrayAdapter.createFromResource(this,R.array.Branch,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BRA.setAdapter(adapter);
        BRA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                br= parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });

        adapter=ArrayAdapter.createFromResource(this,R.array.Semester,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SEM.setAdapter(adapter);
        SEM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               se= parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fn=FN.getText().toString();
                ln=LN.getText().toString();
                bra=br;
                sem=se;
                cid=CID.getText().toString();
                eid=EID.getText().toString();
                pd=PD.getText().toString();
                conpd=CONPD.getText().toString();
                mn=MN.getText().toString();
                if(fn.isEmpty()||ln.isEmpty()||bra.isEmpty()||sem.isEmpty()||cid.isEmpty()||eid.isEmpty()||pd.isEmpty()||conpd.isEmpty()||mn.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"FILL ALL DATA !!!",Toast.LENGTH_LONG).show();
                }

                if(!pd.equals(conpd))
                {
                    Toast.makeText(getApplicationContext(),"PASSWORD MISMATCH MATCH",Toast.LENGTH_LONG).show();
                }
                else
                {
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, reg_url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONArray jsonArray =new JSONArray(response);
                                        JSONObject jsonObject=jsonArray.getJSONObject(0);
                                        String code=jsonObject.getString("code");
                                        String message=jsonObject.getString("message");
                                        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                                        finish();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(),"Error occured!!!:"+error,Toast.LENGTH_LONG).show();
                            finish();
                        }
                    })
                    {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params=new HashMap<String, String>();
                            params.put("first_name",fn);
                            params.put("last_name",ln);
                            params.put("branch",bra);
                            params.put("semester",sem);
                            params.put("collage_id",cid);
                            params.put("email_id",eid);
                            params.put("password",pd);
                            params.put("mobile_number",mn);
                            return params;
                        }
                    };
                    VolleySingleton.getmInstance(getApplicationContext()).addToRequestque(stringRequest);
                }
            }
        });

        refresh_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
                FN.setText("");
                LN.setText("");
                CID.setText("");
                EID.setText("");
                PD.setText("");
                CONPD.setText("");
                MN.setText("");
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
}


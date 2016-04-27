package com.ssteinkellner.webserviceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.basti.webserviceapp.R;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private TextView msg;
    private EditText email, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        msg = (TextView) findViewById(R.id.msgField);
        email = (EditText) findViewById(R.id.email);
        pwd = (EditText) findViewById(R.id.password);
    }

    public void registerUser(View view){
        msg.setText("please Wait!");

        JSONObject params = new JSONObject();
        try{
            params.put("email",email.getText().toString());
            params.put("password",pwd.getText().toString());
        }catch (JSONException jse){
            System.err.println("Problem when creating JSON\n" + jse);
            msg.setText("System Error!");
        }

        AsyncHttpClient client = new AsyncHttpClient();
        StringEntity request = null;
        try {
            request = new StringEntity(params.toString());
            request.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        client.get(this.getApplicationContext(), "http://localhost:8080/login", request, "application/json", new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseBody) {
                if(statusCode >= 200 && statusCode < 300) {
                    msg.setText(responseBody);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseBody, Throwable error) {
                if(responseBody != null){
                    msg.setText(responseBody);
                }else{
                    msg.setText("some Error happened!");
                }
            }
        });
    }

    public void goToRegister(View view){
        Intent loginIntent = new Intent(getApplicationContext(),RegisterActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(loginIntent);
    }
}

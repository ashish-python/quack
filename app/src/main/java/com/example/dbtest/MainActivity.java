package com.example.dbtest;

import android.app.Application;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    TextView resultTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultTextView = (TextView) findViewById(R.id.resultTextView);
                //resultTextView.setText("");
                GetData retrieveData = new GetData();
                retrieveData.execute("");


            }
        });

        Button activityBtn = (Button) findViewById(R.id.activityBtn);
        activityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),HomepageActivity.class);
                startActivity(startIntent);
            }
        });
    }

    private class GetData extends AsyncTask<String,Void,String> {

        @Override
        protected void onPreExecute(){
            resultTextView = (TextView) findViewById(R.id.resultTextView);
            resultTextView.setText("Connecting to database...");
        }

        @Override
        protected void onPostExecute(String s){

            if(s.equals("Login failed")){
                resultTextView.setText("Login failed. Try again");
            }
            else if(s.equals("Login successful")){
                Intent startIntent = new Intent(getApplicationContext(),HomepageActivity.class);
                startActivity(startIntent);
            }
            else resultTextView.setText(s);


        }

        @Override
        protected String doInBackground(String... strings) {
            String message = "";
            String connStr="http://10.0.2.2:5000/";
            String username="ash";
            String password="ash";
            String result = "";
            try {
                URL url = new URL(connStr);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);

                OutputStream ops = http.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));

                TextView usernameTextView = (TextView) findViewById(R.id.usernameEditText);
                username = usernameTextView.getText().toString();

                TextView passwordTextView = (TextView) findViewById(R.id.passwordEditText);
                password = passwordTextView.getText().toString();

                String data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")
                        +"&&"+ URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");

                writer.write(data);
                writer.flush();
                writer.close();
                ops.close();

                InputStream ips = http.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(ips,"ISO-8859-1"));
                String line="";
                while((line = reader.readLine())!=null){
                    result+=line;
                }

                reader.close();
                ips.close();
                http.disconnect();

            } catch (MalformedURLException e) {
                message = e.getMessage();
                resultTextView.setText(message);
            }
            catch (IOException e){
                message = e.getMessage();
                resultTextView.setText(message);
            }


            return result;
            //return "Test value - Not DB value";
        }
    }

} //End of MainActivity

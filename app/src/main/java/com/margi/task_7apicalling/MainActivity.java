package com.margi.task_7apicalling;

import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    int userid,id;
    private Customlist Adapter;
    private ListView listView;
    ArrayList<Post> postlist = new ArrayList<Post>();
    private Exception exception;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);


            loadjason();


        }
    }

    private void loadjason()
    {


        listView = (ListView)findViewById(R.id.main_listview);
        Adapter = new Customlist(this,postlist);
        listView.setAdapter(Adapter);

        HttpURLConnection connection = null;

        URL url = null;
        try
        {
            url = new URL("https://jsonplaceholder.typicode.com/posts");
        try
        {
            connection = (HttpURLConnection)url.openConnection();
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuffer buffer = new StringBuffer();
            String line="";
            while ((line = reader.readLine())!=null)
            {
                buffer.append(line);
            }
            String bufferstring = buffer.toString();



        try
        {
            JSONArray rootobject = new JSONArray(bufferstring);
            for (int i = 0; i < rootobject.length(); i++)
            {
                JSONObject obj1 = rootobject.getJSONObject(i);
                int  userid =obj1.getInt("userId");
                int  id = obj1.getInt("id");
                String title = obj1.getString("title");
                String body = obj1.getString("body");

                //creating the object

                Post p = new Post();
                p.setId(id);
                p.setUserId(userid);
                p.setBody(body);
                p.setTitle(title);
                postlist.add(p);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        } catch (Exception e) {
            this.exception = e;
        }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}


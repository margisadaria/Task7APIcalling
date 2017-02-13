package com.margi.task_7apicalling;

import android.app.ProgressDialog;
import android.os.AsyncTask;
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

public class MainActivity extends AppCompatActivity {

    int userid, id;
    private Customlist Adapter;
    private ListView listView;
    ArrayList<Post> postlist = new ArrayList<Post>();
    private Exception exception;


    public MainActivity() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new myclass().execute("https://jsonplaceholder.typicode.com/posts");


       /* if (Build.VERSION.SDK_INT > 9)
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

        HttpURLConnection connection;

        URL url;
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
                int  userid =obj1.getInt("userid is ="+"userId");
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
    }*/
    }

    class myclass extends AsyncTask<String, Void, String> {
        ProgressDialog dialog1;

        @Override
        protected void onPreExecute() {
            dialog1 = new ProgressDialog(MainActivity.this);
            dialog1.setMessage("Loading for output");
            dialog1.setCancelable(false);
            dialog1.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params)

        {
            HttpURLConnection connection;
            try {
                URL url = new URL(params[0]);
                try {
                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();

                    InputStream stream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer buffer = new StringBuffer();
                    String line = "";

                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }

                    String bufferString = buffer.toString();
                    return bufferString;


                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s)

        {
            super.onPostExecute(s);
            if (dialog1.isShowing()) {
                dialog1.dismiss();
            }
            try {
                JSONArray rootobject = new JSONArray(s);
                for (int i = 0; i < rootobject.length(); i++) {
                    JSONObject obj1 = rootobject.getJSONObject(i);
                    int userid = obj1.getInt("userid is =" + "userId");
                    int id = obj1.getInt("id");
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
            listView = (ListView) findViewById(R.id.main_listview);
            Adapter = new Customlist(MainActivity.this, postlist);
            listView.setAdapter(Adapter);
        }
    }


}




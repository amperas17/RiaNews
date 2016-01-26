package com.vavan.rianews;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public Elements title;
    public ArrayList<String> titleList = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    private ListView listView;

    Button btGet;
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btGet = (Button)findViewById(R.id.btGet);
        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Parse parse = new Parse();
                parse.execute();
            }
        });
        tvTitle = (TextView)findViewById(R.id.tvTitle);
        /*listView = (ListView) findViewById(R.id.listView);
        Parse parse = new Parse();
        parse.execute();

        adapter = new ArrayAdapter<String>(this,R.layout.list_item,R.id.product_name,titleList);
        */
    }

    class Parse extends AsyncTask<Void,Void,Void>{
        String title1;

        @Override
        protected Void doInBackground(Void... params) {
            Document document = null;

            try {
                document = Jsoup.connect("http://m.ria.ru/").get();
                Log.d("MyLog", "try");
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("MyLog", "catch");
            }
            if (document!=null) {
                title1 = document.title();
                //title = document.select("title");
                //titleList.clear();

                /*for (Element titles : title) {
                    titleList.add(titles.text());
                }*/

                //Log.d("MyLog", title.toString());
            } else {
                title1 = "error";
            }


            return null;
        }


        /*@Override
        protected void onPostExecute(ArrayList<String> strings) {

            //listView.setAdapter(adapter);

            super.onPostExecute(strings);
        }*/

        @Override
        protected void onPostExecute(Void aVoid) {
            tvTitle.setText(title1);
            super.onPostExecute(aVoid);
        }
    }

}

package com.vavan.rianews;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    Elements titles,texts,images;

    ArrayList<PeaceOfNews> news = new ArrayList<>();
    NewsAdapter newsAdapter;

    ListView listView;
    ProgressBar progressBar;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        //Parse parse = new Parse();
        //parse.execute();

        newsAdapter = new NewsAdapter(this,news);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        // делаем повеселее
        //mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE);

    }

    @Override
    public void onRefresh() {
        Parse parse = new Parse();
        parse.execute();
    }

    class Parse extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params) {
            Document document;
            try{
                document = Jsoup.connect("http://ria.ru/").get();
                titles = document.select("div.day_news_content>*:not(.day_news_content_hidden)")
                        .select(".day_news_item")
                        .select(".day_news_item_title");


                texts = document.select("div.day_news_content>*:not(.day_news_content_hidden)")
                        .select(".day_news_item")
                        .select(".day_news_item_announce");

                images = document.select("div.day_news_content>*:not(.day_news_content_hidden)")
                        .select(".day_news_item")
                        .select(".day_news_item_img img");

                news.clear();

                for (int i=0;i<titles.size();i++){

                    Bitmap bitmap = null;
                    try {
                        InputStream in = new java.net.URL(images.get(i).absUrl("src")).openStream();
                        bitmap = BitmapFactory.decodeStream(in);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    news.add(new PeaceOfNews(bitmap,
                                            images.get(i).absUrl("src"),
                                            titles.get(i).text(),
                                            texts.get(i).text()));
                }


            } catch (IOException e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            listView.setAdapter(newsAdapter);
            progressBar.setVisibility(View.INVISIBLE);
            mSwipeRefreshLayout.setRefreshing(false);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            //progressBar.setVisibility(View.VISIBLE);
            mSwipeRefreshLayout.setRefreshing(true);

        }


    }


}

package com.vavan.rianews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Вова on 27.01.2016.
 */
public class NewsAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<PeaceOfNews> news;

    NewsAdapter(Context _context,ArrayList<PeaceOfNews> _news){
        context = _context;
        news = _news;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return news.size();
    }

    @Override
    public PeaceOfNews getItem(int position) {
        return news.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsViewHolder newsViewHolder;

        if (convertView == null){
            convertView = inflater.inflate(R.layout.list_item,parent,false);
            newsViewHolder = new NewsViewHolder(convertView);
            convertView.setTag(newsViewHolder);
        } else {
            newsViewHolder = (NewsViewHolder) convertView.getTag();
        }

        PeaceOfNews peaceOfNews = getItem(position);


        newsViewHolder.ivImage.setImageBitmap(peaceOfNews.imageBitmap);
        newsViewHolder.tvTitle.setText(peaceOfNews.title);
        newsViewHolder.tvText.setText(peaceOfNews.text);

        return convertView;
    }

    private class NewsViewHolder{
        TextView tvTitle, tvText;
        ImageView ivImage;

        public  NewsViewHolder(View view){
            tvTitle = (TextView)view.findViewById(R.id.tv_item_title);
            tvText = (TextView)view.findViewById(R.id.tv_item_text);
            ivImage = (ImageView)view.findViewById(R.id.iv_item);
        }

    }
}

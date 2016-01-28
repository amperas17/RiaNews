package com.vavan.rianews;

import android.content.Context;
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

        TextView tvTitle, tvText, tvImageSrc;
        View view = convertView;
        if (view == null){
            view = inflater.inflate(R.layout.list_item,parent,false);
        }

        PeaceOfNews peaceOfNews = getItem(position);

        ((ImageView)view.findViewById(R.id.iv_item)).setImageBitmap(peaceOfNews.imageBitmap);

        //tvImageSrc=(TextView)view.findViewById(R.id.tv_image_src);
        //tvImageSrc.setText(peaceOfNews.imageSrc);

        tvTitle = (TextView)view.findViewById(R.id.tv_item_title);
        tvTitle.setText(peaceOfNews.title);
        tvText = (TextView)view.findViewById(R.id.tv_item_text);
        tvText.setText(peaceOfNews.text);


        return view;
    }
}

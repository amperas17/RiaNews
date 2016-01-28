package com.vavan.rianews;

import android.graphics.Bitmap;

/**
 * Created by Вова on 27.01.2016.
 */
public class PeaceOfNews {
    Bitmap imageBitmap;
    String imageSrc;
    String title;
    String text;


    PeaceOfNews(Bitmap _imageBitmap,String _imageSrc,String _title,String _text){
        imageBitmap = _imageBitmap;
        imageSrc = _imageSrc;
        title = _title;
        text = _text;

    }

}

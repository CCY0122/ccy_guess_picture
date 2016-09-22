package com.example.guessmusic.com.example.guessmusic.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.TypedValue;

/**
 * Created by Administrator on 2016/9/20.
 */
public class ImageDecode {
    public static Bitmap decodeBitmap(Resources res,int imageId,int requestW , int requestH){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res,imageId , options );
        int width = options.outWidth;
        int height = options.outHeight;
        Log.d("ccy","option"+String.valueOf(width)+"---"+String.valueOf(height)); //单位为dip
        options.inSampleSize = 1;
        if(width > requestW || height > requestH){
            Math.ceil((double)((float)width/(float)requestW));
            int xScale =Math.round((float)width/(float)requestW);
            int yScale =Math.round((float)width/(float)requestW); //用float除更准确，例10/7=1(用int除的结果，等于没缩放
            options.inSampleSize =xScale>yScale ? xScale:yScale ;
            Log.d("ccy",String.valueOf(options.inSampleSize));
        }
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res,imageId , options);
    }
}

package com.example.notepad20;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

public class PictureUtils {
    public static Bitmap getScaledBitmap(String path, int destWidth, int destHeight){
        //从存储中读取文件
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeFile(path,options);

        float srcWidth=options.outWidth;
        float srcHeight=options.outHeight;

        //算出缩小多少
        int inSampleSize=1;
        if (srcHeight>destHeight||srcWidth>destWidth){
            float heightScale=srcHeight/destHeight;
            float widthScale=srcWidth/destWidth;
            inSampleSize= Math.round(heightScale>widthScale?heightScale:widthScale);
        }
        options=new BitmapFactory.Options();
        options.inSampleSize=inSampleSize;

        //创建BMP
        return BitmapFactory.decodeFile(path,options);
    }
    public static Bitmap getScaledBitmap(String path, Activity activity){
        Point size=new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);

        return getScaledBitmap(path,size.x,size.y);
    }
}

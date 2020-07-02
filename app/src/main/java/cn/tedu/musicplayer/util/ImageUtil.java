package cn.tedu.musicplayer.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import cn.tedu.musicplayer.R;
import cn.tedu.musicplayer.model.BitmapCallback;

public class ImageUtil {
    /**
     * 设置播放页面的图片
     * @param context
     * @param url
     * @param ivPMAlbum
     * @param ivPMBackground
     */
    public static void setPlayImage(Context context, String url, final ImageView ivPMAlbum, final ImageView ivPMBackground) {
        //更新播放界面中的ivPMAlbum
        BitmapUtils.loadBitmap(context,url, new BitmapCallback() {
            public void onBitmapLoaded(Bitmap bitmap) {
                if(bitmap!=null){ //下载
                    ivPMAlbum.setImageBitmap(bitmap);
                }else{
                    ivPMAlbum.setImageResource(R.mipmap.default_music_pic);
                }
            }
        });
        //更新背景图片 ivPMBackground
        BitmapUtils.loadBitmap(context,url, 8, new BitmapCallback() {
            public void onBitmapLoaded(Bitmap bitmap) {
                if(bitmap!=null){ //背景图片下载成功
                    //把背景图片模糊化处理
                    BitmapUtils.loadBlurBitmap(bitmap, 10, new BitmapCallback() {
                        public void onBitmapLoaded(Bitmap bitmap) {
                            ivPMBackground.setImageBitmap(bitmap);
                        }
                    });
                }else{
                    ivPMBackground.setImageResource(R.mipmap.default_music_background);
                }
            }
        });
    }
}

package cn.tedu.musicplayer.model;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import cn.tedu.musicplayer.entity.Song_list;
import cn.tedu.musicplayer.util.HttpUtils;
import cn.tedu.musicplayer.util.JSONParser;
import cn.tedu.musicplayer.util.UrlFactory;
import cn.tedu.musicplayer.entity.Song;
import cn.tedu.musicplayer.entity.Songinfo;
import cn.tedu.musicplayer.model.SongInfoCallBack;
import java.util.List;

public class MusicModel {
    /**
     * 异步加载音乐信息
     */
    public void loadMusicList(final int offset, final int size, final MusicListCallback callback){
        @SuppressLint("StaticFieldLeak") AsyncTask<Void,Void,List<Song_list>> task=new AsyncTask<Void, Void, List<Song_list>>() {
            //doInBackground运行在子线程,该方法的返回值为AsyncTask泛型中的第三个参数,表示后台执行后返回的结果
            @Override
            protected List<Song_list> doInBackground(Void... voids) {
                try {
                    //加载音乐数据 String json{}
                    //发送Http请求
                    String url= UrlFactory.getNewMusicListUrl(offset,size);
                    String result = HttpUtils.getStringForOkHttp(url);
                    //json解析
                    List<Song_list> song_lists = JSONParser.parseMusicList(result);
                    Log.e("ss",song_lists.toString());
                    return song_lists;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(List<Song_list> s) {
                //音乐加载完成，回调json数据
                callback.onMusicListLoaded(s);
            }
        };
        //执行task
        task.execute();
    }
    public void loadHotMusicList(final int offset, final int size, final MusicListCallback callback){
        @SuppressLint("StaticFieldLeak") AsyncTask<Void,Void,List<Song_list>> task=new AsyncTask<Void, Void, List<Song_list>>() {
            //doInBackground运行在子线程,该方法的返回值为AsyncTask泛型中的第三个参数,表示后台执行后返回的结果
            @Override
            protected List<Song_list> doInBackground(Void... voids) {
                try {
                    //加载音乐数据 String json{}
                    //发送Http请求
                    String url= UrlFactory.getHotMusicListUrl(offset,size);
                    String result = HttpUtils.getStringForOkHttp(url);
                    //json解析
                    List<Song_list> song_lists = JSONParser.parseMusicList(result);
                    Log.e("ss",song_lists.toString());
                    return song_lists;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(List<Song_list> s) {
                //音乐加载完成，回调json数据
                callback.onMusicListLoaded(s);
            }
        };
        //执行task
        task.execute();
    }
    public  void loadSongInfo(final String songId, final SongInfoCallBack callback){
        @SuppressLint("StaticFieldLeak") AsyncTask<Void,Void,Song>task=new AsyncTask<Void, Void, Song>() {
            @Override
            protected Song doInBackground(Void... voids) {
                //发送Http请求获取当前音乐信息
                try {
                    String path=UrlFactory.getSongInfo(songId);
                    String result = HttpUtils.getStringForOkHttp(path);
                    Song song = JSONParser.parseMusicInfo(result);
                    return song;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Song Song) {
                callback.onSongInfoLoaded(Song);
            }
        };
        task.execute();
    }
}

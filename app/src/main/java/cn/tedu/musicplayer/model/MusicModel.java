package cn.tedu.musicplayer.model;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import cn.tedu.musicplayer.app.MyApp;
import cn.tedu.musicplayer.entity.Song_list;
import cn.tedu.musicplayer.util.HttpUtils;
import cn.tedu.musicplayer.util.JSONParser;
import cn.tedu.musicplayer.util.UrlFactory;
import cn.tedu.musicplayer.entity.Song;
import cn.tedu.musicplayer.entity.Songinfo;
import cn.tedu.musicplayer.model.SongInfoCallBack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
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
    /**
     * 加载歌词数据  解析歌词文本  封装为
     * HashMap<String, String> lrc;
     * @param lrcPath
     * @param callback
     */
    @SuppressLint("StaticFieldLeak")
    public void loadLrc(final String lrcPath, final LrcCallback callback) {
        AsyncTask<String, String, HashMap<String, String> > task;
        task = new AsyncTask<String, String, HashMap<String,String>>(){
            protected HashMap<String, String> doInBackground(String... params) {
                try {
                    if(lrcPath == null || lrcPath.equals("")){ //歌词目录不存在
                        return null;
                    }
                    //声明歌词缓存文件对象
                    MyApp app=new MyApp();
                    String filename = lrcPath.substring(lrcPath.lastIndexOf("/"));
                    File file = new File(app.getApp().getCacheDir(), "lrc"+filename);
                    PrintWriter out = null;
                    if(!file.getParentFile().exists()){ //父目录不存在
                        file.getParentFile().mkdirs();
                    }
                    InputStream is = null;
                    boolean isFromFile=false;
                    if(file.exists()){ //缓存目录中已经下载过歌词了
                        is = new FileInputStream(file);
                        isFromFile = true;
                    }else{// 缓存目录中没有则下载歌词
                        is = HttpUtils.getInputStream(lrcPath);
                        out = new PrintWriter(file);
                        isFromFile = false;
                    }
                    //解析输入流
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    String line = null;
                    HashMap<String, String> lrc = new HashMap<String, String>();
                    while((line=reader.readLine())!=null){
                        if(!isFromFile){
                            //把line 保存到歌词缓存文件中
                            out.println(line);
                            out.flush();
                        }
                        //判断格式是否是:
                        //  [title]歌名 ...
                        if(!line.startsWith("[") || !line.contains(":") || !line.contains(".")){
                            continue;
                        }
                        //  [00:00.00]歌词内容
                        String time = line.substring(1, line.indexOf("."));
                        String content = line.substring(line.lastIndexOf("]")+1);
                        lrc.put(time, content);
                    }
                    if(out!=null){ //关闭输出流
                        out.close();
                    }
                    return lrc;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
            protected void onPostExecute(HashMap<String, String> result) {
                //调用回调方法
                callback.onLrcLoaded(result);
            }
        };
        task.execute();
    }

}

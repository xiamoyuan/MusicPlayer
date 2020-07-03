package cn.tedu.musicplayer.util;

public class UrlFactory {
    public static String getNewMusicListUrl(int offset,int size){
        String url="http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=1&size="+size+"&offset="+offset;
        return url;
    }
    /**
     * 通过songid 获取当前音乐的详细信息
     * @param
     * @return
     */
    public static String getHotMusicListUrl(int offset,int size)
    {
        String url="http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=2&size="+size+"&offset="+offset;
        return url;
    }
    public static String getSongInfo(String songId){
        String url="http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.song.play&songid="+songId;
        return url;
    }
    public static String getSongByName(String songName){
        String url="http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.search.catalogSug&query="+songName;
        return url;
    }
}

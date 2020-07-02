/**
  * Copyright 2020 bejson.com 
  */
package cn.tedu.musicplayer.entity;

/**
 * Auto-generated: 2020-06-30 9:1:20
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonSongRootBean {

    private Songinfo songinfo;
    private int error_code;
    private Bitrate bitrate;
    public void setSonginfo(Songinfo songinfo) {
         this.songinfo = songinfo;
     }
     public Songinfo getSonginfo() {
         return songinfo;
     }

    public void setError_code(int error_code) {
         this.error_code = error_code;
     }
     public int getError_code() {
         return error_code;
     }

    public void setBitrate(Bitrate bitrate) {
         this.bitrate = bitrate;
     }
     public Bitrate getBitrate() {
         return bitrate;
     }

}
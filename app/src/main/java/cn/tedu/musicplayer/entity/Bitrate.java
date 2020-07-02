/**
  * Copyright 2020 bejson.com 
  */
package cn.tedu.musicplayer.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Auto-generated: 2020-06-30 9:27:57
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Bitrate implements Parcelable {

    private String show_link;
    private int free;
    private int song_file_id;
    private long file_size;
    private String file_extension;
    private int file_duration;
    private int file_bitrate;
    private String file_link;
    private String hash;

    protected Bitrate(Parcel in) {
        show_link = in.readString();
        free = in.readInt();
        song_file_id = in.readInt();
        file_size = in.readLong();
        file_extension = in.readString();
        file_duration = in.readInt();
        file_bitrate = in.readInt();
        file_link = in.readString();
        hash = in.readString();
    }

    public static final Creator<Bitrate> CREATOR = new Creator<Bitrate>() {
        @Override
        public Bitrate createFromParcel(Parcel in) {
            return new Bitrate(in);
        }

        @Override
        public Bitrate[] newArray(int size) {
            return new Bitrate[size];
        }
    };

    public void setShow_link(String show_link) {
         this.show_link = show_link;
     }
     public String getShow_link() {
         return show_link;
     }

    public void setFree(int free) {
         this.free = free;
     }
     public int getFree() {
         return free;
     }

    public void setSong_file_id(int song_file_id) {
         this.song_file_id = song_file_id;
     }
     public int getSong_file_id() {
         return song_file_id;
     }

    public void setFile_size(long file_size) {
         this.file_size = file_size;
     }
     public long getFile_size() {
         return file_size;
     }

    public void setFile_extension(String file_extension) {
         this.file_extension = file_extension;
     }
     public String getFile_extension() {
         return file_extension;
     }

    public void setFile_duration(int file_duration) {
         this.file_duration = file_duration;
     }
     public int getFile_duration() {
         return file_duration;
     }

    public void setFile_bitrate(int file_bitrate) {
         this.file_bitrate = file_bitrate;
     }
     public int getFile_bitrate() {
         return file_bitrate;
     }

    public void setFile_link(String file_link) {
         this.file_link = file_link;
     }
     public String getFile_link() {
         return file_link;
     }

    public void setHash(String hash) {
         this.hash = hash;
     }
     public String getHash() {
         return hash;
     }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(show_link);
        dest.writeInt(free);
        dest.writeInt(song_file_id);
        dest.writeLong(file_size);
        dest.writeString(file_extension);
        dest.writeInt(file_duration);
        dest.writeInt(file_bitrate);
        dest.writeString(file_link);
        dest.writeString(hash);
    }
}
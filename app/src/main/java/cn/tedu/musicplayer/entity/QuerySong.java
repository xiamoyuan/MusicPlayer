/**
 * Copyright 2020 bejson.com
 */
package cn.tedu.musicplayer.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Auto-generated: 2020-07-01 16:56:31
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class QuerySong implements Parcelable {

    private String bitrate_fee;
    private String weight;
    private String songname;
    private String resource_type;
    private String songid;
    private String has_mv;
    private String yyr_artist;
    private String resource_type_ext;
    private String artistname;
    private String info;
    private String resource_provider;
    private String control;
    private String encrypted_songid;

    protected QuerySong(Parcel in) {
        bitrate_fee = in.readString();
        weight = in.readString();
        songname = in.readString();
        resource_type = in.readString();
        songid = in.readString();
        has_mv = in.readString();
        yyr_artist = in.readString();
        resource_type_ext = in.readString();
        artistname = in.readString();
        info = in.readString();
        resource_provider = in.readString();
        control = in.readString();
        encrypted_songid = in.readString();
    }

    public static final Creator<QuerySong> CREATOR = new Creator<QuerySong>() {
        @Override
        public QuerySong createFromParcel(Parcel in) {
            return new QuerySong(in);
        }

        @Override
        public QuerySong[] newArray(int size) {
            return new QuerySong[size];
        }
    };

    public void setBitrate_fee(String bitrate_fee) {
        this.bitrate_fee = bitrate_fee;
    }
    public String getBitrate_fee() {
        return bitrate_fee;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
    public String getWeight() {
        return weight;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }
    public String getSongname() {
        return songname;
    }

    public void setResource_type(String resource_type) {
        this.resource_type = resource_type;
    }
    public String getResource_type() {
        return resource_type;
    }

    public void setSongid(String songid) {
        this.songid = songid;
    }
    public String getSongid() {
        return songid;
    }

    public void setHas_mv(String has_mv) {
        this.has_mv = has_mv;
    }
    public String getHas_mv() {
        return has_mv;
    }

    public void setYyr_artist(String yyr_artist) {
        this.yyr_artist = yyr_artist;
    }
    public String getYyr_artist() {
        return yyr_artist;
    }

    public void setResource_type_ext(String resource_type_ext) {
        this.resource_type_ext = resource_type_ext;
    }
    public String getResource_type_ext() {
        return resource_type_ext;
    }

    public void setArtistname(String artistname) {
        this.artistname = artistname;
    }
    public String getArtistname() {
        return artistname;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    public String getInfo() {
        return info;
    }

    public void setResource_provider(String resource_provider) {
        this.resource_provider = resource_provider;
    }
    public String getResource_provider() {
        return resource_provider;
    }

    public void setControl(String control) {
        this.control = control;
    }
    public String getControl() {
        return control;
    }

    public void setEncrypted_songid(String encrypted_songid) {
        this.encrypted_songid = encrypted_songid;
    }
    public String getEncrypted_songid() {
        return encrypted_songid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bitrate_fee);
        dest.writeString(weight);
        dest.writeString(songname);
        dest.writeString(resource_type);
        dest.writeString(songid);
        dest.writeString(has_mv);
        dest.writeString(yyr_artist);
        dest.writeString(resource_type_ext);
        dest.writeString(artistname);
        dest.writeString(info);
        dest.writeString(resource_provider);
        dest.writeString(control);
        dest.writeString(encrypted_songid);
    }
}

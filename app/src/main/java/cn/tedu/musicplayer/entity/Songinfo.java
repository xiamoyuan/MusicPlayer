/**
  * Copyright 2020 bejson.com 
  */
package cn.tedu.musicplayer.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;

/**
 * Auto-generated: 2020-06-30 9:27:57
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Songinfo implements Parcelable {

    private int special_type;
    private String pic_huge;
    private String ting_uid;
    private String pic_premium;
    private int havehigh;
    private String si_proxycompany;
    private String author;
    private String toneid;
    private int has_mv;
    private String song_id;
    private String title;
    private String artist_id;
    private String lrclink;
    private String relate_status;
    private int learn;
    private String pic_big;
    private int play_type;
    private String album_id;
    private String pic_radio;
    private String bitrate_fee;
    private String song_source;
    private String all_artist_id;
    private String all_artist_ting_uid;
    private String piao_id;
    private int charge;
    private String copy_type;
    private String all_rate;
    private String korean_bb_song;
    private int is_first_publish;
    private int has_mv_mobile;
    private String album_title;
    private String pic_small;
    private String album_no;
    private String resource_type_ext;
    private String resource_type;

    protected Songinfo(Parcel in) {
        special_type = in.readInt();
        pic_huge = in.readString();
        ting_uid = in.readString();
        pic_premium = in.readString();
        havehigh = in.readInt();
        si_proxycompany = in.readString();
        author = in.readString();
        toneid = in.readString();
        has_mv = in.readInt();
        song_id = in.readString();
        title = in.readString();
        artist_id = in.readString();
        lrclink = in.readString();
        relate_status = in.readString();
        learn = in.readInt();
        pic_big = in.readString();
        play_type = in.readInt();
        album_id = in.readString();
        pic_radio = in.readString();
        bitrate_fee = in.readString();
        song_source = in.readString();
        all_artist_id = in.readString();
        all_artist_ting_uid = in.readString();
        piao_id = in.readString();
        charge = in.readInt();
        copy_type = in.readString();
        all_rate = in.readString();
        korean_bb_song = in.readString();
        is_first_publish = in.readInt();
        has_mv_mobile = in.readInt();
        album_title = in.readString();
        pic_small = in.readString();
        album_no = in.readString();
        resource_type_ext = in.readString();
        resource_type = in.readString();
    }

    public static final Creator<Songinfo> CREATOR = new Creator<Songinfo>() {
        @Override
        public Songinfo createFromParcel(Parcel in) {
            return new Songinfo(in);
        }

        @Override
        public Songinfo[] newArray(int size) {
            return new Songinfo[size];
        }
    };

    public void setSpecial_type(int special_type) {
         this.special_type = special_type;
     }
     public int getSpecial_type() {
         return special_type;
     }

    public void setPic_huge(String pic_huge) {
         this.pic_huge = pic_huge;
     }
     public String getPic_huge() {
         return pic_huge;
     }

    public void setTing_uid(String ting_uid) {
         this.ting_uid = ting_uid;
     }
     public String getTing_uid() {
         return ting_uid;
     }

    public void setPic_premium(String pic_premium) {
         this.pic_premium = pic_premium;
     }
     public String getPic_premium() {
         return pic_premium;
     }

    public void setHavehigh(int havehigh) {
         this.havehigh = havehigh;
     }
     public int getHavehigh() {
         return havehigh;
     }

    public void setSi_proxycompany(String si_proxycompany) {
         this.si_proxycompany = si_proxycompany;
     }
     public String getSi_proxycompany() {
         return si_proxycompany;
     }

    public void setAuthor(String author) {
         this.author = author;
     }
     public String getAuthor() {
         return author;
     }

    public void setToneid(String toneid) {
         this.toneid = toneid;
     }
     public String getToneid() {
         return toneid;
     }

    public void setHas_mv(int has_mv) {
         this.has_mv = has_mv;
     }
     public int getHas_mv() {
         return has_mv;
     }

    public void setSong_id(String song_id) {
         this.song_id = song_id;
     }
     public String getSong_id() {
         return song_id;
     }

    public void setTitle(String title) {
         this.title = title;
     }
     public String getTitle() {
         return title;
     }

    public void setArtist_id(String artist_id) {
         this.artist_id = artist_id;
     }
     public String getArtist_id() {
         return artist_id;
     }

    public void setLrclink(String lrclink) {
         this.lrclink = lrclink;
     }
     public String getLrclink() {
         return lrclink;
     }

    public void setRelate_status(String relate_status) {
         this.relate_status = relate_status;
     }
     public String getRelate_status() {
         return relate_status;
     }

    public void setLearn(int learn) {
         this.learn = learn;
     }
     public int getLearn() {
         return learn;
     }

    public void setPic_big(String pic_big) {
         this.pic_big = pic_big;
     }
     public String getPic_big() {
         return pic_big;
     }

    public void setPlay_type(int play_type) {
         this.play_type = play_type;
     }
     public int getPlay_type() {
         return play_type;
     }

    public void setAlbum_id(String album_id) {
         this.album_id = album_id;
     }
     public String getAlbum_id() {
         return album_id;
     }

    public void setPic_radio(String pic_radio) {
         this.pic_radio = pic_radio;
     }
     public String getPic_radio() {
         return pic_radio;
     }

    public void setBitrate_fee(String bitrate_fee) {
         this.bitrate_fee = bitrate_fee;
     }
     public String getBitrate_fee() {
         return bitrate_fee;
     }

    public void setSong_source(String song_source) {
         this.song_source = song_source;
     }
     public String getSong_source() {
         return song_source;
     }

    public void setAll_artist_id(String all_artist_id) {
         this.all_artist_id = all_artist_id;
     }
     public String getAll_artist_id() {
         return all_artist_id;
     }

    public void setAll_artist_ting_uid(String all_artist_ting_uid) {
         this.all_artist_ting_uid = all_artist_ting_uid;
     }
     public String getAll_artist_ting_uid() {
         return all_artist_ting_uid;
     }

    public void setPiao_id(String piao_id) {
         this.piao_id = piao_id;
     }
     public String getPiao_id() {
         return piao_id;
     }

    public void setCharge(int charge) {
         this.charge = charge;
     }
     public int getCharge() {
         return charge;
     }

    public void setCopy_type(String copy_type) {
         this.copy_type = copy_type;
     }
     public String getCopy_type() {
         return copy_type;
     }

    public void setAll_rate(String all_rate) {
         this.all_rate = all_rate;
     }
     public String getAll_rate() {
         return all_rate;
     }

    public void setKorean_bb_song(String korean_bb_song) {
         this.korean_bb_song = korean_bb_song;
     }
     public String getKorean_bb_song() {
         return korean_bb_song;
     }

    public void setIs_first_publish(int is_first_publish) {
         this.is_first_publish = is_first_publish;
     }
     public int getIs_first_publish() {
         return is_first_publish;
     }

    public void setHas_mv_mobile(int has_mv_mobile) {
         this.has_mv_mobile = has_mv_mobile;
     }
     public int getHas_mv_mobile() {
         return has_mv_mobile;
     }

    public void setAlbum_title(String album_title) {
         this.album_title = album_title;
     }
     public String getAlbum_title() {
         return album_title;
     }

    public void setPic_small(String pic_small) {
         this.pic_small = pic_small;
     }
     public String getPic_small() {
         return pic_small;
     }

    public void setAlbum_no(String album_no) {
         this.album_no = album_no;
     }
     public String getAlbum_no() {
         return album_no;
     }

    public void setResource_type_ext(String resource_type_ext) {
         this.resource_type_ext = resource_type_ext;
     }
     public String getResource_type_ext() {
         return resource_type_ext;
     }

    public void setResource_type(String resource_type) {
         this.resource_type = resource_type;
     }
     public String getResource_type() {
         return resource_type;
     }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(special_type);
        dest.writeString(pic_huge);
        dest.writeString(ting_uid);
        dest.writeString(pic_premium);
        dest.writeInt(havehigh);
        dest.writeString(si_proxycompany);
        dest.writeString(author);
        dest.writeString(toneid);
        dest.writeInt(has_mv);
        dest.writeString(song_id);
        dest.writeString(title);
        dest.writeString(artist_id);
        dest.writeString(lrclink);
        dest.writeString(relate_status);
        dest.writeInt(learn);
        dest.writeString(pic_big);
        dest.writeInt(play_type);
        dest.writeString(album_id);
        dest.writeString(pic_radio);
        dest.writeString(bitrate_fee);
        dest.writeString(song_source);
        dest.writeString(all_artist_id);
        dest.writeString(all_artist_ting_uid);
        dest.writeString(piao_id);
        dest.writeInt(charge);
        dest.writeString(copy_type);
        dest.writeString(all_rate);
        dest.writeString(korean_bb_song);
        dest.writeInt(is_first_publish);
        dest.writeInt(has_mv_mobile);
        dest.writeString(album_title);
        dest.writeString(pic_small);
        dest.writeString(album_no);
        dest.writeString(resource_type_ext);
        dest.writeString(resource_type);
    }
}
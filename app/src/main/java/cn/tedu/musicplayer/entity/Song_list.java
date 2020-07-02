/**
  * Copyright 2020 bejson.com 
  */
package cn.tedu.musicplayer.entity;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Auto-generated: 2020-06-29 9:5:13
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Song_list implements Parcelable {

    private String artist_id;
    private String language;
    private String pic_big;
    private String pic_small;
    private String country;
    private String area;
    private String publishtime;
    private String album_no;
    private String lrclink;
    private String copy_type;
    private String hot;
    private String all_artist_ting_uid;
    private String resource_type;
    private String is_new;
    private String rank_change;
    private String rank;
    private String all_artist_id;
    private String style;
    private String del_status;
    private String relate_status;
    private String toneid;
    private String all_rate;
    private int file_duration;
    private int has_mv_mobile;
    private String versions;
    private String bitrate_fee;
    private String biaoshi;
    private String info;
    private String has_filmtv;
    private String si_proxycompany;
    private String res_encryption_flag;
    private String song_id;
    private String title;
    private String ting_uid;
    private String author;
    private String album_id;
    private String album_title;
    private int is_first_publish;
    private int havehigh;
    private int charge;
    private int has_mv;
    private int learn;
    private String song_source;
    private String piao_id;
    private String korean_bb_song;
    private String resource_type_ext;
    private String mv_provider;
    private String artist_name;
    private String pic_radio;
    private String pic_s500;
    private String pic_premium;
    private String pic_huge;
    private String album_500_500;
    private String album_800_800;
    private String album_1000_1000;

    protected Song_list(Parcel in) {
        artist_id = in.readString();
        language = in.readString();
        pic_big = in.readString();
        pic_small = in.readString();
        country = in.readString();
        area = in.readString();
        album_no = in.readString();
        lrclink = in.readString();
        copy_type = in.readString();
        hot = in.readString();
        all_artist_ting_uid = in.readString();
        resource_type = in.readString();
        is_new = in.readString();
        rank_change = in.readString();
        rank = in.readString();
        all_artist_id = in.readString();
        style = in.readString();
        del_status = in.readString();
        relate_status = in.readString();
        toneid = in.readString();
        all_rate = in.readString();
        file_duration = in.readInt();
        has_mv_mobile = in.readInt();
        versions = in.readString();
        bitrate_fee = in.readString();
        info = in.readString();
        has_filmtv = in.readString();
        si_proxycompany = in.readString();
        res_encryption_flag = in.readString();
        song_id = in.readString();
        title = in.readString();
        ting_uid = in.readString();
        author = in.readString();
        album_id = in.readString();
        album_title = in.readString();
        is_first_publish = in.readInt();
        havehigh = in.readInt();
        charge = in.readInt();
        has_mv = in.readInt();
        learn = in.readInt();
        song_source = in.readString();
        piao_id = in.readString();
        korean_bb_song = in.readString();
        resource_type_ext = in.readString();
        mv_provider = in.readString();
        artist_name = in.readString();
        pic_radio = in.readString();
        pic_s500 = in.readString();
        pic_premium = in.readString();
        pic_huge = in.readString();
        album_500_500 = in.readString();
        album_800_800 = in.readString();
        album_1000_1000 = in.readString();
    }

    public static final Creator<Song_list> CREATOR = new Creator<Song_list>() {
        @Override
        public Song_list createFromParcel(Parcel in) {
            return new Song_list(in);
        }

        @Override
        public Song_list[] newArray(int size) {
            return new Song_list[size];
        }
    };

    public void setArtist_id(String artist_id) {
         this.artist_id = artist_id;
     }
     public String getArtist_id() {
         return artist_id;
     }

    public void setLanguage(String language) {
         this.language = language;
     }
     public String getLanguage() {
         return language;
     }

    public void setPic_big(String pic_big) {
         this.pic_big = pic_big;
     }
     public String getPic_big() {
         return pic_big;
     }

    public void setPic_small(String pic_small) {
         this.pic_small = pic_small;
     }
     public String getPic_small() {
         return pic_small;
     }

    public void setCountry(String country) {
         this.country = country;
     }
     public String getCountry() {
         return country;
     }

    public void setArea(String area) {
         this.area = area;
     }
     public String getArea() {
         return area;
     }

    public void setPublishtime(String publishtime) {
         this.publishtime = publishtime;
     }
     public String getPublishtime() {
         return publishtime;
     }

    public void setAlbum_no(String album_no) {
         this.album_no = album_no;
     }
     public String getAlbum_no() {
         return album_no;
     }

    public void setLrclink(String lrclink) {
         this.lrclink = lrclink;
     }
     public String getLrclink() {
         return lrclink;
     }

    public void setCopy_type(String copy_type) {
         this.copy_type = copy_type;
     }
     public String getCopy_type() {
         return copy_type;
     }

    public void setHot(String hot) {
         this.hot = hot;
     }
     public String getHot() {
         return hot;
     }

    public void setAll_artist_ting_uid(String all_artist_ting_uid) {
         this.all_artist_ting_uid = all_artist_ting_uid;
     }
     public String getAll_artist_ting_uid() {
         return all_artist_ting_uid;
     }

    public void setResource_type(String resource_type) {
         this.resource_type = resource_type;
     }
     public String getResource_type() {
         return resource_type;
     }

    public void setIs_new(String is_new) {
         this.is_new = is_new;
     }
     public String getIs_new() {
         return is_new;
     }

    public void setRank_change(String rank_change) {
         this.rank_change = rank_change;
     }
     public String getRank_change() {
         return rank_change;
     }

    public void setRank(String rank) {
         this.rank = rank;
     }
     public String getRank() {
         return rank;
     }

    public void setAll_artist_id(String all_artist_id) {
         this.all_artist_id = all_artist_id;
     }
     public String getAll_artist_id() {
         return all_artist_id;
     }

    public void setStyle(String style) {
         this.style = style;
     }
     public String getStyle() {
         return style;
     }

    public void setDel_status(String del_status) {
         this.del_status = del_status;
     }
     public String getDel_status() {
         return del_status;
     }

    public void setRelate_status(String relate_status) {
         this.relate_status = relate_status;
     }
     public String getRelate_status() {
         return relate_status;
     }

    public void setToneid(String toneid) {
         this.toneid = toneid;
     }
     public String getToneid() {
         return toneid;
     }

    public void setAll_rate(String all_rate) {
         this.all_rate = all_rate;
     }
     public String getAll_rate() {
         return all_rate;
     }

    public void setFile_duration(int file_duration) {
         this.file_duration = file_duration;
     }
     public int getFile_duration() {
         return file_duration;
     }

    public void setHas_mv_mobile(int has_mv_mobile) {
         this.has_mv_mobile = has_mv_mobile;
     }
     public int getHas_mv_mobile() {
         return has_mv_mobile;
     }

    public void setVersions(String versions) {
         this.versions = versions;
     }
     public String getVersions() {
         return versions;
     }

    public void setBitrate_fee(String bitrate_fee) {
         this.bitrate_fee = bitrate_fee;
     }
     public String getBitrate_fee() {
         return bitrate_fee;
     }

    public void setBiaoshi(String biaoshi) {
         this.biaoshi = biaoshi;
     }
     public String getBiaoshi() {
         return biaoshi;
     }

    public void setInfo(String info) {
         this.info = info;
     }
     public String getInfo() {
         return info;
     }

    public void setHas_filmtv(String has_filmtv) {
         this.has_filmtv = has_filmtv;
     }
     public String getHas_filmtv() {
         return has_filmtv;
     }

    public void setSi_proxycompany(String si_proxycompany) {
         this.si_proxycompany = si_proxycompany;
     }
     public String getSi_proxycompany() {
         return si_proxycompany;
     }

    public void setRes_encryption_flag(String res_encryption_flag) {
         this.res_encryption_flag = res_encryption_flag;
     }
     public String getRes_encryption_flag() {
         return res_encryption_flag;
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

    public void setTing_uid(String ting_uid) {
         this.ting_uid = ting_uid;
     }
     public String getTing_uid() {
         return ting_uid;
     }

    public void setAuthor(String author) {
         this.author = author;
     }
     public String getAuthor() {
         return author;
     }

    public void setAlbum_id(String album_id) {
         this.album_id = album_id;
     }
     public String getAlbum_id() {
         return album_id;
     }

    public void setAlbum_title(String album_title) {
         this.album_title = album_title;
     }
     public String getAlbum_title() {
         return album_title;
     }

    public void setIs_first_publish(int is_first_publish) {
         this.is_first_publish = is_first_publish;
     }
     public int getIs_first_publish() {
         return is_first_publish;
     }

    public void setHavehigh(int havehigh) {
         this.havehigh = havehigh;
     }
     public int getHavehigh() {
         return havehigh;
     }

    public void setCharge(int charge) {
         this.charge = charge;
     }
     public int getCharge() {
         return charge;
     }

    public void setHas_mv(int has_mv) {
         this.has_mv = has_mv;
     }
     public int getHas_mv() {
         return has_mv;
     }

    public void setLearn(int learn) {
         this.learn = learn;
     }
     public int getLearn() {
         return learn;
     }

    public void setSong_source(String song_source) {
         this.song_source = song_source;
     }
     public String getSong_source() {
         return song_source;
     }

    public void setPiao_id(String piao_id) {
         this.piao_id = piao_id;
     }
     public String getPiao_id() {
         return piao_id;
     }

    public void setKorean_bb_song(String korean_bb_song) {
         this.korean_bb_song = korean_bb_song;
     }
     public String getKorean_bb_song() {
         return korean_bb_song;
     }

    public void setResource_type_ext(String resource_type_ext) {
         this.resource_type_ext = resource_type_ext;
     }
     public String getResource_type_ext() {
         return resource_type_ext;
     }

    public void setMv_provider(String mv_provider) {
         this.mv_provider = mv_provider;
     }
     public String getMv_provider() {
         return mv_provider;
     }

    public void setArtist_name(String artist_name) {
         this.artist_name = artist_name;
     }
     public String getArtist_name() {
         return artist_name;
     }

    public void setPic_radio(String pic_radio) {
         this.pic_radio = pic_radio;
     }
     public String getPic_radio() {
         return pic_radio;
     }

    public void setPic_s500(String pic_s500) {
         this.pic_s500 = pic_s500;
     }
     public String getPic_s500() {
         return pic_s500;
     }

    public void setPic_premium(String pic_premium) {
         this.pic_premium = pic_premium;
     }
     public String getPic_premium() {
         return pic_premium;
     }

    public void setPic_huge(String pic_huge) {
         this.pic_huge = pic_huge;
     }
     public String getPic_huge() {
         return pic_huge;
     }

    public void setAlbum_500_500(String album_500_500) {
         this.album_500_500 = album_500_500;
     }
     public String getAlbum_500_500() {
         return album_500_500;
     }

    public void setAlbum_800_800(String album_800_800) {
         this.album_800_800 = album_800_800;
     }
     public String getAlbum_800_800() {
         return album_800_800;
     }

    public void setAlbum_1000_1000(String album_1000_1000) {
         this.album_1000_1000 = album_1000_1000;
     }
     public String getAlbum_1000_1000() {
         return album_1000_1000;
     }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(artist_id);
        dest.writeString(language);
        dest.writeString(pic_big);
        dest.writeString(pic_small);
        dest.writeString(country);
        dest.writeString(area);
        dest.writeString(album_no);
        dest.writeString(lrclink);
        dest.writeString(copy_type);
        dest.writeString(hot);
        dest.writeString(all_artist_ting_uid);
        dest.writeString(resource_type);
        dest.writeString(is_new);
        dest.writeString(rank_change);
        dest.writeString(rank);
        dest.writeString(all_artist_id);
        dest.writeString(style);
        dest.writeString(del_status);
        dest.writeString(relate_status);
        dest.writeString(toneid);
        dest.writeString(all_rate);
        dest.writeInt(file_duration);
        dest.writeInt(has_mv_mobile);
        dest.writeString(versions);
        dest.writeString(bitrate_fee);
        dest.writeString(info);
        dest.writeString(has_filmtv);
        dest.writeString(si_proxycompany);
        dest.writeString(res_encryption_flag);
        dest.writeString(song_id);
        dest.writeString(title);
        dest.writeString(ting_uid);
        dest.writeString(author);
        dest.writeString(album_id);
        dest.writeString(album_title);
        dest.writeInt(is_first_publish);
        dest.writeInt(havehigh);
        dest.writeInt(charge);
        dest.writeInt(has_mv);
        dest.writeInt(learn);
        dest.writeString(song_source);
        dest.writeString(piao_id);
        dest.writeString(korean_bb_song);
        dest.writeString(resource_type_ext);
        dest.writeString(mv_provider);
        dest.writeString(artist_name);
        dest.writeString(pic_radio);
        dest.writeString(pic_s500);
        dest.writeString(pic_premium);
        dest.writeString(pic_huge);
        dest.writeString(album_500_500);
        dest.writeString(album_800_800);
        dest.writeString(album_1000_1000);
    }
}
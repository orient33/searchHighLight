package com.example.adm.demo.search;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 通话记录bean
 * Created by Adm on 2017/10/13.
 */

public class Contacts implements Parcelable, Comparable<Contacts> {
    private String mIndex;
    private String mName;
    private String mMark;
    private String mNumber;
    private String mPhotoPath;
    private String mNumberRegion;
    private int mType;
    private String mCallType;
    private String mCallDatetime;
    private boolean isToday;
    private int mNum = 1;

    public String getIndex() {
        return mIndex;
    }

    public void setIndex(String mIndex) {
        this.mIndex = mIndex;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getMark() {
        return mMark;
    }

    public void setMark(String mark) {
        mMark = mark;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        mNumber = number;
    }

    public String getPhotoPath() {
        return mPhotoPath;
    }

    public void setPhotoPath(String photoPath) {
        mPhotoPath = photoPath;
    }

    public String getNumberRegion() {
        return mNumberRegion;
    }

    public void setNumberRegion(String numberRegion) {
        this.mNumberRegion = numberRegion;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }

    public String getCallType() {
        return mCallType;
    }

    public void setCallType(String callType) {
        this.mCallType = callType;
    }

    public String getCallDatetime() {
        return mCallDatetime;
    }

    public void setCallDatetime(String callDatetime) {
        mCallDatetime = callDatetime;
    }

    public boolean isToday() {
        return isToday;
    }

    public void setToday(boolean today) {
        isToday = today;
    }

    public int getNum() {
        return mNum;
    }

    public void setNum(int num) {
        mNum = num;
    }

    public Contacts() {
    }

    @Override
    public int compareTo(Contacts another) {
        if (getIndex() == null) {
            return -1;
        }

        if (another.getIndex() == null) {
            return 1;
        }

        return getIndex().compareTo(another.getIndex());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mIndex);
        dest.writeString(this.mName);
        dest.writeString(this.mMark);
        dest.writeString(this.mNumber);
        dest.writeString(this.mPhotoPath);
        dest.writeString(this.mNumberRegion);
        dest.writeInt(this.mType);
        dest.writeString(this.mCallType);
        dest.writeString(this.mCallDatetime);
        dest.writeByte(this.isToday ? (byte) 1 : (byte) 0);
        dest.writeInt(this.mNum);
    }

    protected Contacts(Parcel in) {
        this.mIndex = in.readString();
        this.mName = in.readString();
        this.mMark = in.readString();
        this.mNumber = in.readString();
        this.mPhotoPath = in.readString();
        this.mNumberRegion = in.readString();
        this.mType = in.readInt();
        this.mCallType = in.readString();
        this.mCallDatetime = in.readString();
        this.isToday = in.readByte() != 0;
        this.mNum = in.readInt();
    }

    public static final Creator<Contacts> CREATOR = new Creator<Contacts>() {
        @Override
        public Contacts createFromParcel(Parcel source) {
            return new Contacts(source);
        }

        @Override
        public Contacts[] newArray(int size) {
            return new Contacts[size];
        }
    };

    public static Contacts getFake1(){
        Contacts one = new Contacts();
        one.mIndex = "0";
        one.mName = "东方朔";
        one.mNumber = "18610650000";
        one.mMark = "DFS";
        return one;
    }
    public Contacts cloneSame() {
        Contacts other = new Contacts();
        other.mIndex = mIndex;
        other.mName = mName;
        other.mMark = mMark;
        other.mNumber = mNumber;
        other.mPhotoPath = mPhotoPath;
        other.mNumberRegion = mNumberRegion;
        other.mType = mType;
        other.mCallType = mCallType;
        other.mCallDatetime = mCallDatetime;
        other.isToday = isToday;
        other.mNum = mNum;
        return other;
    }
}

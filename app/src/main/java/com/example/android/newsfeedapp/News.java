package com.example.android.newsfeedapp;

/**
 * Created by grzegorzwilusz on 5/16/18.
 */

public class News {

    private String mSection;
    private String mTitle;
    private String mPublicationDate;
    private String mWebUrl;

    public News(String section, String title, String publicationDate, String webUrl) {
        this.mSection = section;
        this.mTitle = title;
        this.mPublicationDate = publicationDate;
        this.mWebUrl = webUrl;
    }

    public String getmSection() {
        return mSection;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmPublicationDate() {
        return mPublicationDate;
    }

    public String getmWebUrl() {
        return mWebUrl;
    }

    @Override
    public String toString() {
        return "News{" +
                "mSection='" + mSection + '\'' +
                ", mTitle='" + mTitle + '\'' +
                ", mPublicationDate='" + mPublicationDate + '\'' +
                ", mWebUrl='" + mWebUrl + '\'' +
                '}';
    }
}

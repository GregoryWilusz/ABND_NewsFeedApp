package com.example.android.newsfeedapp.news;

import java.util.ArrayList;

/**
 * Created by grzegorzwilusz on 5/16/18.
 */

public class News {

    private String mSection;
    private String mTitle;
    private String mPublicationDate;
    private ArrayList<String> mAuthor;
    private String mWebUrl;

    public News(String section, String title, String publicationDate, String webUrl) {
        this.mSection = section;
        this.mTitle = title;
        this.mPublicationDate = publicationDate;
        this.mWebUrl = webUrl;
    }

    public News(String section, String title, String publicationDate, ArrayList<String> author, String webUrl) {
        this.mSection = section;
        this.mTitle = title;
        this.mPublicationDate = publicationDate;
        this.mAuthor = author;
        this.mWebUrl = webUrl;
    }

    public String getSection() {
        return mSection;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getPublicationDate() {
        return mPublicationDate;
    }

    public ArrayList<String> getAuthor() {
        return mAuthor;
    }

    public String getWebUrl() {
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

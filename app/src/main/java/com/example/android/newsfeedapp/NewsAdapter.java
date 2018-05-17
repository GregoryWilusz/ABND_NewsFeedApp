package com.example.android.newsfeedapp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by grzegorzwilusz on 5/16/18.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    @BindView(R.id.section_text_view) TextView sectionTextView;
    @BindView(R.id.title_text_view) TextView titleTextView;
    @BindView(R.id.publication_date_text_view) TextView publicationDateTextView;
    @BindView(R.id.publication_time_text_view) TextView publicationTimeTextView;

    public NewsAdapter(NewsActivity context, List<News> news) {
        super(context, 0, news);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_item, parent, false);
        }

        ButterKnife.bind(this, convertView);

        final News currentNews = getItem(position);

        sectionTextView.setText(currentNews.getSection());
        titleTextView.setText(currentNews.getTitle());

        publicationDateTextView.setText(formatToDate(currentNews.getPublicationDate()));
        publicationTimeTextView.setText(formatToTime(currentNews.getPublicationDate()));

        return convertView;
    }

    private String formatToDate(String date) {
        String formattedStringToDate = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateObject = formatter.parse(date);
            formattedStringToDate = formatter.format(dateObject);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedStringToDate;
    }

    private String formatToTime(String date) {
        String formattedStringToTime = "";
        try {
            Date dateObject = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(date);
            formattedStringToTime = new SimpleDateFormat("HH:mm:ss").format(dateObject);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedStringToTime;
    }
}

package com.example.android.newsfeedapp.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.newsfeedapp.news.News;
import com.example.android.newsfeedapp.news.NewsActivity;
import com.example.android.newsfeedapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by grzegorzwilusz on 5/16/18.
 */

public class NewsAdapter extends ArrayAdapter<News> {


    public NewsAdapter(NewsActivity context, List<News> news) {
        super(context, 0, news);
    }

    static class ViewHolder {
        @BindView(R.id.section_text_view) TextView sectionTextView;
        @BindView(R.id.title_text_view) TextView titleTextView;
        @BindView(R.id.publication_date_text_view) TextView publicationDateTextView;
        @BindView(R.id.publication_time_text_view) TextView publicationTimeTextView;
        @BindView(R.id.author_text_view) TextView authorTextView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        final News currentNews = getItem(position);

        holder.sectionTextView.setText(currentNews.getSection());
        holder.titleTextView.setText(currentNews.getTitle());

        holder.publicationDateTextView.setText(formatToDate(currentNews.getPublicationDate()));
        holder.publicationTimeTextView.setText(formatToTime(currentNews.getPublicationDate()));

        ArrayList<String> authors = currentNews.getAuthor();

        if (authors == null) {
            holder.authorTextView.setVisibility(View.GONE);
        } else {
            holder.authorTextView.setText(displayAuthors(authors));
        }

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

    private String displayAuthors(ArrayList<String> authors) {
        StringBuilder authorsList = new StringBuilder();
        for (String author : authors) {
            if (authors.indexOf(author) != authors.size() - 1) {
                authorsList.append(author).append(", ");
            } else {
                authorsList.append(author);
            }
        }
        return authorsList.toString();
    }
}

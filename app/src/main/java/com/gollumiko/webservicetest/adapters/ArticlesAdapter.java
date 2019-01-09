package com.gollumiko.webservicetest.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gollumiko.webservicetest.R;
import com.gollumiko.webservicetest.model.Article;
import com.gollumiko.webservicetest.model.Category;

public class ArticlesAdapter extends ArrayAdapter<Article> {
    public ArticlesAdapter(Context context, Article[] articles) {
        super(context, 0, articles);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.listview_articles, null);
        }

        TextView title = convertView.findViewById(R.id.article_description);
        title.setText(getItem(position).getDescription());

        TextView price = convertView.findViewById(R.id.article_price);
        price.setText("$" + getItem(position).getPrice());

        return convertView;
    }

}

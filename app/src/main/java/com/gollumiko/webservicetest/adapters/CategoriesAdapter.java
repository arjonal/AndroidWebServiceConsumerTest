package com.gollumiko.webservicetest.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gollumiko.webservicetest.R;
import com.gollumiko.webservicetest.model.Category;

public class CategoriesAdapter extends ArrayAdapter<Category> {
    public CategoriesAdapter(Context context, Category[] categories) {
        super(context, 0, categories);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.listview_categories, null);
        }

        TextView title = (TextView) convertView.findViewById(R.id.category_description);
        title.setText(getItem(position).getDescription());



        return convertView;
    }

}

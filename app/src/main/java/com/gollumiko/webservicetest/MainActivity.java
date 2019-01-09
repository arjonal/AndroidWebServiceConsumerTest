package com.gollumiko.webservicetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.Volley;

import com.gollumiko.webservicetest.adapters.CategoriesAdapter;
import com.gollumiko.webservicetest.model.Category;
import com.gollumiko.webservicetest.util.GsonRequest;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Category[] categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue queue = Volley.newRequestQueue(this);

        GsonRequest<Category[]> myReq = new GsonRequest<Category[]>(Method.GET,
                getString(R.string.server_url) + "/categories/", Category[].class,
                createMyReqSuccessListener(), createMyReqErrorListener());

        queue.add(myReq);

        ListView categoriesLV = (ListView) findViewById(R.id.categories_listview);
        categoriesLV.setOnItemClickListener(this);
    }

    private Response.Listener<Category[]> createMyReqSuccessListener() {
        return new Response.Listener<Category[]>() {
            @Override
            public void onResponse(Category[] response) {
                ListView categoriesLV = (ListView) findViewById(R.id.categories_listview);

                CategoriesAdapter adapter = new CategoriesAdapter(getBaseContext(), response);
                categoriesLV.setAdapter(adapter);

                categories = response;

                Log.i("VOLLEY", "OK");
            }
        };
    }

    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("VOLLEY", "ERROR ----" + error.getMessage());

            }
        };
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getBaseContext(), ArticleActivity.class);
        intent.putExtra(getString(R.string.selected_category_id_key), categories[i].getId());
        startActivity(intent);
    }
}

package com.gollumiko.webservicetest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.gollumiko.webservicetest.adapters.ArticlesAdapter;
import com.gollumiko.webservicetest.adapters.CategoriesAdapter;
import com.gollumiko.webservicetest.model.Article;
import com.gollumiko.webservicetest.model.Category;
import com.gollumiko.webservicetest.util.GsonRequest;

public class ArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        RequestQueue queue = Volley.newRequestQueue(this);

        int categoryId = getIntent().getExtras().getInt(getString(R.string.selected_category_id_key), 0);

        GsonRequest<Article[]> myReq = new GsonRequest<Article[]>(Request.Method.GET,
                getString(R.string.server_url) + "/categories/" + categoryId + "/articles", Article[].class,
                createMyReqSuccessListener(), createMyReqErrorListener());

        queue.add(myReq);
    }

    private Response.Listener<Article[]> createMyReqSuccessListener() {
        return new Response.Listener<Article[]>() {
            @Override
            public void onResponse(Article[] response) {
                ListView articlesLV = (ListView) findViewById(R.id.articles_listview);

                ArticlesAdapter adapter = new ArticlesAdapter(getBaseContext(), response);
                articlesLV.setAdapter(adapter);

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

}

package com.example.mini_.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback{

    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String category = (String) parent.getItemAtPosition(position);

            // put the selected category in the intent and go to menuActivity
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("category", category);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        // connect the click listener to list view
        ListView lv = findViewById(R.id.categories_lv);
        lv.setOnItemClickListener(new ListItemClickListener());

        RequestQueue queue = Volley.newRequestQueue(this);

        CategoriesRequest x = new CategoriesRequest(this);
        x.getCategories(this);
    }


    @Override
    public void gotCategories(ArrayList<String> categories) {
        ListView lv = findViewById(R.id.categories_lv);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, categories);
        lv.setAdapter(adapter);
    }

    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}

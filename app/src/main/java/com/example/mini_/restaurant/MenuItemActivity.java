package com.example.mini_.restaurant;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MenuItemActivity extends AppCompatActivity {

    ImageView itemImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        // get the selected item from the intent
        RequestQueue queue = Volley.newRequestQueue(this);
        Intent intent = getIntent();
        MenuItem selectedItem = (MenuItem) intent.getSerializableExtra("selected_item");

        // get the information from selected item
        String title = selectedItem.getName();
        String image = selectedItem.getImageUrl();
        String description = selectedItem.getDescription();
        Long price = selectedItem.getPrice();

        // get the id's from textViews and imageView
        TextView itemTitle = findViewById(R.id.itemTitle);
        itemImage = findViewById(R.id.itemImage);
        TextView itemDescription = findViewById(R.id.itemContent);
        TextView itemPrice = findViewById(R.id.itemPrice);

        // set the image
        ImageRequest imageRequest = new ImageRequest(image, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                Log.d("MenuItemActivity", "onResponse");
                itemImage.setImageBitmap(response);
            }
        }, 0, 0, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("MenuItemActivity", "onErrorResponse");
                itemImage.setImageResource(R.drawable.ic_launcher_background);
            }
        });
        queue.add(imageRequest);

        // set the rest of the dish its information
        itemTitle.setText(title);
        itemDescription.setText(description);
        itemPrice.setText("$ " + String.valueOf(price));
    }
}

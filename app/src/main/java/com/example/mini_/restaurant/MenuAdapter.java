package com.example.mini_.restaurant;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends ArrayAdapter<MenuItem> {

    ArrayList<MenuItem> listItems;
    Context currContext;

    public MenuAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MenuItem> objects) {
        super(context, resource, objects);
        this.listItems = objects;
        this.currContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate
                    (R.layout.menu_rows, parent, false);
        }

        RequestQueue queue = Volley.newRequestQueue(currContext);
        final ImageView imageUrl = convertView.findViewById(R.id.imageUrl);
        TextView rowTitle = convertView.findViewById(R.id.rowTitle);
        TextView rowPrice =convertView.findViewById(R.id.rowPrice);

        String image = listItems.get(position).getImageUrl();
        String title = listItems.get(position).getName();
        Long price = listItems.get(position).getPrice();

        ImageRequest imageRequest = new ImageRequest(image, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                Log.d("MenuItemActivity", "onResponse");
                imageUrl.setImageBitmap(response);
            }
        }, 0, 0, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("MenuItemActivity", "onErrorResponse");
                imageUrl.setImageResource(R.drawable.ic_launcher_background);
            }
        });
        queue.add(imageRequest);

        rowTitle.setText(title);
        rowPrice.setText("$ " + String.valueOf(price));

        return convertView;
    }
}

package com.example.mini_.restaurant;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends ArrayAdapter<MenuItem> {

    ArrayList<MenuItem> listItems;

    public MenuAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MenuItem> objects) {
        super(context, resource, objects);
        this.listItems = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate
                    (R.layout.menu_rows, parent, false);
        }

//        ImageView imageUrl = convertView.findViewById(R.id.imageUrl);
        TextView rowTitle = convertView.findViewById(R.id.rowTitle);
        TextView rowPrice =convertView.findViewById(R.id.rowPrice);

//        String image = listItems.get(position).getImageUrl();
        String title = listItems.get(position).getName();
        Long price = listItems.get(position).getPrice();


        rowTitle.setText(title);
        rowPrice.setText("$ " + String.valueOf(price));

        return convertView;
    }
}

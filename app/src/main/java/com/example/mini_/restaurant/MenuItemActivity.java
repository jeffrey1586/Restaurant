package com.example.mini_.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MenuItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        Intent intent = getIntent();
        MenuItem selectedItem = (MenuItem) intent.getSerializableExtra("selected_item");

        String title = selectedItem.getName();
//        String image = selectedItem.getImageUrl();
        String description = selectedItem.getDescription();
        Long price = selectedItem.getPrice();

        TextView itemTitle = findViewById(R.id.itemTitle);
//        ImageView itemImage = findViewById(R.id.itemImage);
        TextView itemDescription = findViewById(R.id.itemContent);
        TextView itemPrice = findViewById(R.id.itemPrice);

        itemTitle.setText(title);
//        itemImage.set();
        itemDescription.setText(description);
        itemPrice.setText("$ " + String.valueOf(price));
    }
}

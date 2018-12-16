package com.example.mini_.restaurant;

import android.content.Context;
import android.net.Uri;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

public class MenuItemsRequest implements Response.Listener<JSONObject>, Response.ErrorListener{

    private Context context;
    private MenuItemsRequest.Callback theActivity;
    String url;
    ArrayList array = new ArrayList<MenuItem>();

    public MenuItemsRequest(Context context) {
        this.context = context;
    }
    public interface Callback {
        void gotMenuItems(ArrayList<MenuItem> categories);
        void gotMenuItemsError(String errorMessage);
    }

    // give error message to TheActivity
    @Override
    public void onErrorResponse(VolleyError error) {
        String errorMessage = error.getMessage();
        theActivity.gotMenuItemsError(errorMessage);
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray itemsArray = new JSONArray();
        try {
            itemsArray = response.getJSONArray("items");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // get the dish information and make a new Menu item
        for (int i = 0; i < itemsArray.length(); i++) {
            try {
                JSONObject itemInfo = itemsArray.getJSONObject(i);
                String name = itemInfo.getString("name");
                String description = itemInfo.getString("description");
                String image = itemInfo.getString("image_url");
                String category = itemInfo.getString("category");
                Long price = itemInfo.getLong("price");

                MenuItem item = new MenuItem(name, description, image, category, price);
                array.add(item);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        theActivity.gotMenuItems(array);
    }

    // get the menu data from the url
    public void getMenuItems(Callback activity, String category) {
        RequestQueue queue = Volley.newRequestQueue(context);

        theActivity = activity;

        switch (category) {
            case "entrees": JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    ("https://resto.mprog.nl/menu?category=entrees", null, this, this);
            queue.add(jsonObjectRequest);
                break;
            case "appetizers": JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest
                    ("https://resto.mprog.nl/menu?category=appetizers", null, this, this);
                queue.add(jsonObjectRequest1);
                break;
        }
    }
}


package com.example.mini_.restaurant;

import android.content.ContentValues;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener{

    private Context context;
    private Callback theActivity;
    ArrayList array = new ArrayList<String>();

    public CategoriesRequest(Context context) {
        this.context = context;
    }

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    // give error message back to TheActivity
    @Override
    public void onErrorResponse(VolleyError error) {
        String errorMessage = error.getMessage();
        theActivity.gotCategoriesError(errorMessage);
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray categoriesArray = new JSONArray();
        try {
            categoriesArray = response.getJSONArray("categories");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // give categories back to TheActivity
        for (int i = 0; i < categoriesArray.length(); i++) {
            try {

                array.add(categoriesArray.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        theActivity.gotCategories(array);
    }

    // get categories from url
    public void getCategories(Callback activity) {
        RequestQueue queue = Volley.newRequestQueue(context);

        theActivity = activity;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                ("https://resto.mprog.nl/categories", null, this, this);
        queue.add(jsonObjectRequest);

    }
}

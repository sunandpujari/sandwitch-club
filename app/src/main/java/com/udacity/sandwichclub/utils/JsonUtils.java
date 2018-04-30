package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    static final String TAG = "demo";

    public static Sandwich parseSandwichJson(String json) {
       // Log.d(TAG, "parseSandwichJson: " +json);


        Sandwich sandwich = null;
        JSONObject root = null;
        try {

            sandwich = new Sandwich();
            root = new JSONObject(json);

            JSONObject name = root.getJSONObject("name");
            sandwich.setMainName(name.getString("mainName"));

            JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");
            for(int i=0; i< alsoKnownAs.length();i++){
                sandwich.addAlsoKnownAs(alsoKnownAs.getString(i));
            }

            sandwich.setPlaceOfOrigin(root.getString("placeOfOrigin"));
            sandwich.setDescription(root.getString("description"));
            sandwich.setImage(root.getString("image"));

            JSONArray ingredients = root.getJSONArray("ingredients");
            for(int i=0; i< ingredients.length();i++){
                sandwich.addIngredients(ingredients.getString(i));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }
}

package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    static final String TAG = "demo";
    public static final String KEY_NAME = "name";
    public static final String KEY_MAIN_NAME = "mainName";
    public static final String KEY_KNOWN_AS = "alsoKnownAs";
    public static final String KEY_ORIGIN = "placeOfOrigin";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
       // Log.d(TAG, "parseSandwichJson: " +json);


        Sandwich sandwich = null;
        JSONObject root = null;
        try {

            sandwich = new Sandwich();
            root = new JSONObject(json);

            JSONObject name = root.optJSONObject(KEY_NAME);
            sandwich.setMainName(name.optString(KEY_MAIN_NAME));

            JSONArray alsoKnownAs = name.optJSONArray(KEY_KNOWN_AS);
            for(int i=0; i< alsoKnownAs.length();i++){
                sandwich.addAlsoKnownAs(alsoKnownAs.optString(i));
            }

            sandwich.setPlaceOfOrigin(root.optString(KEY_ORIGIN));
            sandwich.setDescription(root.optString(KEY_DESCRIPTION));
            sandwich.setImage(root.optString(KEY_IMAGE));

            JSONArray ingredients = root.optJSONArray(KEY_INGREDIENTS);
            for(int i=0; i< ingredients.length();i++){
                sandwich.addIngredients(ingredients.optString(i));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }
}

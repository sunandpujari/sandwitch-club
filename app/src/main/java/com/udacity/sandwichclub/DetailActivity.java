package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;
import com.udacity.sandwichclub.utils.StringUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    TextView origin_tv, description_tv,ingredients_tv,also_known_tv;
    Sandwich sandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        origin_tv = findViewById(R.id.origin_tv);
        description_tv = findViewById(R.id.description_tv);
        ingredients_tv = findViewById(R.id.ingredients_tv);
        also_known_tv = findViewById(R.id.also_known_tv);

        description_tv.setMovementMethod(new ScrollingMovementMethod());
        ingredients_tv.setMovementMethod(new ScrollingMovementMethod());
        also_known_tv.setMovementMethod(new ScrollingMovementMethod());

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

        if(TextUtils.isEmpty(sandwich.getPlaceOfOrigin()))
            findViewById(R.id.place_of_origin_container).setVisibility(View.GONE);
        else
            origin_tv.setText(sandwich.getPlaceOfOrigin());

        if(TextUtils.isEmpty(sandwich.getDescription()))
            findViewById(R.id.description_container).setVisibility(View.GONE);
        else
            description_tv.setText(sandwich.getDescription());

        if(TextUtils.isEmpty(sandwich.getIngredientsString()))
            findViewById(R.id.ingredients_container).setVisibility(View.GONE);
        else
            ingredients_tv.setText(sandwich.getIngredientsString());

        if(TextUtils.isEmpty(sandwich.getAlsoKnownAsString()))
            findViewById(R.id.also_known_container).setVisibility(View.GONE);
        else
            also_known_tv.setText(sandwich.getAlsoKnownAsString());

    }
}

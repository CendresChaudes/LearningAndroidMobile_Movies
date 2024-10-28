package com.example.movies;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class ReviewTypeDeserializer implements JsonDeserializer<ReviewType> {
    @Override
    public ReviewType deserialize(
            JsonElement json,
            Type typeOfT,
            JsonDeserializationContext context
    ) throws JsonParseException {
        String typeName = json.getAsString();

        ReviewType reviewType = null;

        switch (typeName) {
            case "Позитивный":
                reviewType = ReviewType.POSITIVE;
                break;
            case "Нейтральный":
                reviewType = ReviewType.NEUTRAL;
                break;
            case "Негативный":
                reviewType = ReviewType.NEGATIVE;
                break;
        }

        if (reviewType == null) {
            Log.e("ReviewTypeDeserializer", "Unexpected review type: " + typeName);
        }

        return reviewType;
    }
}
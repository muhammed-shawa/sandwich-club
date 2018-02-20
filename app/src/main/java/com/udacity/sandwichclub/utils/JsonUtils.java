package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {


    /* Set the key names in the json array */

    private static final String NAME            = "name";
    private static final String NAME_MAINNAME   = "mainName";
    private static final String NAME_KNOWN_AS   = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION     = "description";
    private static final String IMAGE           = "image";
    private static final String INGREDIENTS     = "ingredients";


    public static Sandwich parseSandwichJson(String json) {


        String mainName ;
        String placeOfOrigin ;
        String image ;
        String description ;
        List<String> ingredients   ;
        List<String> alsoKnownAs  ;

        try {

            JSONObject SandwichJSON = new JSONObject(json);
            JSONObject nameObject = SandwichJSON.getJSONObject(NAME);

            mainName      = nameObject.getString(NAME_MAINNAME);
            placeOfOrigin = SandwichJSON.getString(PLACE_OF_ORIGIN);
            description   = SandwichJSON.getString(DESCRIPTION);
            image         = SandwichJSON.getString(IMAGE);
            alsoKnownAs   = ToList(nameObject.getJSONArray(NAME_KNOWN_AS));
            ingredients   = ToList(SandwichJSON.getJSONArray(INGREDIENTS));

        }catch (JSONException e){
            Log.e("JSON ERROR", "Can't parse JSON Object", e);
            return  null;
        }

        return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

    }


    private static List<String> ToList(JSONArray jsonArray){

        List<String> list = new ArrayList<>(0);

        if (jsonArray!=null){

            for (int i=0; i<jsonArray.length();i++){
                try {
                    list.add(jsonArray.getString(i));
                } catch (JSONException e) {
                    Log.e("ArrayListError", "Can't handle json array", e);
                }
            }
        }

        return list;
    }
}

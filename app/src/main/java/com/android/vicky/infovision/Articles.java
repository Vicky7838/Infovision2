package com.android.vicky.infovision;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vicky on 10/24/2016.
 */

public class Articles extends Fragment {
    private RecyclerView recyclerView;
    private Adapter mAdapter;
    List<DetailsModel> d = new ArrayList<>();
    private final String LOG_TAG = "infovision_log";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.articles, container, false);
        String strJson = " {\"user\" : [" +
                "{\"name\" : \"Chandan Chopadkar\", " +
                "\"email\" : \"chandan@applop.com\"," +
                " \"phone\" : \"8447508067\"," +
                " \"password\" : \"pankaj\"," +
                " \"age\" : \"28\"," +
                " \"gender\" : \"male\"," +
                " \"profession\" : \"57c82216a2a46a1ebcee4375\"}," +
                "{\"name\" : \"Chandan Chopadkar\", " +
                " \"email\" : \"chandan@applop.com\"," +
                " \"phone\" : \"8447508067\"," +
                " \"password\" : \"pankaj\"," +
                " \"age\" : \"28\"," +
                "  \"gender\" : \"male\"," +
                "  \"profession\" : \"57c82216a2a46a1ebcee4375\"}," +
                "{\"name\" : \"Chandan Chopadkar\"," +
                "                \"email\" : \"chandan@applop.com\"," +
                "                \"phone\" : \"8447508067\"," +
                "                \"password\" : \"pankaj\"," +
                "                \"age\" : \"28\"," +
                "                 \"gender\" : \"male\"," +
                "                 \"profession\" : \"57c82216a2a46a1ebcee4375\"}]}";

        try {

            JSONObject jsonRootObject = new JSONObject(strJson);

            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = jsonRootObject.optJSONArray("user");

            //Iterate the jsonArray and print the info of JSONObjects
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject json_data = jsonArray.getJSONObject(i);
                DetailsModel fishData = new DetailsModel();
                fishData.name = json_data.getString("name");
                fishData.email = json_data.getString("email");
                fishData.phone = json_data.getString("phone");
                fishData.password = json_data.getString("password");
                fishData.age = json_data.getString("age");
                fishData.gender = json_data.getString("gender");
                fishData.profession = json_data.getString("profession");
                d.add(fishData);
            }
            recyclerView = (RecyclerView)rootView.findViewById(R.id.listview);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            Log.d(LOG_TAG, "SingleTabFragment.java - d : " + d.get(0).name);
            mAdapter = new Adapter(getContext(), d);
            recyclerView.setAdapter(mAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rootView;
    }
}

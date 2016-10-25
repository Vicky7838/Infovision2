package com.android.vicky.infovision;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.vicky.infovision.utils.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;

/**
 * Created by vicky on 10/24/2016.
 */

public class SingleTabFragment extends Fragment {
    private RecyclerView recyclerView;
    private Adapter mAdapter;
    private int bundleVal = 0;
    List<DetailsModel> d = new ArrayList<>();
    private final String LOG_TAG = "infovision_log";
    DetailsModel fishData;

    public static SingleTabFragment newInstance(String value)   {
        SingleTabFragment singleTabFragment = new SingleTabFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.BUNDLE_STRING, value);
        singleTabFragment.setArguments(bundle);
        return singleTabFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.getString(Constant.BUNDLE_STRING) != null) {
                //setText(bundle.getString("all"));
                bundleVal = 1;
            }
        }
            View rootView = null;

            if (bundleVal == 1) {
                rootView = inflater.inflate(R.layout.all, container, false);
                String strJson = " {\"user\" : [" +
                        "{\"name\" : \"Chandan Chopadkar\", " +
                        "\"email\" : \"chandan@applop.com\"," +
                        " \"phone\" : \"8447508067\"," +
                        " \"password\" : \"pankaj\"," +
                        " \"age\" : \"28\"," +
                        " \"gender\" : \"male\"," +
                        " \"profession\" : \"57c82216a2a46a1ebcee4375\"}," +
                        "{\"name\" : \"Ishan Gulani\", " +
                        " \"email\" : \"ishan@applop.com\"," +
                        " \"phone\" : \"8447508067\"," +
                        " \"password\" : \"pankaj\"," +
                        " \"age\" : \"28\"," +
                        "  \"gender\" : \"male\"," +
                        "  \"profession\" : \"57c82216a2a46a1ebcee4375\"}," +
                        "{\"name\" : \"Ravi Verma\"," +
                        "                \"email\" : \"verma@applop.com\"," +
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
                        fishData = new DetailsModel();
                        fishData.setName(json_data.getString("name"));
                        fishData.setEmail(json_data.getString("email"));
                        fishData.setPhone(json_data.getString("phone"));
                        fishData.setPassword(json_data.getString("password"));
                        fishData.setAge(json_data.getString("age"));
                        fishData.setGender(json_data.getString("gender"));
                        fishData.setProfession(json_data.getString("profession"));
                        d.add(fishData);
                    }
                    recyclerView = (RecyclerView) rootView.findViewById(R.id.listview1);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    Log.d(LOG_TAG, "SingleTabFragment.java - d : " + d.get(0).name);
                    mAdapter = new Adapter(getContext(), d);
                    recyclerView.setAdapter(mAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mAdapter.setOnItemClickListener(new Adapter.ClickListener() {
                    @Override
                    public void onItemClick(int position, View v) {
                        Intent intent = new Intent(getContext(),
                                ShowData.class);
                        //Create Parcelable object
                        ParcelableDetails parcelableLaptop = new ParcelableDetails(d.get(position));

                        //Store Parcelable object in Intent
                        intent.putExtra("laptop", parcelableLaptop);

                        //Start next activity
                        startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(int position, View v) {
                    }
                });
            }

       /* }else  {
            rootView = inflater.inflate(R.layout.all_2, container, false);
        }}*/

        return rootView;

    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            if(bundle.getString(Constant.BUNDLE_STRING)!=null) {
                //setText(bundle.getString("all"));
                bundleVal = 1;
            }/*else
            {
                bundleVal = 2;
//                setText(bundle.getString("art"));
                //Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }*/
        }
    }

    /*public void setText(String val) {
        TextView view = (TextView) getView().findViewById(R.id.data);
        view.setText(val);
    }*/
}




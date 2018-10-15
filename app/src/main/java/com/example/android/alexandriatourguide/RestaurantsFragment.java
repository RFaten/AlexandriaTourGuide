package com.example.android.alexandriatourguide;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantsFragment extends Fragment {


    public RestaurantsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Create View refers to the xml file
        View rootView = inflater.inflate(R.layout.attractions_list, container, false);
        // Find the TextView in the xml
        TextView textView = (TextView) rootView.findViewById(R.id.head_text);
        // Set the text on the TextView
        textView.setText(getString(R.string.head_start, getString(R.string.restaurants)));

        // Create an ArrayList of restaurants
        ArrayList<Attraction> restaurants = new ArrayList<>();
        restaurants.add(new Attraction(getString(R.string.kadoura), getString(R.string.kadoura_tel), R.drawable.kadoura, R.drawable.call));
        restaurants.add(new Attraction(getString(R.string.hosney), getString(R.string.hosney_tel), R.drawable.hosny, R.drawable.call));
        restaurants.add(new Attraction(getString(R.string.mohamed_ahmed), getString(R.string.mohamed_ahmed_tel), R.drawable.foul_mohammed_ahmed, R.drawable.call));
        restaurants.add(new Attraction(getString(R.string.elite), getString(R.string.elite_tel), R.drawable.elite, R.drawable.call));
        restaurants.add(new Attraction(getString(R.string.sea_qull), getString(R.string.sea_gull_tel), R.drawable.see_gull, R.drawable.call));
        restaurants.add(new Attraction(getString(R.string.tikka), getString(R.string.tikka_tel), R.drawable.tikka, R.drawable.call));
        restaurants.add(new Attraction(getString(R.string.ebn_el_balad), getString(R.string.ebn_el_balad_tel), R.drawable.ebn_el_balad, R.drawable.call));
        restaurants.add(new Attraction(getString(R.string.delices), getString(R.string.delices_tel), R.drawable.d_lices, R.drawable.call));
        restaurants.add(new Attraction(getString(R.string.roastery), getString(R.string.roastery_tel), R.drawable.roastery, R.drawable.call));
        restaurants.add(new Attraction(getString(R.string.potasta), getString(R.string.potasta_tel), R.drawable.potasta, R.drawable.call));

        // Create custom adapter to hold the Attraction object
        final AttractionAdapter adapter = new AttractionAdapter(getActivity(), restaurants);
        // Find the list view
        final ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Set the adapter on the list view
        listView.setAdapter(adapter);
        // Set onItemClickListener on the list view to display the location of the clicked item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the clicked item object
                Attraction currentAttraction = adapter.getItem(position);
                // Create an intent to handle the call functionality
                Intent call = new Intent(Intent.ACTION_DIAL);
                // Set the number to call on the intent
                call.setData(Uri.parse(getString(R.string.tel) + currentAttraction.getItemLink()));
                // Check whether the user's device has an app that can handle the intent
                if (call.resolveActivity(getActivity().getPackageManager()) != null){
                    // Start the intent
                    startActivity(call);
                }
            }
        });

        // return the view
        return rootView;
    }

    // Set the title of the activity when the user navigate to this fragment
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.restaurants);
    }

}

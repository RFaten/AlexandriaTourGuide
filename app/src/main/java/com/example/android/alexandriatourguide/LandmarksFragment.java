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
public class LandmarksFragment extends Fragment {


    public LandmarksFragment() {
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
        textView.setText(getString(R.string.head_start, getString(R.string.landmarks)));

        // Create ArrayList of landmarks
        ArrayList<Attraction> landmarks = new ArrayList<Attraction>();
        landmarks.add(new Attraction(getString(R.string.citadel_of_qaitbay), getString(R.string.citadel_of_qaitbay_map),
                R.drawable.citadel_of_qaitbay, R.drawable.landmark));
        landmarks.add(new Attraction(getString(R.string.bibliotheca_alex), getString(R.string.bibliotheca_alex_map),
                R.drawable.bibliotheca_alexandrina, R.drawable.landmark));
        landmarks.add(new Attraction(getString(R.string.pompey_pillar), getString(R.string.pompey_pillar_map),
                R.drawable.pompey_s_pillar, R.drawable.landmark));
        landmarks.add(new Attraction(getString(R.string.montazah_palcae), getString(R.string.montazah_palcae_map),
                R.drawable.montazah_palace, R.drawable.landmark));
        landmarks.add(new Attraction(getString(R.string.roman_auditorium), getString(R.string.roman_auditorium_map),
                R.drawable.roman_auditorium, R.drawable.landmark));
        landmarks.add(new Attraction(getString(R.string.abu_al_abbas), getString(R.string.abu_al_abbas_map),
                R.drawable.abu_al_abbas_al_mursi_mosque, R.drawable.landmark));
        landmarks.add(new Attraction(getString(R.string.antoniades_palace), getString(R.string.antoniades_palace_map),
                R.drawable.palais_d_antoniadis, R.drawable.landmark));
        landmarks.add(new Attraction(getString(R.string.nelson_island), getString(R.string.nelson_island_map),
                R.drawable.nelson_s_island, R.drawable.landmark));
        landmarks.add(new Attraction(getString(R.string.graeco_roman_museum), getString(R.string.graeco_roman_museum_map),
                R.drawable.graeco_roman_museum, R.drawable.landmark));
        landmarks.add(new Attraction(getString(R.string.alex_aquarium), getString(R.string.alex_aquarium_map),
                R.drawable.alexandria_aquarium, R.drawable.landmark));

        // Create custom adapter to hold the Attraction object
        final AttractionAdapter adapter = new AttractionAdapter(getActivity(), landmarks);
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
                // Get the map link of the clicked item and convert it into Uri
                Uri itemMap = Uri.parse(currentAttraction.getItemLink());
                // Create an intent to open the map with the appropriate app
                Intent intent = new Intent(Intent.ACTION_VIEW, itemMap);
                // Check whether the user's device has an app that can handle the intent
                if (intent.resolveActivity(getActivity().getPackageManager()) != null){
                    // Start the intent
                    startActivity(intent);
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
        getActivity().setTitle(R.string.landmarks);
    }

}

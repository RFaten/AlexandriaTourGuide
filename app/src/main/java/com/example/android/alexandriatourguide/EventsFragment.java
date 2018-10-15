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
public class EventsFragment extends Fragment {


    public EventsFragment() {
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
        textView.setText(getString(R.string.head_start, getString(R.string.events)));

        // Create an ArrayList of events
        ArrayList<Attraction> events = new ArrayList<Attraction>();
        events.add(new Attraction(getString(R.string.sufi_singing), getString(R.string.sufi_singing_event),
                R.drawable.sufi_singing_egyptian_mawlawiya, R.drawable.events));
        events.add(new Attraction(getString(R.string.pediatric_healthcare), getString(R.string.pediatric_healthcare_event),
                R.drawable.the_6th_global_congress_of_pediatric_healthcare, R.drawable.events));
        events.add(new Attraction(getString(R.string.acod_2018), getString(R.string.acod_2018_event),
                R.drawable.acod_2018, R.drawable.events));
        events.add(new Attraction(getString(R.string.ballet_dance), getString(R.string.ballet_dance_event),
                R.drawable.al___wahj_ballet_dance_party, R.drawable.events));
        events.add(new Attraction(getString(R.string.oud_concert), getString(R.string.oud_concert_event),
                R.drawable.oud_house_concert, R.drawable.events));
        events.add(new Attraction(getString(R.string.reading_program), getString(R.string.reading_program_event),
                R.drawable.great_reading_program, R.drawable.events));
        events.add(new Attraction(getString(R.string.design_folks), getString(R.string.design_folks_event),
                R.drawable.design_folks_architecture_vs_world_music, R.drawable.events));
        events.add(new Attraction(getString(R.string.symbolism_in_architecture), getString(R.string.symbolism_in_architecture_event),
                R.drawable.symbolism_in_architecture_and_the_new_silk_road, R.drawable.events));
        events.add(new Attraction(getString(R.string.gardening_for_all), getString(R.string.gardening_for_all_event),
                R.drawable.gardening_for_all, R.drawable.events));
        events.add(new Attraction(getString(R.string.orphan_day), getString(R.string.orphan_day_event),
                R.drawable.orphan_day_in_ramadan_, R.drawable.events));

        // Create custom adapter to hold the Attraction object
        final AttractionAdapter adapter = new AttractionAdapter(getActivity(), events);
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
                // Get the link of the clicked item and convert it into Uri
                Uri itemLink = Uri.parse(currentAttraction.getItemLink());
                // Create an intent to open the link with the appropriate app
                Intent intent = new Intent(Intent.ACTION_VIEW, itemLink);
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
        getActivity().setTitle(R.string.events);
    }

}

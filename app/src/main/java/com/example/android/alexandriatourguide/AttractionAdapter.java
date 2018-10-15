package com.example.android.alexandriatourguide;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/*
* {@link AttractionAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
* based on a data source, which is a list of {@link Attraction} objects.
* */

public class AttractionAdapter extends ArrayAdapter<Attraction> {

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context     The current context. Used to inflate the layout file.
     * @param attractions A List of Attraction objects to display in a list
     */
    public AttractionAdapter(Activity context, ArrayList<Attraction> attractions) {

        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two ImageView and one TextView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, attractions);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get the {@link Attraction} object located at this position in the list
        Attraction currentAttraction = getItem(position);

        // Find the ImageView in the list_item.xml layout with the ID attraction_image_view
        ImageView imageView = (ImageView) convertView.findViewById(R.id.attraction_image_view);
        // Get the image resource id from the current Attraction object and
        // set this id on the ImageView
        imageView.setImageResource(currentAttraction.geImageResourceId());

        // Find the TextView in the list_item.xml layout with the ID description
        TextView textView = (TextView) convertView.findViewById(R.id.description);
        // Get the item string from the current Attraction object and
        // set this text on the TextView
        textView.setText(currentAttraction.getItem());

        // Find the button ImageView in the lisy_item.xml layout with the id button_image
        ImageView buttonImageView = (ImageView) convertView.findViewById(R.id.button_image);
        // Get the button image resource id from the current Attraction object and
        // set this id on the ImageView
        buttonImageView.setImageResource(currentAttraction.getButtonImage());

        // Return the whole list item layout (containing 2 TImageView and one TextView)
        // so that it can be shown in the ListView
        return convertView;
    }
}

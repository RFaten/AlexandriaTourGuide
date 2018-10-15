package com.example.android.alexandriatourguide;


import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
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
public class LocalDishesFragment extends Fragment {


    /**
     * Handles playback of all the sound files
     */
    MediaPlayer mediaPlayer;
    /**
     * Handles audio focus when playing a sound file
     */
    AudioManager audioManager;
    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener audioFocus = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {

            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT
                    || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();
            }
        }
    };
    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    public LocalDishesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Create View refers to the xml file
        View rootView = inflater.inflate(R.layout.attractions_list, container, false);

        // Create and setup the {@link AudioManager} to request audio focus
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        // Find the TextView in the xml
        TextView textView = (TextView) rootView.findViewById(R.id.head_text);
        // Set the text on the TextView
        textView.setText(getString(R.string.head_start, getString(R.string.local_dishes)));

        // Create an ArrayList of local dishes
        ArrayList<Attraction> localDishes = new ArrayList<Attraction>();
        localDishes.add(new Attraction(getString(R.string.bamia), R.raw.bamia, R.drawable.bamia, R.drawable.ic_play_arrow_black));
        localDishes.add(new Attraction(getString(R.string.feseekh), R.raw.feseekh, R.drawable.feseekh, R.drawable.ic_play_arrow_black));
        localDishes.add(new Attraction(getString(R.string.falafel), R.raw.flafel, R.drawable.flafel, R.drawable.ic_play_arrow_black));
        localDishes.add(new Attraction(getString(R.string.kushari), R.raw.kushari, R.drawable.kushari, R.drawable.ic_play_arrow_black));
        localDishes.add(new Attraction(getString(R.string.mahshi), R.raw.mahshi, R.drawable.mahshi, R.drawable.ic_play_arrow_black));
        localDishes.add(new Attraction(getString(R.string.molokhiya), R.raw.molokhiya, R.drawable.molokhia, R.drawable.ic_play_arrow_black));
        localDishes.add(new Attraction(getString(R.string.shakshouka), R.raw.shakshouka, R.drawable.shakshuka, R.drawable.ic_play_arrow_black));
        localDishes.add(new Attraction(getString(R.string.kunafa), R.raw.kunafa, R.drawable.kunafa, R.drawable.ic_play_arrow_black));
        localDishes.add(new Attraction(getString(R.string.qatayef), R.raw.qatayef, R.drawable.qatayef, R.drawable.ic_play_arrow_black));
        localDishes.add(new Attraction(getString(R.string.om_ali), R.raw.om_ali, R.drawable.om_ali, R.drawable.ic_play_arrow_black));

        // Create custom adapter to hold the Attraction object
        final AttractionAdapter adapter = new AttractionAdapter(getActivity(), localDishes);
        // Find the list view
        final ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Set the adapter on the list view
        listView.setAdapter(adapter);
        // Set onItemClickListener on the list view to display the location of the clicked item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Release the media player if it currently exists because we
                // are about to play a different sound file.
                releaseMediaPlayer();
                // Get the clicked item object
                Attraction currentAttraction = adapter.getItem(position);
                // Get the sound associated with the clicked item resource id
                int soundId = currentAttraction.getSound();

                // Request audio focus for playback
                int result = audioManager.requestAudioFocus(audioFocus, AudioManager.STREAM_MUSIC
                        , AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(getActivity(), soundId);
                    mediaPlayer.start();
                    // Set up a listener on the media player, so tat we can stop and release
                    // the media player once the sound has finished playing
                    mediaPlayer.setOnCompletionListener(mCompletionListener);
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
        getActivity().setTitle(R.string.local_dishes);
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            audioManager.abandonAudioFocus(audioFocus);
        }
    }

    // Release media player when the the activity stops
    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}

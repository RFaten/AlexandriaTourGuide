package com.example.android.alexandriatourguide;

/**
 * {@link Attraction} represents an item in the tour guide app.
 */

public class Attraction {

    // The image resource id for the image view
    private int mImageResourceId;
    // The attraction item string
    private String mItem;
    // The contact link of the attraction item
    private String mItemLink;
    // The button associated with the attraction item
    private int mButtonImage;
    // The sound associated with the attraction item
    private int mSoundResourceId;

    /**
     * Create a new Attraction object.
     *
     * @param item            is the attraction item
     * @param itemLink        is the contact to the attraction item
     * @param imageResourceId is the drawable resource id for the image associated with the Attraction object
     * @param buttonImage     is the button associated with the attraction item drawable resource id
     */
    public Attraction(String item, String itemLink, int imageResourceId, int buttonImage) {
        mImageResourceId = imageResourceId;
        mItem = item;
        mItemLink = itemLink;
        mButtonImage = buttonImage;
    }

    public Attraction(String item, int soundResourceId, int imageResourceId, int buttonResourceId) {
        mImageResourceId = imageResourceId;
        mItem = item;
        mSoundResourceId = soundResourceId;
        mButtonImage = buttonResourceId;
    }

    // Get the attraction item
    public String getItem() {
        return mItem;
    }

    // Get the contact link to the attraction item
    public String getItemLink() {
        return mItemLink;
    }

    // Get the image resource id
    public int geImageResourceId() {
        return mImageResourceId;
    }

    // Get the button image resource id
    public int getButtonImage() {
        return mButtonImage;
    }

    // Get the sound associated withe the attraction item
    public int getSound() {
        return mSoundResourceId;
    }

}

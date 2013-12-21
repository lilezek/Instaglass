package com.google.devezek.instaglass.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageFragment extends Fragment {
	private ImageView  mImage;
	
	void setImage(ImageView imv){
		mImage = imv;
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	container.addView(mImage);
        return mImage;
    }
}

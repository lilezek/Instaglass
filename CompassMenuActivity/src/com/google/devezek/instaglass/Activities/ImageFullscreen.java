package com.google.devezek.instaglass.Activities;


import java.util.Stack;
import java.util.concurrent.locks.Lock;

import com.google.android.glass.sample.compass.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

public class ImageFullscreen extends FragmentActivity {
	private Lock insertionEx; 
    private Stack<ImageView> mImages;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compass);
        
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }
    
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
        	ImageFragment imf = new ImageFragment(); 
        	insertionEx.lock();
        	if (mImages.size() > 0)
        		imf.setImage(mImages.pop());
//        	else
//        		imf.setImage(defaultimage);
            insertionEx.unlock();
        	return imf;
        }

        @Override
        public int getCount() {
        	insertionEx.lock();
        	int c = mImages.size();
            insertionEx.unlock();
            return c;
        }
    }
    
    public void insertImage(ImageView im) {
    	insertionEx.lock();
    	mImages.push(im);
    	insertionEx.unlock();
    }
    
    
}


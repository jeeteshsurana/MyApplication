package com.jeeteshsurana.template.Adapters;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.jeeteshsurana.template.R;
import java.util.ArrayList;

public class IntroSliderViewPagerAdapter extends PagerAdapter {
    private ArrayList<Integer> IMAGES;
    private LayoutInflater inflater;
    private Context context;


    public IntroSliderViewPagerAdapter(Context context, ArrayList<Integer> IMAGES) {

        this.context = context;
        this.IMAGES = IMAGES;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return IMAGES.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.intro_viewpager_items, view, false);

        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout
                .findViewById(R.id.viewpager_image);


        final Button skip = (Button) imageLayout.findViewById(R.id.Intro_Skip);

        imageView.setImageResource(IMAGES.get(position));

        if (position == IMAGES.size()) {
            skip.setText("Login");
        }

        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {

    }

    @Override
    public Parcelable saveState() {
        return null;
    }

}

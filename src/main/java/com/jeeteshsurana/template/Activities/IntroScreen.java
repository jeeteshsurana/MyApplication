package com.jeeteshsurana.template.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jeeteshsurana.template.Adapters.IntroSliderViewPagerAdapter;
import com.jeeteshsurana.template.Utils.PrefManagerIntro;

import java.util.ArrayList;

import butterknife.BindView;
import com.jeeteshsurana.template.R;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import me.relex.circleindicator.CircleIndicator;

public class IntroScreen extends AppCompatActivity {
    private int[] mImageResources = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.image7,
            R.drawable.image8,
    };

    private ArrayList<Integer> ImagesArray;
    public AutoScrollViewPager viewPager;
    public CircleIndicator indicator;

    @BindView(R.id.Intro_Skip)
    public Button button_skip;
    private PrefManagerIntro prefManagerIntro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introscreen);

        prefManagerIntro = new PrefManagerIntro(this);
        if (!prefManagerIntro.isFirstTimeLaunch()) {
            startActivity(new Intent(IntroScreen.this, LoginAct.class));
            finish();
        }

        viewPager = (AutoScrollViewPager) findViewById(R.id.PD_viewpager);
        indicator = (CircleIndicator) findViewById(R.id.PD_indicator);
        init();
        setViewPager();
        ButterKnife.bind(this);

    }

    @OnClick({R.id.Intro_Skip})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Intro_Skip:
                prefManagerIntro.setFirstTimeLaunch(false);
                startActivity(new Intent(IntroScreen.this, LoginAct.class));
                IntroScreen.this.finish();
                break;
        }
    }

    private void init() {
        ImagesArray = new ArrayList<Integer>();
        for (int i = 0; i < mImageResources.length; i++)
            ImagesArray.add(mImageResources[i]);
    }

    private void setViewPager() {
        try {
            viewPager.setAdapter(new IntroSliderViewPagerAdapter(IntroScreen.this, ImagesArray));
            indicator.setViewPager(viewPager);
            viewPager.setInterval(3000);
            viewPager.startAutoScroll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
    }
}

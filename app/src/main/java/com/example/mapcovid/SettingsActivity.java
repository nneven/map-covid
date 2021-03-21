package com.example.mapcovid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsActivity extends AppCompatActivity {

    RelativeLayout relativeLayout;
    Button viewmore;
    ValueAnimator mAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        BottomNavigationView navView = findViewById(R.id.bottom_navigation);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        navView.setSelectedItemId(R.id.settings_activity);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.activity_maps:
                        startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.activity_media:
                        startActivity(new Intent(getApplicationContext(), MediaActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.activity_history:
                        startActivity(new Intent(getApplicationContext(), History.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.settings_activity:
                        return true;
                }
                return false;
            }
        });

        relativeLayout = (RelativeLayout) findViewById(R.id.expandable);

        viewmore = (Button) findViewById(R.id.viewmore);

        viewmore.setOnClickListener((View.OnClickListener) this);

        relativeLayout.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {

                    @Override
                    public boolean onPreDraw() {
                        relativeLayout.getViewTreeObserver().removeOnPreDrawListener(this);
                        relativeLayout.setVisibility(View.GONE);

                        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                        relativeLayout.measure(widthSpec, heightSpec);

                        mAnimator = slideAnimator(0, relativeLayout.getMeasuredHeight());
                        return true;
                    }
                });

    }

    private void expand() {

        relativeLayout.setVisibility(View.VISIBLE);
        mAnimator.start();
    }

    private void collapse() {
        int finalHeight = relativeLayout.getHeight();

        ValueAnimator mAnimator = slideAnimator(finalHeight, 0);

        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animator) {
                //Height=0, but it set visibility to GONE
                relativeLayout.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        mAnimator.start();
    }


    private ValueAnimator slideAnimator(int start, int end) {

        ValueAnimator animator = ValueAnimator.ofInt(start, end);


        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //Update Height
                int value = (Integer) valueAnimator.getAnimatedValue();

                ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
                layoutParams.height = value;
                relativeLayout.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.viewmore:

                if (relativeLayout.getVisibility() == View.GONE) {
                    expand();
                } else {
                    collapse();
                }

                break;

        }
    }
}
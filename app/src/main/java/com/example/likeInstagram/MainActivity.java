package com.example.likeInstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnDoubleTapListener, View.OnClickListener{


    ImageView post, post2;
    ImageButton btnLikes, btnLikes2;
    LottieAnimationView animationView;
    TextView totalLike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLikes = findViewById(R.id.btn_love);
        btnLikes2 = findViewById(R.id.btn_love2);
        post = findViewById(R.id.img_post);
        post2 = findViewById(R.id.img_post2);
        animationView= findViewById(R.id.lottie_like);
        totalLike  = findViewById(R.id.total_like);
        String total = totalLike.getText().toString();

        // hide navbar

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


        // implements double tap

        post.setOnTouchListener(new View.OnTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener(){

                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    animateLike();

                    if (btnLikes.getBackground().getConstantState() == getResources().getDrawable(R.drawable.ic_love).getConstantState()){
                        btnLikes.setBackgroundResource(R.drawable.icon_liked);
                        totalLike.setText(String.valueOf(Integer.parseInt(totalLike.getText().toString()) + 1));
                    }

                    return super.onDoubleTap(e);
                }

                @Override
                public boolean onDown(MotionEvent e) {
                    return true;
                }
            });




            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                 gestureDetector.onTouchEvent(motionEvent);
                return  true;
            }
        });

        btnLikes.setOnClickListener(view -> {
            if (btnLikes.getBackground().getConstantState() == getResources().getDrawable(R.drawable.icon_liked).getConstantState()) {
                totalLike.setText(String.valueOf(Integer.parseInt(totalLike.getText().toString()) - 1));
                btnLikes.setBackground(getResources().getDrawable(R.drawable.ic_love));
                Snackbar.make(view, "You unliked this post", Snackbar.LENGTH_SHORT).show();


            } else {
                animateLike();
                btnLikes.setBackground(getResources().getDrawable(R.drawable.icon_liked));
                totalLike.setText(String.valueOf(Integer.parseInt(totalLike.getText().toString()) + 1));
                Snackbar.make(view, "liked", Snackbar.LENGTH_SHORT).show();
            }
        });




        


    }


    @Override
    public void onClick(View view) {



    }
    private  void animateLike(){
        if (animationView.isAnimating()) {
            animationView.cancelAnimation();
        } else {
            animationView.playAnimation();
        }

        animationView.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animationView.setVisibility(View.GONE);
                animationView.cancelAnimation();
            }
        }, 1000);
    }


    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }
}
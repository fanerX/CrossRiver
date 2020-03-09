package com.example.crossriver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class AnimationActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        imageView=findViewById(R.id.move_image);

        TranslateAnimation translateAnimation_l= new TranslateAnimation(0, 2000,0,0);
        translateAnimation_l.setDuration(2000); //设置动画世界
        imageView.startAnimation(translateAnimation_l);



        RelativeLayout layoutsplash=(RelativeLayout)findViewById(R.id.activity_animation);//布局文件是相对布局
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f,1.0f);
        alphaAnimation.setDuration(1900);

        layoutsplash.startAnimation(alphaAnimation);
        //设置动画监听
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {	//当动画结束后就跳转到其他活动中
                Intent intent=new Intent(AnimationActivity.this,MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }
    @Override
    protected void onPause() {//当动画结束后，就把该动画活动销毁
        super.onPause();
        finish();
    }

}

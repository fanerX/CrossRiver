package my_fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.example.crossriver.R;

import java.util.List;

import androidx.fragment.app.Fragment;

import static java.lang.Thread.sleep;

public class FragmentRiver extends Fragment {
    private ImageView farmer_l,wolf_l,sheep_l,cabbage_l;
    private ImageView farmer_r,wolf_r,sheep_r,cabbage_r;
    private TranslateAnimation translateAnimation_l,translateAnimation_r;
    private List<Integer> path_list;
    private RelativeLayout relativeLayout;
    private boolean farmer,wolf,sheep,cabbag;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.farmer_crossing_river,container,false);
        farmer_l=view.findViewById(R.id.farmer_l);
        wolf_l=view.findViewById(R.id.wolf_l);
        sheep_l=view.findViewById(R.id.sheep_l);
        cabbage_l=view.findViewById(R.id.cabbage_l);

        farmer_r=view.findViewById(R.id.farmer_r);
        wolf_r=view.findViewById(R.id.wolf_r);
        sheep_r=view.findViewById(R.id.sheep_r);
        cabbage_r=view.findViewById(R.id.cabbage_r);

        relativeLayout=view.findViewById(R.id.relative_my);

        farmer=false;
        wolf=false;
        sheep=false;
        cabbag=false;

        return view;
    }


    public void moveToPath(List<Integer> list){

        farmer_l.clearAnimation();
        farmer_r.clearAnimation();
        farmer_r.setVisibility(View.INVISIBLE);
        farmer_l.setVisibility(View.VISIBLE);

        wolf_l.clearAnimation();
        wolf_r.clearAnimation();
        wolf_r.setVisibility(View.INVISIBLE);
        wolf_l.setVisibility(View.VISIBLE);

        sheep_l.clearAnimation();
        sheep_r.clearAnimation();
        sheep_r.setVisibility(View.INVISIBLE);
        sheep_l.setVisibility(View.VISIBLE);

        cabbage_l.clearAnimation();
        cabbage_r.clearAnimation();
        cabbage_r.setVisibility(View.INVISIBLE);
        cabbage_l.setVisibility(View.VISIBLE);

        path_list=list;
        new Thread(new Runnable() {
            @Override
            public void run() {
                int position,previous;
                previous=0;
                for(int i=1;i<path_list.size();i++){
                    position=path_list.get(i);
                    if((position&8)!=(previous&8)){
                        if(farmer_l.getVisibility()==View.VISIBLE){
                            farmer_l.startAnimation(translateAnimation_l);
                        }else {
                            farmer_r.startAnimation(translateAnimation_r);
                        }
                        farmer=true;
                    }
                    if((position&4)!=(previous&4)){
                        if(wolf_l.getVisibility()==View.VISIBLE){
                            wolf_l.startAnimation(translateAnimation_l);
                        }else {
                            wolf_r.startAnimation(translateAnimation_r);
                        }
                        wolf=true;
                    }
                    if((position&2)!=(previous&2)){
                        if(sheep_l.getVisibility()==View.VISIBLE){
                            sheep_l.startAnimation(translateAnimation_l);
                        }else {
                            sheep_r.startAnimation(translateAnimation_r);
                        }
                        sheep=true;
                    }
                    if((position&1)!=(previous&1)){
                        if(cabbage_l.getVisibility()==View.VISIBLE){
                            cabbage_l.startAnimation(translateAnimation_l);
                        }else {
                            cabbage_r.startAnimation(translateAnimation_r);
                        }
                        cabbag=true;
                    }
                    try {
                        sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    previous=position;
                }
            }
        }).start();

    }





    public void init(int width){
        Log.d("Fragment", "onCreateView: "+width);
        translateAnimation_l= new TranslateAnimation(0, width,0,0);
        translateAnimation_l.setDuration(4000); //设置动画世界
        translateAnimation_r= new TranslateAnimation(0, -width,0,0);
        translateAnimation_r.setDuration(4000); //设置动画世界
        translateAnimation_l.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(farmer){
                    farmer_l.clearAnimation();
                    farmer_l.setVisibility(View.INVISIBLE);
                    farmer_r.setVisibility(View.VISIBLE);
                    farmer=false;
                }
                if(wolf){
                    wolf_l.clearAnimation();
                    wolf_l.setVisibility(View.INVISIBLE);
                    wolf_r.setVisibility(View.VISIBLE);
                    wolf=false;
                }
                if(sheep){
                    sheep_l.clearAnimation();
                    sheep_l.setVisibility(View.INVISIBLE);
                    sheep_r.setVisibility(View.VISIBLE);
                    sheep=false;
                }
                if(cabbag){
                    cabbage_l.clearAnimation();
                    cabbage_l.setVisibility(View.INVISIBLE);
                    cabbage_r.setVisibility(View.VISIBLE);
                    cabbag=false;
                }
                //next

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        translateAnimation_r.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(farmer){
                    farmer_r.clearAnimation();
                    farmer_r.setVisibility(View.INVISIBLE);
                    farmer_l.setVisibility(View.VISIBLE);
                    farmer=false;
                }
                if(wolf){
                    wolf_r.clearAnimation();
                    wolf_r.setVisibility(View.INVISIBLE);
                    wolf_l.setVisibility(View.VISIBLE);
                    wolf=false;
                }
                if(sheep){
                    sheep_r.clearAnimation();
                    sheep_r.setVisibility(View.INVISIBLE);
                    sheep_l.setVisibility(View.VISIBLE);
                    sheep=false;
                }
                if(cabbag){
                    cabbage_r.clearAnimation();
                    cabbage_r.setVisibility(View.INVISIBLE);
                    cabbage_l.setVisibility(View.VISIBLE);
                    cabbag=false;
                }
                //next
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }




}

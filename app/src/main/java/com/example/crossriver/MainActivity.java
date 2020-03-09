package com.example.crossriver;

import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import my_class.FarmerCrossingRiver;
import my_fragment.FragmentRiver;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import static com.example.crossriver.R.id.fragment_l;
import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    private FarmerCrossingRiver farmerCrossingRiver;
    private List<Integer> list_path;
    private Button button,DFS_button;
    private FragmentRiver fragmentRiver;
    private TextView textView_path;
    private boolean flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        farmerCrossingRiver =new FarmerCrossingRiver();
        flag=true;

        button=findViewById(R.id.button_test);
        DFS_button=findViewById(R.id.DFS_button);
        textView_path=findViewById(R.id.path_text);
        fragmentRiver=new FragmentRiver();
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(fragment_l,fragmentRiver).commit();

        WindowManager wm1 = this.getWindowManager();
        int width1 = wm1.getDefaultDisplay().getWidth();

        fragmentRiver.init(width1-690);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag){
                    return;
                }
                flag=false;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        list_path=farmerCrossingRiver.BFS_route();
                        String string="BFS: "+list_path.get(0);
                        for(int i=1;i<list_path.size();i++){
                            string+="->"+list_path.get(i);
                        }
                        textView_path.setText(string);
                        fragmentRiver.moveToPath(list_path);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    sleep(35*1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                flag=true;
                            }
                        }).start();
                    }
                });

            }
        });
        DFS_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flag){
                    return;
                }
                flag=false;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        list_path=farmerCrossingRiver.DFS_route();
                        String string="DFS: "+list_path.get(0);
                        for(int i=1;i<list_path.size();i++){
                            string+="->"+list_path.get(i);
                        }
                        textView_path.setText(string);
                        fragmentRiver.moveToPath(list_path);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    sleep(35*1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                flag=true;
                            }
                        }).start();
                    }
                });
            }
        });


    }
}

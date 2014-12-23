package orbs_project.com.orbsproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


/**
 * Created by Omie Kue on 12/6/2014.
 */
public class Splash extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().hide();
        setContentView(R.layout.activity_main);

        final ImageView logo = (ImageView)findViewById(R.id.logo_splash);
        final Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.anime_fade);
        logo.startAnimation(fadeIn);

        //**Start thread
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent gameMenu = new Intent ("orbs_project.com.orbsproject.MAINACTIVITY");
                    startActivity(gameMenu);
                }//finally
            }//run+-
        };//Thread
        timer.start();

    }//onCreate

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }//onPause
}
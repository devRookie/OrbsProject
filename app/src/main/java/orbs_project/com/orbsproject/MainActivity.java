package orbs_project.com.orbsproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {

    private SharedPreferences savedOptions;
    private OrbView orbView;
    MediaPlayer dst1, dst2, dst3;
    private String musicCheck = "";
    Runnable musicRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orbView = new OrbView(this);
        //Need this to set default options or else going back on null color gives error
        PreferenceManager.setDefaultValues(this, R.xml.prefs_layout, false);
        setContentView(orbView);

        dst1 = MediaPlayer.create(this, R.raw.dst_sheep_field);
        dst2 = MediaPlayer.create(this, R.raw.dst_mellow_explanation);
        dst3 = MediaPlayer.create(this, R.raw.dst_silk_leaves);

        //Policy Agreement
        new PolicyAgreement(this).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings){
            if (dst1.isPlaying()){
                dst1.stop();
                dst1.release();
            }
            if (dst2.isPlaying()){
                dst2.stop();
                dst2.release();
            }
            if (dst3.isPlaying()){
                dst3.stop();
                dst3.release();
            }
            finish();
            startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
            return true;
        }

        if (id == R.id.action_about){
            startActivity(new Intent(getApplicationContext(), About.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        //Setting these variables so that it can be passed in from MainActivity to the Orb class
        //to be made or created.
        String orbColor, bgColor, minSize, maxSize, minSpeed, maxSpeed, orbCount;
        this.savedOptions = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        orbColor = this.savedOptions.getString(getString(R.string.orb_color_key), "Random");
        bgColor = this.savedOptions.getString(getString(R.string.bg_color_key), "Random");
        minSize = this.savedOptions.getString(getString(R.string.orb_min_size_key), "5");
        maxSize = this.savedOptions.getString(getString(R.string.orb_max_size_key), "10");
        minSpeed = this.savedOptions.getString(getString(R.string.orb_min_speed_key), "5");
        maxSpeed = this.savedOptions.getString(getString(R.string.orb_max_speed_key), "10");
        orbCount = this.savedOptions.getString(getString(R.string.orb_count_key), "20");
        this.musicCheck = this.savedOptions.getString(getString(R.string.music_key), "DST - Sheep Field");
        orbView.orbPreferences(minSize, maxSize, minSpeed, maxSpeed, orbCount, orbColor, bgColor);

        if (this.musicCheck.equals("DST - Sheep Field")) {
            dst1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.isLooping();
                    mp.start();
                }
            });
            return;
        }
        if (this.musicCheck.equals("DST - Mellow Explanation")) {
            dst2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.isLooping();
                    mp.start();
                }
            });
            return;
        }
        if (this.musicCheck.equals("DST - Space Cloud")) {
            dst3.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.isLooping();
                    mp.start();
                }
            });
            return;
        }
        if (this.musicCheck.equals("Off")) {
            return;
        }
    }

    public void onBackPressed() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this, AlertDialog.THEME_HOLO_DARK); //Create dialog box object
        dialog.setTitle("Orbs");
        dialog.setIcon(R.drawable.ic_launcher);
        dialog.setMessage("Do you wish to exit?");
        dialog.setCancelable(false);
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }
        });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}

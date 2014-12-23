package orbs_project.com.orbsproject;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Omie on 12/12/2014.
 */
public class SettingsPreference extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

    private SharedPreferences savedOptions;

    private Map<String, String> colorOptions;
    private Map<String, String> musicOptions;

    private ListPreference orbColor;

    private EditTextPreference orbMinSize;
    private EditTextPreference orbMaxSize;

    private EditTextPreference orbMinSpeed;
    private EditTextPreference orbMaxSpeed;

    private ListPreference canvasBackground;

    private EditTextPreference orbCount;

    private ListPreference music;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //--Setting the preference layout
        addPreferencesFromResource(R.xml.prefs_layout);

        this.orbCount = (EditTextPreference)findPreference(getString(R.string.orb_count_key));
        this.orbCount.setOnPreferenceChangeListener(this);

        this.orbColor = (ListPreference)findPreference(getString(R.string.orb_color_key));
        this.orbColor.setOnPreferenceChangeListener(this);

        this.canvasBackground = (ListPreference)findPreference(getString(R.string.bg_color_key));
        this.canvasBackground.setOnPreferenceChangeListener(this);

        this.orbMinSize = (EditTextPreference)findPreference(getString(R.string.orb_min_size_key));
        this.orbMinSize.setOnPreferenceChangeListener(this);
        this.orbMaxSize = (EditTextPreference)findPreference(getString(R.string.orb_max_size_key));
        this.orbMaxSize.setOnPreferenceChangeListener(this);

        this.orbMinSpeed = (EditTextPreference)findPreference(getString(R.string.orb_min_speed_key));
        this.orbMinSpeed.setOnPreferenceChangeListener(this);
        this.orbMaxSpeed = (EditTextPreference)findPreference(getString(R.string.orb_max_speed_key));
        this.orbMaxSpeed.setOnPreferenceChangeListener(this);

        this.music = (ListPreference)findPreference(getString(R.string.music_key));
        this.music.setOnPreferenceChangeListener(this);
        //Hash map so that the color is responding to the array in the array of color values
        // = = Link for Color Constants = = //
        //http://developer.android.com/reference/android/graphics/Color.html
        this.colorOptions = new HashMap();
        this.colorOptions.put("-65536", "Red");
        this.colorOptions.put("-16711936", "Green");
        this.colorOptions.put("-16776961", "Blue");
        this.colorOptions.put("-16777216", "Black");
        this.colorOptions.put("-1", "White");
        this.colorOptions.put("-65281", "Pink"); //Magenta
        this.colorOptions.put("-256", "Yellow");
        this.colorOptions.put("Random", "Random");

        // == Music == //
        this.musicOptions = new HashMap();
        this.musicOptions.put("DST - Sheep Field", "DST - Sheep Field");
        this.musicOptions.put("DST - Mellow Explanation", "DST - Mellow Explanation");
        this.musicOptions.put("DST - Space Cloud", "DST - Space Cloud");
        this.musicOptions.put("Off", "Off");
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {

        // == Color == //
        if (preference.equals(this.orbColor)) {
            this.orbColor.setSummary(this.colorOptions.get(newValue.toString()));
            return true;
        }
        if (preference.equals(this.canvasBackground)){
            this.canvasBackground.setSummary(this.colorOptions.get(newValue.toString()));
            return true;
        }
        // == Radius == //
        if (preference.equals(this.orbMinSize)){
            int minCheck = Integer.parseInt(newValue.toString());
            if (minCheck > 20){
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Minimum size cannot exceed 20");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                return false;
            }
            this.orbMinSize.setSummary(newValue.toString());
            return true;
        }
        if (preference.equals(this.orbMaxSize)){
            int maxCheck = Integer.parseInt(newValue.toString());
            if (maxCheck > 30){
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Maximum size cannot exceed 30");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                return false;
                }
            this.orbMaxSize.setSummary(newValue.toString());
            return true;
        }

        // == Speed == //
        if (preference.equals(this.orbMinSpeed)){
            int minCheck = Integer.parseInt(newValue.toString());
            if (minCheck > 10){
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Minimum speed cannot exceed 10");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                return false;
            }
            this.orbMinSpeed.setSummary(newValue.toString());
            return true;
        }
        if (preference.equals(this.orbMaxSpeed)){
            int maxCheck = Integer.parseInt(newValue.toString());
            if (maxCheck > 25){
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Maximum speed cannot exceed 25");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                return false;
            }
            this.orbMaxSpeed.setSummary(newValue.toString());
            return true;
        }
        // == Orb Count == //
        if (preference.equals(this.orbCount)){
            int countCheck = Integer.parseInt(newValue.toString());
            if (countCheck > 200){
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Value cannot exceed 200");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                return false;
            }
            this.orbCount.setSummary(newValue.toString());
            return true;
        }

        // == Music == //
        if (preference.equals(this.music)){
            this.music.setSummary(this.musicOptions.get(newValue.toString()));
            return true;
        }
        return true;
    }

    @Override
    public void onResume(){
        super.onResume();
        //--Fill the preference / Resave
        String color, bgColor, minSize, maxSize, minSpeed, maxSpeed, orbCount, musicStr;
        this.savedOptions = PreferenceManager.getDefaultSharedPreferences(getActivity());

        color = this.savedOptions.getString(getString(R.string.orb_color_key), "Random");
        this.orbColor.setSummary(this.colorOptions.get(color));

        bgColor = this.savedOptions.getString(getString(R.string.bg_color_key), "Random");
        this.canvasBackground.setSummary(this.colorOptions.get(bgColor));

        minSize = this.savedOptions.getString(getString(R.string.orb_min_size_key), "5");
        this.orbMinSize.setSummary(minSize);
        maxSize = this.savedOptions.getString(getString(R.string.orb_max_size_key), "10");
        this.orbMaxSize.setSummary(maxSize);

        minSpeed = this.savedOptions.getString(getString(R.string.orb_min_speed_key), "5");
        this.orbMinSpeed.setSummary(minSpeed);
        maxSpeed = this.savedOptions.getString(getString(R.string.orb_max_speed_key), "10");
        this.orbMaxSpeed.setSummary(maxSpeed);

        orbCount = this.savedOptions.getString(getString(R.string.orb_count_key), "20");
        this.orbCount.setSummary(orbCount);

        musicStr = this.savedOptions.getString(getString(R.string.music_key), "DST - Sheep Field");
        this.music.setSummary(musicStr);

    }

}

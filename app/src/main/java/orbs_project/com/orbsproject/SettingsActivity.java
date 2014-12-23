package orbs_project.com.orbsproject;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

/**
 * Created by Omie on 12/12/2014.
 */
public class SettingsActivity extends PreferenceActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getActionBar().hide();
        FragmentTransaction fragTransaction = getFragmentManager().beginTransaction();
        SettingsPreference prefs = new SettingsPreference();
        fragTransaction.replace(android.R.id.content, prefs); //Replace content of this activity with prefs layout
        fragTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent updateIntent = new Intent(this, MainActivity.class);
        startActivity(updateIntent);
    }
}
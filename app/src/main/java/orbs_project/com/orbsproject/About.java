package orbs_project.com.orbsproject;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Omie on 12/18/2014.
 */
public class About extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().hide();
        setContentView(R.layout.activity_about);
    }
}

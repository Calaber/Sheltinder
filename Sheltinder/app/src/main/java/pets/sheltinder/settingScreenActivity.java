package pets.sheltinder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class settingScreenActivity extends Activity {
    private final String TAG = getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingscreenlayout);
        addListenersOnButtons();
        Log.d(TAG, "Loaded Class");
    }

    public void addListenersOnButtons(){

    }
}

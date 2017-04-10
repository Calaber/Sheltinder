package pets.sheltinder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SettingsScreen extends Activity implements View.OnClickListener{
    private final String TAG = getClass().getSimpleName();

    SharedPreferences sharedPreferences;

    boolean all, dog, cat, other;

    Switch sAll, sDog, sCat, sOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_settings_screen);

        sharedPreferences = getSharedPreferences("settings",Context.MODE_PRIVATE);

        all = true; dog = false; cat = false; other = false;

        setUpSwitches();
        setUpSettings();

        findViewById(R.id.bSave).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bSave) { saveSettings(); }
    }

    private  void setUpSwitches() {
        sAll = (Switch) findViewById(R.id.all);
        sDog = (Switch) findViewById(R.id.dog);
        sCat = (Switch) findViewById(R.id.cat);
        sOther = (Switch) findViewById(R.id.other);

        setupAllSwitch();
        setupDogSwitch();
        setupCatSwitch();
        setupOtherSwitch();
    }

    private void setUpSettings() {

        all = sharedPreferences.getBoolean("wantAll", all);
        dog = sharedPreferences.getBoolean("wantDog", dog);
        cat = sharedPreferences.getBoolean("wantCat", cat);
        other = sharedPreferences.getBoolean("wantOther", other);

        saveSettings();

        sAll.setChecked(all);
        sDog.setChecked(dog);
        sCat.setChecked(cat);
        sOther.setChecked(other);
    }

    public void saveSettings() {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean("wantAll", all);
        editor.putBoolean("wantDog", dog);
        editor.putBoolean("wantCat", cat);
        editor.putBoolean("wantOther", other);

        editor.apply();
    }

    private void setupAllSwitch() {
        sAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    all = true; sAll.setChecked(true);
                    dog = false; sDog.setChecked(false);
                    cat = false; sCat.setChecked(false);
                    other = false; sOther.setChecked(false);
                } else { all = false; }
            }
        });

    }

    private void setupDogSwitch() {
        sDog.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    all = false; sAll.setChecked(false);
                    dog = true;
                } else { dog = false; }
            }
        });

    }

    private void setupCatSwitch() {
        sCat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    all = false; sAll.setChecked(false);
                    cat = true;
                } else { cat = false; }
            }
        });


    }

    private void setupOtherSwitch() {
        sOther.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    all = false; sAll.setChecked(false);
                    other = true;
                } else { other = false; }
            }
        });

    }

    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }
}

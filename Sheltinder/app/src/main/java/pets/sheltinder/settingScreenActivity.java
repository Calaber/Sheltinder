package pets.sheltinder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

public class settingScreenActivity extends Activity {
    private final String TAG = getClass().getSimpleName();
    SharedPreferences sharedPref = settingScreenActivity.this.getPreferences(Context.MODE_PRIVATE);
    boolean dog, cat, other =false;
    boolean all=true;
    int radius=15;
    Switch dogSwi, catSwi, otherSwi,allSwi;
    Button save;
    Spinner range;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingscreenlayout);
        setUpSwitches();
        addListenersOnSaveButton();
        setUpSettings();
        Log.d(TAG, "onCreate");
    }

    public void addListenersOnSaveButton(){
        save = (Button) findViewById(R.id.browse);
        save.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                saveSettings();
            }
        });
    }
    private  void setUpSwitches(){
        allSwi = (Switch) findViewById(R.id.all);
        dogSwi = (Switch) findViewById(R.id.dog);
        catSwi = (Switch) findViewById(R.id.cat);
        otherSwi = (Switch) findViewById(R.id.other);
        range = (Spinner) findViewById(R.id.range);

        setupAllSwi();
        setupCatSwi();
        setupDogSwi();
        setupOtherSwi();
        setupRange();

    }

    private void setUpSettings() {

        dog = sharedPref.getBoolean("wantDog", dog);
        cat = sharedPref.getBoolean("wantCat", cat);
        other = sharedPref.getBoolean("wantOther", other);
        all = sharedPref.getBoolean("wantAll", all);
        radius = sharedPref.getInt("radius", radius);

        saveSettings();

        allSwi.setChecked(all);
        otherSwi.setChecked(other);
        catSwi.setChecked(cat);
        dogSwi.setChecked(dog);

        switch (radius){
            case 15:
                range.setSelection(0);
                break;
            case 30:
                range.setSelection(1);
                break;
            case 45:
                range.setSelection(2);
                break;
            case 60:
                range.setSelection(3);
                break;

        }
    }

    //Saves all the settings
    private void saveSettings(){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("wantDog", dog);
        editor.putBoolean("wantCat", cat);
        editor.putBoolean("wantOther", other);
        editor.putBoolean("wantAll", all);
        editor.putInt("radius",radius);
        editor.commit();
    }

    private void setupAllSwi(){

        allSwi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    all=true;
                    dog=false;
                    dogSwi.setChecked(dog);
                    cat=false;
                    catSwi.setChecked(cat);
                    other=false;
                    otherSwi.setChecked(other);
                }
                else {
                    all=false;
                }
            }
        });

    }

    private void setupDogSwi(){

        dogSwi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    all=false;
                    allSwi.setChecked(all);
                    dog=true;
                }
                else {
                    dog=false;
                }
            }
        });

    }
    private void setupCatSwi(){

        catSwi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    all=false;
                    allSwi.setChecked(all);
                    cat=true;
                }
                else {
                    cat=false;
                }
            }
        });


    }
    private void setupOtherSwi(){

        allSwi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    all=false;
                    allSwi.setChecked(all);
                    other=false;

                }
                else {
                    other=false;
                }
            }
        });

    }

    private  void setupRange(){
        range.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                switch (position){
                    case 0:
                        radius=15;
                        break;
                    case 1:
                        radius=30;
                        break;
                    case 2:
                        radius=45;
                        break;
                    case 3:
                        radius=60;
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                range.setSelection(0);
            }

        });
    }
}

package pets.sheltinder;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

public class settingScreenActivity extends Activity {
    private final String TAG = getClass().getSimpleName();
    SharedPreferences sharedPref;
    boolean dog, cat, other;
    boolean all;
    int radius=15;
    Switch dogSwi, catSwi, otherSwi,allSwi;
    Button save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingscreenlayout);
        sharedPref= getSharedPreferences("settings",Context.MODE_PRIVATE);
        dog=false;
        cat=false;
        other=false;
        all=true;
        setUpSwitches();
        setUpSettings();
        addListenersOnSaveButton();
        Log.d(TAG, "onCreate");
    }

    public void addListenersOnSaveButton(){
        save = (Button) findViewById(R.id.save);
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

        setupAllSwi();
        setupCatSwi();
        setupDogSwi();
        setupOtherSwi();

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
    }

    //Saves all the settings
    public void saveSettings(){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("wantAll", all);
        editor.putBoolean("wantDog", dog);
        editor.putBoolean("wantCat", cat);
        editor.putBoolean("wantOther", other);

        editor.putInt("radius",radius);
        editor.commit();
    }

    private void setupAllSwi(){

        allSwi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dog=false;
                    dogSwi.setChecked(dog);
                    cat=false;
                    catSwi.setChecked(cat);
                    other=false;
                    otherSwi.setChecked(other);
                    all=true;
                    allSwi.setChecked(all);
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

        otherSwi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    all=false;
                    allSwi.setChecked(all);
                    other=true;

                }
                else {
                    other=false;
                }
            }
        });

    }
}

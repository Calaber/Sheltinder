package pets.sheltinder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class mainScreenActivity extends Activity {
    private final String TAG = getClass().getSimpleName();

    Button bPetRegister;
    Button browseB;
    Button settings;
    Button logOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainscreenlayout);
        addListenersOnButtons();
        Log.d(TAG, "onCreate");

    }

    public void addListenersOnButtons(){
        addListenerOnRegisterButton();
        addListenerOnBrowseButton();
        addListenerOnSettingsButton();
        addListenerOnLogOutButton();
    }

    public void addListenerOnRegisterButton() {
        final Context context = this;
        bPetRegister = (Button) findViewById(R.id.bPetRegister);
        bPetRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, PetRegisterScreen.class);
                startActivity(intent);
            }
        });

    }

    public void addListenerOnBrowseButton(){
        final Context context = this;
        browseB = (Button) findViewById(R.id.browse);
        browseB.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, animalScreenActivity.class);
                startActivity(intent);
            }
        });

    }

    public void addListenerOnSettingsButton(){
        final Context context = this;
        settings = (Button) findViewById(R.id.settings);
        settings.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, settingScreenActivity.class);
                startActivity(intent);
            }
        });

    }

    public void addListenerOnLogOutButton(){
        final Context context = this;
        logOut = (Button) findViewById(R.id.logout);
        logOut.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, LoginScreen.class);
                startActivity(intent);
            }
        });
    }

    @Override
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
        Intent intent = new Intent(this, mainScreenActivity.class);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}

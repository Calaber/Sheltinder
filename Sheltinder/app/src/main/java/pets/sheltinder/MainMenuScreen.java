package pets.sheltinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

public class MainMenuScreen extends Activity implements View.OnClickListener{
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_main_menu_screen);

        findViewById(R.id.bRegisterPet).setOnClickListener(this);
        findViewById(R.id.bBrowsePets).setOnClickListener(this);
        findViewById(R.id.bSettings).setOnClickListener(this);
        findViewById(R.id.bLogout).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bPetRegister:
                startActivity(new Intent(MainMenuScreen.this, PetRegisterScreen.class)); break;
            case R.id.bBrowsePets:
                startActivity(new Intent(MainMenuScreen.this, PetImageScreen.class)); break;
            case R.id.bSettings:
                startActivity(new Intent(MainMenuScreen.this, SettingsScreen.class)); break;
            case R.id.bLogout:
                startActivity(new Intent(MainMenuScreen.this, LoginScreen.class)); break;
        }
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
        Intent intent = new Intent(this, MainMenuScreen.class);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}

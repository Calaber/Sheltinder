package pets.sheltinder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PetInformationScreen extends Activity implements View.OnClickListener {
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_pet_information_screen);

        TextView tvPetName = (TextView) findViewById(R.id.tvPetName);
        TextView tvPetInfo = (TextView) findViewById(R.id.tvPetInfo);

        TextView tvPetLocation = (TextView) findViewById(R.id.tvPetLocation);

        SharedPreferences settings = getSharedPreferences("CurrentPet", Context.MODE_PRIVATE);

        tvPetName.setText(settings.getString("pet_name", "Pet Name"));
        tvPetInfo.setText("Pet Type: " + settings.getString("pet_Type", "N/A")
                          + "\n" + settings.getString("pet_description", "N/A"));

        tvPetLocation.setText("Shelter Location: " + settings.getString("pet_location", "N/A"));

        Button bDirections = (Button) findViewById(R.id.bMaps);

        bDirections.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(PetInformationScreen.this, MapsScreen.class));
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

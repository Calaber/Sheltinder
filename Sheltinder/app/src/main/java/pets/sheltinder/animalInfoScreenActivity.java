package pets.sheltinder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class animalInfoScreenActivity extends Activity{
    private final String TAG = getClass().getSimpleName();

    Button bDirections;
    TextView animalName;
    TextView animalInfo;
    TextView animalLoc;
    AnimalInfo animInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animalinfoscreenlayout);
        addListenersOnButtons();
        animalName=(TextView) findViewById(R.id.animalinfo);
        animalInfo=(TextView) findViewById(R.id.ainfo);
        animalLoc=(TextView) findViewById(R.id.sinfo);
        Log.d(TAG, "onCreate");
        SharedPreferences settings = getSharedPreferences("CurrentPet",
                Context.MODE_PRIVATE);
        animInfo = new AnimalInfo(settings.getString("pet_name", "Animal Name"),
                settings.getString("pet_description","N/A"),
                Converter.retTypeFromString(settings.getString("pet_Type","N/A"))
                ,new ShelterInfo(settings.getString("pet_location","N/A")));
        animalName.setText(animInfo.getAnimalName());
        animalInfo.setText(Converter.formatPetTypeDescription(
                Converter.retStringFromType(animInfo.getAnimType())
                ,animInfo.getAnimalInfo()));
        animalLoc.setText(Converter.formatLocation(animInfo.getShelter().getLocation()));
    }

    public void addListenersOnButtons(){
        addListenerOnDirectionsButton();
    }

    public void addListenerOnDirectionsButton(){
        final Context context = this;
        bDirections = (Button) findViewById(R.id.bMaps);
        bDirections.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}

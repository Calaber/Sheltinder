package pets.sheltinder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
/**
 * Created by alber_000 on 2/22/2017.
 */

public class animalInfoScreenActivity extends Activity{
    private final String TAG = getClass().getSimpleName();

    Button bDirections;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animalinfoscreenlayout);
        addListenersOnButtons();
        Log.d(TAG, "onCreate");
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

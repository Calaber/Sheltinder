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

public class animalScreenActivity extends Activity {
    private final String TAG = getClass().getSimpleName();

    Button infoB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animalscreenlayout);
        addListenersOnButtons();
        Log.d(TAG, "Loaded Class");
    }

    public void addListenersOnButtons(){
        addListenerOnInfoButton();
    }

    public void addListenerOnInfoButton(){
        final Context context = this;
        infoB = (Button) findViewById(R.id.info);
        infoB.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, animalInfoScreenActivity.class);
                startActivity(intent);
            }
        });

    }
}

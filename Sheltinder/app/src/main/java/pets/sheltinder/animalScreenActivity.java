package pets.sheltinder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

/**
 * Created by alber_000 on 2/22/2017.
 */

public class animalScreenActivity extends Activity {
    private final String TAG = getClass().getSimpleName();

    Button infoB;
    ImageButton nextAnimB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animalscreenlayout);
        addListenersOnButtons();
        Log.d(TAG, "onCreate");
    }

    public void addListenersOnButtons(){
        addListenerOnInfoButton();
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

    public void addListenerOnNextAnimalButton(){
        final Context context =this;
        nextAnimB = (ImageButton) findViewById(R.id.nextAnimalButton);
        nextAnimB.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, animalScreenActivity.class);
                startActivity(intent);
            }
        });
    }
}

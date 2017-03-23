package pets.sheltinder;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

/**
 * Created by alber_000 on 3/22/2017.
 */

public class ShelterInfoInputActivity extends Activity{

    EditText shelterName;
    EditText shelterLoc;
    EditText shelterDescription;
    Button Accept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shelterinfoinputscreen);
        addEditTexts();
        addListenerOnAcceptButton();
    }

    public void addEditTexts(){
        shelterName = (EditText) findViewById(R.id.shelterName);
        shelterLoc = (EditText) findViewById(R.id.shelterLocation);
        shelterDescription = (EditText) findViewById(R.id.shelterDescription);
    }

    public void addListenerOnAcceptButton(){
        final Context context = this;
        Accept = (Button) findViewById(R.id.browse);
        Accept.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, mainScreenActivity.class);
                startActivity(intent);
            }
        });

    }
}

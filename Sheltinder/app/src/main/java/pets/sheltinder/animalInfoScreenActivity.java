package pets.sheltinder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
/**
 * Created by alber_000 on 2/22/2017.
 */

public class animalInfoScreenActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animalinfoscreenlayout);
        addListenersOnButtons();
    }

    public void addListenersOnButtons(){

    }
}

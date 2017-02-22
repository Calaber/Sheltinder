package pets.sheltinder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class mainScreenActivity extends Activity {

    Button browseB;
    Button fav;
    Button settings;
    Button logOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainscreenlayout);
        addListenersOnButtons();
    }

    public void addListenersOnButtons(){
        addListenerOnBrowseButton();
        addListenerOnFavoritesButton();
        addListenerOnSettingsButton();
        addListenerOnLogOutButton();
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

    public void addListenerOnFavoritesButton(){
        final Context context = this;
        fav = (Button) findViewById(R.id.favorites);
        fav.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, favoritesScreenActivity.class);
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
                Intent intent = new Intent(context, Login.class);
                startActivity(intent);
            }
        });

    }
}

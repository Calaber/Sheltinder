package pets.sheltinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
//import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //    EditText etUsername;
    Button bLogout;

    //UserLocalStore userLocalStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        etUsername = (EditText) findViewById(R.id.etUsername);

        bLogout = (Button) findViewById(R.id.bLogout);

        bLogout.setOnClickListener(this);

        //userLocalStore = new UserLocalStore(this);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        if (authenticate() == true) {
//            displayUserDetails();
//        }
//    }
//
//    private boolean authenticate() {
//        return userLocalStore.getUserLoggedIn();
//    }
//
//    private void displayUserDetails () {
//        User user = userLocalStore.getLoggedInUser();
//
//        etUsername.setText(user.username);
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bLogout:

//                userLocalStore.clearUserData();
//                userLocalStore.setUserLoggedIn(false);

                startActivity(new Intent(this, Login.class));

                break;
        }
    }
}

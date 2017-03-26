package pets.sheltinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.AlertDialog;
import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

// gRxE7K8YMhGRwqQc
// Ad8YkaVzA92bRCdZ

public class UserRegisterScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register_screen);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);

        final CheckBox cbShelter = (CheckBox) findViewById(R.id.cbShelter);

        final EditText etShelterName = (EditText) findViewById(R.id.etShelterName);
        final EditText etShelterLocation = (EditText) findViewById(R.id.etShelterLocation);
        final EditText etShelterDescription = (EditText) findViewById(R.id.etShelterDescription);

        final Button bUserRegister = (Button) findViewById(R.id.bUserRegister);

        final LinearLayout llShelterInfo = (LinearLayout) findViewById(R.id.llShelterInfo);

        cbShelter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    llShelterInfo.setVisibility(View.VISIBLE);
                } else {
                    llShelterInfo.setVisibility(View.GONE);
                }
            }
        });


        bUserRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                int shelter = 0;
                if (cbShelter.isChecked()) {
                    shelter = 1;
                }

                final String shelterName = etShelterName.getText().toString();
                final String shelterLocation = etShelterLocation.getText().toString();
                final String shelterDescription = etShelterDescription.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);

                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                Intent intent = new Intent(UserRegisterScreen.this, LoginScreen.class);
                                UserRegisterScreen.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(UserRegisterScreen.this);
                                builder.setMessage("Shelter Register Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                UserRegisterRequest userRegisterRequest = new UserRegisterRequest(username, password, shelter, shelterName, shelterLocation, shelterDescription, responseListener);
                RequestQueue queue = Volley.newRequestQueue(UserRegisterScreen.this);
                queue.add(userRegisterRequest);
            }
        });

    }
}

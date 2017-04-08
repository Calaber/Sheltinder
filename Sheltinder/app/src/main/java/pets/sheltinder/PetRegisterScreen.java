package pets.sheltinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.AlertDialog;
import android.content.Intent;

import android.net.Uri;
import android.provider.MediaStore;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import android.widget.AdapterView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class PetRegisterScreen extends AppCompatActivity implements View.OnClickListener{

    private static final int RESULT_LOAD_IMAGE = 1;

    private static int getPetTypeMethodInt = 0;

    EditText etPetName, etPetLocation, etPetDescription;

    Spinner sPetType;

    ImageView ivPetImage;

    Button bSelectImage, bUploadImage, bPetRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_register_screen);

        etPetName = (EditText) findViewById(R.id.etPetName);
        etPetLocation = (EditText) findViewById(R.id.etPetLocation);
        etPetDescription = (EditText) findViewById(R.id.etPetDescription);

        sPetType = (Spinner) findViewById(R.id.sPetType);

        ivPetImage = (ImageView) findViewById(R.id.ivPetImage);

        bSelectImage = (Button) findViewById(R.id.bSelectImage);
        bUploadImage = (Button) findViewById(R.id.bUploadImage);

        bPetRegister = (Button) findViewById(R.id.bPetRegister);

        bSelectImage.setOnClickListener(this);
        bUploadImage.setOnClickListener(this);

        bPetRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bSelectImage:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                break;
            case R.id.bUploadImage:

                break;
            case R.id.bPetRegister:

                final String petName = etPetName.getText().toString();
                final String petLocation = etPetLocation.getText().toString();
                final String petDescription = etPetDescription.getText().toString();

                final int petType = sPetType.getSelectedItemPosition();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);

                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                Intent intent = new Intent(PetRegisterScreen.this, mainScreenActivity.class);
                                PetRegisterScreen.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(PetRegisterScreen.this);
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

                PetRegisterRequest petRegisterRequest = new PetRegisterRequest(petName, petLocation, petDescription, petType, responseListener);
                RequestQueue queue = Volley.newRequestQueue(PetRegisterScreen.this);
                queue.add(petRegisterRequest);

                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();

            ivPetImage.setImageURI(selectedImage);
        }
    }

    public int getPetType(Spinner sPetType) {

        sPetType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                getPetTypeMethodInt = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                getPetTypeMethodInt = 0;
            }
        });

        return getPetTypeMethodInt;
    }
}

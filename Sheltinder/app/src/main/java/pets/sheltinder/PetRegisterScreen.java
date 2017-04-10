package pets.sheltinder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class PetRegisterScreen extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = getClass().getSimpleName();

    public static final String UPLOAD_URL = "http://cse.ohio-state.edu/~re.9/registerPet.php";

    private EditText etPetName, etPetLocation, etPetDescription;

    private ImageView ivPetImage;

    private Button bSelectImage, bUploadImage, bPetRegister;

    private static final int PICK_IMAGE_REQUEST = 1;

    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_pet_register_screen);

        etPetName = (EditText) findViewById(R.id.etPetName);
        etPetLocation = (EditText) findViewById(R.id.etPetLocation);
        etPetDescription = (EditText) findViewById(R.id.etPetDescription);

        ivPetImage = (ImageView) findViewById(R.id.ivPetImage);

        bSelectImage = (Button) findViewById(R.id.bSelectImage);
        bUploadImage = (Button) findViewById(R.id.bUploadImage);
        bPetRegister = (Button) findViewById(R.id.bPetRegister);

        bSelectImage.setOnClickListener(this);
        bUploadImage.setOnClickListener(this);
        bPetRegister.setOnClickListener(this);
    }

    private void showFileChooser() {
        Intent intent = new Intent(); intent.setType("image/*"); intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                ivPetImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    private void uploadImage() {
        final ProgressDialog loading = ProgressDialog.show(this,"Uploading...","Please wait...",false,false);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        loading.dismiss();
                        Toast.makeText(PetRegisterScreen.this, s , Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        loading.dismiss();
                        Toast.makeText(PetRegisterScreen.this, volleyError.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String pet_image = getStringImage(bitmap);

                String pet_name = etPetName.getText().toString().trim();
                String pet_location = etPetLocation.getText().toString().trim();
                String pet_description = etPetDescription.getText().toString().trim();

                Map<String, String> params = new Hashtable<>();

                params.put("pet_name", pet_name);
                params.put("pet_location", pet_location);
                params.put("pet_description", pet_description);

                params.put("pet_type", 0 + "");

                params.put("pet_image", pet_image);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if (v == bSelectImage) {
            showFileChooser();

            ivPetImage.setVisibility(View.VISIBLE);

            bSelectImage.setVisibility(View.GONE);
            bUploadImage.setVisibility(View.VISIBLE);
        }

        if (v == bUploadImage) {
            uploadImage();

            bUploadImage.setVisibility(View.GONE);
            bPetRegister.setVisibility(View.VISIBLE);
        }

        if (v == bPetRegister) {
            startActivity(new Intent(PetRegisterScreen.this, MainMenuScreen.class));

            ivPetImage.setVisibility(View.GONE);

            bSelectImage.setVisibility(View.VISIBLE);
            bPetRegister.setVisibility(View.GONE);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }
}

package pets.sheltinder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PetImageScreen extends Activity implements View.OnClickListener{
    private final String TAG = getClass().getSimpleName();

    private static final String DOWNLOAD_URL = "http://cse.ohio-state.edu/~re.9/getPetInfo.php";

    private static String JSON = "JSON";

    private static final String TAG_RESULTS = "results";

    private static final String TAG_ID = "pet_id";

    private static final String TAG_NAME = "pet_name";
    private static final String TAG_LOCATION = "pet_location";
    private static final String TAG_DESCRIPTION = "pet_description";

    private static final String TAG_TYPE = "pet_type";

    NetworkImageView nImageView; ImageLoader imageLoader;

    JSONArray pets = null; int currentPetIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_pet_image_screen);

        SharedPreferences currentPetInfo = getSharedPreferences("currentPetInfo", Context.MODE_PRIVATE);
        currentPetIndex = currentPetInfo.getInt("currentPetIndex", currentPetIndex);

        nImageView = (NetworkImageView) findViewById(R.id.nImageView);

        getJSON(DOWNLOAD_URL); extractJSON(); setCurrentPet();

        Button bNextPet = (Button) findViewById(R.id.bNextPet);
        Button bPetInformation = (Button) findViewById(R.id.bPetInformation);

        bNextPet.setOnClickListener(this);
        bPetInformation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bNextPet:
                currentPetIndex++; setCurrentPet();
                startActivity(new Intent(PetImageScreen.this, PetImageScreen.class)); break;
            case R.id.bPetInformation:
                startActivity(new Intent(PetImageScreen.this, PetInformationScreen.class)); break;
        }
    }

    private void setCurrentPet() {

        String petId = "N/A";

        String petName = "N/A";
        String petLocation = "N/A";
        String petDescription = "N/A";

        String petType = "N/A";

        SharedPreferences currentPetInfo = getSharedPreferences("currentPetInfo", Context.MODE_PRIVATE);
        SharedPreferences searchSettings = getSharedPreferences("searchSettings", Context.MODE_PRIVATE);

        String [] types = new String [3];  extractJSON();

        if (!searchSettings.getBoolean("wantAll",true)) {
            if (searchSettings.getBoolean("wantDog", false)) { types[0] = "0"; }
            if (searchSettings.getBoolean("wantCat", false)) { types[1] = "1"; }
            if (searchSettings.getBoolean("wantOther", false)) { types[2] = "2"; }
        } else {
            types[0] = "0";
            types[1] = "1";
            types[2] = "2";
        }

        if (pets == null || currentPetIndex >= pets.length()) { currentPetIndex = 0; }

        if (pets != null && currentPetIndex < pets.length()) {
            try {
                JSONObject temp = pets.getJSONObject(currentPetIndex);

                petId = temp.getString(TAG_ID);

                petName = temp.getString(TAG_NAME);
                petLocation = temp.getString(TAG_LOCATION);
                petDescription = temp.getString(TAG_DESCRIPTION);

                petType = temp.getString(TAG_TYPE);

                int startingIndex = currentPetIndex; int repeat = 0;

                while (java.util.Arrays.asList(types).indexOf(petType) < 0 && repeat == 0) {
                    currentPetIndex++;

                    if (pets == null || currentPetIndex >= pets.length()) { currentPetIndex = 0; }

                    temp = pets.getJSONObject(currentPetIndex);

                    petId = temp.getString(TAG_ID);

                    petName = temp.getString(TAG_NAME);
                    petLocation = temp.getString(TAG_LOCATION);
                    petDescription = temp.getString(TAG_DESCRIPTION);

                    petType = temp.getString(TAG_TYPE);

                    if (startingIndex == currentPetIndex) { repeat++; }
                }

                loadImage("http://cse.ohio-state.edu/~re.9/images/" + petId + ".JPG");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        SharedPreferences.Editor editor = currentPetInfo.edit();

        editor.putString("pet_id", petId);

        editor.putString("pet_name", petName);
        editor.putString("pet_location", petLocation);
        editor.putString("pet_description", petDescription);

        editor.putString("pet_Type", petType);

        editor.putInt("currentPetIndex", currentPetIndex);

        editor.apply();
    }

    private void getJSON(String url) {
        class GetJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0]; BufferedReader bufferedReader;

                try {
                    URL url = new URL(uri);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    StringBuilder stringBuilder = new StringBuilder(); String json;

                    bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    while ((json = bufferedReader.readLine()) != null) {
                        stringBuilder.append(json).append("\n");
                    }

                    return JSON = stringBuilder.toString().trim();
                } catch (Exception e) {
                    e.printStackTrace(); return null;
                }
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                setCurrentPet();
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute(url);
    }

    private void extractJSON() {
        try {
            if(JSON != null) {
                JSONObject jsonObject = new JSONObject(JSON);
                pets = jsonObject.getJSONArray(TAG_RESULTS);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void loadImage(String url) {
        imageLoader = CustomVolleyRequest.getInstance(this.getApplicationContext()).getImageLoader();
        imageLoader.get(url, ImageLoader.getImageListener(nImageView, 0, android.R.drawable.ic_dialog_alert));
        nImageView.setImageUrl(url, imageLoader);
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

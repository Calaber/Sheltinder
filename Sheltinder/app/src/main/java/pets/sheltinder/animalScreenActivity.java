package pets.sheltinder;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class animalScreenActivity extends Activity {
    private final String TAG = getClass().getSimpleName();
    private static String myJSON="MY_JSON";

    private static final String PET_DATA_URL = "http://web.cse.ohio-state.edu/~re.9/pictures/16.JPG";

    NetworkImageView nImageView; ImageLoader imageLoader;

    private static final String TAG_RESULTS="result";
    private static final String TAG_ID = "pet_id";
    private static final String TAG_NAME = "pet_name";
    private static final String TAG_LOC ="pet_location";
    private static final String TAG_DESC ="pet_description";
    private static final String TAG_TYP ="pet_type";

    int currentPetIndex;

    JSONArray pets = null;


    Button infoB;
    ImageButton nextAnimB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animalscreenlayout);
        addListenersOnButtons();
        Log.d(TAG, "onCreate");
        currentPetIndex=0;
        SharedPreferences settings = getSharedPreferences("CurrentPet",
                Context.MODE_PRIVATE);
        currentPetIndex=settings.getInt("currentPetIndex",currentPetIndex);

        //getData();
        getJSON(PET_DATA_URL);
        extractJSON();
        setCurrentPet();

    }

    public void addListenersOnButtons(){
        addListenerOnInfoButton();
        addListenerOnNextAnimalButton();
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
                currentPetIndex++;
                setCurrentPet();
                Intent intent = new Intent(context, animalScreenActivity.class);
                startActivity(intent);

            }
        });
    }
    private void setCurrentPet(){

        String petID="N/A";
        String petName="Animal Name";
        String petLoc="N/A";
        String petDesc="N/A";
        String petType="N/A";
        extractJSON();
        SharedPreferences settings = getSharedPreferences("CurrentPet",
                Context.MODE_PRIVATE);
        //currentPetIndex=settings.getInt("currentPetIndex",currentPetIndex);
        //getData();
        SharedPreferences searchSettings=getSharedPreferences("settings",Context.MODE_PRIVATE);
        String [] types=new String [3];
        if(!searchSettings.getBoolean("wantAll",true)) {

            if (searchSettings.getBoolean("wantDog", false)) {
                types[0] = "0";
            }
            if (searchSettings.getBoolean("wantCat", false)) {
                types[1]= "1";
            }
            if (searchSettings.getBoolean("wantOther", false)) {
                types[2]="2";
            }
        }else{
            types[0] = "0";
            types[1] = "1";
            types[2] = "2";
        }

        if(pets==null || currentPetIndex>=pets.length()){
            currentPetIndex=0;

        }

        if(pets!=null&& currentPetIndex<pets.length()){
            try {
                JSONObject temp = pets.getJSONObject(currentPetIndex);
                petID = temp.getString(TAG_ID);
                petName= temp.getString(TAG_NAME);
                petLoc=temp.getString(TAG_LOC);
                petDesc=temp.getString(TAG_DESC);
                petType=temp.getString(TAG_TYP);
                int startingIndex=currentPetIndex;
                int repeat=0;
                while(java.util.Arrays.asList(types).indexOf(petType) < 0 && repeat==0){
                    currentPetIndex++;
                    if(pets==null || currentPetIndex>=pets.length()){
                        currentPetIndex=0;

                    }
                    temp = pets.getJSONObject(currentPetIndex);
                    petID = temp.getString(TAG_ID);
                    petName= temp.getString(TAG_NAME);
                    petLoc=temp.getString(TAG_LOC);
                    petDesc=temp.getString(TAG_DESC);
                    petType=temp.getString(TAG_TYP);
                    if(startingIndex==currentPetIndex){
                        repeat++;
                    }
                }


            } catch (JSONException e){
                Log.e("Sheltinder", "unexpected JSON exception", e);
            }
        }




        SharedPreferences.Editor editor = settings.edit();

        editor.putString("pet_id",petID);
        editor.putString("pet_name",petName);
        editor.putString("pet_location",petLoc);
        editor.putString("pet_description",petDesc);
        editor.putString("pet_Type",petType);
        editor.putInt("currentPetIndex",currentPetIndex);
        editor.commit();
    }

    //Not currently using. Deferring to getJSON Method
    public void getData(){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);

                    pets=jsonResponse.getJSONArray(TAG_RESULTS);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        PetData petInfo = new PetData(responseListener);
        RequestQueue queue = Volley.newRequestQueue(animalScreenActivity.this);
        queue.add(petInfo);

    }
    private void getJSON(String url) {
        class GetJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }
                    myJSON=sb.toString().trim();
                    return myJSON;

                }catch(Exception e){
                    return null;
                }

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //extractJSON();
                setCurrentPet();
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute(url);
    }

    private void extractJSON(){
        try {
            if(myJSON!=null) {
                JSONObject jsonObject = new JSONObject(myJSON);
                pets = jsonObject.getJSONArray(TAG_RESULTS);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
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
        Intent intent = new Intent(this, mainScreenActivity.class);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void loadImage(String url){
        imageLoader = CustomVolleyRequest.getInstance(this.getApplicationContext()).getImageLoader();
        imageLoader.get(url, ImageLoader.getImageListener(nImageView, 0, android.R.drawable.ic_dialog_alert));
        nImageView.setImageUrl(url, imageLoader);
    }
}

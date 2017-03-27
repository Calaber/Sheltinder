package pets.sheltinder;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



/**
 * Created by alber_000 on 2/22/2017.
 */

public class animalScreenActivity extends Activity {
    private final String TAG = getClass().getSimpleName();
    String myJSON;

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
        getData();
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
        SharedPreferences settings = getSharedPreferences("CurrentPet",
                Context.MODE_PRIVATE);
        currentPetIndex=settings.getInt("currentPetIndex",currentPetIndex);

        if(currentPetIndex>=pets.length()){
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

}

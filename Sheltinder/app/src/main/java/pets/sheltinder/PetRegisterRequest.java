package pets.sheltinder;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

class PetRegisterRequest extends StringRequest {

    private static final String PET_REGISTER_REQUEST_URL =
            "http://web.cse.ohio-state.edu/~re.9/registerPet.php";

    private Map<String, String> params;

    PetRegisterRequest(String petName, String petLocation, String petDescription, int petType, Response.Listener<String> listener) {
        super(Method.POST, PET_REGISTER_REQUEST_URL, listener, null);

        params = new HashMap<>();

        params.put("pet_name", petName);
        params.put("pet_location", petLocation);
        params.put("pet_description", petDescription);

        params.put("pet_type", petType + "");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

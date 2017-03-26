package pets.sheltinder;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

class UserRegisterRequest extends StringRequest {

    private static final String USER_REGISTER_REQUEST_URL = "http://sheltinderdatabase.000webhostapp.com/UserRegistry.php";

    private Map<String, String> params;

    UserRegisterRequest(String username, String password, int shelter, String shelterName, String shelterLocation, String shelterDescription, Response.Listener<String> listener) {
        super(Method.POST, USER_REGISTER_REQUEST_URL, listener, null);

        params = new HashMap<>();

        params.put("username", username);
        params.put("password", password);

        params.put("shelter", shelter + "");

        params.put("shelter_name", shelterName);
        params.put("shelter_location", shelterLocation);
        params.put("shelter_description", shelterDescription);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

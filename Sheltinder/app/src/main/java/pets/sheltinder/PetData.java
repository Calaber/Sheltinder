package pets.sheltinder;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alber_000 on 3/26/2017.
 */

class PetData extends StringRequest {
    private static final String PET_REGISTER_REQUEST_URL = "http://sheltinderdatabase.000webhostapp.com/getPetInfo.php";

    private Map<String, String> params;

    PetData(Response.Listener<String> listener) {
        super(Method.POST, PET_REGISTER_REQUEST_URL, listener, null);

    }

}

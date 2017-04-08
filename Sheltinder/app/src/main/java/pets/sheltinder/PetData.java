package pets.sheltinder;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.Map;

class PetData extends StringRequest {
    private static final String PET_DATA_URL = "http://sheltinderdatabase.000webhostapp.com/getPetInfo.php";

    private Map<String, String> params;

    PetData(Response.Listener<String> listener) {
        super(Method.POST, PET_DATA_URL, listener, null);

    }
}

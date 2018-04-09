package ke.co.zivish.smartsally;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Luke on 12/31/2016.
 */
public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://altuna.co.ke/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String email, String password, String confirmpassword, String uniqueid, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        params.put("confirmpassword", confirmpassword);
        params.put("uniqueid", uniqueid);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

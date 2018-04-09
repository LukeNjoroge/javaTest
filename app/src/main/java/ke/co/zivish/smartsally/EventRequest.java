package ke.co.zivish.smartsally;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Luke on 1/14/2017.
 */
public class EventRequest extends StringRequest {

    private static final String EVENT_REQUEST_URL = "http://altuna.co.ke/AddEvent.php";
    private Map<String, String> params;

    public EventRequest(String serviceName, String employeeName, String datetime, Response.Listener<String> listener){
        super(Method.POST, EVENT_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("serviceName", serviceName);
        params.put("employeeName", employeeName);
        params.put("datetime", datetime);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

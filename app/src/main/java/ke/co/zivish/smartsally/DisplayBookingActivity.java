package ke.co.zivish.smartsally;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayBookingActivity extends AppCompatActivity {
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    BookingAdapter bookingAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_booking);
        listView = (ListView)findViewById(R.id.lvbooking);

        bookingAdapter = new BookingAdapter(this,R.layout.row_layout);
        listView.setAdapter(bookingAdapter);
        json_string = getIntent().getExtras().getString("json_data");
        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;
            String booking_no,customer_name,sevice_name,time;

            while (count<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                booking_no = JO.getString("booking_no");
                customer_name = JO.getString("customer_id");
                sevice_name = JO.getString("service_id");
                time = JO.getString("start_time");
                Booking booking = new Booking(booking_no,customer_name,sevice_name,time);
                bookingAdapter.add(booking);
                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

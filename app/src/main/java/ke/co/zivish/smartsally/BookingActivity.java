package ke.co.zivish.smartsally;

import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class BookingActivity extends AppCompatActivity {
    ListView bookingListView;
    ProgressBar progressBarBooking;
    String BOOKING_REQUEST_URL = "http://altuna.co.ke/getDataBooking.php";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_booking);

        bookingListView = (ListView)findViewById(R.id.lvbooking);

        progressBarBooking = (ProgressBar)findViewById(R.id.progressBar);

        new GetHttpResponse(BookingActivity.this).execute();
    }

    private class GetHttpResponse extends AsyncTask<Void, Void, Void>
    {
        public Context context;

        String ResultHolder;

        List<bookingRequest> bookingList;

        public GetHttpResponse(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            HttpServicesClass httpServiceObject = new HttpServicesClass(BOOKING_REQUEST_URL);
            try
            {
                httpServiceObject.ExecutePostRequest();

                if(httpServiceObject.getResponseCode() == 200)
                {
                    ResultHolder = httpServiceObject.getResponse();

                    if(ResultHolder != null)
                    {
                        JSONArray jsonArray = null;

                        try {
                            jsonArray = new JSONArray(ResultHolder);

                            JSONObject jsonObject;

                            bookingRequest subjects;

                            bookingList = new ArrayList<bookingRequest>();

                            for(int i=0; i<jsonArray.length(); i++)
                            {
                                subjects = new bookingRequest();

                                jsonObject = jsonArray.getJSONObject(i);

                                subjects.bookingName = jsonObject.getString("booking");

                                bookingList.add(subjects);
                            }
                        }
                        catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                else
                {
                    Toast.makeText(context, httpServiceObject.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)

        {
            progressBarBooking.setVisibility(View.GONE);

            bookingListView.setVisibility(View.VISIBLE);

            if(bookingList != null)
            {
                ListAdapterClass adapter = new ListAdapterClass(bookingList, context);

                bookingListView.setAdapter(adapter);
            }
        }
    }

}
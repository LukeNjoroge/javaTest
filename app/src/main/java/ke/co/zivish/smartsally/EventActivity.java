package ke.co.zivish.smartsally;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class EventActivity extends AppCompatActivity{
    static EditText DateEdit;

    private Spinner spService;
    private ArrayList<String> services;
    private JSONArray server_response;

    private Spinner spEmployee;
    private ArrayList<String> employees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        final EditText etDateTime = (EditText) findViewById(R.id.etDateTime);
        final Button bAddEvent = (Button) findViewById(R.id.bAddEvent);

        services = new ArrayList<String>();
        spService = (Spinner) findViewById(R.id.spService);

        employees = new ArrayList<String>();
        spEmployee = (Spinner) findViewById(R.id.spEmployee);

        ArrayAdapter adapterService = new ArrayAdapter(this, android.R.layout.simple_spinner_item, services);
        ArrayAdapter adapterEmployee = new ArrayAdapter(this, android.R.layout.simple_spinner_item, employees);

        spService.setAdapter(adapterService);
        spEmployee.setAdapter(adapterEmployee);

        getData();

        DateEdit = (EditText) findViewById(R.id.etDateTime);
        DateEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showTruitonTimePickerDialog(v);
                showTruitonDatePickerDialog(v);
            }
        });

        bAddEvent.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View v) {

                String serviceName = (String ) spService.getItemAtPosition(0);
                String employeeName = (String ) spEmployee.getItemAtPosition(0);
                final String datetime = etDateTime.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                Intent intent = new Intent(EventActivity.this, BookingActivity.class);
                                EventActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(EventActivity.this);
                                builder.setMessage("Failed to Book!")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                EventRequest eventRequest = new EventRequest(serviceName, employeeName, datetime, responseListener);
                RequestQueue queue = Volley.newRequestQueue(EventActivity.this);
                queue.add(eventRequest);
            }
        });
        getData();
    }

    private void getData(){
        //Creating a string request
        StringRequest stringRequest = new StringRequest(ConfigEvent.DATA_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            server_response = j.getJSONArray(ConfigEvent.JSON_ARRAY);

                            //Calling method getStudents to get the students from the JSON Array
                            getService(server_response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);

        //Creating a string request
        StringRequest stringRequestEmployee = new StringRequest(ConfigSalonist.DATA_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            server_response = j.getJSONArray(ConfigSalonist.JSON_ARRAY);

                            //Calling method getStudents to get the students from the JSON Array
                            getEmployee(server_response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating a request queue
        RequestQueue requestQueueEmployee = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueueEmployee.add(stringRequestEmployee);
    }

    private void getService(JSONArray j){
        //Traversing through all the items in the json array
        for(int i=0;i<j.length();i++){
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                services.add(json.getString(ConfigEvent.TAG_SERVICE_NAME));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        spService.setAdapter(new ArrayAdapter<String>(EventActivity.this, android.R.layout.simple_spinner_dropdown_item, services));
    }

    private void getEmployee(JSONArray j){
        //Traversing through all the items in the json array
        for(int i=0;i<j.length();i++){
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                employees.add(json.getString(ConfigSalonist.TAG_EMPLOYEE_NAME));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        spEmployee.setAdapter(new ArrayAdapter<String>(EventActivity.this, android.R.layout.simple_spinner_dropdown_item, employees));
    }

    public void showTruitonDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }



    public static class DatePickerFragment extends DialogFragment implements
            OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            DateEdit.setText(day + "/" + (month + 1) + "/" + year);
        }
    }

    public void showTruitonTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public static class TimePickerFragment extends DialogFragment implements
            TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            DateEdit.setText(DateEdit.getText() + " -" + hourOfDay + ":" + minute);
        }
    }


    private class TAG_USERNAME {
    }
}
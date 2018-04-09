package ke.co.zivish.smartsally;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        LinearLayout menu_location = (LinearLayout )findViewById(R.id.lllocation);
        menu_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picture_intent = new Intent(HomeScreenActivity.this, LocationMapsActivity.class);
                startActivity(picture_intent);
            }
        });

        LinearLayout menu_booking = (LinearLayout )findViewById(R.id.llbooking);
        menu_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picture_intent = new Intent(HomeScreenActivity.this, BookingActivity.class);
                startActivity(picture_intent);
            }
        });

        LinearLayout menu_rate = (LinearLayout )findViewById(R.id.llRating);
        menu_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picture_intent = new Intent(HomeScreenActivity.this, RateServiceActivity.class);
                startActivity(picture_intent);
            }
        });

        LinearLayout menu_points = (LinearLayout )findViewById(R.id.llSallyPoints);
        menu_points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picture_intent = new Intent(HomeScreenActivity.this, SallyPointActivity.class);
                startActivity(picture_intent);
            }
        });
    }

}

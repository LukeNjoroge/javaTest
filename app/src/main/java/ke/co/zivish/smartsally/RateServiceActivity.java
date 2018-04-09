package ke.co.zivish.smartsally;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RateServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_service);

        TextView rate_services = (TextView) findViewById(R.id.etRate1);
        rate_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picture_intent = new Intent(RateServiceActivity.this, RateActivity.class);
                startActivity(picture_intent);
            }
        });

        TextView rate_services2 = (TextView) findViewById(R.id.etRate2);
        rate_services2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picture_intent = new Intent(RateServiceActivity.this, RateActivity.class);
                startActivity(picture_intent);
            }
        });

        TextView rate_services3 = (TextView) findViewById(R.id.etRate3);
        rate_services3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picture_intent = new Intent(RateServiceActivity.this, RateActivity.class);
                startActivity(picture_intent);
            }
        });
    }
}

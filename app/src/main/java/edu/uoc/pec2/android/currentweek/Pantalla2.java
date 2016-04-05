package edu.uoc.pec2.android.currentweek;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class Pantalla2 extends AppCompatActivity implements View.OnClickListener{

    private Button mButtonReturn;
    private TextView mMessageView;
    // Define sound player
    private MediaPlayer sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);

        // Set Views
        mMessageView = (TextView) findViewById(R.id.txtMessage);
        mButtonReturn = (Button) findViewById(R.id.btnReturn);

        // Set Listeners
        mButtonReturn.setOnClickListener(this);

        // Get Data Intent
        Bundle bundle = getIntent().getExtras();
        String currentWeek = bundle.getString("currentWeek");

        if (currentWeek != null) {
            if (Integer.parseInt(currentWeek)==weekOfYear()) {
                // Set File to play
                sound = MediaPlayer.create(this, R.raw.ok);
                // Set text message and color
                mMessageView.setText(R.string.ok);
                mMessageView.setTextColor(Color.rgb(0,255,0));
                // Set button text
                mButtonReturn.setText(R.string.startagain);
            } else {
                // Set File to play
                sound = MediaPlayer.create(this, R.raw.bad);
                // Set text message and color
                mMessageView.setText(R.string.bad);
                mMessageView.setTextColor(Color.rgb(255,0,0));
                // Set button text
                mButtonReturn.setText(R.string.tryagain);
            }
            // Sound play
            sound.start();
        }



    }


    // Click Event
    @Override
    public void onClick(View v) {
        if (v == mButtonReturn) {
            finish();
        }
    }

    private static int weekOfYear() {
        // Return the actual week of year following ISO Norm
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek( Calendar.MONDAY);
        calendar.setMinimalDaysInFirstWeek(4);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }
}

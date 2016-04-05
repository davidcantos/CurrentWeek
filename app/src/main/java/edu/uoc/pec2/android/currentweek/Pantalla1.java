package edu.uoc.pec2.android.currentweek;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Pantalla1 extends AppCompatActivity implements View.OnClickListener {

    // UI references.
    private EditText mCurrentWeekView;
    private Button mButtonCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla1);

        // Set Views
        mCurrentWeekView = (EditText) findViewById(R.id.txtWeekNumber);
        mButtonCheck = (Button) findViewById(R.id.btnCheck);

        // Set Listeners
        mButtonCheck.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == mButtonCheck) {
            checkWeek();
        }
    }

    private void checkWeek() {

        // Reset errors.
        mCurrentWeekView.setError(null);

        // Store values.
        String currentWeek = mCurrentWeekView.getText().toString();


        boolean cancel = false;
        View focusView = null;

        // Check for no empty.
        if (TextUtils.isEmpty(currentWeek)) {
            mCurrentWeekView.setError(getString(R.string.emptyweek));
            focusView = mCurrentWeekView;
            cancel = true;
        }

        // Check for a number.
        if (!isNumeric(currentWeek) || Integer.parseInt(currentWeek)<1 || Integer.parseInt(currentWeek)>52) {
            mCurrentWeekView.setError(getString(R.string.notnumber));
            focusView = mCurrentWeekView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; Focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            Intent i=new Intent(Pantalla1.this, Pantalla2.class);
            mCurrentWeekView.setText("");
            i.putExtra("currentWeek", currentWeek);
            startActivity(i);
        }
    }

    private static boolean isNumeric(String cadena){
     // Return true if the string is a number else return false
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }
}

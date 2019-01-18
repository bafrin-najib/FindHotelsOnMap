package me.dlovan.findhotelsonmap;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class AddingHotels extends AppCompatActivity {

    Button add_btn;
    EditText edtLat, edtLng;

    // hide keyboard
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_hotels);
        edtLat = findViewById(R.id.editText7);
        edtLng = findViewById(R.id.editText8);
        edtLat.setEnabled(false);
        edtLat.setInputType(InputType.TYPE_NULL);
        edtLng.setEnabled(false);
        edtLng.setInputType(InputType.TYPE_NULL);

        Bundle bundle = getIntent().getExtras();
        edtLat.setText(String.valueOf(bundle.getDouble("LAT")));
        edtLng.setText(String.valueOf(bundle.getDouble("LNG")));

        add_btn = findViewById(R.id.btnAdd);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hideKeyboard(AddingHotels.this);
                //TODO: add info to database and return to main activity

/*

                String message = "Hotel has been added !";
                Snackbar mSnackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
                View v = mSnackBar.getView();
                v.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                // v.setMinimumHeight(300);
                TextView mainTextView = (v).findViewById(android.support.design.R.id.snackbar_text);
                mainTextView.setTextColor(Color.WHITE);
                mSnackBar.show();
*/

            }
        });

    }
}

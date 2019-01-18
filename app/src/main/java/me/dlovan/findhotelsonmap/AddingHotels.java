package me.dlovan.findhotelsonmap;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddingHotels extends AppCompatActivity {

    Button add_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_hotels);


        add_btn = findViewById(R.id.btnAdd);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message = "Hotel has been added !";
                Snackbar mSnackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
                View v = mSnackBar.getView();
                v.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                // v.setMinimumHeight(300);
                TextView mainTextView = (v).findViewById(android.support.design.R.id.snackbar_text);
                mainTextView.setTextColor(Color.WHITE);
                mSnackBar.show();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.add:
                Toast.makeText(this, "Icon Add Clicked", Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }
}

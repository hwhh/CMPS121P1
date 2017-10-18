package practical1.com.practical1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class EnterInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_info);

        Button button_done = (Button) findViewById(R.id.button_done);
        EditText editTextName = (EditText) findViewById(R.id.name_editText);
        EditText editTextPhotographer = (EditText) findViewById(R.id.photographer_editText);

        //Populates the year spinner
        ArrayList<String> years = new ArrayList<>();
        for (int i = 2017; i >= 1900; i--) { years.add(String.valueOf(i)); }

        Spinner spinner = (Spinner) findViewById(R.id.years_spinner);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, years);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);

        button_done.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            String photographer = editTextPhotographer.getText().toString();
            String year = spinner.getSelectedItem().toString();
            if(!name.equals("") && !photographer.equals("")){ //Check to ensure valid data is entered
                Picture p = new Picture(name, photographer, year); //Create a new picture object and send it back to the main activity
                Intent intent = new Intent(EnterInfoActivity.this, MainActivity.class);
                intent.putExtra("practical1.com.practical.Picture", p);
                startActivity(intent);
            }else{ //If input fields are empty show a popup
                Toast.makeText(this.getApplicationContext(), "Please enter valid name/photographer", Toast.LENGTH_SHORT).show();
            }

        });
    }
}

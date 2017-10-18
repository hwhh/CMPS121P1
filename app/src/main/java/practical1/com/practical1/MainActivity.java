package practical1.com.practical1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * I have opted to use GSON and shard preferences because there are one of the faster methods for saving data
 * Also for proficiency I only save and load data upon app start / exit and use Intent to share objects between activities
 */
public class MainActivity extends AppCompatActivity {

    private static final String PREFS_TAG = "SharedPrefs";
    private static final String SAVED_PICTURES = "savedPictures";
    DataHandler d = DataHandler.getInstance();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context context = this.getApplicationContext();

        Bundle b = getIntent().getExtras();
        if (b == null) { //first time opening app
            //Loads the string from the shared preferences
            Gson gson = new Gson();
            SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(PREFS_TAG, Context.MODE_PRIVATE);
            String jsonPreferences = sharedPref.getString(SAVED_PICTURES, "");
            Type type = new TypeToken<List<Picture>>() {
            }.getType();
            d.setList(gson.fromJson(jsonPreferences, type)); //Converts the string and saves the list of pictures
        } else {
            Picture p = b.getParcelable("practical1.com.practical.Picture");
            d.getList().add(p); //Adds new pictures to the list of pictures
        }

        Button exitButton = (Button) findViewById(R.id.exit_button);
        exitButton.setOnClickListener(v -> {
            //Converts the list of pictures to a JSON string and saves it in the shared preferences
            Gson gson = new Gson();
            String jsonCurProduct = gson.toJson(d.getList());
            SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(PREFS_TAG, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(SAVED_PICTURES, jsonCurProduct);
            editor.apply();
            finishAffinity();
        });

        Button infoButton = (Button) findViewById(R.id.info_button);
        infoButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EnterInfoActivity.class);
            startActivity(intent);
        });


        Button viewButton = (Button) findViewById(R.id.view_button);
        viewButton.setOnClickListener(v -> {
            //Hands the list of pictures to the view activity
            Intent intent = new Intent(MainActivity.this, ViewActivity.class);
            intent.putParcelableArrayListExtra("picturesList", d.getList());
            startActivity(intent);
        });
    }


}

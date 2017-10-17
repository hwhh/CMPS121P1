package practical1.com.practical1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DataHandler d =  DataHandler.getInstance();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context context = this.getApplicationContext();

        Bundle b = getIntent().getExtras();
        if(b == null){ //first time opening app
            SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
            String json = appSharedPrefs.getString("pictureList1", "");
            Type picturesList = new TypeToken<ArrayList<Picture>>(){}.getType();

            d.setList(new Gson().fromJson(json, picturesList));
        }else{
              Picture p = b.getParcelable("practical1.com.practical.Picture");
              d.getList().add(p);
        }
        Button exitButton = (Button) findViewById(R.id.exit_button);

        exitButton.setOnClickListener(v -> {
            SharedPreferences mPrefs= context.getSharedPreferences(context.getApplicationInfo().name, Context.MODE_PRIVATE);
            SharedPreferences.Editor ed=mPrefs.edit();
            Gson gson = new Gson();
            ed.putString("pictures", gson.toJson(d.getList()));
            ed.apply();
        });


        Button infoButton = (Button) findViewById(R.id.info_button);
        infoButton.setOnClickListener(v -> {
            //TODO Refactor to take list out
            Intent intent = new Intent(MainActivity.this, EnterInfoActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putParcelableArrayList("pictures", (ArrayList<? extends Parcelable>) pictures);
//            intent.putExtras(bundle);
            startActivity(intent);
        });


        Button viewButton = (Button) findViewById(R.id.view_button);
        viewButton.setOnClickListener(v -> {
            //TODO Refactor to take list out
            Intent intent = new Intent(MainActivity.this, ViewActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putParcelableArrayList("pictures", (ArrayList<? extends Parcelable>) pictures);
//            intent.putExtras(bundle);
            startActivity(intent);
        });
    }

//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState) {
//        // Save the user's current game state
//        savedInstanceState.putParcelableArrayList("pictureList", (ArrayList<? extends Parcelable>) pictures);
//        // Always call the superclass so it can save the view hierarchy state
//        super.onSaveInstanceState(savedInstanceState);
//    }
//
//    public void onRestoreInstanceState(Bundle savedInstanceState) {
//        // Always call the superclass so it can restore the view hierarchy
//        super.onRestoreInstanceState(savedInstanceState);
//        // Restore state members from saved instance
//        pictures = savedInstanceState.getParcelableArrayList("pictureList");
//
//    }
}

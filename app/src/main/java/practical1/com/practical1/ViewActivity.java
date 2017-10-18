package practical1.com.practical1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        TextView textView = (TextView) findViewById(R.id.photo_info);

        //Populates the text object with data received from the bundle
        ArrayList<Picture> list = getIntent().getParcelableArrayListExtra("picturesList");
        if(list.isEmpty())
            textView.append("No photos");
        else {
            for (Picture picture : list) {
                textView.append("Photo Name: " + picture.name + " " + "Photographer Name: " + picture.photographer + " " + "Year: " + picture.year + " \n\n");
            }
        }

    }

}

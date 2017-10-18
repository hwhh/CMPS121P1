package practical1.com.practical1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Picture class to hold needed data, implements Parcelable to allow the data to be transferred between activities
 * and is more efficient than serializable
 */
public class Picture implements Parcelable {


    public String name, photographer, year;

    public Picture(String name, String photographer, String year) {
        this.name = name;
        this.photographer = photographer;
        this.year = year;
    }

    protected Picture(Parcel in) {
        name = in.readString();
        photographer = in.readString();
        year = in.readString();
    }

    public static final Creator<Picture> CREATOR = new Creator<Picture>() {
        @Override
        public Picture createFromParcel(Parcel in) {
            return new Picture(in);
        }

        @Override
        public Picture[] newArray(int size) {
            return new Picture[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(photographer);
        dest.writeString(year);
    }
}

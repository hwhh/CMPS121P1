package practical1.com.practical1;


import java.util.ArrayList;

/**
 * Singleton class used to store data transferred between multiple activities
 */
public class DataHandler {

    private static DataHandler handler;
    private ArrayList<Picture> list;

    private DataHandler() {
    }


    public static DataHandler getInstance() {
        if (handler == null) //Makes sure only one instance is running
            handler = new DataHandler();
        return handler;
    }

    public ArrayList<Picture> getList() {
        if(list == null){
            list = new ArrayList<>();
        }
        return list;
    }

    public void setList(ArrayList<Picture> list) {
        this.list = list;
    }
}
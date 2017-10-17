package practical1.com.practical1;


import java.util.ArrayList;

public class DataHandler {

    private static DataHandler handler;
    private ArrayList<Picture> list;

    private DataHandler() {
    }

    public static DataHandler getInstance() {
        if (handler == null)
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
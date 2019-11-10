package by.pisl.lab3.bundle;

import java.util.ResourceBundle;

public class DataBaseResourceBundle {
    private static volatile DataBaseResourceBundle instance;
    private ResourceBundle bundle = ResourceBundle.getBundle("db");

    private DataBaseResourceBundle() {
    }

    public static DataBaseResourceBundle getInstance() {
        if (instance == null) {
            synchronized (DataBaseResourceBundle.class) {
                if (instance == null)
                    instance = new DataBaseResourceBundle();
            }
        }

        return instance;
    }

    public String getValue(String key){
        return bundle.getString(key);
    }
}
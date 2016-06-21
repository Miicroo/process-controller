package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

public class JSONConverter {
    public static String mapToString(Map obj) {
        JSONObject jsonObject = new JSONObject(obj);
        return jsonObject.toString();
    }

    public static String objToString(Object obj) {
        JSONObject jsonObject = new JSONObject(obj);
        return jsonObject.toString();
    }

    public static String arrToString(Object[] objs) {
        JSONArray jsonArray = new JSONArray(objs);
        return jsonArray.toString();
    }
}

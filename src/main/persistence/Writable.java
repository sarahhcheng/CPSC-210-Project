package persistence;

import org.json.JSONObject;

// Referenced from demo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();

}

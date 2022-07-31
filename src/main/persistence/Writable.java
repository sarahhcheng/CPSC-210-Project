package persistence;

import org.json.JSONObject;

// Interface for saving data to JSON
// Referenced from demo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();

}

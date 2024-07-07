package cartAPI.cartAPI;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Fileutil {
    static Object Json(String path) throws FileNotFoundException {
        File nn = new File(path);
        FileReader fr = new FileReader(nn);

        JSONTokener jt = new JSONTokener(fr);

        JSONObject job = new JSONObject(jt);

        return job;

    }
}

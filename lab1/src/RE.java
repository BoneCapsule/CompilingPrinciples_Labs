import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Lijie
 * @Description:
 * @Date: Create in 2018/10/30 16:33
 */
public class RE {

    public static String[] type = {"Integer", "Indentifier", "Float"};
    public static String[] reg = {"NN*", "L(L|N)*", "N.NN*"};

    Map<String, String> map;

    public RE() {
        this.map = new HashMap<>();

        for (int i = 0; i < type.length; i++) {
            map.put(type[i], reg[i]);
        }
    }
}

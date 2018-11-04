import java.util.HashMap;

/**
 * @Author: Lijie
 * @Description:
 * @Date: Create in 15:50, 2018/10/30
 */
public class RE {

    private HashMap<String, String> map;

    public static String[] type = {"Integer", "Identifier", "Float"};
    public static String[] tag = {"NN*", "L(L|N)*", "N.NN*"};


    public RE(){
        this.map = new HashMap<>();
        for(int i = 0; i < type.length; i++){
            map.put(type[i], tag[i]);
        }
    }

    public HashMap<String, String> getMap() {
        return map;
    }

    public void setMap(HashMap<String, String> map) {
        this.map = map;
    }
}

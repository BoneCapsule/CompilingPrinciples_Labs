import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Lijie
 * @Description:
 * @Date: Create in 2018/10/30 16:24
 */
public class NFAState {

    int id;

    String reg_name;

    ArrayList<State> states;

    Map<Character, NFAState> link;

    public NFAState(int id) {
        this.id = id;
        link = new HashMap<>();
    }
}

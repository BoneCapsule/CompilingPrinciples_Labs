import java.util.LinkedList;

/**
 * @Author: Lijie
 * @Description:
 * @Date: Create in 16:19, 2018/10/30
 */
public class NFA {

    private LinkedList<State> states;

    public NFA(){
        states = new LinkedList<>();
    }

    public void addState(State state){
        states.add(state) ;
    }

    public LinkedList<State> getStates(){
        return this.states;
    }

}

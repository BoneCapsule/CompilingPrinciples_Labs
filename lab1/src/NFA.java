import java.util.LinkedList;

/**
 * @Author: Lijie
 * @Description:
 * @Date: Create in 20:50, 2018/10/30
 */
public class NFA {

    LinkedList<State> states;

    public NFA() {
        this.states = new LinkedList<>();
    }

    public LinkedList<State> getStates() {
        return states;
    }

    public void setStates(LinkedList<State> states) {
        this.states = states;
    }

    public void print(){
        System.out.println("NFA: ");
        for(State s:states)
        {
            s.print();
        }
    }
}

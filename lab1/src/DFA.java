import java.util.LinkedList;

/**
 * @Author: Lijie
 * @Description:
 * @Date: Create in 15:23, 2018/10/30
 */
public class DFA {
    LinkedList<DFAState> dfastates ;

    public DFA(){
        this.dfastates = new LinkedList<DFAState>();

    }

    public LinkedList<DFAState> getDFAStates(){
        return this.dfastates;
    }

    public void setDFAStates(LinkedList<DFAState> dfastates){
        this.dfastates = dfastates ;
    }
}

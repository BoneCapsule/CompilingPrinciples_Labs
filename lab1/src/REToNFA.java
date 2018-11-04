import java.util.Stack;

/**
 * @Author: Lijie
 * @Description:
 * @Date: Create in 16:42, 2018/10/30
 */
public class REToNFA {

    private char epsilon;
    private int stateId = 1 ;

    private Stack<NFA> nfaStack ;
    private Stack<Character> opStack ;

    public REToNFA() {
        this.epsilon = 'ε';
    }

    public NFA RE_To_NFA(String expr, String tagName){
        expr = splitExpression(expr);
        opStack = new Stack<>();
        nfaStack = new Stack<>();

        for(int i = 0; i < expr.length(); i++) {
            char ch = expr.charAt(i) ;
            if (isLetter(ch)) {
                pushStack(ch);
            }
            else if (ch == '(') {
                opStack.push(ch);
            }
            else if (ch == ')') {
                while (opStack.get(opStack.size() - 1) != '(') {
                    operate();
                }
                opStack.pop();  // 将'('出栈
            }
            else if (opStack.isEmpty()) {
                opStack.push(ch);
            }
            else {  // 操作符: '*' , '|' , '&'
                char nextChar = opStack.get(opStack.size() - 1);
                while (!opStack.isEmpty() && getPriority(ch, nextChar) ){
                    operate();
                }
                if (getPriority(ch) > getPriority(nextChar)) {
                    NFA nfa = new NFA();
                }
                opStack.push(ch);
            }
        }

        while (!opStack.isEmpty()) {
            operate();
        }

        NFA result = nfaStack.pop();
        int len = result.getStates().size();
        result.getStates().get(len - 1).setToEndState();
        result.getStates().getLast().set_name(tagName);
        return result;
    }

    private int getPriority(char ch) {
        if (ch == '*') return 1;
        if (ch == '|') return 0;
        if (ch == '$') return 2;
        return -1;
    }
    private boolean getPriority(char nowOperator, char lastOperator) {
        if (lastOperator == nowOperator) {
            return true;
        }
        if(nowOperator == '*') {
            return false;
        }
        if(lastOperator == '*') {
            return true;
        }
        if(nowOperator == '$') {
            return false;
        }
        if(lastOperator == '$') {
            return true;
        }
        if(nowOperator == '|') {
            return false;
        }
        return true ;
    }

    private void operate(){
        if(opStack.size() > 0) {
            char ch = opStack.pop();
            switch (ch) {
                case '|': merge(); break;
                case '*': repeat(); break;
                case '$': connect(); break;
                default:
                    System.out.println("Unknown symbol: " + ch);
                    System.exit(1);
            }
        }
    }

    // 处理'*'
    private void repeat() {
        NFA nfa = nfaStack.pop() ;
        State start = new State (stateId++);
        State end	= new State (stateId++);

        start.addNextState(epsilon, nfa.getStates().getFirst()); // 重复
        start.addNextState(epsilon, end); // 不重复

        nfa.getStates().getLast().addNextState(epsilon,end) ;
        nfa.getStates().getLast().addNextState(epsilon,nfa.getStates().getFirst()) ;

        nfa.getStates().addFirst(start);
        nfa.getStates().add(end);

        nfaStack.push(nfa);
    }

    // 处理'|'
    private void merge() {
        NFA nfa1 = nfaStack.pop() ;
        NFA nfa2 = nfaStack.pop() ;

        State start = new State (stateId++);
        State end = new State (stateId++);

        start.addNextState(epsilon , nfa1.getStates().getFirst());
        start.addNextState(epsilon , nfa2.getStates().getFirst());

        nfa1.getStates().getLast().addNextState(epsilon,end) ;
        nfa2.getStates().getLast().addNextState(epsilon,end) ;

        nfa1.getStates().addFirst(start);
        nfa2.getStates().addLast(end);

        for (State s : nfa2.getStates()) {
            nfa1.getStates().addLast(s);
        }

        nfaStack.push(nfa1);
    }


    // 处理'$'
    private void connect() {
        NFA nfa2 = nfaStack.pop();
        NFA nfa1 = nfaStack.pop();

        State state = nfa2.getStates().getFirst();
        nfa1.getStates().getLast().addNextState(epsilon, state);
        for (State s : nfa2.getStates()) {
            nfa1.getStates().addLast(s);
        }

        nfaStack.push(nfa1);
    }

    private void pushStack(char ch){
        State s0 = new State (stateId++);
        State s1 = new State (stateId++);

        s0.addNextState(ch, s1);

        NFA nfa = new NFA ();
        nfa.getStates().addLast(s0);
        nfa.getStates().addLast(s1);
        nfaStack.push(nfa);
    }


    // 分割正则表达式
    private String splitExpression(String expression) {
        StringBuilder sb = new StringBuilder();
        String result = "";
        String seperator = "$";
        for (int i = 0; i < expression.length() - 1; i++) {
            char ch = expression.charAt(i);
            char nextCh = expression.charAt(i + 1);
            if (isLetter(ch) && isLetter(nextCh)) {
                sb.append(ch);
                sb.append(seperator);
            }
            else if (isLetter(ch) && nextCh == '(') {
                sb.append(ch);
                sb.append(seperator);
            }
            else if (ch == '*' && isLetter(nextCh)) {
                sb.append(ch);
                sb.append(seperator);
            }
            else if (ch == '*' && nextCh == '(') {
                sb.append(ch);
                sb.append(seperator);
            }
            else if ( ch == ')' && isLetter(nextCh) ) {
                sb.append(ch);
                sb.append(seperator);
            }
            else if (ch == ')' && nextCh == '(') {
                sb.append(ch);
                sb.append(seperator);
            }
            else {
                sb.append(ch);
            }
        }

        char c = expression.charAt(expression.length() - 1);
        sb.append(c);
        return sb.toString();
    }


    private boolean isLetter(char ch){
        if (('0' <= ch && ch<='9') || ('a'<=ch && ch<='z') ||
                ('A'<=ch && ch<='Z') || ch=='.' || ch=='_') {
            return true;
        }
        return false;
    }



}

import java.util.HashMap;

/**
 * @Author: Lijie
 * @Description:
 * @Date: Create in 11:51, 2018/10/31
 */
public class ErrorHandling {

    public static String output(DFA dfa, String expr) {
        DFAState dfaState = dfa.getDFAStates().getFirst();
        DFAState nextState = null;
        int len = dfa.getDFAStates().size();

        if (len > 0) {
            for (int i = 0 ; i < expr.length(); i++) {
                char ch = expr.charAt(i);
                if ('0' <= ch && ch <= '9') {
                    ch = 'N';
                }
                else if (ch == '_' || ('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z')) {
                    ch = 'L';
                }

                HashMap<Character, DFAState> dfaStateMap = dfaState.getNextDFAState();
                if(dfaStateMap.containsKey(ch)) {
                    nextState = dfaState.getNextDFAState().get(ch);
                }
                else {
                    String str = expr.substring(0, i);
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    sb.append(", ");
                    if (dfaState.isEndState()) {
                        if ("Identifier".equals(dfaState.getTagName())) {
                            if (str.charAt(0) <= '9' && str.charAt(0) >= '0') {
                                sb.append("Unknown");
                            }
                            else {
                                sb.append(dfaState.getTagName());
                            }
                        }

                        else if ("Integer".equals(dfaState.getTagName())) {
                            boolean isFound = false;
                            for (int k = 0; k < str.length(); k++) {
                                if (str.charAt(k) == '.') {
                                    isFound = true;
                                    break;
                                }
                            }
                            if (isFound) {
                                sb.append("Float");
                            }
                            else {
                                sb.append(dfaState.getTagName());
                            }
                        }

                        else if ("Float".equals(dfaState.getTagName())) {
                            if (str.charAt(0) == '.') {
                                sb.append("Unknown");
                            }
                            else {
                                sb.append(dfaState.getTagName());
                            }
                        }

                        else {
                            sb.append(dfaState.getTagName());
                        }

                        return sb.toString();
                    }
                    else {
                        sb.append("Unknown");
                        return sb.toString();
                    }
                }

                if(i < expr.length()-1) {
                    dfaState = nextState;
                }
            }

            StringBuilder sb = new StringBuilder();
            sb.append(expr);
            sb.append(", ");
            if(nextState.isEndState()) {
//                if (dfaState.getTagName() == null) {
//                    sb.append("Unknown");
//                }
                if ("Identifier".equals(dfaState.getTagName())) {
                    if (expr.charAt(0) <= '9' && expr.charAt(0) >= '0') {
                        sb.append("Unknown");
                    }
                    else {
                        sb.append(dfaState.getTagName());
                    }
                }
                else if ("Float".equals(dfaState.getTagName())) {
                    if (expr.charAt(0) == '.') {
                        sb.append("Unknown");
                    }
                    else {
                        sb.append(dfaState.getTagName());
                    }
                }
                else if ("Integer".equals(dfaState.getTagName())) {
                    boolean isFound = false;
                    for (int k = 0; k < expr.length(); k++) {
                        if (expr.charAt(k) == '.') {
                            isFound = true;
                            break;
                        }
                    }
                    if (isFound) {
                        sb.append("Float");
                    }
                    else {
                        sb.append(dfaState.getTagName());
                    }
                }
                else {
                    if (dfaState.getTagName() == null) {
                        sb.append("Unknown");
                    }

                }

                return sb.toString();
            }
            else {
                sb.append("Unknown");
                return sb.toString();
            }
        }

        else if (expr.equals("ε")) {
            if (dfaState.isEndState()) {
                return "Epsilon end";
            }
            else {
                return "Epsilon not end";
            }
        }

        else {
            return "All invalid";
        }
    }

}

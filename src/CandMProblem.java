import java.util.ArrayList;
import java.util.Stack;
public class CandMProblem {
    public static void main(String[] args) {
        State firstState = new State(3, 3, true, State.Transition.Start);
        for (Stack s : State.solutions) {
            System.out.println(s);
        }
    }
}
class State {
    enum Transition {
        CC, C, MM, M, CM, Start
    }
    static Stack<State> stack = new Stack<>();
    public static ArrayList<Stack> solutions = new ArrayList<>();

    final int c, m;
    final boolean b;
    Transition tran;

    public State(int can, int mis, boolean boat, Transition t) {
        c = can;
        m = mis;

        b = boat;
        tran = t;
        if (legal()) {
            stack.push(this);
            if (winner()) {
                solutions.add((Stack) stack.clone());
            }
            nextState();
            stack.pop();//after all future paths have been tried, pop this.
        }
    }
    public boolean winner() {
        if (c == 0 && m == 0) {
            return true;
        }
        return false;
    }
    public boolean legal() {
        if (c > m) {
            if (m != 0) {
                return false;
            }
        }
        if ((3 - c) > (3 - m)) {
            if (3 - m > 0) {
                return false;
            }
        }
        if (c > 3 || c < 0 || m > 3 || m < 0) {
            return false;
        }
        for (State s : stack) {
            if (s.equals(this)) {
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof State)) {
            return false;
        }
        State that = (State) o;
        if (b == that.b && c == that.c && m == that.m) {
            return true;
        }
        return false;
    }
    public void nextState() {
        tryCC();
        tryC();
        tryCM();
        tryM();
        tryMM();
    }
    public void tryCC() {
        if (b)
            new State(c - 2, m, !b, Transition.CC);
        else
            new State(c + 2, m, !b, Transition.CC);
    }
    public void tryC() {
        if (b)
            new State(c - 1, m, !b, Transition.C);
        else
            new State(c + 1, m, !b, Transition.C);
    }
    public void tryM() {
        if (b)
            new State(c, m - 1, !b, Transition.M);
        else
            new State(c, m + 1, !b, Transition.M);
    }
    public void tryMM() {
        if (b)
            new State(c, m - 2, !b, Transition.MM);
        else
            new State(c, m + 2, !b, Transition.MM);
    }
    public void tryCM() {
        if (b)
            new State(c - 1, m - 1, !b, Transition.CM);
        else
            new State(c + 1, m + 1, !b, Transition.CM);
    }
    @Override
    public String toString() {
        String result = " ";
        String haveBoat;
        if (!b) {
            result += "-";
        }
        result += tran.toString() + " ";
        if (b) {
            haveBoat = " Yes";
        } else {
            haveBoat = " No ";
        }
        result += m + "M, " + c + "C " + haveBoat + " b ";
        return result;
    }
}

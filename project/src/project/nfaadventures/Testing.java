package project.nfaadventures;

import java.util.List;

/**
 * Test code
 * @author Jeroen Peeters 1643057
 */
public class Testing
{
    public static void main(String[] args)
    {
        System.out.println("--- Test Code Start ---");
        Testing testing = new Testing();
        testing.TestCode();
    }

    public void TestCode()
    {
       StateTesting();
    }

    private void StateTesting()
    {
        //State test code
        State state = new State("test", false);
        State a = new State("A", true);
        State b = new State("B", false);
        State c = new State("C", false);

        state.AddNextState("a", a);
        state.AddNextState("d", b);
        state.AddNextState("d", c);

        System.out.println("Get all next states (a, b, c)");
        System.out.println(state.GetAllNextStates());
        System.out.println("Get next states for b (none, only for a and d)");
        System.out.println(state.GetNextStatesFor("b"));
        System.out.println("Get next states for d (b, c)");
        System.out.println(state.GetNextStatesFor("d"));

        System.out.println("-> State works");
    }
}

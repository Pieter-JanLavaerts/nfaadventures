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
        System.out.println("--- Test parser ---");
        ParserTesting();
        System.out.println("--- Test lengthMap ---");
        LengthMapTesting();
        System.out.println("--- Test merging Automatons ---");
        MergeTesting();
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

    private void ParserTesting()
    {
        //Parser test code
        AutomatonParser parser = new AutomatonParser("src/project/nfaadventures/test.aut");
        try
        {
            parser.parse();
            Automaton a = parser.automaton();
            if (a != null)
            {
                System.out.println("-> Automaton parser returned something! (so it probably works)");
            }
        }
        catch (Exception e)
        {
            System.out.println("Parse error: " + e);
        }
    }

    private void LengthMapTesting()
    {
        AutomatonParser parser = new AutomatonParser("src/project/nfaadventures/test.aut");

        try
        {
            parser.parse();
            Automaton a = parser.automaton();

            if (a != null)
            {
                System.out.println("Making lengthmap");
                //LengthMap lMap = new LengthMap(a.GetStartState());

                System.out.println("Getting shortest accept path . . .");
                //TODO: replace with asking the automaton which should do it himself
                //System.out.println(lMap.GetShortestPathAccept());
            }
        }
        catch (Exception e)
        {
            System.out.println("Parse error: " + e);
        }
    }

    private void MergeTesting()
    {
        AutomatonParser parser = new AutomatonParser("src/project/nfaadventures/test.aut");
        try
        {
            parser.parse();
        }
        catch (Exception e)
        {
            System.out.println("Error parsing for mergetexst: e");
        }

        Automaton a = parser.automaton();
        Automaton b = parser.automaton();
        Automaton merged = a.Merge(b);
        if (merged != null) {
            System.out.println("Merging didn't blow up!");
        }
        else {
            System.out.println("Merging probably went wrong!");
        }
    }
}

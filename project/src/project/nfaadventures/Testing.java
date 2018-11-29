package project.nfaadventures;

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
        System.out.println("--- Test intersecting Automatons ---");
        IntersectTesting();
        System.out.println("--- Multiple intersection test ---");
        MultipleIntersectTesting();

        AutomatonParser a = new AutomatonParser("a.aut");
        AutomatonParser b = new AutomatonParser("a.aut");
        try
        {
            a.parse();
            b.parse();
            Automaton outa = a.automaton();
            Automaton outb = b.automaton();

            outa.intersection(outb);
            System.out.println(outa.getShortestExample(true));
        }
        catch (Exception e)
        {
            System.out.println("Wrong: " + e);
        }
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
        AutomatonParser parser = new AutomatonParser("adventure.aut");
        try
        {
            parser.parse();
            Automaton a = parser.automaton();
            if (a != null)
            {
                System.out.println("-> Automaton parser returned something! (so it might work)");
            }
        }
        catch (Exception e)
        {
            System.out.println("Parse error: " + e);
        }
    }

    private void LengthMapTesting()
    {
        AutomatonParser parser = new AutomatonParser("adventure.aut");

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

    private void IntersectTesting()
    {
        AutomatonParser parserA = new AutomatonParser("beginningOrEndingWith1.aut");
        AutomatonParser parserB = new AutomatonParser("lengthOfTwo.aut");
        try
        {
            parserA.parse();
            parserB.parse();
            Automaton A = parserA.automaton();
            Automaton B = parserB.automaton();
            A.intersection(B);
            if (A.GetStartState() == null) {
                throw new Exception("The intersection failed!");
            }
            else {
                System.out.println("The intersection returned something! (so it might work)");
                System.out.println(A.getShortestExample(true));
            }
        }
        catch (Exception e)
        {
            System.out.println("Parse error: " + e);
        }
    }

    private void MultipleIntersectTesting()
    {
        try
        {
            AutomatonParser autA = new AutomatonParser("testA.aut");
            AutomatonParser autB = new AutomatonParser("testB.aut");
            AutomatonParser autC = new AutomatonParser("testC.aut");
            autA.parse();
            autB.parse();
            autC.parse();
            Automaton A = autA.automaton();
            Automaton B = autB.automaton();
            Automaton C = autC.automaton();

            A.intersection(B);
            A.intersection(C);

            System.out.println("Shortest path: " + A.getShortestExample(true));
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e);
        }
    }
}


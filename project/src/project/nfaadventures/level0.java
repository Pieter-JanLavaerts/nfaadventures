package project.nfaadventures;

/**
 * God mode level, gives shortest path if it exists
 * @author Jeroen Peeters 1643057
 */
public class level0
{
    public static void main(String[] args)
    {
        try
        {
            //Make adventure automaton
            AutomatonParser parser = new AutomatonParser("adventure.aut");
            parser.parse();
            Automaton aut = parser.automaton();

            //Get shortest path
            String path = aut.getShortestExample(true);

            //Print result
            System.out.println(path);
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e);
        }
    }
}

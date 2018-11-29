package project.nfaadventures;

/**
 * Level 1
 * -Find two treasures.
 * -Only pass door if you have a key
 * -If you encounter a dragon your next move must be a river unless you have a key.
 * Prints shortest path in adventure that works here.
 * @author Jeroen Peeters 1643057
 */
public class level1
{
    public static void main(String[] args)
    {
        try
        {
            //Make adventure automaton
            AutomatonParser parser = new AutomatonParser("adventure.aut");
            parser.parse();
            Automaton adventure = parser.automaton();

            //Make constraints
            AutomatonParser treasureParser = new AutomatonParser("treasureConstraint.aut");
            AutomatonParser doorParser = new AutomatonParser("doorConstraint.aut");
            AutomatonParser dragonParser = new AutomatonParser("dragonConstraint.aut");
            treasureParser.parse();
            doorParser.parse();
            dragonParser.parse();
            Automaton treasureConstraint = treasureParser.automaton();
            Automaton doorConstraint = doorParser.automaton();
            Automaton dragonConstraint = dragonParser.automaton();

            //intersection constraints on adventure
            adventure.intersection(treasureConstraint);
            adventure.intersection(doorConstraint);
            adventure.intersection(dragonConstraint);

            //Get shortest path
            String path = adventure.getShortestExample(true);

            //Print result
            System.out.println(path);
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e);
        }
    }
}

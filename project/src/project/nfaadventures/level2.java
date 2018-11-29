package project.nfaadventures;

/**
 * Level 2:
 * -level 1, but when you pass an arc you lose all your treasure
 * Gives shortest working path that works on adventure.aut with these constraints
 * @author Jeroen Peeters 1643057
 */
public class level2
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
            AutomatonParser treasureParser2 = new AutomatonParser("treasureConstraintLvl2.aut");
            AutomatonParser doorParser = new AutomatonParser("doorConstraint.aut");
            AutomatonParser dragonParser = new AutomatonParser("dragonConstraint.aut");
            treasureParser2.parse();
            doorParser.parse();
            dragonParser.parse();
            Automaton treasureConstraint2 = treasureParser2.automaton();
            Automaton doorConstraint = doorParser.automaton();
            Automaton dragonConstraint = dragonParser.automaton();

            //Intersect constraints on adventure
            adventure.Intersect(treasureConstraint2);
            adventure.Intersect(doorConstraint);
            adventure.Intersect(dragonConstraint);

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

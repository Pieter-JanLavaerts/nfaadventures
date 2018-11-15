package project.nfaadventures;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that generates an automaton from a given file
 * @author Pieter-Jan Lavaerts 1746060
 */
public class AutomatonParser
{
    private Automaton mAutomaton;
    private List<String> fileText;

    /**
     * Constructor from file
     * @param filename the file that contains the description of the automaton
     */
    public AutomatonParser(String filename) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        try {
            String line = br.readLine();

            while (line != null) {
                fileText.add(line);
                line = br.readLine();
            }
        } finally {
            br.close();
        }

        try
        {
            parse();
        }
        catch(Exception e)
        {
            System.out.println("Badly formatted automaton file.");
        }
    }

    /**
     * getter for automaton
     * @return mAutomaton
     */
    public Automaton automaton()
    {
        return mAutomaton;
    }

    /**
     * Parses the file to initialize automaton
     */
    public void parse() throws Exception
    {
        for (String line : fileText) {

        }
    }

}

package project.nfaadventures;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * Class that generates an automaton from a given file
 * @author Pieter-Jan Lavaerts 1746060
 */
public class AutomatonParser
{
    private Automaton mAutomaton;
    private String mFileName;

    /**
     * Constructor from file
     * @param filename the file that contains the description of the automaton
     */
    public AutomatonParser(String filename)
    {
        mFileName = filename;
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
     * Parses the file to initialize mAutomaton
     */
    public void parse() throws Exception
    {
        BufferedReader br = new BufferedReader(new FileReader(mFileName));
        try {
            String line = br.readLine();
            br.mark(0);
            List<State> states = new ArrayList<>();
            List<String> acceptLabels = new ArrayList<>();

            while (line != null) {
                String acceptLabel = parseAcceptLine(line);
                if (acceptLabel != null) {
                    acceptLabels.add(acceptLabel);
                }

                line = br.readLine();
            }

            br.close();
            br = new BufferedReader(new FileReader(mFileName));
            line = br.readLine();
            while (line != null) {
                parseLine(line, states, acceptLabels);
                line = br.readLine();
            }
        } finally {
            br.close();
        }
    }

    private static String parseAcceptLine(String line)
    {
        String words[] = line.split(" ");
        if (!words[1].equals("-|")) {
            return null;
        }
        return words[0];
    }

    private void parseLine(String line, List<State> states, List<String> acceptLabels)
    {
        String words[] = line.split(" ");
        switch (words[1])
        {
            case "|-":
                mAutomaton = new Automaton(getState(states, acceptLabels, words[2]));
                break;
            case "-|":
                //already done when looping over the file the first time
                break;
            default:
                State from = getState(states, acceptLabels, words[0]);
                State to = getState(states, acceptLabels, words[2]);
                from.AddNextState(words[1], to);
                break;
        }
    }

    private static State getState(List<State> states, List<String> acceptStates, String label) {
        for (State s : states) {
            if (s.GetLabel().equals(label)) {
                return s;
            }
        }

        //state does not exist yet
        State s = new State(label, acceptStates.contains(label));
        states.add(s);
        return s;
    }
}

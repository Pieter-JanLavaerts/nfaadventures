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
     * @throws Exception throws at incorrect form or file doesn't exist
     */
    public void parse() throws Exception
    {
        BufferedReader br = new BufferedReader(new FileReader(mFileName));
        try {
            String line = br.readLine();
            List<State> states = new ArrayList<>(); //list that holds states that have already been initialized
            List<String> acceptLabels = new ArrayList<>(); //list that holds labels of states that should be initialized as accept states
            State startState = null;
            Set<String> alphabet = new HashSet<>();

            //read all lines for accept labels
            while (line != null)
            {
                String acceptLabel = parseAcceptLine(line);
                if (acceptLabel != null) {
                    acceptLabels.add(acceptLabel);
                }

                line = br.readLine();
            }

            //read all lines
            br.close();
            br = new BufferedReader(new FileReader(mFileName));
            line = br.readLine();
            while (line != null) {
                State result = parseLine(line, states, acceptLabels, alphabet);
                if (result != null) {
                    startState = result;
                }
                line = br.readLine();
            }
            if (startState != null)
            {
                mAutomaton = new Automaton(startState, alphabet);
            }
            else
            {
                throw new Exception("Faulty automaton file: no start state");
            }
        } finally {
            br.close();
        }
    }

    /**
     * parses an accept line
     * @param line
     * @return returns the label of the accept state
     */
    private static String parseAcceptLine(String line)
    {
        String words[] = line.split(" ");
        if (!words[1].equals("-|")) {
            return null;
        }
        return words[0];
    }

    /**
     * parses a line of an .aut file
     * @param line line of the aut file
     * @param states a list of states that are already initialized
     * @param acceptLabels a list of state labels that should be initialized as accept states
     * @param alphabet the alphabet of the automaton
     * @return reference to state in states that corresponds to the line in the .aut file
     */
    private State parseLine(String line, List<State> states, List<String> acceptLabels, Set<String> alphabet)
    {
        String words[] = line.split(" ");
        switch (words[1])
        {
            case "|-":
                return getState(states, acceptLabels, words[2]);
            case "-|":
                //already done when looping over the file the first time
                return null;
            default:
                alphabet.add(words[1]);
                State from = getState(states, acceptLabels, words[0]);
                State to = getState(states, acceptLabels, words[2]);
                from.AddNextState(words[1], to);
                return null;
        }
    }

    /**
     * Gets a state with a certain label from a list of states or create it if it doesn't exsit
     * @param states a list of states
     * @param acceptStates a list of state labels of states that should be intialized as accept states
     * @param label the label of the state to get from states
     * @return a reference to a state in states
     */
    private static State getState(List<State> states, List<String> acceptStates, String label)
    {
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

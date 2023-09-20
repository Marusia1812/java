package cz.cuni.mff.java.chinkovm;

import java.util.*;

/**
 * Main class for the Chord Generator application. It provides a command-line interface for users to generate chords.
 */
public class Main {

    /**
     * Prints the generated chord and its notes based on the input Chord object.
     *
     * @param chord The Chord object to generate notes for.
     * @param chordGenerator The ChordGenerator object used for generating notes.
     */
    public static void printChord(Chord chord, ChordGenerator chordGenerator) {
        try {
            List<String> chordNotes = chordGenerator.getChordNotes(chord);
            System.out.println("Generated Chord: " + chord);
            System.out.println("Notes: " + chordNotes);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input. Please enter a valid root note, chord type, and inversion.");
        }
    }

    /**
     * Parses user input and generates a chord based on the input root note, chord type, and inversion.
     *
     * @param input The user input as a string.
     */
    public static void parseInput(String input) {
        ChordGenerator chordGenerator = new ChordGenerator();
        String rootNote, chordType;
        int inversion = 0;

        if ("exit".equalsIgnoreCase(input)) {
            System.out.println("Goodbye!");
            System.exit(0);
        }

        if ("random".equalsIgnoreCase(input)) {
            Chord chord = chordGenerator.generateRandomChord();
            printChord(chord, chordGenerator);
            return;
        }

        String[] inputParts = input.split("\\s+");
        if (inputParts.length < 2 || inputParts.length > 3) {
            System.out.println("Invalid input. Please enter the root note, chord type, and inversion (optional) separated by spaces.");
            return;
        }

        rootNote = inputParts[0].toUpperCase();
        chordType = inputParts[1].toLowerCase();

        if (inputParts.length == 3) {
            try {
                inversion = Integer.parseInt(inputParts[2]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid inversion. Please enter a valid root note, chord type, and inversion.");
                return;
            }
        }

        Chord chord = new Chord(rootNote, chordType, inversion);
        printChord(chord, chordGenerator);
    }

    /**
     * The entry point for the Chord Generator application.
     *
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        String lastCommand = "";
        System.out.println("Welcome to the Chord Generator!");
        System.out.println("Enter the root note (C, C#, D, D#, E, F, F#, G, G#, A, A#, B), chord type (major, minor, diminished, augmented, major7, minor7, dominant7, sus2, sus4), and inversion (0, 1, 2, etc.) separated by spaces.");
        System.out.println("For example: 'C major 1' or 'A# minor7 2'. Type 'random' for random chord generation and 'exit' to quit.");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String input;
            try {
                input = scanner.nextLine().trim();
            } catch (Exception e) {
                return;
            }

            if (input.isEmpty()) {
                input = lastCommand;
            } else {
                lastCommand = input;
            }

            if (input.isEmpty()) {
                return;
            }

            parseInput(input);
        }
    }
}

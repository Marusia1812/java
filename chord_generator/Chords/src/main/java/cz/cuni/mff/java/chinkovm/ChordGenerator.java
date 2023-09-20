package cz.cuni.mff.java.chinkovm;

import java.util.*;

/**
 * A class to generate chords and their notes based on a Chord object.
 */
public class ChordGenerator {
    private static final List<String> NOTES = Arrays.asList("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B");
    private static final List<String> CHORD_TYPES = Arrays.asList("major", "minor", "diminished", "augmented", "major7", "minor7", "dominant7", "sus2", "sus4");

    /**
     * Generates a random Chord object with a random root note, chord type, and inversion.
     *
     * @return A randomly generated Chord object.
     */
    public Chord generateRandomChord() {
        Random random = new Random();
        int randomNoteIndex = random.nextInt(NOTES.size());
        int randomChordTypeIndex = random.nextInt(CHORD_TYPES.size());
        int randomInversion = random.nextInt(3);

        String randomNote = NOTES.get(randomNoteIndex);
        String randomChordType = CHORD_TYPES.get(randomChordTypeIndex);

        return new Chord(randomNote, randomChordType, randomInversion);
    }

    /**
     * Returns a list of notes for a given chord, based on its root note, chord type, and inversion.
     *
     * @param chord The Chord object to generate notes for.
     * @return A list of notes in the chord.
     * @throws IllegalArgumentException If the chord type is invalid.
     */
    public List<String> getChordNotes(Chord chord) {
        int[] intervals;
        switch (chord.getChordType()) {
            case "major":
                intervals = new int[]{0, 4, 7};
                break;
            case "minor":
                intervals = new int[]{0, 3, 7};
                break;
            case "diminished":
                intervals = new int[]{0, 3, 6};
                break;
            case "augmented":
                intervals = new int[]{0, 4, 8};
                break;
            case "major7":
                intervals = new int[]{0, 4, 7, 11};
                break;
            case "minor7":
                intervals = new int[]{0, 3, 7, 10};
                break;
            case "dominant7":
                intervals = new int[]{0, 4, 7, 10};
                break;
            case "sus2":
                intervals = new int[]{0, 2, 7};
                break;
            case "sus4":
                intervals = new int[]{0, 5, 7};
                break;
            default:
                throw new IllegalArgumentException("Invalid chord type");
        }

        int rootIndex = NOTES.indexOf(chord.getRootNote());
        List<String> chordNotes = new ArrayList<>();
        for (int interval : intervals) {
            int noteIndex = (rootIndex + interval) % NOTES.size();
            chordNotes.add(NOTES.get(noteIndex));
        }

        int inversion = chord.getInversion();
        for (int i = 0; i < inversion; i++) {
            String removedNote = chordNotes.remove(0);
            chordNotes.add(removedNote);
        }

        return chordNotes;
    }
}

package cz.cuni.mff.java.chinkovm;

/**
 * Represents a musical chord with a root note, chord type, and inversion.
 */
public class Chord {
    private String rootNote;
    private String chordType;
    private int inversion;

    /**
     * Constructs a Chord object with the specified root note, chord type, and inversion.
     *
     * @param rootNote  The root note of the chord.
     * @param chordType The type of the chord.
     * @param inversion The inversion of the chord.
     */
    public Chord(String rootNote, String chordType, int inversion) {
        this.rootNote = rootNote;
        this.chordType = chordType;
        this.inversion = inversion;
    }

    /**
     * Returns the root note of the chord.
     *
     * @return The root note of the chord.
     */
    public String getRootNote() {
        return rootNote;
    }

    /**
     * Returns the type of the chord.
     *
     * @return The type of the chord.
     */
    public String getChordType() {
        return chordType;
    }

    /**
     * Returns the inversion of the chord.
     *
     * @return The inversion of the chord.
     */
    public int getInversion() {
        return inversion;
    }

    /**
     * Returns a string representation of the chord.
     *
     * @return A string representation of the chord.
     */
    @Override
    public String toString() {
        return rootNote + " " + chordType + " (inversion " + inversion + ")";
    }
}

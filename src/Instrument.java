/**
 * The Instrument interface specifies methods used to play a
 * musical instrument using notes (pitches) mapped to char values.
 * <p>
 * For example, the notes might be mapped to musical notation
 * ("C" = C natural; "d" = D flat; "D" = D natural; "e" = E flat; etc.).
 * Alternatively, the notes might be mapped to a qwerty keyboard
 * representation of a piano keyboard
 * ("q" = A; "2" = B-flat; "w" = B; "e" = C; "4" = C#; "r" = D; etc.).
 */
public interface Instrument {
    /** A mapping for notes based on qwerty-piano locations. */
    String QWERTY_KEY_MAP = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    /**
     * Determines whether the instrument can produce the note corresponding
     * to the specified parameter.
     * @param noteChar the character specifying the note for this query
     * @return true if the instrument contains a note that corresponds
     *              to the specified char; false otherwise
     */
    boolean hasNote(char noteChar);

    /**
     * Initiates the note associated with the specified char.
     * @param noteChar the note to initiate
     */
    void startNote(char noteChar);

    /**
     * Plays the current sound.
     * This is comprised of (sum of) the current energy states
     * of all notes for the instrument.
     */
    void play();

    /**
     * Advance the simulation by one time unit.
     */
    void tick();
}

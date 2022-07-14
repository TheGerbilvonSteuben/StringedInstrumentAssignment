import javax.swing.JFileChooser;
/**
 * Basics for playing a song with notes read from a file.
 */
public interface SongPlayer {
    /**
     * Selects the file containing the song to be played.
     * @return reference to file with the song
     */
    JFileChooser getSong();

    /**
     * Plays the specified song using the specified instrument.
     * @param songFile the file containing the song
     * @param instrument the instrument to play
     */
    void playSong(JFileChooser songFile, Instrument instrument);
}

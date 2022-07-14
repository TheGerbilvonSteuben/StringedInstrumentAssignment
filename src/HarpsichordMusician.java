// Derived from sources by Stuart Reges.
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * Plays a song on a harpsichord.
 */
public class HarpsichordMusician implements SongPlayer {
    /** Differential for prepared music to QWERTY chars. */
    private static final int SHIFT = 12;
    /** Mapping from characters to notes. */
    private String keyboard;

    /**
     * Constructs a harpsichord musician.
     */
    public HarpsichordMusician() {
        this.keyboard = Instrument.QWERTY_KEY_MAP;
    }

    @Override
    public JFileChooser getSong() {
        JFileChooser chooser = null;
        chooser = new JFileChooser(new File("."));
        FileNameExtensionFilter filter =
            new FileNameExtensionFilter("Song Files", "txt");
        chooser.setFileFilter(filter);
        int result = chooser.showOpenDialog(null);
        if (result != JFileChooser.APPROVE_OPTION) {
            chooser = null;
        }
        return chooser;
    }

    @Override
    public void playSong(final JFileChooser songFile,
                         final Instrument instrument) {
        Scanner input = null;
        try {
            input = new Scanner(songFile.getSelectedFile());
        } catch (FileNotFoundException e) {
            System.err.println(e);
            return;
        }

        int elapsed = 0;

        while (input.hasNextInt()) {
            // Read in the note, where 0 = Concert A (A4).
            int note = input.nextInt();
            // Convert the note to the appropriate keyboard index.
            int index = note + SHIFT;
            // If the note is included in the keyboard, start playing it.
            if (index >= 0 && index < this.keyboard.length()) {
                instrument.startNote(keyboard.charAt(index));
            }
            // Read in duration in seconds and convert to a tick count.
            double duration = input.nextDouble();
            int target = elapsed
                         + (int) Math.round(duration * StdAudio.SAMPLE_RATE);
            // Keep playing for that duration.
            while (elapsed < target) {
                instrument.play();
                instrument.tick();
                elapsed++;
            }
        }
    }

    /**
     * Driver that invokes the musician.
     * Asks the user to select a file containing the score for
     *   a song to be performed.
     * @param args ignored
     */
    public static void main(final String[] args) {
        SongPlayer player = new HarpsichordMusician();
        player.playSong(player.getSong(), new Harpsichord());
    }
}

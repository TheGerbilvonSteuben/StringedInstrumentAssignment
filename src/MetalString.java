import java.util.concurrent.ThreadLocalRandom;
import java.util.LinkedList;
/**
 * MetalString objects simulate metal strings that resonate at a specified
 * fundamental frequency.
 * Internally, the object uses the Karplus-Strong
 * algorithm to simulate changes in the string's vibration.
 *
 * @author Jerret Stovall
 * @version 08/20/2020
 *
 *
 */
public class MetalString implements MusicalString {

    /** Decay factor appropriate for a plucked string. */
    public static final double ENERGY_DECAY_FACTOR = 0.996;
    /**
     * The upper bound on the update function.
     * */
    private static final double LOWER_BOUND = -.5;
    /**
     * The lower bound on the update function.
     * */
    private static final double UPPER_BOUND = .5;

    /** Number of times tick() has been called.  */
    private int ticks;
    /** Circular buffer needed for Karplus - Strong Update. */
    private LinkedList<Double> buffer;
    /** Rate needed to determine buffer size. */
    private final double sampleRate = StdAudio.SAMPLE_RATE;
    /** n is calculated by the given frequency and sampling size.*/
    private long n;

    /**
    * Construct a metal harpsichord string of the given frequency.
    * @param frequency The frequency of the desired MetalString object.
    * @exception IllegalArgumentException Will throw an exception
    *if frequency is 0 or if list size is less than 2.
    * */
    public MetalString(final double frequency)
        throws IllegalArgumentException {

        //  n calculates the size of the list needed.
        n = Math.round(sampleRate / frequency);

        // Throw the appropriate exceptions for invalid inputs
        if (frequency <= 0) {

            throw new IllegalArgumentException("Invalid MetalString "
            + "Frequency: " + frequency);

        } else if (n < 2) {

            throw new IllegalArgumentException("Invalid list size: " + n);

        } //End else if

        // Create the buffer with size n and fill it with zeros.
        buffer = new LinkedList<Double>();

        for (int i = 0; i < n; i++) {

            buffer.add((double) 0);

        } //End for

        //Initialize amount of time passed.
        ticks = 0;

        }

    /**
     * This method simulates the plucking of the string.
     * */
    public void pluck() {

        // Pluck the string.
        for (int i = 0; i < buffer.size(); i++) {

            // Fill the buffer with values between -.5 and .5.
            double randomNum = ThreadLocalRandom.
                 current().nextDouble(LOWER_BOUND, UPPER_BOUND);
            buffer.set(i, randomNum);

        } //End for

    } //End pluck

    /**
     * This method advances the simulation one time step.
     * It also applies the Karplus-Strong update to the buffer
     * once per method call.
     * */
    public void tick() {

        ticks++;

        // Average the sum the first two elements in the buffer
        // and multiply by the decay factor to begin the Karplus-Strong update.
        double newNum = ((buffer.get(0) + buffer.get(1)) / 2)
                                           * ENERGY_DECAY_FACTOR;

        // Remove the first element and replace the last element with
        // newNum to complete the update.
        buffer.removeFirst();
        buffer.addLast(newNum);

    } //End tick

    /**
     * This method returns the current audio sample.
     * @return double A sample of the current audio
     * */
    public double sample() {

        return buffer.get(0);

    } //End sample

    /**
    * This method returns the number of times that the tick method
    * has been called, reflecting the amount of time passed.
    * @return ticks Number of time units accrued.
    * */
    public int now() {

        return ticks;

    } //End now
} //End MetalString

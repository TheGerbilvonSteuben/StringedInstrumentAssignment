/**
 * MusicalString objects simulate musical instrument strings
 * that resonate at a specified fundamental frequency.
 */
public interface MusicalString {
    /**
     * Simulates plucking the string with a plectrum.
     */
    void pluck();

    /**
     * Advance the simulation one time step.
     */
    void tick();

    /**
     * Accesses the current audio sample value.
     * @return the current sample value
     */
    double sample();

    /**
     * Accesses the amount of simulated time steps that have elapsed.
     * @return the number of times tick has been called
     */
    int now();
}

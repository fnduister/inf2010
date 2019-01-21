package SAC;

/**
 * Hello world!
 */
public final class Main {
    private Main() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        SAC.Sac<Integer> sac = new SAC.Sac<Integer>();
        SAC.Pile<String> pile = new SAC.Pile<String>();
        System.out.println("Hello World!");
    }
}

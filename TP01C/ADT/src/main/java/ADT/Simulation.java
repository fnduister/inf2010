package ADT;

import java.util.Random;

public class Simulation {
    private static Random random = new Random();

    // Retourne un nombre réel aléatoire uniformément dans [0,1[
    public static double uniform() {
        // completer
        return 0;
    }

    // Retourne un nombre entier aléatoire uniformément dans [0,n[
    public static int uniform(int n) {
        // completer
        return 0;
    }

    // Retourne un entier long aléatoire uniformément dans [0, n[.
    // Vous pouviez trouver le code
    // https://docs.oracle.com/javase/8/docs/api/java/util/Random.html#longs-long-long-long-
    public static long uniform(long n) {
        if (n <= 0L)
            throw new IllegalArgumentException("Argument doit etre positive: " + n);

        long r = random.nextLong();
        long m = n - 1;

        if ((n & m) == 0L) {
            return r & m;
        }

        long u = r >>> 1;
        while (u + m - (r = u % n) < 0L) {
            u = random.nextLong() >>> 1;
        }
        return r;
    }

    // Retourne avec succès un booléen true si p suit d'une distribution de
    // Bernoulli
    public static boolean bernoulli(double p) {
        // completer
        return true;
    }

    public static Compteur max(Compteur x, Compteur y) {
        // completer
        return new Compteur("id");
    }

    public static void main(String[] args) {
        System.out.println("Hello World");
        long x = 23423423;
        uniform(x);
        int n = 10;long p = 12; double d = 34;
        Compteur pile = new Compteur("pile");
        Compteur face = new Compteur("face");

        // Les instructions du simulation
        // completer
        // afficher la différence entre les score des compteur

        Compteur pile_c = new Compteur("pile");
        Compteur face_c = new Compteur("face");
        
        System.out.print(uniform(34));
        System.out.print(uniform(34));
        System.out.print(uniform(34));


        // Les instructions du simulation
        // completer
        // afficher le maximum entre les score des compteur

    }

}

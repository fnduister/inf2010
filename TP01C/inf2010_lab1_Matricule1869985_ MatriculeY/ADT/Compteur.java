package ADT;

import java.util.Random;

public class Compteur implements Comparable<Compteur> {

     private final String nom; // nom du compteur
     private int compteur = 0; // initialisation la valeur courant est 0

     public Compteur(String id) {
          nom = id;
     }

     public void increment() {
          compteur++;
     }

     public int score() {
          return 0;
     }

     public String toString() {
          return this.nom + " obtient un score de " + this.score() + " pour un compte de " + this.compteur;
     }

     public int compareTo(Compteur x) {
          return this.score() - x.score();
     }

     private static Random random = new Random(10000);

     // Retourne un nombre entier aléatoire uniformément dans [0,n[
     public static int uniform(int n) {
          return random.nextInt(n);
     }

     public static void main(String[] args) {
          int n = 6;
          int essais = 60000;

          // Creation n compteurs
          Compteur compteurs[] = new Compteur[n];

          for (int i = 0; i < n; i++) {
               String name = "compteur" + i;
               compteurs[i] = new Compteur(name);
          }

          // incrémente les compteurs d'essais au hasard
          for (int i = 0; i < essais; i++) {
               int randomIndex = uniform(n);
               compteurs[randomIndex].increment();
          }

          // Affichage des resultat
          for (Compteur compteur : compteurs) {
               System.out.print(compteur.toString() + System.lineSeparator());
          }

     }
}

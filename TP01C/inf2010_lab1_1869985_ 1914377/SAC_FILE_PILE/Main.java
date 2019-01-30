package SAC;

/**
 * Hello world!
 */
public final class Main {
    private Main() {
    }

    public static class ClassTest {
        public ClassTest(Integer nombre, String mot) {
            this.nombre = nombre;
            this.mot = mot;
        }

        public void read() {
            System.out.println("mot: " + mot + System.lineSeparator() + "nombre: " + nombre);
        }

        Integer nombre;
        String mot;
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        ClassTest test = new ClassTest(45, "papa");
        ClassTest test1 = new ClassTest(49, "dori");
        ClassTest test2 = new ClassTest(56, "bastian");
        ClassTest test3 = new ClassTest(78, "poly");

        SAC.Sac<Integer> sac = new SAC.Sac<Integer>();
        SAC.Pile<String> pile = new SAC.Pile<String>();
        SAC.File<ClassTest> file = new SAC.File<ClassTest>();

        sac.add(32);
        sac.add(45);
        sac.add(409);
        sac.add(4555);
        sac.add(4509);

        file.enfile(test);
        file.enfile(test1);
        file.enfile(test2);
        file.enfile(test3);

        pile.enfile("premier");
        pile.enfile("deuxieme");
        pile.enfile("troisieme");
        pile.enfile("quatrieme");
        pile.enfile("cinquieme");

        System.out.println("AFFICHE PILE:");
        System.out.println(" ");

        for (String mot : pile) {
            System.out.println(mot);
        }
        System.out.println(" ");

        System.out.println("AFFICHE FILE:");
        System.out.println(" ");

        for (ClassTest fileElement : file) {
            fileElement.read();
        }
        System.out.println(" ");

        System.out.println("AFFICHE SAC:");
        System.out.println(" ");

        for (Integer nombre : sac) {
            System.out.println(nombre);
        }

        if (pile.estVide() || sac.estVide() || file.estVide()) {
            System.out.println("quelquechose n'est pas vide");
        }

        System.out.println(" ");
        System.out.println(" ");
        System.out.println("FILE: mot qu'on vient de defiler: " + pile.defile());
        System.out.println("FILE: nouveau dernier element: " + pile.voirDernierElement());
        System.out.println(" ");
        System.out.println("PILE: nombre qu'on vient de defiler: ");
        file.defile().read();
        System.out.println("PILE: nouveau premier element: ");
        file.voirPremierElement().read();
        System.out.println(" ");

        System.out.println("FILE: nombre d'elements: " + file.size());
        System.out.println("SAC: nombre d'elements " + sac.size());
        System.out.println("PILE: nombre d'elements: " + pile.size());
    }
}

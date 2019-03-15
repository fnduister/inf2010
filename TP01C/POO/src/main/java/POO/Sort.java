package POO;

public final class Sort {
    public static <AnyType extends Comparable<? super AnyType>> void quicksort(AnyType[] a) {
        quicksort(a, 0, a.length - 1);
    }

    private static final int CUTOFF = 5;
    private static int nbrAppelRecursion = 0;

    public static <AnyType> void swapReferences(AnyType[] a, int index1, int index2) {
        AnyType tmp = a[index1];
        a[index1] = a[index2];
        a[index2] = tmp;
    }

    private static <AnyType extends Comparable<? super AnyType>> AnyType median3(AnyType[] a, int left, int right) {
        int center = (left + right) / 2;
        if (a[center].compareTo(a[left]) < 0)
            swapReferences(a, left, center);
        if (a[right].compareTo(a[left]) < 0)
            swapReferences(a, left, right);
        if (a[right].compareTo(a[center]) < 0)
            swapReferences(a, center, right);
        swapReferences(a, center, right - 1);
        return a[right - 1];
    }

    private static <AnyType extends Comparable<? super AnyType>> void quicksort(AnyType[] a, int left, int right) {
        System.out.println("DEBUG: call to quicksort: " + ++nbrAppelRecursion);

        if (left + CUTOFF <= right) {
            AnyType pivot = median3(a, left, right);
            System.out.println("DEBUG: Subarray after call to median3: " + pivot);
            printArray(a, left, right);
            int i = left, j = right - 1;
            for (;;) {
                while (a[++i].compareTo(pivot) < 0) {
                }
                while (a[--j].compareTo(pivot) > 0) {
                }
                System.out.println("DEBUG: Je viens de swipper " + i + " avec " + j + " valeurs " + a[i] + "  " + a[j]);
                if (i < j)
                    swapReferences(a, i, j);
                else
                    break;
            }
            swapReferences(a, i, right - 1);

            System.out.println("DEBUG: Subarray after repositionning pivot " + a[i]);
            printArray(a, left, right);

            quicksort(a, left, i - 1);
            quicksort(a, i + 1, right);
        } else
            insertionSort(a, left, right);
    }

    public static <AnyType extends Comparable<? super AnyType>> void printArray(AnyType[] a) {
        printArray(a, 0, a.length - 1);
    }

    private static <AnyType extends Comparable<? super AnyType>> void printArray(AnyType[] a, int left, int right) {
        for (int k = left; k <= right; k++)
            System.out.print(a[k] + " ");
        System.out.println();
    }

    private static <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType[] a, int left, int right) {
        System.out.println("DEBUG: call to insertion sort");
        printArray(a, left, right);

        for (int p = left + 1; p <= right; p++) {
            AnyType tmp = a[p];
            int j;
            for (j = p; j > left && tmp.compareTo(a[j - 1]) < 0; j--)
                a[j] = a[j - 1];
            a[j] = tmp;
        }
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[16];
        for (int i = 0; i < a.length; i++) {
            int val = (2 * i * i - 3 * i + 15) % 16 + 1;
            a[i] = (val < 0) ? -val : val;
        }

        printArray(a);
        quicksort(a);
        printArray(a);
    }
}
public class RationalTest1 {

  private static void testa(int m, int n) {
    int z = Rational.sgd(m,n);
    int a = m, b = n;
    int r = a % b;
    while (r != 0) {
      a = b;
      b = r;
      r = a % b;
    }

    System.out.print("Talen är " + m + " och " + n +
                     ".\tDitt resultat: " + z +
                     ".\tRätt resultat: " + b + ".");
    if (z != b)
      System.out.println("  *************");
    else
      System.out.println();
  }

  public static void main (String[] arg) {
    testa(1, 1);
    testa(12, 1);
    testa(12, 2);
    testa(12, 14);
    testa(22, 14);
    testa(39, 15);
    testa(40, 12);
    testa(168, 49);
    testa(143, 7);
    testa(1260, 36);
    testa(15775, 100);
    testa(15776, 100);
    testa(15775, 12);
  }
}


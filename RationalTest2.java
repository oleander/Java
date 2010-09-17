public class RationalTest2 {

  private static void fel(int nr) {
    System.out.println("RationalTest2: Fel nummer " + nr);
    System.exit(1);
  }

  public static void divTester() {
    Rational r = new Rational();


    // test av set
    r.set(9);
    if (r.getNumerator() != 9 || r.getDenominator() != 1)
      fel(1);
    r.set(4, 9);
    if (r.getNumerator() != 4 || r.getDenominator() != 9)
      fel(2);
    r.set(49, 168);
    if (r.getNumerator() != 7 || r.getDenominator() != 24)
      fel(3);
    Rational r2 = new Rational();
    r2.set(r);
    if (r2.getNumerator() != 7 || r2.getDenominator() != 24)
      fel(4);

    // test av konstruktorer
    Rational x = new Rational();
    if (x.getNumerator() != 0 || x.getDenominator() != 1)
      fel(5);
    Rational y = new Rational(5);
    if (y.getNumerator() != 5 || y.getDenominator() != 1)
      fel(6);
    Rational z = new Rational(20, 4);
    if (z.getNumerator() != 5 || z.getDenominator() != 1)
      fel(7);
    Rational w = new Rational(0,1);
    if (w.getNumerator() != 0 || w.getDenominator() != 1)
      fel(8);
    Rational q = new Rational(y);
    if (q.getNumerator() != 5 || q.getDenominator() != 1)
      fel(9);


    // test av negativa parametrar
    r.set(-49, 168);
    if (r.getNumerator() != -7 || r.getDenominator() != 24)
      fel(10);
    r.set(49, -168);
    if (r.getNumerator() != -7 || r.getDenominator() != 24)
      fel(11);
    r.set(-49, -168);
    if (r.getNumerator() != 7 || r.getDenominator() != 24)
      fel(12);

  }

  public static void main(String[] arg) {
    divTester();
    System.out.println("Inga fel!");
  }
}


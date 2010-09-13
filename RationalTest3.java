import java.io.*;
import java.util.*;

public class RationalTest3 {

  private static void divTester() {
    Rational x = new Rational();
    Rational y = new Rational(5);
    Rational z = new Rational(20, 4);
    Rational w = new Rational(0,1);

    y.set(0); z.set(0,1); x.set(6,2); w.set(75,25);
    if (!y.equals(z) || !x.equals(w))
      System.out.println("RationalTest3: FEL i equals!!");
    y = (Rational) x.clone();
    if (!y.equals(x) || y==x)
       System.out.println("RationalTest3: FEL I clone!!");
  }

  public static void main(String[] arg) throws IOException {
    RationalTest2.divTester();
    divTester();
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Skriv uttryck på formen a/b ? c/d, där ? är något av tecknen + - * / = <");
    while (true) {
      System.out.print("> ");  System.out.flush();
      String s = in.readLine();
      if (s == null)
        break;
      Scanner sc = new Scanner(s);
      String[] a = new String[3];

      int i;
      for(i=0; i<3 && sc.hasNext(); i++)
        a[i] = sc.next();
      if (i > 0 ) {
        System.out.print(s + "\t--> ");
        if (i != 3 || sc.hasNext())
          System.out.println("Felaktigt uttryck!");
        else {
          Rational r1 = Rational.parse(a[0]);
          String op = a[1];
          char c = op.charAt(0);
          Rational r2 = Rational.parse(a[2]);
          if (r1 == null || r2 == null || op.length() != 1 || "+-*/=<".indexOf(c) < 0)
            System.out.println("Felaktigt uttryck!");
          else {
            Rational res = null;
            if (c == '+')
              res = r1.add(r2);
            else if (c == '-')
              res = r1.sub(r2);
            else if (c == '*')
              res = r1.mul(r2);
            else  if (c == '/')
              res = r1.div(r2);
            else if (c == '=')
              System.out.println(r1.equals(r2));
            else if (c == '<')
              System.out.println(r1.lessThan(r2));

            if ("+-*/".indexOf(c) >= 0)
              if (res == null)
                System.out.println("FEL i add, sub, mul eller div");
              else
                System.out.println(res);
          }
        }
      }
    }
  }
}


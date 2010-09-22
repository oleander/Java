import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rational{
  
  /* Bättre och mer lättstavade namn är
     (bottom) denominator och (top) numerator
  */
  private int top;
  private int bottom;
  
  /* Konstruktorer */
  public Rational(){
    this.set(0,1);
  }
  
  public Rational(int a, int b){
    this.set(a,b);
  }
  
  public Rational(int a){
    this.set(a,1);
  }
  
  public Rational(Rational r){
    this.set(r);
  }
  
  /* Retunerar täljare och nämnare */
  public int getNumerator(){
    return this.top;
  }
  
  public int getDenominator(){
    return this.bottom;
  }
  
  /* Retunerar minsta gemensamma delare */
  public static int sgd(int first, int last){
    
    /* Om båda talen är negativa så kan vi lika väl säga 
       att talen tillsammans är positiva
    */
    if(first < 0 && last < 0){
      return sgd(first*-1, last*-1);
    }
    
    int m, n, r;
    /* m = stort tal
       n = litet tal */
     m = first <= last ? last : first;
     n = first > last ? last : first;
     
     if(n == 0){
       return 1;
     }
     
     /* Kör loopen tills dess att r är noll 
        En do while-loop skulle fungera lika bra, 
        Men denna koden är aningen kortare
    */
     while(true){
      r = m % n;

      if (r == 0){
        return n;
      }

      m = n; n = r;
    }
  }
  
  /* Sätter ingående värden.
     Avbryter om det är så att b är noll
  */
  public void set(int a, int b){
    if(b == 0){
      System.err.println("b är 0");
      System.exit(1);
    }
    
    int sgd   = this.sgd(a,b);
    
    this.top    = a / sgd;
    this.bottom = b / sgd;
    
    if (this.bottom < 0){
      this.top    *= -1;
      this.bottom *= -1;
    }
  }
  
  public void set(Rational r){
    this.top    = r.getNumerator();
    this.bottom = r.getDenominator();
  }
  
  public void set(int a){
    this.set(a,1);
  }
  
  /* Gör om den nuvarande objektet till en sträng på formen n/x, där n och x är heltal */
  public String toString(){
    return (Integer.toString(this.top) + "/" + Integer.toString(this.bottom));
  }
 
  /* Gör det möjligt att plocka tolka värden (integers) ur en sträng */
  public static Rational parse(String value){
    
    /* Matchar (där a och b är heltal):
       a/b
       -a/-b
       -a/b
       a/-b
    */
    Pattern p = Pattern.compile("^(-?\\d+)(\\/(-?[1-9]{1}\\d*))?$");
    Matcher m = p.matcher(value);
    int b = 1, a;
    
    /* Hittades något utifrån uttrycket ? */
    if (m.find()){
      a = Integer.valueOf(m.group(1));
      
      if(m.group(3) != null){
        b = Integer.valueOf(m.group(3));
      }
      
      return new Rational(a,b);
    }
    return null;
  }
  
  /* Skapar en klon av det nuvarnade objektet */
  public Object clone(){
    Rational r = new Rational(this.getNumerator(), this.getDenominator());
    return r;
  }
  
  /* Kontrollerar ifall två objekt är lika
     Def av lika:
        Sträng-representationen av de två objekten måste vara lika
        Det ska alltså peka på samma värde 
  */
  public boolean equals(Rational r){
    int[] ret = this.same(r);
    return ret[0] == ret[1];
  }
  
  /* Retunerar en lista med Rationals som har samma bas */
  public int[] same(Rational b){
    int[] ret = new int[2];
    
    ret[0] = this.getNumerator() * b.getDenominator();
    ret[1] = this.getDenominator() * b.getNumerator();
    return ret;
  }
  
  /* Gör om de två objekten till floats och jämför dem
     Kan ge fel då a och b är lika och går mot oändligheten,
     men fungerar för mindre tal */
  public boolean lessThan(Rational r){
    int[] ret = this.same(r);
    return ret[0] < ret[1];
  }
  
  /* Metoderna nedan;
     Adderar
     Summerar
     Multiplicerar
     och Dividerar
  */
  public Rational add(Rational r){
    int[] top = this.same(r);
    int t = top[0] + top[1];    
    return new Rational(t, (this.getDenominator() * r.getDenominator()));
  }
  
  public Rational sub(Rational r){
    int[] top = this.same(r);
    int t = top[0] - top[1];
    return new Rational(t, (this.getDenominator() * r.getDenominator()));
  }

  public Rational mul(Rational r){
    int top    = this.getNumerator() * r.getNumerator();
    int bottom = this.getDenominator() * r.getDenominator(); 
    
    return new Rational(top, bottom);
  }
  
  public Rational div(Rational r){
    int top    = this.getNumerator() * r.getDenominator();
    int bottom = this.getDenominator() * r.getNumerator(); 
    
    return new Rational(top, bottom);
  }
}
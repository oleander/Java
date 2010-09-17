import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rational{
  
  private int top;
  private int bottom;
  
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
  
  public int getNumerator(){
    return this.top;
  }
  
  public int getDenominator(){
    return this.bottom;
  }
  
  /* Retunerar minsta gemensamma delare */
  public int sgd(int first, int last){
    
    /* Om båda talen är negativa så kan vi lika väl säga 
       att talen tillsammans är positiva
    */
    if(first < 0 && last < 0){
      return this.sgd(first*-1, last*-1);
    }
    
    int m, n, r;
    /* m = stort tal
       n = litet tal */
     m = first <= last ? last : first;
     n = first > last ? last : first;
     
     if(n == 0){
       return 1;
     }
     
     
     while(true){
      r = m % n;

      if (r == 0){
        return n;
      }

      m = n; n = r;
    }
  }
  
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
  
  public String toString(){
    return (Integer.toString(this.top) + "/" + Integer.toString(this.bottom));
  }
 
  public static Rational parse(String value){
    Pattern p = Pattern.compile("^(-?\\d+)(\\/(-?[1-9]{1}\\d*))?$");
    Matcher m = p.matcher(value);
    int b = 1, a;
    
    while (m.find()){
      a = Integer.valueOf(m.group(1));
      
      if(m.group(3) != null){
        b = Integer.valueOf(m.group(3));
      }
      
      return new Rational(a,b);
    }
    return null;
  }
  
  public Object clone(){
    Rational r = new Rational(this.getNumerator(), this.getDenominator());
    return (Object) r;
  }
  
  public boolean equals(Rational r){
    return r.toString().equals(this.toString());
  }
  
  private float getValue(Rational r){
    return this.getValue(r.getNumerator(), r.getDenominator());
  }
  
  private float getValue(int a, int b){
    return a / b;
  }
  
  private float getValue(){
    return this.getValue(this.getNumerator(), this.getNumerator());
  }
  
  public boolean lessThan(Rational r){
    float current = this.getNumerator() / this.getDenominator();
    float in = r.getNumerator() / r.getDenominator();
    return current < in;
  }
  

  public Rational add(Rational r){
    int top = (this.getDenominator() * r.getNumerator() + this.getNumerator() * r.getDenominator());
    return new Rational(top, (this.getDenominator() * r.getDenominator()));
  }
  
  public Rational sub(Rational r){
    int top = (this.getDenominator() * r.getNumerator() - this.getNumerator() * r.getDenominator());
    return new Rational(top, (this.getDenominator() * r.getDenominator()));
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
public class Rational{
  public static int sgd(int first, int last){
    int m, n, r;
    /* m = stort tal
       n = litet tal */
     m = first < last ? last : first;
     n = first > last ? last : first;
    while(true){
      r = m % n;
    
      if (r == 0){
        return n;
      }

      m = n; n = r;
    }
  }
}
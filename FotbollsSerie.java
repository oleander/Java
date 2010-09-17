import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Collections;

class FotbollsSerie {
  /* Namnet på serien */
  private String name;
  
  /* Listan med alla lag i */ 
  private ArrayList<FotbollsLag> teams;
  
  public static void main(String [] args){
    String team_name = "The rails super team";
    String t1 = "Lag1";
    String t2 = "Lag2";
    
    /* Antalet mål som lag ett gör respektive lag 2 */
    int g1 = 10;
    int g2 = 20;
    
    /* Skapar två lag med 10 respektive 20 spelare i */
    FotbollsLag team1 = new FotbollsLag(t1, 10);
    FotbollsLag team2 = new FotbollsLag(t2, 20);
    
    ArrayList<FotbollsLag> fbl = new ArrayList<FotbollsLag>();
    
    fbl.add(team2);
    fbl.add(team1);
    
    FotbollsSerie serie = new FotbollsSerie(team_name, fbl);
    
    /* Kontrollerar att laget vi nyss stoppade in verkligen finns */
    if(serie.getLag(t1) != team1){
      System.err.println("Error 1");
      System.exit(1);
    }
    
    /* Gör samma som ova, fast för lag 2 */
    if(serie.getLag(t2) != team2){
      System.err.println("Error 2");
      System.exit(1);
    }
    
    /* Kontrollerar så att alla lag verkligen finns med, igen 
       Simulerar sedan ett spel, där angiva mål används */
    if(!serie.registreraMatch(t1, t2, g1, g2)){
      System.err.println("Error 3");
      System.exit(1);
    }
    
    /* Finns lagens namn med i match-serie-resultatet ? */
    boolean match_1 = serie.toString().indexOf(t1) >= 0;
    boolean match_2 = serie.toString().indexOf(t2) >= 0;
    
    if(!match_2 || !match_1){
      System.err.println("Error 4");
      System.exit(1);
    }
    
    /* Kontrollerar så att utgående matchresultat innehåller en korrekt struktur */
    for(String tmp : serie.toString().split("\n")){
      if(!tmp.matches(".+ \\d+ \\d+ \\d+ \\d+ \\d+ \\d+-\\d+ \\d+") && tmp.length() > 0){
        System.err.println("Error 5, value: " + tmp);
        System.exit(1);
      }
    }
    
    /* Sortera tabellen efter match-resultat */
    serie.sortTabell();
    
    /* Kontrollerar att första laget i listan verkligen är laget som vunnit mest
       I vårt fall så ska lag två ligga längst upp, 
       eftersom dom har gjort 20 mål vs 10 mål som lag ett gjort 
    */
    String[] s = serie.toString().split("\n");
    if(!(s[1].indexOf(t2) >= 0)){
      System.err.println("Error 6");
      System.exit(1);
    }
    
    /* Här bör vi då finnas lag ett */
    if(!(s[2].indexOf(t1) >= 0)){
      System.err.println("Error 7");
      System.exit(1);
    }
  }
  
  public FotbollsSerie(String name, ArrayList<FotbollsLag> teams){
    this.name  = name;
    this.teams = teams;
  }
  
  /* Hämtar angivet lag ur listan av lag */
  public FotbollsLag getLag(String name){
    
    /* Letar igenom hela listan med lag, hittas något så retuneras laget
       Annars retuneras null
    */
   
    for(FotbollsLag team : this.teams){
      if(name.equals(team.getName())){
        return team;
      }
    }
    
    return null;
  }
  
  /* Anger poängställningen mellan två lag 
     Vi börjar med att leta upp lagen, finns inte båda så retuneras false, annars true
     Hittas lagen så uppdateras deras poängställning 
  */
  public boolean registreraMatch(String name1, String name2, int goals1, int goals2){
    FotbollsLag team_1 = null;
    FotbollsLag team_2 = null;
    
    /* Finns lag ett och två ? */
    if((team_1 = this.getLag(name1)) != null && (team_2 = this.getLag(name2)) != null){
      
     /* Uppdaterar lagen med poäng-informationen */
     team_1.registreraMatch(goals1,goals2);
     team_2.registreraMatch(goals2,goals1);
     return true;
    }
    return false;
  }
  
  /* Läser in match-resultatet på formen:
     Rails Team-Rails Team2 0-5 
     eller
     Rails Team;Rails Team2 0;5 
     med godstyckligt antal mellanslag */
  public void readResult(){
    String input   = JOptionPane.showInputDialog(null, "Enter your team");
    Pattern p      = Pattern.compile("^\\s?(.+)\\s?[-|;]\\s?(.+)\\s(\\d+)[-|;](\\d+)\\s?$");
    Matcher m      = p.matcher(input);
    String team1   = null;
    String team2   = null;
    int value1     = 0;
    int value2     = 0;
    boolean result = false;
     while (m.find()){       
       team1  = m.group(1);
       team2  = m.group(2);
       value1 = Integer.valueOf(m.group(3));
       value2 = Integer.valueOf(m.group(4));
       result = this.registreraMatch(team1, team2, value1, value2);
     }
     
     if(result){
       JOptionPane.showMessageDialog(null, "Done");
     } else{
       JOptionPane.showMessageDialog(null, "No match");
     }
  }
  
  /* Sorterar listan med lag efter antalet poäng (bl.a) */
  public void sortTabell(){
    Collections.sort(this.teams, Collections.reverseOrder());
  }
  
  public String toString(){
    String r = "";
    
    for(FotbollsLag f : teams){
      r += "\n" + f;
    }
    return r;
  }
}
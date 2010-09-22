/* Testar FotbollsLag-klassen */
public class Test {

  public static void main(String[] args){
     /* Namnet på teamet */
    String team_name = "Rails Team";
    
    /* Antalet spelare i laget */
    int players      = 40;
    
    /* Antalet gjorda mål */
    int got          = 10;
    
    /* Antalet insläppta mål */
    int let          = 5;
    
    FotbollsLag team = new FotbollsLag(team_name, players);
    FotbollsLag team2 = new FotbollsLag(team_name, players + players);
    
    /* returneras samma namn som stoppades in ? */
    if(!team_name.equals(team.getName())){
      System.err.println("Error 1");
      System.exit(1);
    }
    
    /* Kontrollerar antalet spelare */
    if(players != team.getPlayers()){
      System.err.println("Error 2");
      System.exit(1);
    }
    
    /* Kontrollerar antalet spelare för team2, players-variabeln ska inte ha samma värde som team ett */
    if(players == team2.getPlayers()){
      System.err.println("Error 3");
      System.exit(1);
    }
    
    /* Ska generera vinst då laget har gjort 10 mål och släppt in 5 */
    team.registreraMatch(got,let);
    team2.registreraMatch(let, got);
    
    /* Kontrollerar så att antalet poäng för laget är 3 */
    if(team.getPoints() != 3){
      System.err.println("Error 5");
      System.exit(1);
    }
    
    /* Kontrollerar så att antalet vister är 1 */
    if(team.getWins() != 1){
      System.err.println("Error 6");
      System.exit(1);
    }
     
    /* Antalet förluster ska vara 0 */
    if(team.getLosses() != 0){
      System.err.println("Error 7");
      System.exit(1);
    }
    
    /* Skillnaden i poäng ska vara differensen mellan gjorda mål och insläppta */
    if(team.getDiff() != (got - let)){
      System.err.println("Error 8");
      System.exit(1);
    }
    
    /* Kontrollerar så att team 0 vann */
    if(team.compareTo(team2) != 1){
      System.err.println("Error 9");
      System.exit(1);
    }
    
    /* Kontrollerar så att team 0 vann, igen */
    if(team2.compareTo(team) != -1){
      System.err.println("Error 11");
      System.exit(1);
    }
    
    /* Låter lag 2 spela oavgjort mot lag ett */
    team2 = new FotbollsLag(team_name, 20);
    team2.registreraMatch(got, let);
    
    if(team2.compareTo(team) != 0){
       System.err.println("Error 12");
       System.exit(1);
    }
     
    /* Kontrollerar så att stringklassen inte är en lika med ett FotbollsLags-objekt */
    if(team2.equals(team_name)){
       System.err.println("Error 13");
       System.exit(1);
    }
    
    /* Kontrollerar så att två olika FotbollsLags-objekt med samma namn är lika */
    if(!team2.equals(team)){
      System.err.println("Error 14");
      System.exit(1);
    }
    
    /* Kontrollerar så att strängen som toString genererar är samma som om vi själva skulle plockat ut värden m.h.a get-metoderna */
    if(team2.toString().equals(team_name + " " + Integer.toString(players) + " " + Integer.toString(team2.getMatches()) + " " + Integer.toString(team2.getWins()) + " " + Integer.toString(team2.getDraws()) + " " + Integer.toString(team2.getLosses()) + " " + team2.getGoals() + " " + Integer.toString(team2.getPoints()))){
      System.err.println("Error 15");
      System.exit(1);
    }
    
    System.out.println("OK!");
  }
}
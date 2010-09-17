import java.util.ArrayList;

class Run {
  public static void main (String[] args) {
    
    
    FotbollsLag team1 = new FotbollsLag("BK Hejsan", 10);
    FotbollsLag team2 = new FotbollsLag("BK Nej", 10);
    FotbollsLag team3 = new FotbollsLag("BKKK", 10);
    
    ArrayList<FotbollsLag> list = new ArrayList<FotbollsLag>();
    
    list.add(team1);
    list.add(team2);
    list.add(team3);
    
        
    FotbollsSerie allsvenskan = new FotbollsSerie("allsvenskan", list);
    allsvenskan.registreraMatch("BK Hejsan", "BK Nej", 10, 20);
    allsvenskan.registreraMatch("BK Nej", "BKKK", 10, 20);
    allsvenskan.registreraMatch("BK Hejsan", "BKKK", 100, 20);
    
    System.out.println(allsvenskan.toString());
  }}
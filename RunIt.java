import java.util.ArrayList;
class RunIt {
  public static void main(String [] in){
    FotbollsLag team = new FotbollsLag("Rails Team", 10);
    FotbollsLag team2 = new FotbollsLag("Rails Team2", 20);
    ArrayList<FotbollsLag> teams = new ArrayList<FotbollsLag>();
    teams.add(team);
    teams.add(team2);
    FotbollsSerie serie = new FotbollsSerie("Chalmers", teams);
    serie.readResult();
  }
}
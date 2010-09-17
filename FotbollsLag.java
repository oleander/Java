class FotbollsLag implements Comparable<FotbollsLag>{
  /* Lagets namn */
  private String team;
  
  /* Antalet spelare i laget */
  private int players;
  
  /* Antalet spelade matcher */
  private int matches;
  
  /* Antalet vunna matchert */
  private int wins;
  
  /* Antalet förlorade matcher */
  private int losses;
  
  /* Antalet gjorda mål resp. antalet insläppta mål 
     Te.x 10-20, där 10 är antalet gjorda mål och 20 antalet insläppta
  */
  private String goals;
  
  /* Antalet oavgjorda matcher */
  private int draws;
  
  /* Antalet poäng */
  private int points;
  
  public FotbollsLag(String team, int players){
    this.team    = team;
    this.players = players;
    this.wins    = 0;
    this.losses  = 0;
    this.points  = 0;
    this.matches = 0;
    this.draws   = 0;
    this.goals   = "0-0";
    
    /* Sätter antalet mål som gjorts och antalet som släppts in */
    this.setGoals(0,0);
  }
  
  /* Sätter antalet gjorda mål resp. antalet insläppta mål */
  public void setGoals(int first, int last){
    
    /* Hämtar nuvarande insläppta och gjorda mål */
    int[] current = this.getGoals();
    
    /* Adderas ingående värde på de nuvarande värdet, sparar sedan undran resultatet */
    String goals = Integer.toString(first + current[0]) + "-" + Integer.toString(last + current[1]);
    
    this.goals = goals;
  }
  
  public int [] getGoals(){
    String[] tmp = this.goals.split("-");
    int[] x = new int[8];
    int a;
    int b;
    a = Integer.valueOf(tmp[0]);
    b = Integer.valueOf(tmp[1]);
    x[0] = a;
    x[1] = b;
    return x;
  }
  
  public int getPoints(){
    return this.points;
  }
  
  public String getName(){
    return this.team;
  }
  
  public int getPlayers(){
    return this.players;
  }
  
  public int getWins(){
    return this.wins;
  }
  
  public int getMatches(){
    return this.matches;
  }
  
  public int getLosses(){
    return this.losses;
  }
  
  public int getDraws(){
    return this.draws;
  }
  
  /* Antalet gjorda mål */
  public int getDoneGoals(){
    int[] tmp = this.getGoals();
    return tmp[0];
  }
  
  /* Antalet insäppta mål */
  public int getLetGoals(){
    int[] tmp = this.getGoals();
    return tmp[1];
  }
  
  /* Ger laget ett viss antalet poäng beroende på vad som gjorts 
     Vid vinst =>
        3 poäng
     Oavgjort => 
        1 poäng
      Förlust =>
        0 poäng
      Där done är antalet gjorda mål
      Let är antalet insläppta
  */
  void registreraMatch(int done, int let){
    /* Oavgjort */
    if(done == let){
      this.points += 1;
      this.draws  += 1;
    } else if(done > let){
      /* Vinst */
      /* Get laget poäng för visten */
      this.points += 3;
      this.wins   += 1;
    } else{
      /* Förlust */
      this.losses += 1;
    }
    
    /* Sparar undran antalet insläppta resp. antalet gjorda mål */
    this.setGoals(done,let);
    
    /* Laget har nu spelat en match till */
    this.matches += 1;
    
  }
  
  /* Retunerar en sträng-version av klassen på formen
     Kalmar FF	16	11	2	3	37-16	35
  */
  public String toString(){
    return this.team + " " + 
    Integer.toString(this.players) + " " + 
    Integer.toString(this.matches) + " " + 
    Integer.toString(this.wins) + " " + 
    Integer.toString(this.draws) + " " + 
    Integer.toString(this.losses) + " " + 
    this.goals + " " + 
    Integer.toString(this.points);
  }
  
  /* Retunerar differensen mellan antalet gjorda mål och antalet insläppta */
  public int getDiff(){
    return this.getDoneGoals() - this.getLetGoals();
  }
  
  /* Kolla om det nuvarande laget är bättre än det ingånede i form av poäng */
  private int betterThanPoints(FotbollsLag other){
    if(this.getPoints() > other.getPoints()){
      return 1;
    } else if(this.getPoints() < other.getPoints()){
      return -1;
    } else{
      return 0;
    }
  }
  
  private int betterThanGoals(FotbollsLag other){
    if(this.getDoneGoals() < other.getDoneGoals()){
      return 1;
    } else if(this.getDoneGoals() > other.getDoneGoals()){
      return -1;
    }
    
    return 0;
  }
  
  /* Kolla om det nuvarande laget är bättre än det ingånede i form av antalet gjorda mål */
  private int betterThanGoalsDiff(FotbollsLag other){
    if(this.getDiff() > other.getDiff()){
      return 1;
    } else if(this.getDiff() < other.getDiff()){
      return -1;
    }
    
    return 0;
  }
  
  /* Jämför nuvarande laget med ingående paramter 
    Retunerar 1 om det nuvranade laget är bättre 
    Retunerar 0 om om båda lagen är lika
    Retunerar -1 om det nuvarande laget är sämmre
  */
  
  public int compareTo(FotbollsLag other){
    int tmp = 0;
    
    /* Har det det nuvarande laget bättre poäng än det ingående ? */
    if((tmp = this.betterThanPoints(other)) != 0){
      return tmp;
    } else if((tmp = this.betterThanGoalsDiff(other)) != 0){
      return tmp;
    }
    
    return this.betterThanGoals(other);
  }
  
  public boolean equals(Object o){
    if(o instanceof FotbollsLag){
      FotbollsLag f = (FotbollsLag) o;
      return f.getName().equals(this.getName());
    }
    
    return false;
  }
}
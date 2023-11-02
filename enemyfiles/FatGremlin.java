package enemyfiles;
import app.Colors;
import app.Combat;
import app.Entity;
import app.Intent;
import app.IntentType;

public class FatGremlin extends Enemy{

  private Intent intent;
  public static final Intent SMASH = new Intent("Smash", IntentType.ATTACK, IntentType.DEBUFF, 4);
  public static final String[] art = Colors.fillColor(new String[] {" █▄████▄ ▄ ", " ▄███████▀ ","▀▄██████▀▄ ", " ████████  ", "  ██████▀  ", "  █    █   "}, Colors.fatGGreen);


  public FatGremlin(int middleX, Combat c){
    super("Fat Gremlin", (int)(Math.random()*5)+13, false, middleX, 11, art, c);
    intent = SMASH;
  }

  //Getters and Setters
  public Intent getIntent(){ return intent; }
  public String getIntentName(){ return intent.getName(); }

  
  public void doIntent(Entity player, Enemy copy){
    if(intent == SMASH){
      this.attack(player, 4);
      player.addStatusStrengthDuringEndOfTurn("Weak", 1);
    }
  }

  public void setNextIntent(){
    intent = SMASH;
  }
}
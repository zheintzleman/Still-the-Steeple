package enemyfiles;
import java.util.*;

import app.Card;
import app.Combat;
import app.Entity;
import enemyfiles.Intent.IntentType;
import util.Colors;

public class SpikeSlimeLarge extends Enemy{

  private Intent intent;
  public static final Intent FLAMETACKLE = new Intent("Flame Tackle", IntentType.ATTACK, IntentType.DEBUFF, 16);
  public static final Intent LICK = new Intent("Lick", IntentType.DEBUFF);
  public static final Intent SPLIT = new Intent("Split", IntentType.UNKNOWN);
  private int FTsInARow = 0;
  private int licksInARow = 0;
  public static final String[] art = Colors.fillColor(new String[] {"             ▄▄▄▄▄▄▄▄▄▄                  ", "          ▄██████████████▄               ", "       ██████████████████████            ", "     ▄▄███████████████████████▄▄         ", "   ▄█████████████████████████████▄▄      ", "▄████████████████████████████████████▄   ", "█████████████████████████████████████▀   ", "▀█████████████████████████████████▀  ▄██▄", "   ▀▀▀██████▀▀▀▀▀▀█████████████▀▀        ", "                   ▀▀▀███▀▀              "}, Colors.spikeSlimeColor);


  public SpikeSlimeLarge(int middleX){
    super("Spike Slime (L)", (int)(Math.random()*6)+64, false, middleX, 41, art);
    setStatusStrength("Split", 1);
    setNextIntent();
  }

  //Getters and Setters
  public Intent getIntent(){ return intent; }
  public String getIntentName(){ return intent.getName(); }

  
  public void doIntent(Entity player){
    if(intent == FLAMETACKLE){
      attack(player, 16);
      ArrayList<Card> disc = Combat.c.getDiscardPile();
      disc.add(Card.getCard("Slimed"));
      disc.add(Card.getCard("Slimed"));
    }else if(intent == LICK){
      player.addStatusStrength("Frail", 2);
    }else if(intent == SPLIT){
      ArrayList<Enemy> enemiesToUpdate = Combat.c.getEnemiesToUpdate();
      int thisIndex = -1;
      for(int i=0; i<enemiesToUpdate.size(); i++){
        Enemy e = enemiesToUpdate.get(i);
        if(e == this){
          thisIndex = i;
        }
      }
      enemiesToUpdate.remove(thisIndex);
      enemiesToUpdate.add(thisIndex, new SpikeSlimeMed(this, this.getMiddleX() + 11));
      enemiesToUpdate.add(thisIndex, new SpikeSlimeMed(this, this.getMiddleX() - 11));
    }
  }

  public void setNextIntent(){
    boolean decided = false;
    double probTackle = .3;
    while(!decided){
      double rng = Math.random();
      if(rng < probTackle){
        if(FTsInARow < 2){
          intent = FLAMETACKLE;
          FTsInARow++;
          licksInARow = 0;
          decided = true;
        }
      }else{
        if(licksInARow < 2){
          intent = LICK;
          licksInARow++;
          FTsInARow = 0;
          decided = true;
        }
      }
    }
  }
  
  public void setSplitIntent(){
    intent = SPLIT;
  }
}
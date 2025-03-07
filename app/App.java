package app;

import java.util.*;

import app.Card.CardType;
import app.Card.Color;
import app.Card.Rarity;
import util.Colors;
import util.Str;
import util.Util;


/** Statically contains and initializes various program-wide constants and data structures.
 * 
 * @see Str
 * @see Colors
 * @see Card
 * @see Status
*/
public abstract class App {
  public static final String SETTINGS_PATH = "data\\settings.dat";
  public static final String CARD_LIST_PATH = "data\\cardList1.dat";
  public static final String INSTRUCTIONS_TEXT = "Interact with the game by typing commands in the terminal. "
                                               + "You can see these instructions mid-game by typing \"help\", or in the \"esc\" menu. "
                                               + "Specific actions have their respective commands written near them in " + Colors.magenta + "magenta" + Colors.reset + ". "
                                               + "Along with the commands shown on screen, you can type the following:\n\n\"E\", \"end\", or \"end turn\" to end your turn,\n"
                                               + "\"T\", \"stat\" or \"status\" to see all entities' status effects, and\n"
                                               + "For convenience, you can refer to draw, discard, and exhaust piles as \"draw\"/\"a\", \"disc\"/\"s\", and \"exh\"/\"x\" respectively.\n\n"
                                               + "Some screens have additional information written below the screen, so check there if you're confused. See the README for further details & help regarding visual bugs.\n\n"
                                               + "Each card costs energy to play (shown by the red number in the corner) -- you get 3 energy a turn. Each combat drops 10-20 " + Colors.gold + "gold" + Colors.reset + "; Try to get as much as possible before dying!\n\n";
  public static final String INSTRUCTIONS = "Instructions:\n\n" + INSTRUCTIONS_TEXT;
  // Big texts generated using https://patorjk.com/software/taag/
  public static final String TITLE = Colors.headerBrown + "\n   ▄▄▄▄▄   █    ██  ▀▄    ▄        ▄▄▄▄▀ ▄  █ ▄███▄          ▄▄▄▄▄   █ ▄▄  ▄█ █▄▄▄▄ ▄███▄   \n"
                                                          + "  █     ▀▄ █    █ █   █  █      ▀▀▀ █   █   █ █▀   ▀        █     ▀▄ █   █ ██ █  ▄▀ █▀   ▀  \n"
                                                          + "▄  ▀▀▀▀▄   █    █▄▄█   ▀█           █   ██▀▀█ ██▄▄        ▄  ▀▀▀▀▄   █▀▀▀  ██ █▀▀▌  ██▄▄    \n"
                                                          + " ▀▄▄▄▄▀    ███▄ █  █   █           █    █   █ █▄   ▄▀      ▀▄▄▄▄▀    █     ▐█ █  █  █▄   ▄▀ \n"
                                                          + "               ▀   █ ▄▀           ▀        █  ▀███▀                   █     ▐   █   ▀███▀   \n"
                                                          + "                  █                       ▀                            ▀       ▀            \n"
                                                          + "                 ▀                                                                          \n" + Colors.reset;
  public static final String GAME_OVER = "  ▄████  ▄▄▄       ███▄ ▄███▓▓█████     ▒█████   ██▒   █▓▓█████  ██▀███  \n" +
                                        " ██▒ ▀█▒▒████▄    ▓██▒▀█▀ ██▒▓█   ▀    ▒██▒  ██▒▓██░   █▒▓█   ▀ ▓██ ▒ ██▒\n" +
                                        "▒██░▄▄▄░▒██  ▀█▄  ▓██    ▓██░▒███      ▒██░  ██▒ ▓██  █▒░▒███   ▓██ ░▄█ ▒\n" +
                                        "░▓█  ██▓░██▄▄▄▄██ ▒██    ▒██ ▒▓█  ▄    ▒██   ██░  ▒██ █░░▒▓█  ▄ ▒██▀▀█▄  \n" +
                                        "░▒▓███▀▒ ▓█   ▓██▒▒██▒   ░██▒░▒████▒   ░ ████▓▒░   ▒▀█░  ░▒████▒░██▓ ▒██▒\n" +
                                        " ░▒   ▒  ▒▒   ▓▒█░░ ▒░   ░  ░░░ ▒░ ░   ░ ▒░▒░▒░    ░ ▐░  ░░ ▒░ ░░ ▒▓ ░▒▓░\n" +
                                        "  ░   ░   ▒   ▒▒ ░░  ░      ░ ░ ░  ░     ░ ▒ ▒░    ░ ░░   ░ ░  ░  ░▒ ░ ▒░\n" +
                                        "░ ░   ░   ░   ▒   ░      ░      ░      ░ ░ ░ ▒       ░░     ░     ░░   ░ \n" +
                                        "      ░       ░  ░       ░      ░  ░       ░ ░        ░     ░  ░   ░     \n" +
                                        "                                                     ░                   \n";
  public static final int POPUP_WIDTH = 43;
  public static final int POPUP_HEIGHT = 30;
  public static final int DEFAULT_SCREEN_WIDTH = 199;
  public static final int DEFAULT_SCREEN_HEIGHT = 50;
  public static final int MIN_SCREEN_WIDTH = 10;
  public static final int MIN_SCREEN_HEIGHT = 10;
  // TODO: Check for realistic values of MSW/MSH^
  public static final Set<Eff> ATTACK_PRIMARIES = new HashSet<Eff>(List.of(Eff.Attack, Eff.AtkAll, Eff.BodySlam, Eff.SearingBlow, Eff.HeavyAttack, Eff.AtkRandom));
  public static final Set<Eff> DEFENSE_PRIMARIES = new HashSet<Eff>(List.of(Eff.Block));
  // Primaries that affect game state outside of the current combat (i.e. that matter even after the combat ends.)
  public static final Set<Eff> RUN_STATE_PRIMARIES = new HashSet<Eff>(List.of(Eff.DmgPlayerC, Eff.DmgPlayerNC, Eff.LoseHPC, Eff.LoseHPNC, Eff.Feed, Eff.Reap));

  // Can use Card.getCard(String) and Status.getStatus(String) to easily access w/ null-checking:
  public static final HashMap<String, Card> CARDSET = loadCards();
  public static final ArrayList<Card> CARDS = new ArrayList<>(CARDSET.values());
  public static final ArrayList<Card> COMMON_CARDS = Util.filter(CARDS, Card::isCommon);
  public static final ArrayList<Card> UNCOMMON_CARDS = Util.filter(CARDS, Card::isUncommon);
  public static final ArrayList<Card> RARE_CARDS = Util.filter(CARDS, Card::isRare);
  
  public static final HashMap<String, Status> STATUSSET = loadStatuses();

  /** Generates the final hashmap containing all cards in the game.
   * 
   * @see Card Card.getCard()
   * @see Effect Effect / CardEffect
   * @see Combat Combat.playCard()
   * @see Combat Combat.playEff()
   * @see Card.Description
   * @see EventManager
   */
  private static HashMap<String, Card> loadCards(){
    HashMap<String, Card> cards = new HashMap<String, Card>();
    // From Effect.java's javadoc:

    //For encoding card effects (copied from CardEffect.java:)
    //Primary: First word of input data.
    //Secondary: Rest of input data, apart from terminal integer
    //Power: Terminal integer, or 1 if last word not an integer
    //WhenPlayed: Defaults to ONPLAY; begin with "(OnDiscarded) "/"(OnTurnEnd) "/etc. to change.
    //e.g. Stores "Lorem Ipsum Dolor 4" as: P = "Lorem", S = "Ipsum Dolor", p = 4,
    // or "Lorem Ipsum 4 Dolor" as: P = "Lorem", S = "Ipsum 4 Dolor", p = 1.
    // or "(OnExhaust) Lorem Ipsum 4 Dolor" as: P = "Lorem", S = "Ipsum 4 Dolor", p = 1, WP = ONEXHAUST
    cards.put("Burn", new Card("Burn", "Unplayable.\nAt the end of your turn, take 2 damage.\n", CardType.STATUS, -1, false, List.of("Unplayable", "(OnTurnEnd) DmgPlayerC 2"),
                      "Unplayable.\nAt the end of your turn, take 4 damage.\n", -1, false, List.of("Unplayable", "(OnTurnEnd) DmgPlayerC 4"), Rarity.BASIC, Color.NEUTRAL)); //TODO: Something that makes burn discard even w/ retain? Or just hard-code that in?
    // Statuses can't be upgraded by default (incl. by apotheosis, etc.) So Burn+ is effectively its own card.
    cards.put("Burn+", new Card(Colors.upgradeGreen + "Burn+", "Unplayable.\nAt the end of your turn, take 4 damage.\n", CardType.STATUS, -1, false, List.of("Unplayable", "(OnTurnEnd) DmgPlayerC 4"),
                      "", -1, false, List.of(), Rarity.BASIC, Color.NEUTRAL));
    cards.put("Dazed", new Card("Dazed", CardType.STATUS, -1, false, List.of("Unplayable", "Ethereal"), List.of(), Rarity.BASIC, Color.NEUTRAL));
    cards.put("Slimed", new Card("Slimed", CardType.STATUS, 1, false, List.of("Exhaust"), List.of(), Rarity.BASIC, Color.NEUTRAL));
    cards.put("Void", new Card("Void", "Unplayable.\nEthereal.\nWhenever this card is drawn, lose 1 Energy.\n", CardType.STATUS, -1, false, List.of("Unplayable", "Ethereal", "(OnDrawn) GainEnergy -1"),
                      "", -1, false, List.of(), Rarity.BASIC, Color.NEUTRAL));
    cards.put("Wound", new Card("Wound", CardType.STATUS, -1, false, List.of("Unplayable"), List.of(), Rarity.BASIC, Color.NEUTRAL));

    cards.put("Strike", new Card("Strike", CardType.ATTACK, 1, true, List.of("Attack 6"), //TODO: Should these arraylists really just be arrays?
                      List.of("Attack 9"), Rarity.BASIC, Color.IRONCLAD));
    cards.put("Defend", new Card("Defend", CardType.SKILL, 1, false, List.of("Block 5"),
                      List.of("Block 8"), Rarity.BASIC, Color.IRONCLAD)); 
    cards.put("Bash", new Card("Bash", CardType.ATTACK, 2, true, List.of("Attack 8", "Apply Vulnerable 2"),
                      List.of("Attack 10", "Apply Vulnerable 3"), Rarity.BASIC, Color.IRONCLAD));
    cards.put("Anger", new Card("Anger", CardType.ATTACK, 0, true, List.of("Attack 6", "Anger"),
                      List.of("Attack 8", "Anger"), Rarity.COMMON, Color.IRONCLAD));
    cards.put("Armaments", new Card("Armaments", CardType.SKILL, 1, false, List.of("Block 5", "Upgrade Choose1FromHand"),
                      List.of("Block 5", "Upgrade Hand"), Rarity.COMMON, Color.IRONCLAD));
    cards.put("Searing Blow", new Card("Searing Blow", CardType.ATTACK, 2, true, List.of("SearingBlow"),
                      List.of("SearingBlow"), Rarity.COMMON, Color.IRONCLAD));
    cards.put("Clash", new Card("Clash", CardType.ATTACK, 0, true, List.of("Clash", "Attack 14"),
                      List.of("Clash", "Attack 18"), Rarity.COMMON, Color.IRONCLAD));
    cards.put("Body Slam", new Card("Body Slam", CardType.ATTACK, 1, true, List.of("BodySlam"),
                      0, true, List.of("BodySlam"), Rarity.COMMON, Color.IRONCLAD));
    cards.put("Cleave", new Card("Cleave", CardType.ATTACK, 1, false, List.of("AtkAll 8"),
                      List.of("AtkAll 11"), Rarity.COMMON, Color.IRONCLAD));
    cards.put("Clothesline", new Card("Clothesline", CardType.ATTACK, 2, true, List.of("Attack 12", "Apply Weak 2"),
                      List.of("Attack 14", "Apply Weak 3"), Rarity.COMMON, Color.IRONCLAD));
    cards.put("Flex", new Card("Flex", "Gain 2 Strength.\nAt the end of this turn, lose 2 Strength.\n", CardType.SKILL, 0, false, List.of("AppPlayer Strength 2", "AppPlayer Strength Down 2"),
                                      "Gain 4 Strength.\nAt the end of this turn, lose 4 Strength.\n", 0, false, List.of("AppPlayer Strength 4", "AppPlayer Strength Down 4"), Rarity.COMMON, Color.IRONCLAD));
    cards.put("Havoc", new Card("Havoc", CardType.SKILL, 1, false, List.of("Havoc"),
                      0, false, List.of("Havoc"), Rarity.COMMON, Color.IRONCLAD));
    cards.put("Headbutt", new Card("Headbutt", CardType.ATTACK, 1, true, List.of("Attack 9", "PutOnDrawPile Choose1FromDisc"),
                      List.of("Attack 12", "PutOnDrawPile Choose1FromDisc"), Rarity.COMMON, Color.IRONCLAD));
    cards.put("Heavy Blade", new Card("Heavy Blade", CardType.ATTACK, 2, true, List.of("HeavyAttack 3"),
                      List.of("HeavyAttack 5"), Rarity.COMMON, Color.IRONCLAD));
    cards.put("Iron Wave", new Card("Iron Wave", CardType.ATTACK, 1, true, List.of("Block 5", "Attack 5"),
                      List.of("Block 7", "Attack 7"), Rarity.COMMON, Color.IRONCLAD));
    cards.put("Pommel Strike", new Card("Pommel Strike", CardType.ATTACK, 1, true, List.of("Attack 9", "Draw 1"), //TODO: rn in Card, energy cost displays right next to the name (adj. chars)
                      List.of("Attack 10", "Draw 2"), Rarity.COMMON, Color.IRONCLAD));
    cards.put("Shrug It Off", new Card("Shrug It Off", CardType.SKILL, 1, false, List.of("Block 8", "Draw 1"),
                      List.of("Block 11", "Draw 1"), Rarity.COMMON, Color.IRONCLAD));
    cards.put("Sword Boomerang", new Card("Sword Boomerang", "Deal <atk>3<endatk> damage to a random enemy 3 times.\n", CardType.ATTACK, 1, false, List.of("AtkRandom 3", "AtkRandom 3", "AtkRandom 3"),
                      "Deal <atk>3<endatk> damage to a random enemy 4 times.\n", 1, false, List.of("AtkRandom 3","AtkRandom 3", "AtkRandom 3", "AtkRandom 3"), Rarity.COMMON, Color.IRONCLAD));
    cards.put("Thunderclap", new Card("Thunderclap", CardType.ATTACK, 1, false, List.of("AtkAll 4", "AppAll Vulnerable 1"),
                      List.of("AtkAll 7", "AppAll Vulnerable 1"), Rarity.COMMON, Color.IRONCLAD));
    cards.put("True Grit", new Card("True Grit", "Gain <blk>7<endblk> block.\nExhaust a random card in your hand.\n", CardType.SKILL, 1, false, List.of("Block 7", "Exhaust RandHand"),
                      "Gain <blk>9<endblk> block.\nExhaust a card in your hand.\n", 1, false, List.of("Block 9", "Exhaust Choose1FromHand"), Rarity.COMMON, Color.IRONCLAD));
    cards.put("Twin Strike", new Card("Twin Strike", "Deal <atk>5<endatk> damage twice.\n", CardType.ATTACK, 1, true, List.of("Attack 5", "Attack 5"),
                      "Deal <atk>7<endatk> damage twice.\n", 1, true, List.of("Attack 7", "Attack 7"), Rarity.COMMON, Color.IRONCLAD));
    cards.put("Warcry", new Card("Warcry", CardType.SKILL, 0, false, List.of("Draw 1", "PutOnDrawPile Choose1FromHand", "Exhaust"),
                      List.of("Draw 2", "PutOnDrawPile Choose1FromHand", "Exhaust"), Rarity.COMMON, Color.IRONCLAD));
    cards.put("Wild Strike", new Card("Wild Strike", CardType.ATTACK, 1, true, List.of("Attack 12", "GainToDraw Wound"),
                      List.of("Attack 17", "GainToDraw Wound"), Rarity.COMMON, Color.IRONCLAD));
    cards.put("Battle Trance", new Card("Battle Trance", "Draw 3 cards.\nYou cannot draw additional cards this turn.\n", CardType.SKILL, 0, false, List.of("Draw 3", "AppPlayer No Draw"),
                      "Draw 4 cards.\nYou cannot draw additional cards this turn.\n", 0, false, List.of("Draw 4", "AppPlayer No Draw"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Blood for Blood", new Card("Blood for Blood", "Costs 1 less Energy for each time you lose HP this combat.\nDeal <atk>18<endatk> damage.\n", CardType.ATTACK, 4, true, List.of("(OnPlayerLoseHP) AddCost -1", "Attack 18"),
                      "Costs 1 less Energy for each time you lose HP this combat.\nDeal <atk>22<endatk> damage.\n", 3, true, List.of("(OnPlayerLoseHP) AddCost -1", "Attack 22"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Bloodletting", new Card("Bloodletting", CardType.SKILL, 0, false, List.of("LoseHPC 3", "GainEnergy 2"), List.of("LoseHPC 3", "GainEnergy 3"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Burning Pact", new Card("Burning Pact", CardType.SKILL, 1, false, List.of("Exhaust Choose1FromHand", "Draw 2"), List.of("Exhaust Choose1FromHand", "Draw 3"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Carnage", new Card("Carnage", CardType.ATTACK, 2, true, List.of("Ethereal", "Attack 20"), List.of("Ethereal", "Attack 28"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Combust", new Card("Combust", "At the end of your turn, lose 1 HP and deal 5 damage to ALL enemies.\n", CardType.POWER, 1, false, List.of("AppPlayer Combust 5", "IncrCombustCnt"),
                      "At the end of your turn, lose 1 HP and deal 7 damage to ALL enemies.\n", 1, false, List.of("AppPlayer Combust 7", "IncrCombustCnt <str>"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Dark Embrace", new Card("Dark Embrace", "Whenever a card is Exhausted, draw 1 card.\n", CardType.POWER, 2, false, List.of("AppPlayer Dark Embrace"),
                      "Whenever a card is Exhausted, draw 1 card.\n", 1, false, List.of("AppPlayer Dark Embrace"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Disarm", new Card("Disarm", "Enemy loses 2 Strength.\nExhaust.\n", CardType.SKILL, 1, true, List.of("Unapply Strength 2", "Exhaust"),
                      "Enemy loses 3 Strength.\nExhaust.\n", 1, true, List.of("Unapply Strength 3", "Exhaust"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Dropkick", new Card("Dropkick", "Deal <atk>5<endatk> damage.\nIf the enemy has Vulnerable,\ngain 1 Energy and draw 1 card.\n", CardType.ATTACK, 1, true, List.of("Attack 5", "Dropkick"),
                      "Deal <atk>8<endatk> damage.\nIf the enemy has Vulnerable,\ngain 1 Energy and draw 1 card.\n", 1, true, List.of("Attack 8", "Dropkick"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Dual Wield", new Card("Dual Wield", "Choose an Attack or Power card. Add a copy of that card into your hand.\n", CardType.SKILL, 1, false, List.of("CopyToHand Choose1AtkOrPwrFromHand"),
                      "Choose an Attack or Power card. Add 2 copies of that card into your hand.\n", 1, false, List.of("CopyToHand Choose1AtkOrPwrFromHand 2"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Entrench", new Card("Entrench", CardType.SKILL, 2, false, List.of("Entrench"),
                      1, false, List.of("Entrench"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Evolve", new Card("Evolve", "Whenever you draw a Status card, draw 1 card.\n", CardType.POWER, 1, false, List.of("AppPlayer Evolve"),
                      "Whenever you draw a Status card, draw 2 cards.\n", 1, false, List.of("AppPlayer Evolve 2"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Feel No Pain", new Card("Feel No Pain", "Whenever a card is Exhausted,\ngain 3 block.\n", CardType.POWER, 1, false, List.of("AppPlayer Feel No Pain 3"),
                      "Whenever a card is Exhausted,\ngain 4 block.\n", 1, false, List.of("AppPlayer Feel No Pain 4"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Fire Breathing", new Card("Fire Breathing", "Whenever you draw a Status or Curse card, deal 6 damage to ALL enemies.\n", CardType.POWER, 1, false, List.of("AppPlayer Fire Breathing 6"),
                      "Whenever you draw a Status or Curse card, deal 10 damage to ALL enemies.\n", 1, false, List.of("AppPlayer Fire Breathing 10"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Flame Barrier", new Card("Flame Barrier", "Gain <blk>12<endblk> block.\nWhenever you are attacked this turn, deal 4 damage back.\n", CardType.SKILL, 2, false, List.of("Block 12", "AppPlayer Flame Barrier 4"),
                      "Gain <blk>16<endblk> block.\nWhenever you are attacked this turn, deal 6 damage back.\n", 2, false, List.of("Block 16", "AppPlayer Flame Barrier 6"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Ghostly Armor", new Card("Ghostly Armor", CardType.SKILL, 1, false, List.of("Ethereal", "Block 10"),
                      List.of("Ethereal", "Block 13"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Hemokenesis", new Card("Hemokenesis", CardType.ATTACK, 1, true, List.of("LoseHPC 2", "Attack 15"),
                      List.of("LoseHPC 2", "Attack 20"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Infernal Blade", new Card("Infernal Blade", "Add a random Attack into your hand. It costs 0 this turn.\nExhaust.\n", CardType.SKILL, 1, false, List.of("CopyToHandFree RandAtk", "Exhaust"),
                      "Add a random Attack into your hand. It costs 0 this turn.\nExhaust.\n", 0, false, List.of("CopyToHandFree RandAtk", "Exhaust"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Inflame", new Card("Inflame", CardType.POWER, 1, false, List.of("AppPlayer Strength 2"),
                      List.of("AppPlayer Strength 3"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Intimidate", new Card("Intimidate", CardType.SKILL, 0, false, List.of("AppAll Weak 1", "Exhaust"),
                      List.of("AppAll Weak 2", "Exhaust"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Metallicize", new Card("Metallicize", "At the end of your turn, gain 3 block.\n", CardType.POWER, 1, false, List.of("AppPlayer Metallicize 3"),
                      "At the end of your turn, gain 4 block.\n", 1, false, List.of("AppPlayer Metallicize 4"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Power Through", new Card("Power Through", "Add 2 Wounds into your hand.\nGain <blk>15<endblk> block.\n", CardType.SKILL, 1, false, List.of("CopyToHand Wound 2", "Block 15"),
                      "Add 2 Wounds into your hand.\nGain <blk>20<endblk> block.\n", 1, false, List.of("CopyToHand Wound 2", "Block 20"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Pummel", new Card("Pummel", "Deal <atk>2<endatk> damage 4 times.\nExhaust.\n", CardType.ATTACK, 1, true, List.of("Attack 2", "Attack 2", "Attack 2", "Attack 2", "Exhaust"),
                      "Deal <atk>2<endatk> damage 5 times.\nExhaust.\n", 1, true, List.of("Attack 2", "Attack 2", "Attack 2", "Attack 2", "Attack 2", "Exhaust"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Rage", new Card("Rage", "Whenever you play an Attack this turn, gain 3 block.\n", CardType.SKILL, 0, false, List.of("AppPlayer Rage 3"),
                      "Whenever you play an Attack this turn, gain 4 block.\n", 0, false, List.of("AppPlayer Rage 4"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Rampage", new Card("Rampage", CardType.ATTACK, 1, true, List.of("Attack 8", "Rampage 5"),
                      List.of("Attack 8", "Rampage 8"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Reckless Charge", new Card("Reckless Charge", CardType.ATTACK, 0, true, List.of("Attack 7", "GainToDraw Dazed"),
                      List.of("Attack 10", "GainToDraw Dazed"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Rupture", new Card("Rupture", "Whenever you lose HP from a card,\ngain 1 Strength.\n", CardType.POWER, 1, false, List.of("AppPlayer Rupture"),
                      "Whenever you lose HP from a card,\ngain 2 Strength.\n", 1, false, List.of("AppPlayer Rupture 2"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Second Wind", new Card("Second Wind", CardType.SKILL, 1, false, List.of("ExhaustNonattacks 5"),
                      List.of("ExhaustNonattacks 7"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Seeing Red", new Card("Seeing Red", CardType.SKILL, 1, false, List.of("GainEnergy 2", "Exhaust"),
                      0, false, List.of("GainEnergy 2", "Exhaust"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Sentinel", new Card("Sentinel", "Gain <blk>5<endblk> block.\nIf this card is Exhausted,\ngain 2 Energy.\n", CardType.SKILL, 1, false, List.of("Block 5", "(OnExhausted) GainEnergy 2"),
                      "Gain <blk>8<endblk> block.\nIf this card is Exhausted,\ngain 3 Energy.\n", 1, false, List.of("Block 8", "(OnExhausted) GainEnergy 3"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Sever Soul", new Card("Sever Soul", CardType.ATTACK, 2, true, List.of("ExhaustNonattacks 0", "Attack 16"),
                      List.of("ExhaustNonattacks 0", "Attack 22"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Shockwave", new Card("Shockwave", "Apply 3 Weak and Vulnerable to ALL enemies.\nExhaust.\n", CardType.SKILL, 2, false, List.of("AppAll Weak 3", "AppAll Vulnerable 3", "Exhaust"),
                      "Apply 5 Weak and Vulnerable to ALL enemies.\nExhaust.\n", 2, false, List.of("AppAll Weak 5", "AppAll Vulnerable 5", "Exhaust"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Spot Weakness", new Card("Spot Weakness", CardType.SKILL, 1, true, List.of("SpotWeakness 3"),
                      List.of("SpotWeakness 4"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Uppercut", new Card("Uppercut", CardType.ATTACK, 2, true, List.of("Attack 13", "Apply Weak", "Apply Vulnerable"),
                      List.of("Attack 13", "Apply Weak 2", "Apply Vulnerable 2"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Whirlwind", new Card("Whirlwind", CardType.ATTACK, -2, false, List.of("Whirlwind 5"),
                      List.of("Whirlwind 8"), Rarity.UNCOMMON, Color.IRONCLAD));
    cards.put("Barricade", new Card("Barricade", "Block is not removed at the start of your turn.\n", CardType.POWER, 3, false, List.of("AppPlayer Barricade"),
                      "Block is not removed at the start of your turn.\n", 2, false, List.of("AppPlayer Barricade"), Rarity.RARE, Color.IRONCLAD));
    cards.put("Berserk", new Card("Berserk", "Gain 2 Vulnerable.\nAt the start of your turn, gain 1 Energy.\n", CardType.POWER, 0, false, List.of("AppPlayer Vulnerable 2", "AppPlayer Berserk"),
                      "Gain 1 Vulnerable.\nAt the start of your turn, gain 1 Energy.\n", 0, false, List.of("AppPlayer Vulnerable", "AppPlayer Berserk"), Rarity.RARE, Color.IRONCLAD));
    cards.put("Bludgeon", new Card("Bludgeon", CardType.ATTACK, 3, true, List.of("Attack 32"),
                      List.of("Attack 42"), Rarity.RARE, Color.IRONCLAD));
    cards.put("Brutality", new Card("Brutality", "At the start of your turn, lose 1 HP and draw 1 card.\n", CardType.POWER, 0, false, List.of("AppPlayer Brutality"),
                      "Innate.\nAt the start of your turn, lose 1 HP and draw 1 card.\n", 0, false, List.of("Innate", "AppPlayer Brutality"), Rarity.RARE, Color.IRONCLAD));
    cards.put("Corruption", new Card("Corruption", "Skills cost 0.\nWhenever you play a Skill, Exhaust it.\n", CardType.POWER, 3, false, List.of("AppPlayer Corruption"),
                      "Skills cost 0.\nWhenever you play a Skill, Exhaust it.\n", 2, false, List.of("AppPlayer Corruption"), Rarity.RARE, Color.IRONCLAD));
    cards.put("Demon Form", new Card("Demon Form", "At the start of your turn, gain 2 Strength.\n", CardType.POWER, 3, false, List.of("AppPlayer Demon Form 2"),
                      "At the start of your turn, gain 2 Strength.\n", 3, false, List.of("AppPlayer Demon Form 3"), Rarity.RARE, Color.IRONCLAD));
    cards.put("Double Tap", new Card("Double Tap", "This turn, your next Attack is played twice.\n", CardType.SKILL, 1, false, List.of("AppPlayer Double Tap"),
                      "This turn, your next 2 Attacks are played twice.\n", 1, false, List.of("AppPlayer Double Tap 2"), Rarity.RARE, Color.IRONCLAD));
    cards.put("Exhume", new Card("Exhume", CardType.SKILL, 1, false, List.of("Exhume", "Exhaust"),
                      0, false, List.of("Exhume", "Exhaust"), Rarity.RARE, Color.IRONCLAD));
    cards.put("Feed", new Card("Feed", CardType.ATTACK, 1, true, List.of("Attack 10", "Feed 3", "Exhaust"),
                      List.of("Attack 12", "Feed 4", "Exhaust"), Rarity.RARE, Color.IRONCLAD));
    cards.put("Fiend Fire", new Card("Fiend Fire", CardType.ATTACK, 2, true, List.of("FiendFire 7", "Exhaust"),
                      List.of("FiendFire 10", "Exhaust"), Rarity.RARE, Color.IRONCLAD));
    cards.put("Immolate", new Card("Immolate", CardType.ATTACK, 2, false, List.of("AtkAll 21", "GainToDisc Burn"),
                      List.of("AtkAll 28", "GainToDisc Burn"), Rarity.RARE, Color.IRONCLAD));
    cards.put("Impervious", new Card("Impervious", CardType.SKILL, 2, false, List.of("Block 30", "Exhaust"),
                      List.of("Block 40", "Exhaust"), Rarity.RARE, Color.IRONCLAD));
    cards.put("Juggernaut", new Card("Juggernaut", "Whenever you gain block, deal 5 damage to a random enemy.\n", CardType.POWER, 2, false, List.of("AppPlayer Juggernaut 5"),
                      "Whenever you gain block, deal 7 damage to a random enemy.\n", 2, false, List.of("AppPlayer Juggernaut 7"), Rarity.RARE, Color.IRONCLAD));
    cards.put("Limit Break", new Card("Limit Break", CardType.SKILL, 1, false, List.of("LimitBreak", "Exhaust"),
                      List.of("LimitBreak"), Rarity.RARE, Color.IRONCLAD));
    cards.put("Offering", new Card("Offering", CardType.SKILL, 0, false, List.of("LoseHPC 6", "GainEnergy 2", "Draw 3", "Exhaust"),
                      List.of("LoseHPC 6", "GainEnergy 2", "Draw 5", "Exhaust"), Rarity.RARE, Color.IRONCLAD));
    cards.put("Reaper", new Card("Reaper", CardType.ATTACK, 2, false, List.of("Reap 4", "Exhaust"),
                      List.of("Reap 5", "Exhaust"), Rarity.RARE, Color.IRONCLAD));
    
    // Assert that all entries in `cards` are named correctly:
    assert cards.entrySet().stream().allMatch(
      (Map.Entry<String, Card> e) -> Str.equalsSkipEscSeqs(e.getKey(), e.getValue().getName()));

    return cards;
  }
  
  /** Generates the final hashmap containing all statuses in the game.
   * 
   * @see Status Status.getStatus()
   * @see Effect Effect / StatusEffect
   * @see Combat Combat.playEff()
   * @see EventManager
   */
  private static HashMap<String, Status> loadStatuses(){
    HashMap<String, Status> statuses = new HashMap<String, Status>();
    statuses = new HashMap<String, Status>();
    
    //Name, image, isDecreasing, hasStrength, description, (Optional: Effects -- can use <str> as effect strength)
    statuses.put("Vulnerable", new Status("Vulnerable", Colors.vulnRed + "V", true, true, "Takes 50% more damage from attacks for the next <str> turn(s)."));
    statuses.put("Weak", new Status("Weak", Colors.lightGreen + "W", true, true, "Deals 25% less attack damage for the next <str> turn(s)."));
    statuses.put("Frail", new Status("Frail", Colors.lightBlue + "F", true, true, "Block gained from cards is reduced by 25% for the next <str> turn(s)."));
    //TODO: Cap at 999 (dex too)
    statuses.put("Strength", new Status("Strength", Colors.energyCostRed + "S", false, true, "Increases attack damage by <str> (per hit)"));
    statuses.put("Strength Down", new Status("Strength Down", Colors.lightYellow + "s", false, true, "At the end of your turn, lose <str> Strength."));
    statuses.put("Dexterity", new Status("Dexterity", Colors.dexGreen + "D", false, true, "Increases block gained from cards by <str>."));
    statuses.put("Dexterity Down", new Status("Dexterity Down", Colors.lightYellow + "d", false, true, "At the end of your turn, lose <str> Dexterity."));
    //TODO: See how this interacts w/, eg, Twin Strike
    statuses.put("Curl Up", new Status("Curl Up", Colors.lightBlue + "C", false, true, "Gains <str> block upon first receiving attack damage."));
    statuses.put("Ritual", new Status("Ritual", Colors.lightBlue + "R", false, true, "Gains <str> Strength at the end of each turn."));
    statuses.put("Angry", new Status("Angry", Colors.lightYellow + "A", false, true, "Gains <str> Strength when this receives attack damage."));
    //TODO: Make display the amount of HP needed for splitting (or just add max HP to the HP bar)
    statuses.put("Split", new Status("Split", Colors.lightGreen + "S", false, false, "When at half HP or below, this splits into two smaller slimes with its current HP."));
    statuses.put("Entangled", new Status("Entangled", Colors.white + "E", true, false, "You may not play any attacks this turn"));
    statuses.put("Spore Cloud", new Status("Spore Cloud", Colors.lightYellow + "S", false, true, "On death, applies <str> Vulnerable to the player."));
    statuses.put("Thievery", new Status("Thievery", Colors.lightYellow + "T", false, true, "<str> Gold is stolen with every attack. Total Gold stolen is returned if the enemy is killed."));
    statuses.put("Vigor", new Status("Vigor", Colors.vigorOrange + "v", false, true, "Your next Attack deals <str> additional damage."));
    statuses.put("No Draw", new Status("No Draw", Colors.lightBlue + "N", true, false, "You may not draw any more cards this turn."));
    statuses.put("Combust", new Status("Combust", Colors.vigorOrange + "C", false, true, "At the end of your turn, lose 1 HP(for each Combust played) and deal <str> damage to ALL enemies.", List.of("(OnTurnEnd) Combust <str>")));
    statuses.put("Dark Embrace", new Status("Dark Embrace", Colors.darkEmbracePurple + "D", false, true, "Whenever a card is Exhausted, draw <str> card(s).", List.of("(OnExhaust) Draw <str>")));
    statuses.put("Evolve", new Status("Evolve", Colors.lightYellow + "E", false, true, "Whenever you draw a Status, draw <str> card(s)."));
    statuses.put("Feel No Pain", new Status("Feel No Pain", Colors.hpBarRed + "F", false, true, "Whenever a card is Exhausted, gain <str> block.", List.of("(OnExhaust) Block <str>")));
    statuses.put("Fire Breathing", new Status("Fire Breathing", Colors.vulnRed + "F", false, true, "Whenever you draw a Status or Curse card, deal <str> damage to ALL enemies."));
    statuses.put("Flame Barrier", new Status("Flame Barrier", Colors.vulnRed + "B", false, true, "When attacked, deals <str> damage back. (Wears off at the start of your turn)", List.of("(OnTurnStart) ClearStatusPlayer Flame Barrier")));
    statuses.put("Metallicize", new Status("Metallicize", Colors.lightBlue + "M", false, true, "At the end of your turn, gain <str> block.", List.of("(OnTurnEnd) Block <str>")));
    statuses.put("Rage", new Status("Rage", Colors.lightYellow + "R", false, true, "Whenever you play an Attack, gain <str> block. (Wears off at the start of your turn)", List.of("(OnTurnStart) ClearStatusPlayer Rage")));
    statuses.put("Rupture", new Status("Rupture", Colors.vulnRed + "R", false, true, "Whenever you lose HP from a card, gain <str> Strength."));
    statuses.put("Barricade", new Status("Barricade", Colors.lightBlue + "B", false, false, "Block is not removed at the start of your turn."));
    statuses.put("Berserk", new Status("Berserk", Colors.lightYellow + "B", false, true, "At the start of your turn, gain <str> Energy.", List.of("(OnTurnStart) GainEnergy <str>")));
    statuses.put("Brutality", new Status("Brutality", Colors.purple + "B", false, true, "At the start of your turn, lose <str> HP and draw <str> cards.", List.of("(OnTurnStart) LoseHPC <str>", "(OnTurnStart) Draw <str>")));
    statuses.put("Corruption", new Status("Corruption", Colors.purple + "C", false, false, "Skills cost 0. Whenever you play a Skill, Exhaust it."));
    statuses.put("Demon Form", new Status("Demon Form", Colors.demonFormRed + "D", false, true, "At the start of your turn, gain <str> Strength.", List.of("(OnTurnStart) AppPlayer Strength <str>")));
    statuses.put("Double Tap", new Status("Double Tap", Colors.doubleTapBlue + "D", false, true, "Your next <str> Attacks this turn are played twice.", List.of("(OnTurnStart) ClearStatusPlayer Double Tap")));
    statuses.put("Juggernaut", new Status("Juggernaut", Colors.descNumBlue + "J", false, true, "Whenever you gain block, deal <str> damage to a random enemy."));

    
    // Assert that all entries in `cards` are named correctly:
    assert statuses.entrySet().stream().allMatch(
      (Map.Entry<String, Status> e) -> Str.equalsSkipEscSeqs(e.getKey(), e.getValue().getName()));

    return statuses;
  
    // Places I could edit for card/relic/w/e effects:
    // - Combat.c.playCard / Combat.c.playEffect
    // - EventManager
    // - Entity.calcAttackDamage / Entity.calcAtkDmgFromThisStats
    // - Card.Description constructor
  }
}
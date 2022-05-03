import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Collections;

public class Yahtzee {
  private Scanner sc;
  private static ArrayList<Integer> dice;
  private int[] scoreSheet;
  private int turn;

  public static void main(String[] args) {
    Yahtzee game = new Yahtzee();
    while (game.turn < 14) {
      game.turn();
    }
  }

  public Yahtzee() {
    turn = 1;
    sc = new Scanner(System.in);
    dice = new ArrayList<Integer>();
    /*
      scoresheet array to store scores
      0: 1's
      1: 2's
      2: 3's
      3: 4's
      4: 5's
      5: 6's
      6: 3 of a kind
      7: 4 of a kind
      8: full house
      9: small straight
      10: large straigt
      11: Yahtzee
      12: Chance
      13: Bonus Yahtzee
      14: Upper Bonus
    */
    scoreSheet = new int[15];
    for (int i = 0; i < 15; i++) {
      scoreSheet[i] = 0;
    }
  }

  public void turn() {
    roll(5);
    checkDice();
    int drop;
    int count = 0;
    /*
      testing score display
    
    System.out.println("before");
    displayPossibleScores(this);
    System.out.println("after");
    */

    /**
     * Need to change the following code so that the user can choose
     * to score their turn after each roll
     */

    displayPossibleScores(this);
    promptUserToScoreOrNot();
    
    do {
      System.out.print("Choose which dice to reroll (Enter 0 to get reroll): ");
      drop = sc.nextInt();
      if(drop>0 && drop<=dice.size()) {
        dice.remove(drop-1);
        count++;
      }
    } while(drop !=0 && dice.size()>0);

    roll(count);
    checkDice();
    count = 0;
    do {
      System.out.print("Choose which dice to reroll (Enter 0 to get reroll): ");
      drop = sc.nextInt();
      if(drop>0 && drop<=dice.size()) {
        dice.remove(drop-1);
        count++;
      }
    } while(drop !=0 && dice.size()>0);

    roll(count);
    checkDice();
  }

  public void roll(int n) {
    for(int i=0; i<n; i++)
      dice.add((int)(Math.random()*6 + 1));
  }

  private boolean promptUserToScoreOrNot() {  
    String userScoreChoice;
    do {
      System.out.print("Do you want to score this roll?");
      userScoreChoice = sc.nextLine();
    } while (!(userScoreChoice.equals("y") || userScoreChoice.equals("n")));
    if (userScoreChoice.equals("y")) {
      return true;
    } else {
      return false;
    }
  }
  
  private int score(int scoreRow) {
    int result = 0;
    switch (scoreRow) {
      // Aces
      case 1:
        result = possibleSingleUpperSectionPoints(this).get(scoreRow-1);
        break;
      // Twos
      case 2:
        result = possibleSingleUpperSectionPoints(this).get(scoreRow-1);
        break;
      // Threes
      case 3:
        result = possibleSingleUpperSectionPoints(this).get(scoreRow-1);
        break;
      // Fours
      case 4:
        result = possibleSingleUpperSectionPoints(this).get(scoreRow-1);
        break;
      // Fives
      case 5:
        result = possibleSingleUpperSectionPoints(this).get(scoreRow-1);
        break;
      // Sixes
      case 6:
        result = possibleSingleUpperSectionPoints(this).get(scoreRow-1);
        break;
      // 3 of a Kind
      case 7:
        result = possibleThreeOfAKindPoints(this);
        break;
      // 4 of a Kind
      case 8:
        result = possibleFourOfAKindPoints(this);
        break;
      // Full House
      case 9:
        result = possibleFullHousePoints(this);
        break;
      // Small Straight
      case 10:
        result = possibleSmallStraightPoints(this);
        break;
      // Large Straight
      case 11:
        result = possibleLargeStraightPoints(this);
        break;
      // Yahtzee
      case 12:
        result = possibleYahtzeePoints(this);
        break;
      // Chance
      case 13:
        result = possibleChancePoints(this);
        break;
    }
    // If statement handles bonus yahtzee
    if (result == 100) {
      this.scoreSheet[13] += result;
    } else {
      this.scoreSheet[scoreRow-1] = result;
    }
    return 0;
  }

  /*
    public int[] checkScoring(ArrayList<Integer> dice) {
      int [] arr = [0, 0, 0, 0, 0, 0];
      for (i = 0; i < 5; i++) {
        if (dice.get(i) == 1) {
          arr[0] = arr[0]++;
        }
        if (dice.get(i) == 2) {
          arr[1] = arr[1]++;
        }
        if (dice.get(i) == 3) {
          arr[2] = arr[2]++;
        }
        if (dice.get(i) == 4) {
          arr[3] = arr[3]++;
        }
        if (dice.get(i) == 5) {
          arr[4] = arr[4]++;
        }
        if (dice.get(i) == 6) {
          arr[5] = arr[5]++;
        }
    }
    return arr;
    }
  */

  public void checkDice() {
    for(int val:dice)
      System.out.println(val);
  }

  public ArrayList<Integer> getDice() {
    return dice;
  }

  public static int sumDice(Yahtzee game) {
    int sumOfDice = 0;
    for (int die : game.dice) {
      sumOfDice += die;
    }
    return sumOfDice;
  }
  /**
    Checks total from upper section to see if
    bonus should be awarded or not. Returns 35
    if threshold is met, 0 otherwise.
   */
  public int checkUpperBonus(Yahtzee game) {
    int sum = 0;
    for (int i = 0; i < 6; i++) {
      sum += scoreSheet[i];
    }
    if (sum > 63) {
      return 35;
    } else {
      return 0;
    }
  }

  public static void displayPossibleScores(Yahtzee game) {
    // upper section
    int i = 1;
    for (int score : possibleSingleUpperSectionPoints(game)) {
      System.out.println("Possible Points in " + String.valueOf(i) + 
        "'s: " + String.valueOf(score));
      i++;
    }
    // lower section
    System.out.println("Possible Points in 3 of a Kind: " + 
        String.valueOf(possibleThreeOfAKindPoints(game)));
    System.out.println("Possible Points in 4 of a Kind: " + 
        String.valueOf(possibleFourOfAKindPoints(game)));
    System.out.println("Possible Points in Full House: " + 
        String.valueOf(possibleFullHousePoints(game)));
    System.out.println("Possible Points in Small Straight: " + 
        String.valueOf(possibleSmallStraightPoints(game)));
    System.out.println("Possible Points in Large Straight: " + 
        String.valueOf(possibleLargeStraightPoints(game)));
    System.out.println("Possible Points in Yahtzee: " + 
        String.valueOf(possibleYahtzeePoints(game)));
    System.out.println("Possible Points in Chance: " + 
        String.valueOf(possibleChancePoints(game))); 
  }

  /**
   * This method calculates the possible points for every row in the
   * upper section
   * @param game
   * @return an array of possible scores for the 6 rows 
   */
  public static ArrayList<Integer> possibleSingleUpperSectionPoints(Yahtzee game) {
    ArrayList<Integer> result = new ArrayList<Integer>();
    int score;
    for (int i = 1; i <= 6; i++) {
      score = 0;
      for(int die : game.dice) {
        if (die == i) {
          score += die;
        }
      }
      result.add(score);
    }
    return result;
  }

  /**
   * Calculates possible points in the 3 of a kind row
   * @param game
   * @return the sum of all dice if a 3 of a kind is present, 0
   * otherwise
   */
  public static int possibleThreeOfAKindPoints(Yahtzee game) {
    HashMap<Integer, Integer> diceCount = new HashMap<Integer, Integer>();
    int sumOfDice = 0;
    for (int die : game.dice) {
      if (diceCount.get(die) == null) {
        diceCount.put(die, 1);
      } else {
        diceCount.put(die, diceCount.get(die) + 1);
        if (diceCount.get(die) == 3) {
          sumOfDice = sumDice(game);
          return sumOfDice;
        }
      }
    }
    return 0;
  }

  /**
   * Calculates possible points in the 4 of a kind row
   * @param game
   * @return the sum of all dice if 4 of a kind is present, 0
   * otherwise
   */
  public static int possibleFourOfAKindPoints(Yahtzee game) {
    HashMap<Integer, Integer> diceCount = new HashMap<Integer, Integer>();
    int sumOfDice = 0;
    for (int die : game.dice) {
      if (diceCount.get(die) == null) {
        diceCount.put(die, 1);
      } else {
        diceCount.put(die, diceCount.get(die) + 1);
        if (diceCount.get(die) == 4) {
          sumOfDice = sumDice(game);
          return sumOfDice;
        }
      }
    }
    return 0;
  }

  /**
   * Calculates the possible points in the full house row
   * @param game
   * @return 25 if user has a full house, 0 otherwise
   */
  public static int possibleFullHousePoints(Yahtzee game) {
    HashMap<Integer, Integer> diceCount = new HashMap<Integer, Integer>();
    int sumOfDice = 0;
    for (int die : game.dice) {
      if (diceCount.get(die) == null) {
        diceCount.put(die, 1);
      } else {
        diceCount.put(die, diceCount.get(die) + 1);
      }

      boolean twoOfAKind = false, threeOfAKind = false;
      for (Entry<Integer, Integer> elem : diceCount.entrySet()) {
        int count = ((int)elem.getValue());
        if (count == 2) {
          twoOfAKind = true;
        } else if (count == 3) {
          threeOfAKind = true;
        }
    }
      if (twoOfAKind && threeOfAKind) {
        return 25;
      }
    }

    return 0;
  }

  /**
   * Calculates possible points in small straight row
   * [(1, 2, 3, 4), (2, 3, 4, 5), (3, 4, 5, 6)]
   * @param game
   * @return 30 if the user has a small straight, 0 otherwise
   */
  public static int possibleSmallStraightPoints(Yahtzee game) {
    if(game.dice.contains(1) && game.dice.contains(2) && 
        game.dice.contains(3) && game.dice.contains(4)) {
      return 30;
    } 
    else if(game.dice.contains(2) && game.dice.contains(3) && 
        game.dice.contains(4) && game.dice.contains(5)) {
      return 30;
    } 
    else if(game.dice.contains(3) && game.dice.contains(4) && 
        game.dice.contains(5) && game.dice.contains(6)) {
      return 30;
    }
    return 0;
  }

  /**
   * Calculates possible points in large straight row
   * [(1, 2, 3, 4, 5), (2, 3, 4, 5, 6)]
   * @param game
   * @return 40 if the user has a small straight, 0 otherwise
   */
  public static int possibleLargeStraightPoints(Yahtzee game) {
    ArrayList<Integer> dice = game.dice;
    Collections.sort(dice);
    for (int i = 1; i < 5; i++) {
      if(dice.get(i) != dice.get(i - 1) + 1) {
        return 0;
      }
    }
    return 40;
  }

  /**
   * Calculates possible points in Yahtzee row
   * @param game
   * @return 100 if the user has 5 dice of the same value and a 
   * previously scored Yahtzee, 50 if the user has 5 dice of the
   * same value and no previous Yahtzee, and 0 otherwise
   */
  public static int possibleYahtzeePoints(Yahtzee game) {
    ArrayList<Integer> dice = game.dice;
    Collections.sort(dice);
    if (dice.get(0) == dice.get(4)) {
      if (game.scoreSheet[11] == 50) {
        return 100;
      }
      return 50;
    }
    return 0;
  }

  /**
   * Calculates the possible points for the chance row
   * @param game
   * @return the sum of all dice
   */
  public static int possibleChancePoints(Yahtzee game) {
    return sumDice(game);
  }

  /*
      scoresheet array to store scores
      0: 1's
      1: 2's
      2: 3's
      3: 4's
      4: 5's
      5: 6's
      6: 3 of a kind
      7: 4 of a kind
      8: full house
      9: small straight
      10: large straigt
      11: Yahtzee
      12: Chance
      13: Bonus Yahtzee
      14: Upper Bonus
    */
}

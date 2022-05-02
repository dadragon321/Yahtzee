import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Collections;

public class Yahtzee {
  private Scanner sc;
  private static ArrayList<Integer> dice;
  private int[] scoreSheet;

  public static void main(String[] args) {
    Yahtzee game = new Yahtzee();
    game.turn();
  }

  public Yahtzee() {
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
  }

  public void turn() {
    roll(6);
    checkDice();
    int drop;
    int count = 0;
    /*
      testing score display
    */
    System.out.println("before");
    displayPossibleScores(this);
    System.out.println("after");
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

  private int score(ArrayList<Integer> roll) {
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
    This method checks each of the upper section boxes. It provides
    the user possible points for each of 1's, 2's, 3's, 4's, 5's and
    6's  
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
    This method checks to see if the user has a three of a kind  
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
    This method checks to see if the user has a four of a kind  
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
    This method checks for a full house. If one dice value has
    2 occurances and another dice value has 3, the user has a
    possible full house.
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
    This method checks all 3 possibilities of a small straight
   */
  public static String possibleSmallStraightPoints(Yahtzee game) {
    if(game.dice.contains(1) && game.dice.contains(2) && 
        game.dice.contains(3) && game.dice.contains(4)) {
      return "Possible Points in Small Straight: 30";
    } 
    else if(game.dice.contains(2) && game.dice.contains(3) && 
        game.dice.contains(4) && game.dice.contains(5)) {
      return "Possible Points in Small Straight: 30";
    } 
    else if(game.dice.contains(3) && game.dice.contains(4) && 
        game.dice.contains(5) && game.dice.contains(6)) {
      return "Possible Points in Small Straight: 30";
    }
    return "Possible Points in Small Straight: 0";
  }

  public static String possibleLargeStraightPoints(Yahtzee game) {
    ArrayList<Integer> dice = game.dice;
    Collections.sort(dice);
    for (int i = 1; i < 5; i++) {
      if(dice.get(i) != dice.get(i - 1) + 1) {
        return "Possible Points in Large Straight: 0";
      }
    }
    return "Possible Points in Large Straight: 40";
  }

  public static String possibleYahtzeePoints(Yahtzee game) {
    ArrayList<Integer> dice = game.dice;
    Collections.sort(dice);
    if (dice.get(0) == dice.get(4)) {
      if (game.scoreSheet[11] == 50) {
        return "Possible Points in Yahtzee: 100";
      }
      return "Possible Points in Yahtzee: 50";
    }
    return "Possible Points in Yahtzee: 0";
  }

  public static String possibleChancePoints(Yahtzee game) {
    return "Possible Points in Chance: " + String.valueOf(sumDice(game));
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

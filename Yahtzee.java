import java.util.ArrayList;
import java.util.Scanner;

public class Yahtzee {
  private Scanner sc;
  private ArrayList<Integer> dice;
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
    do {
      System.out.print("Choose which dice to reroll (Enter 0 to move to reroll): ");
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
      System.out.print("Choose which dice to reroll (Enter 0 to move to reroll): ");
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
    System.out.print(possibleSingleUpperSectionPoints(game));
  }

  public static String possibleSingleUpperSectionPoints(Yahtzee game) {
    String resultString = "";
    int score;

    for (int i = 1; i <= 6; i++) {
      score = 0;
      for(int die : game.dice) {
        if (die == i) {
          score += die;
        }
      }
      resultString += "Possible Points in " + String.valueOf(i) + "'s: " +
                      String.valueOf(score) + "\n";
    }

    return resultString;
  }

  public int possibleThreeOfAKindPoints(Yahtzee game) {
    return 0;
  }

  public int possibleFourOfAKindPoints(Yahtzee game) {
    return 0;
  }

  public int possibleFullHousePoints(Yahtzee game) {
    return 0;
  }

  public int possibleSmallStraightPoints(Yahtzee game) {
    return 0;
  }

  public int possibleSmallLargePoints(Yahtzee game) {
    return 0;
  }

  public int possibleYahtzeePoints(Yahtzee game) {
    return 0;
  }

  public int possibleChancePoints(Yahtzee game) {
    return 0;
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

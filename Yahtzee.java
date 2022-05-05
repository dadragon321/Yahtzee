import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Collections;

public class Yahtzee {
  private Scanner sc;
  private ArrayList<Integer> dice;
  private int[] scoreSheet;
  private int turn;

  public static void main(String[] args) {
    Yahtzee game = new Yahtzee();
    while (game.turn < 14)
      game.turn();

    game.finalScore();
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
    for (int i = 0; i < 15; i++)
      scoreSheet[i] = -1;
  }

  public void turn() {
    roll(5);
    Collections.sort(dice);
    checkDice();
    int drop;
    ArrayList<Integer> dropList = new ArrayList<Integer>();
    int count = 0;
    /*
      testing score display

    System.out.println("before");
    displayPossibleScores(this);
    System.out.println("after");
    */

    /*
     * displays the possible scores after first roll and asks the user if
     * they want to score the turn or not.
     */
    displayPossibleScores();
    if (promptUserToScoreOrNot()) {
      System.out.println(getScoreRow());
      turn++;
      return;
    }

    do {
      System.out.print("Choose which dice to reroll (Enter 0 to get reroll): ");
      drop = sc.nextInt();
      if(drop>0 && drop<=dice.size() && !dropList.contains(drop)) {
        dropList.add(drop);
        count++;
      }
    } while(drop !=0 && dice.size()>0);

    Collections.sort(dropList, Collections.reverseOrder());
    for(int d : dropList)
      dice.remove(d-1);

    roll(count);
    Collections.sort(dice);
    checkDice();
    dropList.clear();
    count = 0;

    displayPossibleScores();
    if (promptUserToScoreOrNot()) {
      System.out.println(getScoreRow());
      turn++;
      return;
    }

    do {
      System.out.print("Choose which dice to reroll (Enter 0 to get reroll): ");
      drop = sc.nextInt();
      if(drop>0 && drop<=dice.size() && !dropList.contains(drop)) {
        dropList.add(drop);
        count++;
      }
    } while(drop !=0 && dice.size()>0);

    Collections.sort(dropList, Collections.reverseOrder());
    for(int d : dropList)
      dice.remove(d-1);

    roll(count);
    Collections.sort(dice);
    checkDice();
    dropList.clear();

    displayPossibleScores();
    System.out.println(getScoreRow());
    dice.clear();
    turn++;
  }

  private void roll(int n) {
    for(int i=0; i<n; i++)
      dice.add((int)(Math.random()*6 + 1));
  }

  public void finalScore() {
    int totalScore = 0;
    scoreSheet[14] = upperTotalScore() > 63 ? 35 : 0;
    for (int score : scoreSheet)
      totalScore += score;
    System.out.println("Final Game Score: " + totalScore);
  }

  /*
   * Asks user to decide whether to score their roll or not
   * @return true if use says yes, false if they say no
   */
  private boolean promptUserToScoreOrNot() {
    String userScoreChoice;
    do {
      System.out.print("Do you want to score this roll? (y or n): ");
      userScoreChoice = sc.next();
    } while (!(userScoreChoice.equals("y") || userScoreChoice.equals("n")));
    if (userScoreChoice.equals("y"))
      return true;
    else
      return false;
  }

  private String getScoreRow() {
    int scoreRow = -1;
      do {
        System.out.print("Select Row to Score (See rows above): ");
        scoreRow = sc.nextInt();
      } while(scoreRow <= 0 || scoreRow > 13);
      return score(scoreRow);
  }

  private String score(int scoreRow) {
    int result = 0;
    switch (scoreRow) {
      // Aces
      case 1:
        result = possibleSingleUpperSectionPoints().get(scoreRow-1);
        break;
      // Twos
      case 2:
        result = possibleSingleUpperSectionPoints().get(scoreRow-1);
        break;
      // Threes
      case 3:
        result = possibleSingleUpperSectionPoints().get(scoreRow-1);
        break;
      // Fours
      case 4:
        result = possibleSingleUpperSectionPoints().get(scoreRow-1);
        break;
      // Fives
      case 5:
        result = possibleSingleUpperSectionPoints().get(scoreRow-1);
        break;
      // Sixes
      case 6:
        result = possibleSingleUpperSectionPoints().get(scoreRow-1);
        break;
      // 3 of a Kind
      case 7:
        result = possibleThreeOfAKindPoints();
        break;
      // 4 of a Kind
      case 8:
        result = possibleFourOfAKindPoints();
        break;
      // Full House
      case 9:
        result = possibleFullHousePoints();
        break;
      // Small Straight
      case 10:
        result = possibleSmallStraightPoints();
        break;
      // Large Straight
      case 11:
        result = possibleLargeStraightPoints();
        break;
      // Yahtzee
      case 12:
        result = possibleYahtzeePoints();
        break;
      // Chance
      case 13:
        result = sumDice();
        break;
    }
    // If statement handles bonus yahtzee
    if (result == 100) {
      this.scoreSheet[13] += result;
      dice.clear();
      return "Scored " + result + " in Bonus Yahtzee";
    } else {
      this.scoreSheet[scoreRow-1] = result;
      dice.clear();
      return "Scored " + result + " in row " + scoreRow;
    }
  }

  private void checkDice() {
	  int count = 1;
    for(int val : dice) {
      System.out.println("Die " + count + ": " + val);
      count++;
    }
    System.out.println();
  }

  private int sumDice() {
    int sumOfDice = 0;
    for (int die : dice)
      sumOfDice += die;
    return sumOfDice;
  }

  private void displayPossibleScores() {
    // upper section
    /*
    for (int score : scoreSheet) {
      System.out.println(score);
    }
    */
    int i = 1;
    for (int score : possibleSingleUpperSectionPoints()) {
      if (scoreSheet[i-1] == -1) {
        System.out.println("ROW " + i + ":  Possible Points in " + i +
          "'s............." + score);
      }
      i++;
    }

    // lower section
    if(scoreSheet[i-1] == -1)
      System.out.println("ROW 7:  Possible Points in 3 of a Kind....." + possibleThreeOfAKindPoints());
    i++;

    if(scoreSheet[i-1] == -1)
      System.out.println("ROW 8:  Possible Points in 4 of a Kind....." + possibleFourOfAKindPoints());
    i++;

    if(scoreSheet[i-1] == -1)
      System.out.println("ROW 9:  Possible Points in Full House......" + possibleFullHousePoints());
    i++;

    if(scoreSheet[i-1] == -1)
      System.out.println("ROW 10: Possible Points in Small Straight.." + possibleSmallStraightPoints());
    i++;

    if(scoreSheet[i-1] == -1)
      System.out.println("ROW 11: Possible Points in Large Straight.." + possibleLargeStraightPoints());
    i++;

    System.out.println("ROW 12: Possible Points in Yahtzee........." + possibleYahtzeePoints());
    i++;

    if(scoreSheet[i-1] == -1)
      System.out.println("ROW 13: Possible Points in Chance.........." + sumDice());
    i++;
  }

  /*
   * This method calculates the possible points for every row in the
   * upper section
   * @return an array of possible scores for the 6 rows
   */
  private ArrayList<Integer> possibleSingleUpperSectionPoints() {
    ArrayList<Integer> result = new ArrayList<Integer>();
    int score;
    for (int i = 1; i <= 6; i++) {
      score = 0;
      for(int die : dice)
        if (die == i)
          score += die;
      result.add(score);
    }
    return result;
  }

  /*
   * Calculates possible points in the 3 of a kind row
   * @return the sum of all dice if a 3 of a kind is present, 0
   * otherwise
   */
  private int possibleThreeOfAKindPoints() {
    HashMap<Integer, Integer> diceCount = new HashMap<Integer, Integer>();
    int sumOfDice = 0;
    for (int die : dice) {
      if (diceCount.get(die) == null)
        diceCount.put(die, 1);
      else {
        diceCount.put(die, diceCount.get(die) + 1);
        if (diceCount.get(die) == 3) {
          sumOfDice = sumDice();
          return sumOfDice;
        }
      }
    }
    return 0;
  }

  /*
   * Calculates possible points in the 4 of a kind row
   * @return the sum of all dice if 4 of a kind is present, 0
   * otherwise
   */
  private int possibleFourOfAKindPoints() {
    HashMap<Integer, Integer> diceCount = new HashMap<Integer, Integer>();
    int sumOfDice = 0;
    for (int die : dice) {
      if (diceCount.get(die) == null)
        diceCount.put(die, 1);
      else {
        diceCount.put(die, diceCount.get(die) + 1);
        if (diceCount.get(die) == 4) {
          sumOfDice = sumDice();
          return sumOfDice;
        }
      }
    }
    return 0;
  }

  /*
   * Calculates the possible points in the full house row
   * @return 25 if user has a full house, 0 otherwise
   */
  private int possibleFullHousePoints() {
    HashMap<Integer, Integer> diceCount = new HashMap<Integer, Integer>();
    for (int die : dice) {
      if (diceCount.get(die) == null)
        diceCount.put(die, 1);
      else
        diceCount.put(die, diceCount.get(die) + 1);

      boolean twoOfAKind = false, threeOfAKind = false;
      for (Entry<Integer, Integer> elem : diceCount.entrySet()) {
        int count = ((int)elem.getValue());
        if (count == 2)
          twoOfAKind = true;
        else if (count == 3)
          threeOfAKind = true;
    }
      if (twoOfAKind && threeOfAKind)
        return 25;
    }

    return 0;
  }

  /*
   * Calculates possible points in small straight row
   * [(1, 2, 3, 4), (2, 3, 4, 5), (3, 4, 5, 6)]
   * @return 30 if the user has a small straight, 0 otherwise
   */
  private int possibleSmallStraightPoints() {
    if(dice.contains(1) && dice.contains(2) && dice.contains(3) && dice.contains(4))
      return 30;
    else if(dice.contains(2) && dice.contains(3) && dice.contains(4) && dice.contains(5))
      return 30;
    else if(dice.contains(3) && dice.contains(4) && dice.contains(5) && dice.contains(6))
      return 30;
    return 0;
  }

  /*
   * Calculates possible points in large straight row
   * [(1, 2, 3, 4, 5), (2, 3, 4, 5, 6)]
   * @return 40 if the user has a small straight, 0 otherwise
   */
  private int possibleLargeStraightPoints() {
    for (int i = 1; i < 5; i++)
      if(dice.get(i) != dice.get(i - 1) + 1)
        return 0;
    return 40;
  }

  /*
   * Calculates possible points in Yahtzee row
   * @return 100 if the user has 5 dice of the same value and a
   * previously scored Yahtzee, 50 if the user has 5 dice of the
   * same value and no previous Yahtzee, and 0 otherwise
   */
  private int possibleYahtzeePoints() {
    if (dice.get(0) == dice.get(4)) {
      if (scoreSheet[11] == 50)
        return 100;
      return 50;
    }
    return 0;
  }

  private int upperTotalScore() {
    int upperTotal = 0;
    for (int i = 0; i < 6; i++)
      upperTotal += scoreSheet[i];
    return upperTotal;
  }
}

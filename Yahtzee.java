import java.util.ArrayList;
import java.util.Scanner;

public class Yahtzee {
  private Scanner sc;
  private ArrayList<Integer> dice;

  public static void main(String[] args) {
    Yahtzee game = new Yahtzee();
    game.roll(7);
    for(int val:game.getDice())
      System.out.println(val);
  }

  public Yahtzee() {
    sc = new Scanner(System.in);
    dice = new ArrayList<Integer>();
    dice = new int[5];
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
    int[] scoreSheet = new int[15];
  }

  public turn() {
    game.roll(6);
    int drop;
    while(i !=0 && dice.size()>0) {
      System.out.print("Choose which dice to reroll (Enter 0 to move to reroll): ");
      drop = sc.nextInt();
      if(drop>0 && drop<dice.size()+1)
        dice.remove(drop+1);
    }
  }

  public void roll(int n) {
    for(int i=0; i<n; i++)
      dice.add((int)(Math.random()*6 + 1));
  }

  private int score(ArrayList<Integer> roll) {
    return 0;
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
      sum += game.scoreSheet[i];
    }
    if (sum > 63) {
      return 35;
    } else {
      return 0;
    }
  }
}

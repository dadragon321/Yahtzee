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
}

import java.util.ArrayList;

public class Yahtzee {
    private ArrayList<Integer> dice;

  public static void main(String[] args) {
    Yahtzee game = new Yahtzee();
    game.roll(7);
    for(int val:game.getDice())
      System.out.println(val);
  }

  public Yahtzee() {
    dice = new ArrayList<Integer>();
    dice = new int[5];
    /*
      scoresheet 2D array to store scores
      0: 


    */
    int[] scoreSheet = new int[13];
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

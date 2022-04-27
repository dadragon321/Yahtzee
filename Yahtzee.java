public class Yahtzee {
  public static void main(String[] args) {
    Yahtzee game = new Yahtzee();
    int[] dice = game.roll();
    for(int val:dice)
      System.out.println(val);
  }

  public int[] roll() {
    int[] dice = new int[5];
    for(int i=0; i<5; i++)
      dice[i] = (int)(Math.random()*6 + 1);
    return dice;
  }
  public boolean testMethod() {
    return true;
  }
}

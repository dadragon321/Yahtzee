public class Yahtzee {
  private int[] dice;

  public static void main(String[] args) {
    Yahtzee game = new Yahtzee();
    game.roll();
    for(int val:game.getDice())
      System.out.println(val);
  }

  public Yahtzee() {
    dice = new int[5];
  }

  public void roll() {
    for(int i=0; i<5; i++)
      dice[i] = (int)(Math.random()*6 + 1);
  }

  private int score(int[] roll) {
    
  }

  public int[] getDice() {
    return dice;
  }
  public boolean testMethod() {
    return false;
  }
}

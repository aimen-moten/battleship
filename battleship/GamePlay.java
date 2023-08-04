import java.util.Scanner;

class GamePlay{
  private Player currentPlayer;
  private Player waitingPlayer;
  private Player lastToHit;
  private HumanPlayer user;
  private ComputerPlayer computer = new ComputerPlayer();
  //private boolean hit;
  private Scanner scanner;
  private boolean gameOver = false;

// 6
  
  public GamePlay() {
    
  }
  public void play() {
    //does all the gameplay stuff here
    startTasks();
    
    currentPlayer = user;
    waitingPlayer = computer;

    //loop until game over
    do {
      //player takes their turn
      currentPlayer.takeTurn(waitingPlayer);
      //let them know if it hit or missed
      if (currentPlayer.getPrevShot()) {
        System.out.println("It hits!");
        lastToHit = currentPlayer;
      }
      else {
        System.out.println("It missed!");
      }
      
      //if the player hit, they go again, if they missed the other player goes next
      switchTurns(currentPlayer.getPrevShot());

      //set the game over variable so the loop will end when the game does
      setGameOver();
    } while (!gameOver);

    gameOverMsg();

    //if the user loses show them the computer's board
    if(user.getHasWon()) {
      computer.printOwnBoard();
      System.out.println("");
      System.out.println("");
      System.out.println("Here were your guesses");
      System.out.println("");
      user.printPreviousGuesses();
    }
  }

  public void startTasks () {
    scanner = new Scanner(System.in);
    //player enters a name & player is created
    System.out.println("Enter name"); 
    user = new HumanPlayer(scanner.nextLine());
    //calls place ships for the player
    user.placeShips();
    user.printOwnBoard();
    computer.placeShips();
  }
  
  public void switchTurns(boolean hit) {
    Player store = currentPlayer;
    if (!hit) {
      currentPlayer = waitingPlayer;
      waitingPlayer = store;
    }
  }

  public void setGameOver() {
    if(currentPlayer.getHasWon()) {
      gameOver = true;
    }
  }

  public void gameOverMsg() {
      System.out.println("");
      System.out.println("Player: " + lastToHit.getName() + " has won!");
      System.out.println("GAME OVER");
  }

  /*
  public void placeShips() {
    for(Ship ship : currentPlayer.getShipList()) { //problem: should only loop to next value if previous placement was successful
      System.out.println("Please enter an x-coordinate value for ship " + ship.getSize());
      int x = scanner.nextInt();
      System.out.println("Please enter a y-coordinate value for ship " + ship.getSize());
      int y = scanner.nextInt();
      System.out.println("Is the ship vertical? (enter true or false)");
      boolean b = scanner.nextBoolean();
      currentPlayer.setLocation(x, y, b, ship);
    }
  }
  */
}
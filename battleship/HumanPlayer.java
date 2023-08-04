import java.util.Scanner;

class HumanPlayer implements Player {
  private String name;
  private Scanner scanner = new Scanner(System.in);
  private boolean prevShot;
  private boolean hasWon = false;
  private int hits = 0;
  private Board board = new Board();
  private String[][] guessed = new String[10][10];

  public HumanPlayer(String initName) {
    name = initName;
    for(int i = 0; i < 10; i++) {
      for(int j = 0; j < 10; j++) {
        guessed[j][i] = " ~ |";
      }
    }
  }

  public Ship[] getShipList() {
    return board.ships;
  }

  public boolean getHasWon() {
    if(hits == 17) {
      hasWon = true;
    }
    return hasWon;
  }


/* part of the GamePlay class startTasks function
  public void setName() {
    System.out.println("Enter your name: ");
    name = scanner.nextLine();
  }
*/

  public String getName() {
    return name;
  }

  public boolean getPrevShot() {
    return prevShot;
  }

  public void takeTurn (Player computer) {
    //helper for the play function
    //runs through tasks of a whole turn for one player
    System.out.println("");
    System.out.println("Here are your guesses so far (X represents a hit, O represents a miss, and ~ represents a spot you haven't guessed yet)");
    System.out.println("");
    printPreviousGuesses();
    System.out.println("");
    
    System.out.println("Enter an x coordinate 1 through 10: ");
    int x = scanner.nextInt()-1;

    System.out.println("Enter a y coordinate 1 through 10: ");

    int y = scanner.nextInt()-1;

    //send guess to other player to ask if there's a ship there
    prevShot = computer.checkGuess(x, y);
    logGuess(prevShot, x, y);
  }

  public void logGuess(boolean hit, int x, int y) {
    if(hit) {
      guessed[y][x] = " X |";
      hits++;
    }
    else {
      guessed[y][x] = " O |";
    }
  }

  public void printPreviousGuesses() {
    System.out.println(" 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10");
    System.out.println("———————————————————————————————————————");
    for(int i = 0; i < 10; i++) {
      for(int j = 0; j < 10; j++) {
          System.out.print(guessed[i][j]);
      }
    System.out.println(i+1);
    }
  }

  public boolean checkGuess(int x, int y) {
    return board.getVal(x, y);
  }

  /*
  public boolean checkWinStatus() {
    Ship[] list = this.board.ships;
    for (Ship ship : list){
      if(!(ship.getSunk())){
        return hasWon;
      }
    }
    hasWon = true;
    return hasWon;
  }
  */

  public void placeShips() {
    boolean placed = false;
    for(Ship ship : board.ships) {
      do {
        System.out.println("Please enter an x-coordinate value for ship " + ship.getSize());
        int x = scanner.nextInt()-1;
        System.out.println("Please enter a y-coordinate value for ship " + ship.getSize());
        int y = scanner.nextInt()-1;
        System.out.println("Is the ship vertical? (enter true or false)");
        boolean b = scanner.nextBoolean();
        placed = setLocation(x, y, b, ship);
      } while(!placed);
    }
  }

  public boolean setLocation(int x, int y, boolean vert, Ship ship) {
    ship.setX(x); // y = row # & x = col #
    ship.setY(y);
    ship.setVertical(vert);
    boolean valid = board.validPlacement(x, y, vert, ship);
    
    if(valid && vert) {
      for(int i = 0; i < ship.getSize(); i++) {
        board.setVal(x, (y+i), true);
      }
        System.out.println("Ship of size " + ship.getSize() + " placed successfully at (" + (x+1) + ", " + (y+1) + ") through " + "(" + (x+1) + ", " + (y+1+(ship.getSize()-1)) + ")");
      return valid;
    }
    else if(valid) {
        for(int j = 0; j < ship.getSize(); j++) {
          board.setVal((x+j), y, true);
        }
      System.out.println("Ship of size " + ship.getSize() + " placed successfully at (" + (x+1) + ", " + (y+1) + ") through " + "(" + (x+1+(ship.getSize()-1)) + ", " + (y+1) + ")");
      return valid;
      }
    else {
      System.out.println("Invalid placement, select another location");
      return valid;
    }
  }

  public void printOwnBoard() {
    System.out.println("");
    System.out.println("Here is your board (O represents a ship and ~ represents water)");
    System.out.println("");
    System.out.println(" 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10");
    System.out.println("———————————————————————————————————————");
    for(int i = 0; i < 10; i++) {
      for(int j = 0; j < 10; j++) {
        if(board.getVal(j, i)) {
          System.out.print(" O |");
        }
        else if(!board.getVal(j, i)) {
          System.out.print(" ~ |");
        }
      }
    System.out.println(i+1);
    }
  }
}
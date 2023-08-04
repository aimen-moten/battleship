//import random class and have the computer act randomly within reason
import java.util.Random;

class ComputerPlayer implements Player {
  private String name = "computer";
  private Random r = new Random();
  private boolean prevShot;
  private boolean hasWon = false;
  private int hits = 0;
  private Board board = new Board();
  private boolean[][] guessed = new boolean[10][10];

  public ComputerPlayer() {
    for(int i = 0; i < 10; i++) {
      for(int j = 0; j < 10; j++) {
        guessed[i][j] = false;
      }
    }
  }

  public String getName() {
    return name;
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

  public boolean getPrevShot() {
    return prevShot;
  }
  
  public void takeTurn (Player human) {
    //helper for the play function
    //runs through tasks of a whole turn for one player
    int x;
    int y;
    do {
      x = r.nextInt(10);
      y = r.nextInt(10);
    } while(this.guessed[y][x]);
    

    //send guess to otherPlayer and ask if there's a ship there
    System.out.println("The computer guessed (" + (x+1) + ", " + (y+1) + ") ");
    prevShot = human.checkGuess(x, y);
    logGuess(prevShot, x, y);
  }

  public void logGuess(boolean hit, int x, int y) {
    guessed[y][x] = true;
    if(hit) {
      hits++;
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
    //hard coded random placement of ships similar to user version
    boolean placed = false;
    for(Ship ship : board.ships) {
      do {
        int x = r.nextInt(10);
        int y = r.nextInt(10);
        boolean b = r.nextBoolean();
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
    }
    else if(valid) {
        for(int j = 0; j < ship.getSize(); j++) {
          board.setVal((x+j), y, true);
        }
      }
    return valid;
  }

  public void printOwnBoard() {
    System.out.println("");
    System.out.println("Here is the computer's board (O represents a ship and ~ represents water)");
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
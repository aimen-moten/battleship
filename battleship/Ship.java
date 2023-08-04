class Ship{
  private final int size;
  private int firstX;
  private int firstY;
  private boolean vertical;
  private boolean sunk = false;
  //private int hits;

  public Ship(int initSize){
    size = initSize;
    //hits = 0;
  }

  public void setVertical(boolean vert) {
    vertical = vert;
  }

  public int getSize() {
    return size;
  }

  public boolean getSunk () {
    return sunk;
  }

  public int getX() {
    return firstX;
  }

  public int getY() {
    return firstY;
  }

  public void setX(int x) {
    firstX = x;
  }

  public void setY(int y) {
    firstY = y;
  }

/*  
  public boolean validPlacement (Point xy, boolean vert) {
    vertical = vert;
    if(currentPlayer.board[firstIndicies.getY()][firstIndicies.getX()].getHasShip()){
      return false;
    }
    else if(vertical) {
      for(int i = 0; i < size; i++){
        if(board[firstIndicies.getY()+i][firstIndicies.getX()].getHasShip()) {
          return false;
        }
      }
    }
    else {
      for(int j = 0; j < size; j++){
        if(board[firstIndicies.getY()][firstIndicies.getX()+i].getHasShip()) {
          return false;
        }
      }
    }
  }
*/

/*
  public boolean setLocation(int x, int y, boolean vert, Player thisPlayer) {
    firstX = x; // y = row # & x = col #
    firstY = y;
    vertical = vert;
    boolean valid = thisPlayer.board.validPlacement(firstX, firstY, vertical, this);
    
    if(valid && vertical) {
      for(int i = 0; i < size; i++) {
        thisPlayer.board[firstY+i][firstX] = true;
      }
        System.out.println("Ship of size " + size + " placed successfully at (" + firstX + ", " + firstY + ") through " + "(" + firstX + ", " + (firstY+(size-1)) + ")");
      return valid;
    }
    else if(valid) {
        for(int j = 0; j < size; j++) {
          thisPlayer.board[firstY][firstX+1] = true;
        }
      System.out.println("Ship of size " + size + " placed successfully at (" + firstX + ", " + firstY + ") through " + "(" + (firstX+(size-1)) + ", " + firstY + ")");
      return valid;
      }
    else {
      System.out.println("Invalid placement, select another location");
      return valid;
    }
  }
*/

/*
  public void beenHit () {
    board[lastGuess.getY()][lastGuess.getX()].beenHit = true;
    for(int i = 0; i < 10; i++) {
      for(int j = 0; i < 10; i++) {
        //if()
      }
    }
  }
*/
}
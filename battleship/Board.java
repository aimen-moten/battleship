class Board {
  public Ship[] ships = new Ship[5];
  public boolean[][] board = new boolean[10][10];

  //initializes the 2d array for the board and initializes a list of ships of the sizes being used in the game to be placed by the player
  public Board () {
    for(int i = 0; i < 10; i++) {
      for(int j = 0; i < 10; i++) {
        board[i][j] = false;
      }
    }
    ships[0] = new Ship(2);
    ships[1] = new Ship(3);
    ships[2] = new Ship(3);
    ships[3] = new Ship(4);
    ships[4] = new Ship(5);
  }

  public boolean getVal(int x, int y) {
    return board[y][x];
  }

  public void setVal(int x, int y, boolean val) {
    board[y][x] = val;
  }

  public boolean validPlacement (int x, int y, boolean vert, Ship ship) {
    
    ship.setVertical(vert);
    int i;
    if(board[y][x]){
      return false;
    }
    else if(vert) { //ship is vertical, check y-axis
      if(y+ship.getSize() > 10) {
        return false;
      }
      else {
        for(i = 0; i < ship.getSize(); i++){
          if(board[y+i][x]) {
            return false;
          }
        }
      }
    }
    else { //ship is horizontal, check x-axis
      if(x+ship.getSize() > 10) {
        return false;
      }
      else {
        for(i = 0; i < ship.getSize(); i++){
          if(board[y][x+i]) {
            return false;
          }
        }
      }
    }
    return true;
  }
}
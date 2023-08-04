//make interface with human player and computer player

interface Player{
  public void takeTurn (Player otherPlayer);
  //public boolean checkWinStatus();
  public Ship[] getShipList();
  public boolean getPrevShot();
  public boolean getHasWon();
  public boolean checkGuess(int x, int y);
  public void placeShips();
  public boolean setLocation(int x, int y, boolean vert, Ship ship);
  //public void getOwnBoard();
  public void printOwnBoard();
  public String getName();
}
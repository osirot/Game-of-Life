/**
 * Class Life is the model (data structure) that holds and updates the
 * status of our cellular automaton game.  It provides methods to initialize
 * the game (done automatically when the game is first created), update one generation
 * and access the game status, which can be used by client code to display
 * the game board and the state of the game
 * 
 * @author  Barbara Goldner, based on work by Hal Perkins.  
 * @author Olga Sirotinsky
 * 5/28/15

 */
public class Life {
    // public constants

    /** Number of rows/columns in Life Grid  */
    public static final int NROWSCOLS = 19; 

    // private instance variables
    private State[][] board;      // game board
    private int generations;   // how many generations have happened

    /** Construct and initialize new game board
     *  @param display The LifeGUI display object for this game */
    public Life() {
        board = new State[NROWSCOLS][NROWSCOLS];
        newGame();
    }

    /** initialize new game
     * */
    public void newGame() {
        /*****************************************************
         *  STUDENTS:  setup your initial Life environment here
         *****************************************************/
        for(int row=0; row<NROWSCOLS; row++){
            for(int col=0; col<NROWSCOLS; col++){
                //set every sell in game to dead
                board[row][col]=State.DEAD;
            }
        }
        //board[NROWSCOLS/2][NROWSCOLS/2]=State.ALIVE;//test
        //call shape staic methods for new game
        beehive(5,5);
        block(6,9);
        blinkerToad(12,7);
        glider(12,14);
        getGenerationCount();
    }

    /** Returns the number of generations
     * @return The number of generations that have been run so far.
     */
    public int getGenerationCount() {
        return generations;
    }

    /** Return state of game board cell at given row/column
     *  (Squares numbered from 0 to NROWSCOLS-1). 
     *  @throws IllegalArgumentException for bad row/col 
     *  @param row The row of the deisred cell.
     *  @param col The column of the desired cell.
     */
    public State getCell(int row, int col) {
        //if check for valid row/col see above  
        if(row <0 || row >NROWSCOLS-1){
            throw new IllegalArgumentException();
        }
        if(col <0 || col >NROWSCOLS-1){
            throw new IllegalArgumentException();
        }
        return board [row][col];
        //return State.DEAD;  // stub value
        /***************************************
         *  STUDENTS: fix this 
         **************************************/
    }

    /** Process one life cycle of the cellular automaton
     * 
     */
    public void lifeCycle() {
        /***************************************
         *  STUDENTS: implement this 
         **************************************/

        for(int row=1; row<NROWSCOLS-1; row++){  
            for(int col=1; col<NROWSCOLS-1; col++){
                int neighbors=0;              
                if( col==0 || col==NROWSCOLS-1){
                    board[row][col]=State.DEAD;//keep boundaries dead 
                    col++;
                }else{
                    //count surrounding 8 neighbors with if statements
                    if ((board[row-1][col-1])==State.ALIVE){
                        neighbors++;
                    }
                    if ((board[row-1][col])==State.ALIVE){
                        neighbors++;
                    }
                    if ((board[row-1][col+1])==State.ALIVE){
                        neighbors++;
                    }
                    if (board[row][col-1]==State.ALIVE){
                        neighbors++;
                    }
                    if ((board[row][col+1])==State.ALIVE){
                        neighbors++;
                    }
                    if ((board[row+1][col-1])==State.ALIVE){
                        neighbors++;
                    }
                    if ((board[row+1][col])==State.ALIVE){
                        neighbors++;
                    }
                    if ((board[row+1][col+1])==State.ALIVE){
                        neighbors++;
                    }

                    //After neighbors are counted update state of cell either dead or alive
                    if (board[row][col]==State.ALIVE){
                        if (neighbors==2 || neighbors==3){
                            //alive if 2 or 3 neighbors
                            board[row][col]=State.ALIVE;
                        }else{
                            //dead if anything besides 2 or 3
                            board[row][col]=State.DEAD;
                        }
                    }else{
                        if(neighbors==3){
                            //alive if 3 neighbors
                            board[row][col]=State.ALIVE;
                        }else{
                            //die if not 3 neighbors
                            board[row][col]=State.DEAD;
                        }
                    }
                }
            }
        }
        generations++;
    }

    /**
     * create a block shape on gameboard 2x2 image below cell to be called in newGame()
     *   ..
     *   ..
     */
    public void block(int row, int col){
        board[row][col]=State.ALIVE;
        board[row][col+1]=State.ALIVE;
        board[row+1][col]=State.ALIVE;
        board[row+1][col+1]=State.ALIVE;
    }

    /**
     * create a beehive shape using 3x3 cells image below and call in newGame()
     *   . 
     *  . .
     *  . .
     *   .  
     */
    public void beehive(int row, int col){
        board[row][col]=State.ALIVE;
        board[row+1][col-1]=State.ALIVE;
        board[row+1][col+1]=State.ALIVE;
        board[row+2][col+-1]=State.ALIVE;
        board[row+2][col+1]=State.ALIVE;  
        board[row+3][col]=State.ALIVE; 
    }

    /** create "blinker toad" shape image belowin new game
     *    ...
     *   ... 
     */public void blinkerToad(int row, int col){
        board[row][col]=State.ALIVE;
        board[row][col+1]=State.ALIVE;
        board[row][col+2]=State.ALIVE;
        board[row+1][col-1]=State.ALIVE;
        board[row+1][col]=State.ALIVE;
        board[row+1][col+1]=State.ALIVE;
    }

    /** create glider shape image below in new game
     *    .
     *    ..
     *   . . 
     * 
     */public void glider(int row, int col){
        board[row][col]=State.ALIVE;
        board[row+1][col]=State.ALIVE;
        board[row+1][col+1]=State.ALIVE;
        board[row+2][col-1]=State.ALIVE;
        board[row+2][col+1]=State.ALIVE;
    }
}

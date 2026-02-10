/*                                         __             __             __              
 * .--------.---.-.-----.-----.-----.-----|  .--.--.-----|__.-----.---.-|  |_.-----.----.
 * |        |  _  |-- __|  -__|__ --|  _  |  |  |  |  -__|  |     |  _  |   _|  _  |   _|
 * |__|__|__|___._|_____|_____|_____|_____|__|\___/|_____|__|__|__|___._|____|_____|__|  
 *  
 * Add maze(s) to test_cases and run for pretty formatting
 */

class MazeSolver {
    // Array of test mazes to iterate over for debugging
    // 3D like Minecraft!
    static int[][][] test_cases = {  
        // Canvas example
       {{0,0,1,0,0,0,1,0,0,0}, 
        {1,0,1,0,1,0,1,0,1,0},
        {1,0,0,0,1,0,0,0,1,0},
        {1,1,1,0,1,1,1,0,1,0},
        {0,0,0,0,0,0,1,0,0,0},
        {0,1,1,1,1,0,1,1,1,0},
        {0,1,0,0,0,0,0,0,1,0},
        {0,1,0,1,1,1,1,0,1,0},
        {0,0,0,1,0,0,0,0,0,0},
        {1,1,0,1,0,1,1,1,1,0}},
        
        // Obstructed start
       {{1,0},
        {0,0}},
        
        // Unobstructed start, no paths
       {{0,1},
        {1,1}},

        // Simple branch
       {{0,0,0,0,0,0,0,0,0,0}, 
        {1,1,1,1,0,1,1,1,1,1},
        {0,0,0,1,0,1,0,0,0,0},
        {0,0,0,1,0,1,1,1,1,1},
        {0,0,0,1,0,0,0,0,0,0}},

        // Empty array handling
        // Empty two-dimensional
       {{}}, 
       
       /*
       // Empty one-dimensional
       {},
       */

       // One line
       {{0,0,0}},

       // Bounds detection
       {{0,0,0,0,0,0},
        {1,1,1,1,1,0,0,0,0},
        {0,0,0,0,1,1,1,1,0}},
    };

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        int case_num = 0;
        
        // Run all test cases
        for (int[][] maze : test_cases) {
            case_num++;
            System.out.println("Case " + case_num + ":");
            
            recurse(maze, 0, 0);

            for (int[] y : maze) {
                for (int x : y) {
                    System.out.print(x);
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static boolean recurse(int[][] maze, int y, int x) {
        int[] row = {};

        int h = maze.length;
        int w = 0;

        // Check size, height
        if (h <= 0) {
            System.out.println("Unsolveable / invalid maze : Empty array");
            return false;
        } else {
            row = maze[y];
            w = row.length;
        }
        if (w <= 0) {
            System.out.println("Unsolveable / invalid maze : Empty array");
            return false;
        }
      
        if ( x == 0 && y == 0 && maze[y][x] == 1) {
            System.out.println("Unsolveable / invalid maze : Obstructed Start");
            return false;
        }

        if (maze[y][x] == 0) {
            maze[y][x] = 2;

            if (x - 1 >= 0 && maze[y][x - 1] == 0) { // left
                recurse(maze, y, x - 1);
            }
             if (y + 1 < h && maze[y + 1][x] == 0) { // down
                recurse(maze, y + 1, x);
            }
             if (x + 1 < w && maze[y][x + 1] == 0) { // right
                recurse(maze, y, x + 1);
            }
             if (y - 1 >= 0 && maze[y - 1][x] == 0) { // up
                recurse(maze, y - 1, x);
            } 
            
            if (y == h - 1 && x == w - 1) {
                System.out.println("Solved!");
            } /* else {
                System.out.println("Unsolveable / invalid maze : Paths exhausted");
            } */
        } 
        
        return true;
    }

}
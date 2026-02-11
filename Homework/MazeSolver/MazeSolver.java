/*                                         __             __             __              
 * .--------.---.-.-----.-----.-----.-----|  .--.--.-----|__.-----.---.-|  |_.-----.----.
 * |        |  _  |-- __|  -__|__ --|  _  |  |  |  |  -__|  |     |  _  |   _|  _  |   _|
 * |__|__|__|___._|_____|_____|_____|_____|__|\___/|_____|__|__|__|___._|____|_____|__|  
 *  
 * Add maze(s) to test_cases and run
 */

class MazeSolver {

    // Array of test mazes to iterate over for debugging
    // 3D like Minecraft!
    static int[][][] test_cases = {
        // Canvas example
        {{0, 0, 1, 0, 0, 0, 1, 0, 0, 0},
        {1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
        {1, 0, 0, 0, 1, 0, 0, 0, 1, 0},
        {1, 1, 1, 0, 1, 1, 1, 0, 1, 0},
        {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
        {0, 1, 1, 1, 1, 0, 1, 1, 1, 0},
        {0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
        {0, 1, 0, 1, 1, 1, 1, 0, 1, 0},
        {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
        {1, 1, 0, 1, 0, 1, 1, 1, 1, 0}},
        // Obstructed start
        {{1, 0},
        {0, 0}},
        // Unobstructed start, no paths
        {{0, 1},
        {1, 1}},
        // Simple branch
        {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
        {0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
        {0, 0, 0, 1, 0, 1, 1, 1, 1, 1},
        {0, 0, 0, 1, 0, 0, 0, 0, 0, 0}},
        // Empty array handling
        // Empty two-dimensional
        {{}},
        // Empty one-dimensional
        {},
        // One line
        {{0, 0, 0}},
        // Bounds detection
        {{0, 0, 0, 0, 0, 0},
        {1, 1, 1, 1, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 1, 1, 1, 0}},};

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        int case_num = 0;

        // Run all test cases
        for (int[][] maze : test_cases) {
            case_num++;
            System.out.println("Case " + case_num + ":");

            if (recurse(maze, 0, 0)) {
                System.out.println("Solved!");
            } else {
                System.out.println("No paths found.");
            }

            // Print out full maze including the final path found (if there is one)
            // denoted by 2s
            // Walls: 1s
            // Possible paths: 0s
            // Path found: 2s
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
            System.out.println("Unsolveable / invalid maze : Empty array"); // Invalid maze -> Base case
            return false;
        } else {
            row = maze[y];
            w = row.length;
        }
        // Check size, width
        if (w <= 0) {
            System.out.println("Unsolveable / invalid maze : Empty array"); // Invalid maze -> Base case
            return false;
        }

        // Check start position is valid
        if (x == 0 && y == 0) {
            if (maze[y][x] == 1) {
                System.out.println("Unsolveable / invalid maze : Obstructed Start"); // Obstructed start -> Base case
                return false;
            }
        }

        // Paths exhausted
        if (maze[y][x] != 0) {
            return false;
        }

        // Mark current
        maze[y][x] = 2;

        if (y == h - 1 && x == w - 1) { // Maze solved -> Base Case
            return true;
        }

        // try all 4 directions (Recursive cases)
        if (x - 1 >= 0 && recurse(maze, y, x - 1)) { // left
            return true;
        }
        if (y + 1 < h && recurse(maze, y + 1, x)) { // down
            return true;
        } 
        if (x + 1 < w && recurse(maze, y, x + 1)) { // right
            return true;
        }
        if (y - 1 >= 0 && recurse(maze, y - 1, x)) { // up
            return true;
        }

        // Erase previous
        maze[y][x] = 0;

        return false;
    }

}

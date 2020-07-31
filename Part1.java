import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;

public class Part1 {
    private static int[][] grid;
    private static int n;
    private static int m;

    public static long getMax(String fn, int mVal) {
        loadGrid(fn);
        m = mVal;
        System.out.println("M val = " + mVal);
        long max_product = Integer.MIN_VALUE;
        System.out.println("Size of grid: " + n);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

        long max_horizontal_product = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= (n - mVal); j++) {
                long product_horizontal = 1;
                int k = 0;
                while (k < mVal) {
                    product_horizontal = product_horizontal * grid[i][j + k];
                    k++;
                }
                max_horizontal_product = Math.max(product_horizontal, max_horizontal_product);

            }

        }
        //System.out.println("Horiz = " + max_horizontal_product);

        long max_vertical_product = 0;
        for (int i = 0; i <= (n  - mVal); i++) {
            for (int j = 0; j < n; j++) {
                long product_vertical = 1;
                int  k = 0;
                while (k < mVal) {
                    product_vertical = product_vertical * grid[i + k][j];
                    k++;
                }
                max_vertical_product = Math.max(product_vertical, max_vertical_product);
            }
        }
        //System.out.println("Vert = " + max_vertical_product);

        long max_anti_diagonal_product = 0;
        for (int i = 0; i < (n - mVal); i++) {
            for (int j = 0; j < (n - mVal); j++) {
                long product_anti_diagonal = 1;
                int k = 0;
                while (k < mVal) {
                    product_anti_diagonal = product_anti_diagonal * grid[i + k][j + k];
                    k++;
                }
                max_anti_diagonal_product = Math.max(max_anti_diagonal_product, product_anti_diagonal);
            }
        }
        //System.out.println("Diag = " + max_anti_diagonal_product);

        long max_diagonal_product = 0;
        for (int i = mVal; i < n; i++) {
            for (int j = 0; j < (n - mVal); j++) {
                long product_diagonal = 1;
                int k = 0;
                while (k < mVal) {
                    product_diagonal = product_diagonal * grid[i - k][j + k];
                    k++;
                }
                max_diagonal_product = Math.max(max_diagonal_product, product_diagonal);
            }
        }
        //System.out.println("Diag = " + max_anti_diagonal_product);

        max_product = Math.max(Math.max(max_diagonal_product, max_anti_diagonal_product), Math.max(max_horizontal_product, max_vertical_product));
        return max_product;
    }

    private static void loadGrid(String fn) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fn));
            String line = reader.readLine();
            if(line != null) {
                n = Integer.parseInt(line);
                grid = new int[n][n];
                line = reader.readLine();
                int r = 0;
                while(line != null) {
                    String[] str = line.split(" ");
                    if(str.length < n)
                        break;
                    for(int c = 0; c < n; c++)
                        grid[r][c] = Integer.parseInt(str[c]);
                    r++;
                    line = reader.readLine();
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(Arrays.deepToString(grid));
    }
}
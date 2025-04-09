class Solution {
    private int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
    private int m, n;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        m = matrix.length;
        n = matrix[0].length;
        int[][] memo = new int[m][n];
        int maxLen = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxLen = Math.max(maxLen, dfs(matrix, i, j, memo));
            }
        }

        return maxLen;
    }

    private int dfs(int[][] matrix, int i, int j, int[][] memo) {
        if (memo[i][j] != 0) return memo[i][j];

        int max = 1; // at least the cell itself
        for (int[] dir : directions) {
            int x = i + dir[0], y = j + dir[1];
            if (x >= 0 && y >= 0 && x < m && y < n && matrix[x][y] > matrix[i][j]) {
                int len = 1 + dfs(matrix, x, y, memo);
                max = Math.max(max, len);
            }
        }

        memo[i][j] = max;
        return max;
    }
}

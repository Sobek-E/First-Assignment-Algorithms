import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Exercises {

    public int[] productIndices(int[] values, int target) {
        for (int i = 0; i < values.length; i++) {
            for (int j = i + 1; j < values.length; j++) {
                if (values[i] * values[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }


    public int[] spiralTraversal(int[][] values, int rows, int cols) {
        List<Integer> result = new ArrayList<>();
        int top = 0, bottom = rows - 1, left = 0, right = cols - 1;

        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) result.add(values[top][i]);
            top++;
            for (int i = top; i <= bottom; i++) result.add(values[i][right]);
            right--;
            if (top <= bottom) {
                for (int i = right; i >= left; i--) result.add(values[bottom][i]);
                bottom--;
            }
            if (left <= right) {
                for (int i = bottom; i >= top; i--) result.add(values[i][left]);
                left++;
            }
        }
        return result.stream().mapToInt(i -> i).toArray();
    }

    private void partitionHelper(int n, int max, List<Integer> current, List<List<Integer>> result) {
        if (n == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = Math.min(n, max); i >= 1; i--) {
            current.add(i);
            partitionHelper(n - i, i, current, result);
            current.remove(current.size() - 1);
        }
    }

    public int[][] intPartitions(int n) {
        List<List<Integer>> partitions = new ArrayList<>();
        partitionHelper(n, n, new ArrayList<>(), partitions);
        int[][] result = new int[partitions.size()][];
        for (int i = 0; i < partitions.size(); i++) {
            result[i] = partitions.get(i).stream().mapToInt(Integer::intValue).toArray();
        }
        return result;
    }

    public static void main(String[] args) {
        Exercises exercises = new Exercises();

        System.out.println(Arrays.toString(exercises.productIndices(new int[]{1, 2, 3, 4}, 8))); // [1, 3]
        System.out.println(Arrays.toString(exercises.spiralTraversal(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, 3, 3))); // [1, 2, 3, 6, 9, 8, 7, 4, 5]
        System.out.println(Arrays.deepToString(exercises.intPartitions(4))); // [[4], [3, 1], [2, 2], [2, 1, 1], [1, 1, 1, 1]]
    }
}
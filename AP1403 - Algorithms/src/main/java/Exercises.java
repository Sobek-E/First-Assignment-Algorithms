public class Exercises {

    /*
        there is an array of positive integers as input of function and another integer for the target value
        all the algorithm should do is to find those two integers in array which their multiplication is the target
        then it should return an array of their indices
        e.g. {1, 2, 3, 4} with target of 8 -> {1, 3}

        note: you should return the indices in ascending order and every array's solution is unique
    */
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


    /*
        given a matrix of random integers, you should do spiral traversal in it
        e.g. if the matrix is as shown below:
        1 2 3
        4 5 6
        7 8 9
        then the spiral traversal of that is:
        {1, 2, 3, 6, 9, 8, 7, 4, 5}

        so you should walk in that matrix in a curl and then add the numbers in order you've seen them in a 1D array
    */
    public int[] spiralTraversal(int[][] values, int rows, int cols) {
        int[] result = new int[rows * cols];
        int index = 0;

        int top = 0, bottom = rows - 1, left = 0, right = cols - 1;

        while (top <= bottom && left <= right) {

            for (int i = left; i <= right; i++) {
                result[index++] = values[top][i];
            }
            top++;

            for (int i = top; i <= bottom; i++) {
                result[index++] = values[i][right];
            }
            right--;

            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result[index++] = values[bottom][i];
                }
                bottom--;
            }

            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result[index++] = values[i][left];
                }
                left++;
            }
        }

        return result;
    }


    /*
        integer partitioning is a combinatorics problem in discreet maths
        the problem is to generate sum numbers which their summation is the input number

        e.g. 1 -> all partitions of integer 3 are:
        3
        2, 1
        1, 1, 1

        e.g. 2 -> for number 4 goes as:
        4
        3, 1
        2, 2
        2, 1, 1
        1, 1, 1, 1

        note: as you can see in examples, we want to generate distinct summations, which means 1, 2 and 2, 1 are no different
        you should generate all partitions of the input number and

        hint: you can measure the size and order of arrays by finding the pattern of partitions and their number
        trust me, that one's fun and easy :)

        if you're familiar with lists and arraylists, you can also edit method's body to use them instead of array
    */
    public int[][] intPartitions(int n) {
        int maxPartitions = 100;
        int[][] result = new int[maxPartitions][n];
        int[] currentPartition = new int[n];
        int partitionCount = 0;

        partitionCount = findPartitions(n, 1, currentPartition, result, partitionCount);

        int[][] finalResult = new int[partitionCount][];
        for (int i = 0; i < partitionCount; i++) {
            finalResult[i] = result[i];
        }

        return finalResult;
    }

    private int findPartitions(int n, int start, int[] currentPartition, int[][] result, int partitionCount) {
        if (n == 0) {
            int[] partitionCopy = new int[partitionCount];
            System.arraycopy(currentPartition, 0, partitionCopy, 0, partitionCount);
            result[partitionCount] = partitionCopy;
            partitionCount++;
            return partitionCount;
        }

        for (int i = start; i <= n; i++) {
            currentPartition[partitionCount] = i;
            partitionCount = findPartitions(n - i, i, currentPartition, result, partitionCount);
        }

        return partitionCount;
    }

    public static void main(String[] args) {
        Exercises exercises = new Exercises();

        System.out.println("Product Indices Test:");
        int[] values1 = {1, 2, 3, 4};
        int target1 = 8;
        int[] result1 = exercises.productIndices(values1, target1);
        System.out.println("Indices for product 8: [" + result1[0] + ", " + result1[1] + "]");

        System.out.println("\nSpiral Traversal Test:");
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[] result2 = exercises.spiralTraversal(matrix, 3, 3);
        System.out.print("Spiral Traversal: ");
        for (int val : result2) {
            System.out.print(val + " ");
        }

        System.out.println("\n\nInteger Partitions Test:");
        int n = 4;
        int[][] result3 = exercises.intPartitions(n);
        System.out.println("Partitions for " + n + ":");
        for (int[] partition : result3) {
            for (int num : partition) {
                if (num != 0) {
                    System.out.print(num + " ");
                }
            }
            System.out.println();
        }
    }
}

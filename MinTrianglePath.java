import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MinTrianglePath {

	public static final String NODE_SEPARATOR = " ";

	public static void main(String[] args) throws IOException {

		// reads the standard input
		List<String> input = new ArrayList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		while (s != null && !s.equals("EOF")) {
			input.add(s);
			s = br.readLine();
		}

		// auxiliaries data structures declaration
		ArrayList<ArrayList<Integer>> triangleAux = new ArrayList<>();
		ArrayList<String> nodesString = new ArrayList<>();
		ArrayList<Integer> nodes;

		// auxiliaries data structures population
		for (String row : input) {
			nodes = new ArrayList<>();
			nodesString = new ArrayList<String>(Arrays.asList(row.split(NODE_SEPARATOR)));
			nodes = (ArrayList<Integer>) nodesString.stream().map(nS -> Integer.parseInt(nS))
					.collect(Collectors.toList());
			triangleAux.add(nodes);
		}

		// arraylist to array
		int x = triangleAux.size();
		int y = triangleAux.get(x - 1).size();
		int[][] triangle = new int[x][y];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < i + 1; j++) {
				triangle[i][j] = triangleAux.get(i).get(j);
			}
		}

		// calculate solution
		int w = 0;
		int[] solution = findShortestPath(triangle);
		System.out.print("Minimal path is :");
		for (int i = 0; i < solution.length; i++) {
			int n = triangle[i][solution[i]];
			w += n;
			System.out.print(n);
			if (i < solution.length - 1) {
				System.out.print(" -> ");
			}
		}
		System.out.println(" = " + w);
	}

	public static int[] findShortestPath(int[][] triangle) {
		// iterate rows backward
		int minColIndex = 0;
		for (int rowIndex = triangle.length - 2; rowIndex >= 0; rowIndex--) {
			int[] row = triangle[rowIndex];
			int[] nextRow = triangle[rowIndex + 1];
			// iterate columns backward
			for (int col = rowIndex; col >= 0; col--) {
				minColIndex = col;// row.length-1;
				int min;
				min = Math.min(nextRow[col], nextRow[col + 1]);
				row[col] += min;
				// the value in vectors will be the place/column the value came from
				if (triangle[rowIndex][col] < triangle[rowIndex][minColIndex]) {
					minColIndex = col;
				}
			}
		}
		int[] path = new int[triangle.length];
		minColIndex = 0;
		// loops row top-bottom
		for (int i = 0; i < triangle.length - 1; i++) {
			int nextMinColIndex = minColIndex;
			if (triangle[i + 1][minColIndex] > triangle[i + 1][minColIndex + 1]) {
				nextMinColIndex = minColIndex + 1;
			}
			triangle[i][minColIndex] -= triangle[i + 1][nextMinColIndex];
			minColIndex = nextMinColIndex;
			path[i + 1] = nextMinColIndex;
		}
		return path;
	}

}
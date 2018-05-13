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
		// public TrianglePath findShortestPath(int[][] triangle) {
		int[][] copy = copy2Array(triangle);
		int[][] auxiliray = new int[copy.length][];
		// iterate rows backward
		for (int rowIndex = copy.length - 2; rowIndex >= 0; rowIndex--) {
			int[] row = copy[rowIndex];
			int[] nextRow = copy[rowIndex + 1];
			auxiliray[rowIndex] = new int[row.length];
			// iterate columns backward
			for (int col = row.length - 2; col >= 0; col--) {
				int min;
				min = Math.min(nextRow[col], nextRow[col + 1]);
				row[col] += min;
				// the value in vectors will be the place/column the value came from
				auxiliray[rowIndex][col] = nextRow[col] == min ? col : col + 1;
			}
		}
		int[] path = new int[auxiliray.length];
		for (int i = 1; i < auxiliray.length; i++) {
			path[i] = auxiliray[i - 1][path[i - 1]];
		}
		// return new TrianglePath(path);
		return path;
	}

	static int[][] copy2Array(int[][] input) {
		int[][] result = new int[input.length][];
		for (int i = 0; i < input.length; i++) {
			result[i] = Arrays.copyOf(input[i], input[i].length);
		}
		return result;
	}

}

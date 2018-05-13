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
		int[] solution = findShortestPath(triangle);
		System.out.println("Shortest path:");
		for (int i = 0; i < solution.length; i++) {
			int n = triangle[i][solution[i]];
			System.out.print(n);
			if (i < solution.length - 1) {
				System.out.print(" -> ");
			}
		}
		System.out.println();
	}

	public static int[] findShortestPath(int[][] triangle) {
		// public TrianglePath findShortestPath(int[][] triangle) {
		int[][] aux = copy2Array(triangle);
		int[][] vectors = new int[aux.length][];
		// iterate rows backward
		for (int row = aux.length - 2; row >= 0; row--) {
			vectors[row] = new int[aux[row].length];
			// iterate columns backward
			for (int col = aux[row].length - 2; col >= 0; col--) {
				int min;
				min = Math.min(aux[row + 1][col], aux[row + 1][col + 1]);
				aux[row][col] += min;
				// the value in vectors will be the place/column the value came from
				vectors[row][col] = aux[row + 1][col] == min ? col : col + 1;
			}
		}
		int[] path = new int[vectors.length];
		for (int i = 1; i < vectors.length; i++) {
			path[i] = vectors[i - 1][path[i - 1]];
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

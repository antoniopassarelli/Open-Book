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
		Triangle t = new Triangle(triangle);
		TrianglePath result = t.findShortestPath();
		int[] solution = (result.getPath());
		System.out.println("Shortest path:");
		for (int i = 0; i < solution.length; i++) {
			int n = t.getTriangle()[i][solution[i]];
			System.out.print(n);
			if (i < solution.length - 1) {
				System.out.print(" -> ");
			}
		}
		System.out.println();
	}

}

import java.util.Arrays;

public class Triangle {

	private final int[][] triangle;

	public Triangle(int[][] triangle) {
		this.triangle = triangle;
	}

	static int[][] copy2Array(int[][] input) {
		int[][] result = new int[input.length][];
		for (int i = 0; i < input.length; i++) {
			result[i] = Arrays.copyOf(input[i], input[i].length);
		}
		return result;
	}

	public TrianglePath findShortestPath() {
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
		return new TrianglePath(path);
	}

	public int[] pathValues(int[] path) {
		int[] vals = new int[path.length];
		for (int i = 0; i < path.length; i++) {
			vals[i] = triangle[i][path[i]];
		}
		return vals;
	}

	public int[][] getTriangle() {
		return triangle;
	}
}

import java.util.Arrays;

public class TrianglePath {

	private final int[] path;

	public TrianglePath(int[] path) {
		this.path = Arrays.copyOf(path, path.length);
	}

	public int[] getPath() {
		return Arrays.copyOf(path, path.length);
	}

}

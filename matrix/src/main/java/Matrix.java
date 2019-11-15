import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Matrix {

	private final List<int[]> matrix;

	Matrix(String matrixAsString) {
		matrix = Stream.of(matrixAsString.split("\n"))
		    .map(rowAsString -> Stream.of(rowAsString.split(" ")).mapToInt(Integer::valueOf).toArray())
		    .collect(Collectors.toList());
	}

	int[] getRow(int rowNumber) {
		return matrix.get(rowNumber - 1);
	}

	int[] getColumn(int columnNumber) {
		return matrix.stream().mapToInt(rowArray -> rowArray[columnNumber - 1]).toArray();
	}

}

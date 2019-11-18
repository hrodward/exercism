import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class MinesweeperBoard {
	private List<String> inputBoard;
	private List<Point2D> mines;

	public MinesweeperBoard(List<String> inputBoard) {
		super();
		this.inputBoard = inputBoard;
	}

	public List<String> withNumbers() {
		if (inputBoard.size() == 0) {
			return Collections.emptyList();
		} else if (inputBoard.size() == 1 && "".contentEquals(inputBoard.get(0))) {
			return Collections.singletonList("");
		}
		mines = findAllMines();
		return fillBoard();
	}

	private List<Point2D> findAllMines() {
		final Point point = new Point(-1, -1);
		return 
				inputBoard.stream()
					.flatMap(rowAsString -> {
      			Stream<Point2D> rowMines = Stream.of(rowAsString.split(""))
      					.map(position -> {
          				point.x++;
          				return "*".equals(position) ? new Point(point) : null;
          			})
      					.filter(p -> p != null)
      					.flatMap(Stream::of);
      			point.y++;
      			point.x = -1;
      			return rowMines;
					})
					.collect(Collectors.toList());
	}

	private List<String> fillBoard() {
		List<String> board = new ArrayList<String>();
		for (int y = 0; y < inputBoard.size(); y++) {
			board.add(traverseRow(y));
		}
		return board;
	}

	private String traverseRow(int y) {
		StringBuilder outputRow = new StringBuilder();
		String[] positions = inputBoard.get(y).split("");

		for (int x = 0; x < positions.length; x++) {
			if ("*".equals(positions[x])) {
				outputRow.append("*");
				continue;
			}
			Point2D currentPosition = new Point(x, y);
			long counter = mines.stream().filter(mine -> currentPosition.distanceSq(mine) <= 2).count();
			outputRow.append(counter > 0 ? counter : " ");
		}

		return outputRow.toString();
	}

}
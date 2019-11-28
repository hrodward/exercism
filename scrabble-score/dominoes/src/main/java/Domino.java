import java.util.Objects;

class Domino {
	private final int left;
	private final int right;

	Domino(final int left, final int right) {
		this.left = left;
		this.right = right;
	}

	int getLeft() {
		return this.left;
	}

	int getRight() {
		return this.right;
	}

	@Override
	public boolean equals(final Object o) {
		Domino otherDomino = (Domino) o;
		return this.getLeft() == otherDomino.getLeft() && this.getRight() == otherDomino.getRight()
		    || this.getLeft() == otherDomino.getRight() && this.getRight() == otherDomino.getLeft();
	}

	@Override
	public int hashCode() {
		return Objects.hash(left, right);
	}

	@Override
	public String toString() {
		return "[" + left + ", " + right + "]";
	}


}

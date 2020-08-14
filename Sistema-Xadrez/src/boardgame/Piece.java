package boardgame;

public abstract class Piece {
	protected Position position;
	private Board board;

	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	protected Board getBoard() {
		return board;
	}
	
	//  Abstract for possibleMoves
	public abstract boolean[][] possibleMoves();
	
	// Returns if there are possible moves for that piece in a specific row and column
	public boolean possibleMove (Position pos) {
		return possibleMoves()[pos.getRow()][pos.getColumn()];
	}
	
	// Checks if the piece is "stuck" and return true movements for that piece to perform
	public boolean isTheAnyPossibleMove() {
		boolean[][] mat = possibleMoves();
		
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if (mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
}

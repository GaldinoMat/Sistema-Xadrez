package boardgame;

public class Board {
	private int rows;
	private int columns;
	private Piece[][] pieces;

	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			throw new BoardGameException("Error creating board: there must be at least 1 row and 1 column.");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	// Checks if it is possible to place piece on given position
	public Piece piece(int row, int column) {
		if (!positionExists(row, column)) {
			throw new BoardGameException("Position not on the board.");
		}
		return pieces[row][column];
	}

	// Checks if it is possible to place piece on given position
	public Piece piece(Position pos) {
		if (!positionExists(pos)) {
			throw new BoardGameException("Position not on the board.");
		}
		return pieces[pos.getRow()][pos.getColumn()];
	}

	// Check if there is a piece on position, if not places the piece
	public void placePiece(Piece piece, Position pos) {
		if (thereIsAPiece(pos)) {
			throw new BoardGameException("There is already a piece on position " + pos + ".");
		}
		pieces[pos.getRow()][pos.getColumn()] = piece;
		piece.position = pos;
	}
	
	public Piece removePiece(Position pos) {
		if (!positionExists(pos)) {
			throw new BoardGameException("Position not on the board.");
		}
		if (piece(pos) == null) {
			return null;
		}
		Piece aux = piece(pos);
		aux.position = null;
		pieces[pos.getRow()][pos.getColumn()] = null;
		return aux;
	}

	// Checks if position exists on given row and column
	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}

	// Checks if position exists on given position
	public boolean positionExists(Position pos) {
		return positionExists(pos.getRow(), pos.getColumn());
	}

	// Checks if there is a piece in position
	public boolean thereIsAPiece(Position pos) {
		if (!positionExists(pos)) {
			throw new BoardGameException("Position not on the board.");
		}
		return piece(pos) != null;
	}
}

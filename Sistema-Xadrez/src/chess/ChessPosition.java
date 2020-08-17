package chess;

import boardgame.Position;

public class ChessPosition {
	private char column;
	private int row;

	public ChessPosition(char column, int row) {
		if (column < 'a' || column > 'h' || row < 1 || row > 8) {
			throw new ChessException("Error instantiating Chess Position. Valid values are from a1 to h8.");
		}
		this.column = column;
		this.row = row;
	}

	protected char getColumn() {
		return column;
	}

	protected int getRow() {
		return row;
	}

	// Gets matrix position and converts to chess position
	protected Position toPosition() {
		return new Position(8 - row, column - 'a');
	}

	// Gets chess position and converts to matrix position
	protected static ChessPosition fromPosition(Position pos) {
		return new ChessPosition((char) ('a' + pos.getColumn()), 8 - pos.getRow());
	}

	@Override
	public String toString() {
		return "" + column + row;
	}
}

package chess;

import boardgame.*;
import chess.pieces.*;

public class ChessMatch {
	private Board board;

	public ChessMatch() {
		board = new Board(8, 8);
		initialSetup();
	}

	// Gets pieces and casts them as chess pieces
	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}

		return mat;
	}
	
	public boolean[][] possibleMoves(ChessPosition sourcePos){
		Position pos = sourcePos.toPosition();
		validateSourcePos(pos);
		return board.piece(pos).possibleMoves();
	}

	// Gets a piece and returns any captured piece in board
	public ChessPiece performChessMove(ChessPosition sourcePos, ChessPosition targetPos) {
		Position source = sourcePos.toPosition();
		Position target = targetPos.toPosition();

		validateSourcePos(source);
		validateTargetPos(source, target);
		Piece capturedPiece = makeMove(source, target);
		return (ChessPiece) capturedPiece;
	}

	// Moves the piece and removes piece at target pos if any
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece capturedP = board.removePiece(target);
		board.placePiece(p, target);

		return capturedP;
	}

	// Checks if move is possible
	private void validateSourcePos(Position source) {
		if (!board.thereIsAPiece(source)) {
			throw new ChessException("There is no piece on source position");
		}
		if (!board.piece(source).isTheAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece.");
		}
	}

	private void validateTargetPos(Position source, Position target) {
		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can not move to the desired target");
		}
	}

	// Places piece in matrix from chess coordinates
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}

	private void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
		placeNewPiece('d', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new King(board, Color.WHITE));

		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
}

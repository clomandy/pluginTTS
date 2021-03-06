package ch.supsi.dti.parser;

/** Token Manager. */
public class CommandParserTokenManager implements CommandParserConstants {

	static final long[] jjbitVec0 = { 0x0L, 0x0L, 0xffffffffffffffffL,
			0xffffffffffffffffL };
	static final int[] jjnextStates = { 81, 82, 83, 70, 85, 86, 88, 34, 39, 57,
			26, 27, 29, 35, 36, 40, 41, 42, 44, 54, 55, 45, 47, 49, 50, 51, 52,
			54, 55, 45, 72, 74, 75, 76, 77, 82, 83, 70, 121, 127, 133, 139,
			105, 110, 114, 94, 100, 34, 35, 36, 46, 47, 49, 66, 67, 71, 72, 74, };
	/** Token literal values. */
	public static final String[] jjstrLiteralImages = { "", null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, };
	static final long[] jjtoSkip = { 0x3000001eL, };
	static final long[] jjtoSpecial = { 0x30000000L, };
	static final long[] jjtoToken = { 0xe412043e1L, };
	/** Lexer state names. */
	public static final String[] lexStateNames = { "DEFAULT", };
	protected char curChar;
	int curLexState = 0;
	/** Debug output. */
	public java.io.PrintStream debugStream = System.out;
	int defaultLexState = 0;
	protected SimpleCharStream input_stream;
	int jjmatchedKind;
	int jjmatchedPos;
	int jjnewStateCnt;

	int jjround;

	private final int[] jjrounds = new int[140];
	private final int[] jjstateSet = new int[280];

	/** Constructor. */
	public CommandParserTokenManager(SimpleCharStream stream) {
		if (SimpleCharStream.staticFlag)
			throw new Error(
					"ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
		input_stream = stream;
	}

	/** Constructor. */
	public CommandParserTokenManager(SimpleCharStream stream, int lexState) {
		this(stream);
		SwitchTo(lexState);
	}

	/** Get the next Token. */
	public Token getNextToken() {
		Token specialToken = null;
		Token matchedToken;
		int curPos = 0;

		EOFLoop: for (;;) {
			try {
				curChar = input_stream.BeginToken();
			} catch (java.io.IOException e) {
				jjmatchedKind = 0;
				matchedToken = jjFillToken();
				matchedToken.specialToken = specialToken;
				return matchedToken;
			}

			try {
				input_stream.backup(0);
				while (curChar <= 32 && (0x100002600L & (1L << curChar)) != 0L)
					curChar = input_stream.BeginToken();
			} catch (java.io.IOException e1) {
				continue EOFLoop;
			}
			jjmatchedKind = 0x7fffffff;
			jjmatchedPos = 0;
			curPos = jjMoveStringLiteralDfa0_0();
			if (jjmatchedKind != 0x7fffffff) {
				if (jjmatchedPos + 1 < curPos)
					input_stream.backup(curPos - jjmatchedPos - 1);
				if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L) {
					matchedToken = jjFillToken();
					matchedToken.specialToken = specialToken;
					return matchedToken;
				} else {
					if ((jjtoSpecial[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L) {
						matchedToken = jjFillToken();
						if (specialToken == null)
							specialToken = matchedToken;
						else {
							matchedToken.specialToken = specialToken;
							specialToken = (specialToken.next = matchedToken);
						}
					}
					continue EOFLoop;
				}
			}
			int error_line = input_stream.getEndLine();
			int error_column = input_stream.getEndColumn();
			String error_after = null;
			boolean EOFSeen = false;
			try {
				input_stream.readChar();
				input_stream.backup(1);
			} catch (java.io.IOException e1) {
				EOFSeen = true;
				error_after = curPos <= 1 ? "" : input_stream.GetImage();
				if (curChar == '\n' || curChar == '\r') {
					error_line++;
					error_column = 0;
				} else
					error_column++;
			}
			if (!EOFSeen) {
				input_stream.backup(1);
				error_after = curPos <= 1 ? "" : input_stream.GetImage();
			}
			throw new TokenMgrError(EOFSeen, curLexState, error_line,
					error_column, error_after, curChar,
					TokenMgrError.LEXICAL_ERROR);
		}
	}

	private void jjAddStates(int start, int end) {
		do {
			jjstateSet[jjnewStateCnt++] = jjnextStates[start];
		} while (start++ != end);
	}

	private void jjCheckNAdd(int state) {
		if (jjrounds[state] != jjround) {
			jjstateSet[jjnewStateCnt++] = state;
			jjrounds[state] = jjround;
		}
	}

	private void jjCheckNAddStates(int start, int end) {
		do {
			jjCheckNAdd(jjnextStates[start]);
		} while (start++ != end);
	}

	private void jjCheckNAddTwoStates(int state1, int state2) {
		jjCheckNAdd(state1);
		jjCheckNAdd(state2);
	}

	protected Token jjFillToken() {
		final Token t;
		final String curTokenImage;
		final int beginLine;
		final int endLine;
		final int beginColumn;
		final int endColumn;
		String im = jjstrLiteralImages[jjmatchedKind];
		curTokenImage = (im == null) ? input_stream.GetImage() : im;
		beginLine = input_stream.getBeginLine();
		beginColumn = input_stream.getBeginColumn();
		endLine = input_stream.getEndLine();
		endColumn = input_stream.getEndColumn();
		t = Token.newToken(jjmatchedKind, curTokenImage);

		t.beginLine = beginLine;
		t.endLine = endLine;
		t.beginColumn = beginColumn;
		t.endColumn = endColumn;

		return t;
	}

	private int jjMoveNfa_0(int startState, int curPos) {
		int startsAt = 0;
		jjnewStateCnt = 140;
		int i = 1;
		jjstateSet[0] = startState;
		int kind = 0x7fffffff;
		for (;;) {
			if (++jjround == 0x7fffffff)
				ReInitRounds();
			if (curChar < 64) {
				long l = 1L << curChar;
				do {
					switch (jjstateSet[--i]) {
					case 14:
					case 32:
						if ((0x3ff401800000000L & l) == 0L)
							break;
						if (kind > 30)
							kind = 30;
						jjCheckNAdd(32);
						break;
					case 19:
						if ((0x3ff401800000000L & l) == 0L)
							break;
						if (kind > 30)
							kind = 30;
						jjCheckNAdd(32);
						break;
					case 17:
						if ((0x3ff401800000000L & l) == 0L)
							break;
						if (kind > 30)
							kind = 30;
						jjCheckNAdd(32);
						break;
					case 15:
						if ((0x3ff401800000000L & l) == 0L)
							break;
						if (kind > 30)
							kind = 30;
						jjCheckNAdd(32);
						break;
					case 18:
						if ((0x3ff401800000000L & l) == 0L)
							break;
						if (kind > 30)
							kind = 30;
						jjCheckNAdd(32);
						break;
					case 16:
						if ((0x3ff401800000000L & l) == 0L)
							break;
						if (kind > 30)
							kind = 30;
						jjCheckNAdd(32);
						break;
					case 140:
						if ((0x3ff401800000000L & l) == 0L)
							break;
						if (kind > 30)
							kind = 30;
						jjCheckNAdd(32);
						break;
					case 4:
						if ((0x3ff401800000000L & l) == 0L)
							break;
						if (kind > 30)
							kind = 30;
						jjCheckNAdd(32);
						break;
					case 5:
						if ((0x3ff000000000000L & l) != 0L) {
							if (kind > 24)
								kind = 24;
							jjCheckNAddStates(0, 6);
						} else if (curChar == 46)
							jjCheckNAddTwoStates(69, 79);
						else if (curChar == 34)
							jjCheckNAddTwoStates(66, 67);
						else if (curChar == 39)
							jjCheckNAddTwoStates(61, 62);
						else if (curChar == 58)
							jjAddStates(7, 9);
						else if (curChar == 47)
							jjstateSet[jjnewStateCnt++] = 24;
						else if (curChar == 45)
							jjstateSet[jjnewStateCnt++] = 21;
						break;
					case 21:
						if (curChar != 45)
							break;
						if (kind > 28)
							kind = 28;
						jjCheckNAdd(22);
						break;
					case 22:
						if ((0xffffffffffffdbffL & l) == 0L)
							break;
						if (kind > 28)
							kind = 28;
						jjCheckNAdd(22);
						break;
					case 23:
						if (curChar == 45)
							jjstateSet[jjnewStateCnt++] = 21;
						break;
					case 24:
						if (curChar == 42)
							jjCheckNAddTwoStates(25, 26);
						break;
					case 25:
						if ((0xfffffbffffffffffL & l) != 0L)
							jjCheckNAddTwoStates(25, 26);
						break;
					case 26:
						if (curChar == 42)
							jjCheckNAddStates(10, 12);
						break;
					case 27:
						if ((0xffff7bffffffffffL & l) != 0L)
							jjCheckNAddTwoStates(28, 26);
						break;
					case 28:
						if ((0xfffffbffffffffffL & l) != 0L)
							jjCheckNAddTwoStates(28, 26);
						break;
					case 29:
						if (curChar == 47 && kind > 29)
							kind = 29;
						break;
					case 30:
						if (curChar == 47)
							jjstateSet[jjnewStateCnt++] = 24;
						break;
					case 33:
						if (curChar == 58)
							jjAddStates(7, 9);
						break;
					case 35:
						if ((0x3ff401800000000L & l) == 0L)
							break;
						if (kind > 33)
							kind = 33;
						jjAddStates(13, 14);
						break;
					case 36:
						if (curChar == 46)
							jjstateSet[jjnewStateCnt++] = 37;
						break;
					case 38:
						if ((0x3ff401800000000L & l) == 0L)
							break;
						if (kind > 33)
							kind = 33;
						jjstateSet[jjnewStateCnt++] = 38;
						break;
					case 39:
						if ((0x3ff000000000000L & l) == 0L)
							break;
						if (kind > 33)
							kind = 33;
						jjCheckNAddStates(15, 21);
						break;
					case 40:
						if ((0x3ff000000000000L & l) == 0L)
							break;
						if (kind > 33)
							kind = 33;
						jjCheckNAdd(40);
						break;
					case 41:
						if ((0x3ff000000000000L & l) == 0L)
							break;
						if (kind > 33)
							kind = 33;
						jjCheckNAddTwoStates(41, 42);
						break;
					case 42:
						if (curChar == 46)
							jjCheckNAdd(43);
						break;
					case 43:
						if ((0x3ff000000000000L & l) == 0L)
							break;
						if (kind > 33)
							kind = 33;
						jjCheckNAdd(43);
						break;
					case 44:
						if ((0x3ff000000000000L & l) == 0L)
							break;
						if (kind > 33)
							kind = 33;
						jjCheckNAddTwoStates(44, 45);
						break;
					case 46:
						if ((0x280000000000L & l) != 0L)
							jjAddStates(22, 23);
						break;
					case 47:
						if (curChar == 46)
							jjCheckNAdd(48);
						break;
					case 48:
						if ((0x3ff000000000000L & l) == 0L)
							break;
						if (kind > 33)
							kind = 33;
						jjCheckNAdd(48);
						break;
					case 49:
						if ((0x3ff000000000000L & l) == 0L)
							break;
						if (kind > 33)
							kind = 33;
						jjCheckNAddStates(24, 26);
						break;
					case 50:
						if ((0x3ff000000000000L & l) == 0L)
							break;
						if (kind > 33)
							kind = 33;
						jjCheckNAdd(50);
						break;
					case 51:
						if ((0x3ff000000000000L & l) == 0L)
							break;
						if (kind > 33)
							kind = 33;
						jjCheckNAddTwoStates(51, 52);
						break;
					case 52:
						if (curChar == 46)
							jjCheckNAdd(53);
						break;
					case 53:
						if ((0x3ff000000000000L & l) == 0L)
							break;
						if (kind > 33)
							kind = 33;
						jjCheckNAdd(53);
						break;
					case 54:
						if ((0x3ff000000000000L & l) == 0L)
							break;
						if (kind > 33)
							kind = 33;
						jjCheckNAddStates(27, 29);
						break;
					case 55:
						if (curChar == 46)
							jjCheckNAdd(56);
						break;
					case 56:
						if ((0x3ff000000000000L & l) == 0L)
							break;
						if (kind > 33)
							kind = 33;
						jjCheckNAddTwoStates(56, 45);
						break;
					case 57:
						if (curChar == 46)
							jjCheckNAddTwoStates(58, 59);
						break;
					case 58:
						if ((0x3ff000000000000L & l) == 0L)
							break;
						if (kind > 33)
							kind = 33;
						jjCheckNAdd(58);
						break;
					case 59:
						if ((0x3ff000000000000L & l) == 0L)
							break;
						if (kind > 33)
							kind = 33;
						jjCheckNAddTwoStates(59, 45);
						break;
					case 60:
						if (curChar == 39)
							jjCheckNAddTwoStates(61, 62);
						break;
					case 61:
						if ((0xffffff7fffffffffL & l) != 0L)
							jjCheckNAddTwoStates(61, 62);
						break;
					case 62:
						if (curChar != 39)
							break;
						if (kind > 34)
							kind = 34;
						jjstateSet[jjnewStateCnt++] = 63;
						break;
					case 63:
						if (curChar == 39)
							jjCheckNAddTwoStates(64, 62);
						break;
					case 64:
						if ((0xffffff7fffffffffL & l) != 0L)
							jjCheckNAddTwoStates(64, 62);
						break;
					case 65:
						if (curChar == 34)
							jjCheckNAddTwoStates(66, 67);
						break;
					case 66:
						if ((0xfffffffbffffdbffL & l) != 0L)
							jjCheckNAddTwoStates(66, 67);
						break;
					case 67:
						if (curChar == 34 && kind > 35)
							kind = 35;
						break;
					case 68:
						if (curChar == 46)
							jjCheckNAddTwoStates(69, 79);
						break;
					case 69:
						if ((0x3ff000000000000L & l) == 0L)
							break;
						if (kind > 24)
							kind = 24;
						jjCheckNAddTwoStates(69, 70);
						break;
					case 71:
						if ((0x280000000000L & l) != 0L)
							jjAddStates(30, 31);
						break;
					case 72:
						if (curChar == 46)
							jjCheckNAdd(73);
						break;
					case 73:
						if ((0x3ff000000000000L & l) == 0L)
							break;
						if (kind > 24)
							kind = 24;
						jjCheckNAdd(73);
						break;
					case 74:
						if ((0x3ff000000000000L & l) == 0L)
							break;
						if (kind > 24)
							kind = 24;
						jjCheckNAddStates(32, 34);
						break;
					case 75:
						if ((0x3ff000000000000L & l) == 0L)
							break;
						if (kind > 24)
							kind = 24;
						jjCheckNAdd(75);
						break;
					case 76:
						if ((0x3ff000000000000L & l) == 0L)
							break;
						if (kind > 24)
							kind = 24;
						jjCheckNAddTwoStates(76, 77);
						break;
					case 77:
						if (curChar == 46)
							jjCheckNAdd(78);
						break;
					case 78:
						if ((0x3ff000000000000L & l) == 0L)
							break;
						if (kind > 24)
							kind = 24;
						jjCheckNAdd(78);
						break;
					case 79:
						if ((0x3ff000000000000L & l) == 0L)
							break;
						if (kind > 24)
							kind = 24;
						jjCheckNAdd(79);
						break;
					case 80:
						if ((0x3ff000000000000L & l) == 0L)
							break;
						if (kind > 24)
							kind = 24;
						jjCheckNAddStates(0, 6);
						break;
					case 81:
						if ((0x3ff000000000000L & l) == 0L)
							break;
						if (kind > 24)
							kind = 24;
						jjCheckNAddTwoStates(81, 70);
						break;
					case 82:
						if ((0x3ff000000000000L & l) == 0L)
							break;
						if (kind > 24)
							kind = 24;
						jjCheckNAddStates(35, 37);
						break;
					case 83:
						if (curChar == 46)
							jjCheckNAdd(84);
						break;
					case 84:
						if ((0x3ff000000000000L & l) == 0L)
							break;
						if (kind > 24)
							kind = 24;
						jjCheckNAddTwoStates(84, 70);
						break;
					case 85:
						if ((0x3ff000000000000L & l) == 0L)
							break;
						if (kind > 24)
							kind = 24;
						jjCheckNAddTwoStates(85, 86);
						break;
					case 86:
						if (curChar == 46)
							jjCheckNAdd(87);
						break;
					case 87:
						if ((0x3ff000000000000L & l) == 0L)
							break;
						if (kind > 24)
							kind = 24;
						jjCheckNAdd(87);
						break;
					case 88:
						if ((0x3ff000000000000L & l) == 0L)
							break;
						if (kind > 24)
							kind = 24;
						jjCheckNAdd(88);
						break;
					default:
						break;
					}
				} while (i != startsAt);
			} else if (curChar < 128) {
				long l = 1L << (curChar & 077);
				do {
					switch (jjstateSet[--i]) {
					case 14:
						if ((0x7fffffe87fffffeL & l) != 0L) {
							if (kind > 30)
								kind = 30;
							jjCheckNAdd(32);
						}
						if ((0x7fffffe07fffffeL & l) != 0L) {
							if (kind > 30)
								kind = 30;
							jjCheckNAddTwoStates(31, 32);
						}
						if ((0x2000000020L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 13;
						break;
					case 19:
						if ((0x7fffffe87fffffeL & l) != 0L) {
							if (kind > 30)
								kind = 30;
							jjCheckNAdd(32);
						}
						if ((0x7fffffe07fffffeL & l) != 0L) {
							if (kind > 30)
								kind = 30;
							jjCheckNAddTwoStates(31, 32);
						}
						if ((0x2000000020L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 18;
						break;
					case 17:
						if ((0x7fffffe87fffffeL & l) != 0L) {
							if (kind > 30)
								kind = 30;
							jjCheckNAdd(32);
						}
						if ((0x7fffffe07fffffeL & l) != 0L) {
							if (kind > 30)
								kind = 30;
							jjCheckNAddTwoStates(31, 32);
						}
						if ((0x2000000020L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 16;
						break;
					case 15:
						if ((0x7fffffe87fffffeL & l) != 0L) {
							if (kind > 30)
								kind = 30;
							jjCheckNAdd(32);
						}
						if ((0x7fffffe07fffffeL & l) != 0L) {
							if (kind > 30)
								kind = 30;
							jjCheckNAddTwoStates(31, 32);
						}
						if ((0x10000000100000L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 14;
						break;
					case 18:
						if ((0x7fffffe87fffffeL & l) != 0L) {
							if (kind > 30)
								kind = 30;
							jjCheckNAdd(32);
						}
						if ((0x7fffffe07fffffeL & l) != 0L) {
							if (kind > 30)
								kind = 30;
							jjCheckNAddTwoStates(31, 32);
						}
						if ((0x100000001000L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 17;
						break;
					case 16:
						if ((0x7fffffe87fffffeL & l) != 0L) {
							if (kind > 30)
								kind = 30;
							jjCheckNAdd(32);
						}
						if ((0x7fffffe07fffffeL & l) != 0L) {
							if (kind > 30)
								kind = 30;
							jjCheckNAddTwoStates(31, 32);
						}
						if ((0x800000008L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 15;
						break;
					case 140:
						if ((0x7fffffe87fffffeL & l) != 0L) {
							if (kind > 30)
								kind = 30;
							jjCheckNAdd(32);
						}
						if ((0x7fffffe07fffffeL & l) != 0L) {
							if (kind > 30)
								kind = 30;
							jjCheckNAddTwoStates(31, 32);
						}
						break;
					case 4:
						if ((0x7fffffe87fffffeL & l) != 0L) {
							if (kind > 30)
								kind = 30;
							jjCheckNAdd(32);
						}
						if ((0x7fffffe07fffffeL & l) != 0L) {
							if (kind > 30)
								kind = 30;
							jjCheckNAddTwoStates(31, 32);
						}
						if ((0x1000000010L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 3;
						break;
					case 5:
						if ((0x7fffffe07fffffeL & l) != 0L) {
							if (kind > 30)
								kind = 30;
							jjCheckNAddTwoStates(31, 32);
						}
						if ((0x1000000010000L & l) != 0L)
							jjAddStates(38, 41);
						else if ((0x800000008L & l) != 0L)
							jjAddStates(42, 44);
						else if ((0x200000002000L & l) != 0L)
							jjAddStates(45, 46);
						else if ((0x8000000080000L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 19;
						else if ((0x1000000010L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 11;
						else if ((0x2000000020L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 4;
						break;
					case 0:
						if ((0x4000000040000L & l) != 0L && kind > 14)
							kind = 14;
						break;
					case 1:
					case 111:
						if ((0x800000008000L & l) != 0L)
							jjCheckNAdd(0);
						break;
					case 2:
						if ((0x10000000100000L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 1;
						break;
					case 3:
						if ((0x20000000200L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 2;
						break;
					case 6:
						if ((0x10000000100000L & l) != 0L && kind > 21)
							kind = 21;
						break;
					case 7:
						if ((0x100000001000L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 6;
						break;
					case 8:
						if ((0x20000000200000L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 7;
						break;
					case 9:
						if ((0x200000002L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 8;
						break;
					case 10:
						if ((0x4000000040L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 9;
						break;
					case 11:
						if ((0x2000000020L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 10;
						break;
					case 12:
						if ((0x1000000010L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 11;
						break;
					case 13:
						if ((0x1000000010L & l) != 0L && kind > 21)
							kind = 21;
						break;
					case 20:
						if ((0x8000000080000L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 19;
						break;
					case 22:
						if (kind > 28)
							kind = 28;
						jjstateSet[jjnewStateCnt++] = 22;
						break;
					case 25:
						jjCheckNAddTwoStates(25, 26);
						break;
					case 27:
					case 28:
						jjCheckNAddTwoStates(28, 26);
						break;
					case 31:
						if ((0x7fffffe07fffffeL & l) == 0L)
							break;
						if (kind > 30)
							kind = 30;
						jjCheckNAddTwoStates(31, 32);
						break;
					case 32:
						if ((0x7fffffe87fffffeL & l) == 0L)
							break;
						if (kind > 30)
							kind = 30;
						jjCheckNAdd(32);
						break;
					case 34:
						if ((0x7fffffe07fffffeL & l) == 0L)
							break;
						if (kind > 33)
							kind = 33;
						jjCheckNAddStates(47, 49);
						break;
					case 35:
						if ((0x7fffffe87fffffeL & l) == 0L)
							break;
						if (kind > 33)
							kind = 33;
						jjCheckNAddTwoStates(35, 36);
						break;
					case 37:
						if ((0x7fffffe07fffffeL & l) == 0L)
							break;
						if (kind > 33)
							kind = 33;
						jjCheckNAddTwoStates(37, 38);
						break;
					case 38:
						if ((0x7fffffe87fffffeL & l) == 0L)
							break;
						if (kind > 33)
							kind = 33;
						jjCheckNAdd(38);
						break;
					case 45:
						if ((0x2000000020L & l) != 0L)
							jjAddStates(50, 52);
						break;
					case 61:
						jjCheckNAddTwoStates(61, 62);
						break;
					case 64:
						jjCheckNAddTwoStates(64, 62);
						break;
					case 66:
						jjAddStates(53, 54);
						break;
					case 70:
						if ((0x2000000020L & l) != 0L)
							jjAddStates(55, 57);
						break;
					case 89:
						if ((0x200000002000L & l) != 0L)
							jjAddStates(45, 46);
						break;
					case 90:
						if ((0x1000000010L & l) != 0L && kind > 9)
							kind = 9;
						break;
					case 91:
						if ((0x800000008000L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 90;
						break;
					case 92:
						if ((0x10000000100L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 91;
						break;
					case 93:
						if ((0x10000000100000L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 92;
						break;
					case 94:
						if ((0x2000000020L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 93;
						break;
					case 95:
						if ((0x8000000080000L & l) != 0L && kind > 14)
							kind = 14;
						break;
					case 96:
						if ((0x1000000010L & l) != 0L)
							jjCheckNAdd(95);
						break;
					case 97:
						if ((0x800000008000L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 96;
						break;
					case 98:
						if ((0x10000000100L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 97;
						break;
					case 99:
						if ((0x10000000100000L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 98;
						break;
					case 100:
						if ((0x2000000020L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 99;
						break;
					case 101:
						if ((0x800000008L & l) != 0L)
							jjAddStates(42, 44);
						break;
					case 102:
						if ((0x8000000080000L & l) != 0L && kind > 9)
							kind = 9;
						break;
					case 103:
						if ((0x8000000080000L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 102;
						break;
					case 104:
						if ((0x200000002L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 103;
						break;
					case 105:
						if ((0x100000001000L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 104;
						break;
					case 106:
					case 134:
						if ((0x2000000020L & l) != 0L)
							jjCheckNAdd(95);
						break;
					case 107:
						if ((0x8000000080000L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 106;
						break;
					case 108:
						if ((0x8000000080000L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 107;
						break;
					case 109:
						if ((0x200000002L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 108;
						break;
					case 110:
						if ((0x100000001000L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 109;
						break;
					case 112:
						if ((0x8000000080000L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 111;
						break;
					case 113:
						if ((0x4000000040000L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 112;
						break;
					case 114:
						if ((0x20000000200000L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 113;
						break;
					case 115:
						if ((0x1000000010000L & l) != 0L)
							jjAddStates(38, 41);
						break;
					case 116:
						if ((0x10000000100000L & l) != 0L && kind > 9)
							kind = 9;
						break;
					case 117:
						if ((0x800000008L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 116;
						break;
					case 118:
						if ((0x2000000020L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 117;
						break;
					case 119:
						if ((0x40000000400L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 118;
						break;
					case 120:
						if ((0x800000008000L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 119;
						break;
					case 121:
						if ((0x4000000040000L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 120;
						break;
					case 122:
						if ((0x2000000020L & l) != 0L && kind > 9)
							kind = 9;
						break;
					case 123:
						if ((0x8000000080L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 122;
						break;
					case 124:
						if ((0x200000002L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 123;
						break;
					case 125:
						if ((0x80000000800L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 124;
						break;
					case 126:
						if ((0x800000008L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 125;
						break;
					case 127:
						if ((0x200000002L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 126;
						break;
					case 128:
						if ((0x10000000100000L & l) != 0L)
							jjCheckNAdd(95);
						break;
					case 129:
						if ((0x800000008L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 128;
						break;
					case 130:
						if ((0x2000000020L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 129;
						break;
					case 131:
						if ((0x40000000400L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 130;
						break;
					case 132:
						if ((0x800000008000L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 131;
						break;
					case 133:
						if ((0x4000000040000L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 132;
						break;
					case 135:
						if ((0x8000000080L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 134;
						break;
					case 136:
						if ((0x200000002L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 135;
						break;
					case 137:
						if ((0x80000000800L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 136;
						break;
					case 138:
						if ((0x800000008L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 137;
						break;
					case 139:
						if ((0x200000002L & l) != 0L)
							jjstateSet[jjnewStateCnt++] = 138;
						break;
					default:
						break;
					}
				} while (i != startsAt);
			} else {
				int i2 = (curChar & 0xff) >> 6;
				long l2 = 1L << (curChar & 077);
				do {
					switch (jjstateSet[--i]) {
					case 22:
						if ((jjbitVec0[i2] & l2) == 0L)
							break;
						if (kind > 28)
							kind = 28;
						jjstateSet[jjnewStateCnt++] = 22;
						break;
					case 25:
						if ((jjbitVec0[i2] & l2) != 0L)
							jjCheckNAddTwoStates(25, 26);
						break;
					case 27:
					case 28:
						if ((jjbitVec0[i2] & l2) != 0L)
							jjCheckNAddTwoStates(28, 26);
						break;
					case 61:
						if ((jjbitVec0[i2] & l2) != 0L)
							jjCheckNAddTwoStates(61, 62);
						break;
					case 64:
						if ((jjbitVec0[i2] & l2) != 0L)
							jjCheckNAddTwoStates(64, 62);
						break;
					case 66:
						if ((jjbitVec0[i2] & l2) != 0L)
							jjAddStates(53, 54);
						break;
					default:
						break;
					}
				} while (i != startsAt);
			}
			if (kind != 0x7fffffff) {
				jjmatchedKind = kind;
				jjmatchedPos = curPos;
				kind = 0x7fffffff;
			}
			++curPos;
			if ((i = jjnewStateCnt) == (startsAt = 140 - (jjnewStateCnt = startsAt)))
				return curPos;
			try {
				curChar = input_stream.readChar();
			} catch (java.io.IOException e) {
				return curPos;
			}
		}
	}

	private int jjMoveStringLiteralDfa0_0() {
		switch (curChar) {
		case 69:
		case 101:
			return jjMoveStringLiteralDfa1_0(0x80L);
		case 73:
		case 105:
			return jjMoveStringLiteralDfa1_0(0x20L);
		case 79:
		case 111:
			return jjMoveStringLiteralDfa1_0(0x40L);
		case 83:
		case 115:
			return jjMoveStringLiteralDfa1_0(0x100L);
		default:
			return jjMoveNfa_0(5, 0);
		}
	}

	private int jjMoveStringLiteralDfa1_0(long active0) {
		try {
			curChar = input_stream.readChar();
		} catch (java.io.IOException e) {
			jjStopStringLiteralDfa_0(0, active0);
			return 1;
		}
		switch (curChar) {
		case 69:
		case 101:
			return jjMoveStringLiteralDfa2_0(active0, 0x100L);
		case 78:
		case 110:
			return jjMoveStringLiteralDfa2_0(active0, 0x20L);
		case 80:
		case 112:
			return jjMoveStringLiteralDfa2_0(active0, 0x40L);
		case 88:
		case 120:
			return jjMoveStringLiteralDfa2_0(active0, 0x80L);
		default:
			break;
		}
		return jjStartNfa_0(0, active0);
	}

	private int jjMoveStringLiteralDfa2_0(long old0, long active0) {
		if (((active0 &= old0)) == 0L)
			return jjStartNfa_0(0, old0);
		try {
			curChar = input_stream.readChar();
		} catch (java.io.IOException e) {
			jjStopStringLiteralDfa_0(1, active0);
			return 2;
		}
		switch (curChar) {
		case 69:
		case 101:
			return jjMoveStringLiteralDfa3_0(active0, 0x40L);
		case 70:
		case 102:
			return jjMoveStringLiteralDfa3_0(active0, 0x20L);
		case 76:
		case 108:
			return jjMoveStringLiteralDfa3_0(active0, 0x100L);
		case 80:
		case 112:
			return jjMoveStringLiteralDfa3_0(active0, 0x80L);
		default:
			break;
		}
		return jjStartNfa_0(1, active0);
	}

	private int jjMoveStringLiteralDfa3_0(long old0, long active0) {
		if (((active0 &= old0)) == 0L)
			return jjStartNfa_0(1, old0);
		try {
			curChar = input_stream.readChar();
		} catch (java.io.IOException e) {
			jjStopStringLiteralDfa_0(2, active0);
			return 3;
		}
		switch (curChar) {
		case 65:
		case 97:
			return jjMoveStringLiteralDfa4_0(active0, 0x80L);
		case 69:
		case 101:
			return jjMoveStringLiteralDfa4_0(active0, 0x100L);
		case 78:
		case 110:
			if ((active0 & 0x40L) != 0L)
				return jjStartNfaWithStates_0(3, 6, 140);
			break;
		case 79:
		case 111:
			if ((active0 & 0x20L) != 0L)
				return jjStartNfaWithStates_0(3, 5, 140);
			break;
		default:
			break;
		}
		return jjStartNfa_0(2, active0);
	}

	private int jjMoveStringLiteralDfa4_0(long old0, long active0) {
		if (((active0 &= old0)) == 0L)
			return jjStartNfa_0(2, old0);
		try {
			curChar = input_stream.readChar();
		} catch (java.io.IOException e) {
			jjStopStringLiteralDfa_0(3, active0);
			return 4;
		}
		switch (curChar) {
		case 67:
		case 99:
			return jjMoveStringLiteralDfa5_0(active0, 0x100L);
		case 78:
		case 110:
			return jjMoveStringLiteralDfa5_0(active0, 0x80L);
		default:
			break;
		}
		return jjStartNfa_0(3, active0);
	}

	private int jjMoveStringLiteralDfa5_0(long old0, long active0) {
		if (((active0 &= old0)) == 0L)
			return jjStartNfa_0(3, old0);
		try {
			curChar = input_stream.readChar();
		} catch (java.io.IOException e) {
			jjStopStringLiteralDfa_0(4, active0);
			return 5;
		}
		switch (curChar) {
		case 68:
		case 100:
			if ((active0 & 0x80L) != 0L)
				return jjStartNfaWithStates_0(5, 7, 140);
			break;
		case 84:
		case 116:
			if ((active0 & 0x100L) != 0L)
				return jjStartNfaWithStates_0(5, 8, 14);
			break;
		default:
			break;
		}
		return jjStartNfa_0(4, active0);
	}

	private final int jjStartNfa_0(int pos, long active0) {
		return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
	}

	private int jjStartNfaWithStates_0(int pos, int kind, int state) {
		jjmatchedKind = kind;
		jjmatchedPos = pos;
		try {
			curChar = input_stream.readChar();
		} catch (java.io.IOException e) {
			return pos + 1;
		}
		return jjMoveNfa_0(state, pos + 1);
	}

	private int jjStopAtPos(int pos, int kind) {
		jjmatchedKind = kind;
		jjmatchedPos = pos;
		return pos + 1;
	}

	private final int jjStopStringLiteralDfa_0(int pos, long active0) {
		switch (pos) {
		case 0:
			if ((active0 & 0x60L) != 0L) {
				jjmatchedKind = 30;
				return 140;
			}
			if ((active0 & 0x100L) != 0L) {
				jjmatchedKind = 30;
				return 19;
			}
			if ((active0 & 0x80L) != 0L) {
				jjmatchedKind = 30;
				return 4;
			}
			return -1;
		case 1:
			if ((active0 & 0xe0L) != 0L) {
				jjmatchedKind = 30;
				jjmatchedPos = 1;
				return 140;
			}
			if ((active0 & 0x100L) != 0L) {
				jjmatchedKind = 30;
				jjmatchedPos = 1;
				return 18;
			}
			return -1;
		case 2:
			if ((active0 & 0x100L) != 0L) {
				jjmatchedKind = 30;
				jjmatchedPos = 2;
				return 17;
			}
			if ((active0 & 0xe0L) != 0L) {
				jjmatchedKind = 30;
				jjmatchedPos = 2;
				return 140;
			}
			return -1;
		case 3:
			if ((active0 & 0x80L) != 0L) {
				jjmatchedKind = 30;
				jjmatchedPos = 3;
				return 140;
			}
			if ((active0 & 0x100L) != 0L) {
				jjmatchedKind = 30;
				jjmatchedPos = 3;
				return 16;
			}
			if ((active0 & 0x60L) != 0L)
				return 140;
			return -1;
		case 4:
			if ((active0 & 0x80L) != 0L) {
				jjmatchedKind = 30;
				jjmatchedPos = 4;
				return 140;
			}
			if ((active0 & 0x100L) != 0L) {
				jjmatchedKind = 30;
				jjmatchedPos = 4;
				return 15;
			}
			return -1;
		default:
			return -1;
		}
	}

	/** Reinitialise parser. */
	public void ReInit(SimpleCharStream stream) {
		jjmatchedPos = jjnewStateCnt = 0;
		curLexState = defaultLexState;
		input_stream = stream;
		ReInitRounds();
	}

	/** Reinitialise parser. */
	public void ReInit(SimpleCharStream stream, int lexState) {
		ReInit(stream);
		SwitchTo(lexState);
	}

	private void ReInitRounds() {
		int i;
		jjround = 0x80000001;
		for (i = 140; i-- > 0;)
			jjrounds[i] = 0x80000000;
	}

	/** Set debug output. */
	public void setDebugStream(java.io.PrintStream ds) {
		debugStream = ds;
	}

	/** Switch to specified lex state. */
	public void SwitchTo(int lexState) {
		if (lexState >= 1 || lexState < 0)
			throw new TokenMgrError("Error: Ignoring invalid lexical state : "
					+ lexState + ". State unchanged.",
					TokenMgrError.INVALID_LEXICAL_STATE);
		else
			curLexState = lexState;
	}

}


// import com.sun.org.apache.xml.internal.utils.IntVector;
import java.util.*;

class WModel {
	static String[][] board = new String[19][11];
	static int my_player_no = -1;
	static int opp_player_no = -1;
	static int[][][] idxToHex_maping = {
			{ { -1, -1 }, { -1, -1 }, { -1, -1 }, { -1, -1 }, { 5, 29 }, { -1, -1 }, { 5, 1 }, { -1, -1 }, { -1, -1 },{ -1, -1 }, { -1, -1 } },
			{ { -1, -1 }, { -1, -1 }, { -1, -1 }, { 5, 28 }, { -1, -1 }, { 4, 0 }, { -1, -1 }, { 5, 2 }, { -1, -1 },{ -1, -1 }, { -1, -1 } },
			{ { -1, -1 }, { -1, -1 }, { 5, 27 }, { -1, -1 }, { 4, 23 }, { -1, -1 }, { 4, 1 }, { -1, -1 }, { 5, 3 },	{ -1, -1 }, { -1, -1 } },
			{ { -1, -1 }, { 5, 26 }, { -1, -1 }, { 4, 22 }, { -1, -1 }, { 3, 0 }, { -1, -1 }, { 4, 2 }, { -1, -1 },	{ 5, 4 }, { -1, -1 } },
			{ { -1, -1 }, { -1, -1 }, { 4, 21 }, { -1, -1 }, { 3, 17 }, { -1, -1 }, { 3, 1 }, { -1, -1 }, { 4, 3 },{ -1, -1 }, { -1, -1 } },
			{ { -1, -1 }, { 4, 20 }, { -1, -1 }, { 3, 16 }, { -1, -1 }, { 2, 0 }, { -1, -1 }, { 3, 2 }, { -1, -1 },{ 4, 4 }, { -1, -1 } },
			{ { 5, 24 }, { -1, -1 }, { 3, 15 }, { -1, -1 }, { 2, 11 }, { -1, -1 }, { 2, 1 }, { -1, -1 }, { 3, 3 },{ -1, -1 }, { 5, 6 } },
			{ { -1, -1 }, { 4, 19 }, { -1, -1 }, { 2, 10 }, { -1, -1 }, { 1, 0 }, { -1, -1 }, { 2, 2 }, { -1, -1 },{ 4, 5 }, { -1, -1 } },
			{ { 5, 23 }, { -1, -1 }, { 3, 14 }, { -1, -1 }, { 1, 5 }, { -1, -1 }, { 1, 1 }, { -1, -1 }, { 3, 4 },{ -1, -1 }, { 5, 7 } },
			{ { -1, -1 }, { 4, 18 }, { -1, -1 }, { 2, 9 }, { -1, -1 }, { 0, 0 }, { -1, -1 }, { 2, 3 }, { -1, -1 },{ 4, 6 }, { -1, -1 } },
			{ { 5, 22 }, { -1, -1 }, { 3, 13 }, { -1, -1 }, { 1, 4 }, { -1, -1 }, { 1, 2 }, { -1, -1 }, { 3, 5 },{-1, -1 }, { 5, 8 } },
			{ { -1, -1 }, { 4, 17 }, { -1, -1 }, { 2, 8 }, { -1, -1 }, { 1, 3 }, { -1, -1 }, { 2, 4 }, { -1, -1 },{ 4, 7 }, { -1, -1 } },
			{ { 5, 21 }, { -1, -1 }, { 3, 12 }, { -1, -1 }, { 2, 7 }, { -1, -1 }, { 2, 5 }, { -1, -1 }, { 3, 6 },{ -1, -1 }, { 5, 9 } },
			{ { -1, -1 }, { 4, 16 }, { -1, -1 }, { 3, 11 }, { -1, -1 }, { 2, 6 }, { -1, -1 }, { 3, 7 }, { -1, -1 },
					{ 4, 8 }, { -1, -1 } },
			{ { -1, -1 }, { -1, -1 }, { 4, 15 }, { -1, -1 }, { 3, 10 }, { -1, -1 }, { 3, 8 }, { -1, -1 }, { 4, 9 },
					{ -1, -1 }, { -1, -1 } },
			{ { -1, -1 }, { 5, 19 }, { -1, -1 }, { 4, 14 }, { -1, -1 }, { 3, 9 }, { -1, -1 }, { 4, 10 }, { -1, -1 },
					{ 5, 11 }, { -1, -1 } },
			{ { -1, -1 }, { -1, -1 }, { 5, 18 }, { -1, -1 }, { 4, 13 }, { -1, -1 }, { 4, 11 }, { -1, -1 }, { 5, 12 },
					{ -1, -1 }, { -1, -1 } },
			{ { -1, -1 }, { -1, -1 }, { -1, -1 }, { 5, 17 }, { -1, -1 }, { 4, 12 }, { -1, -1 }, { 5, 13 }, { -1, -1 },
					{ -1, -1 }, { -1, -1 } },
			{ { -1, -1 }, { -1, -1 }, { -1, -1 }, { -1, -1 }, { 5, 16 }, { -1, -1 }, { 5, 14 }, { -1, -1 }, { -1, -1 },
					{ -1, -1 }, { -1, -1 } },

	};

	static public int[] getindex(int hex_no, int pos) {
		int[] index = new int[2];
		if (hex_no == 0 && pos == 0) {
			index = new int[] { 9, 5 };
			return index;
		}
		if (pos < 0 || pos > 6 * hex_no - 1) {
			return new int[] { -1, -1 };
		} else if (hex_no == 5 && (pos % hex_no == 0)) {
			// corners
			return new int[] { -1, -1 };
		}
		double jj = pos;
		double ii = hex_no;
		int v = (int) Math.floor(jj / ii);
		int j = pos;
		int i = hex_no;
		if (v == 0)
			index = new int[] { 9 - 2 * i + j, 5 + j };
		else if (v == 1)
			index = new int[] { 9 - i + 2 * (j % i), 5 + i };
		else if (v == 2)
			index = (new int[] { 9 + i + (j % i), 5 + i - (j % i) });
		else if (v == 3)
			index = (new int[] { 9 + 2 * i - j % i, 5 - j % i });
		else if (v == 4)
			index = (new int[] { 9 + i - 2 * (j % i), 5 - i });
		else if (v == 5)
			index = (new int[] { 9 - i - j % i, 5 - i + j % i });
		return (index);
	}

	public static void showboard(String[][] gboard) {
		for (int i = 0; i < gboard.length; i++) {
			for (int j = 0; j < gboard[0].length; j++) {
				if (gboard[i][j].equals("X")) {
					System.err.print("  ");
				}
				else{
					System.err.print(gboard[i][j] + " ");
				}
			}
			System.err.println();

		}
	}

	public static void initial_config() {
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 11; j++) {
				board[i][j] = "X";
			}
		}
		for (int h = 0; h < 6; h++) {
			for (int pos = 0; pos < 6 * h; pos++) {
				int i = -1;
				int j = -1;
				if (getindex(h, pos) != new int[] { -1, -1 }) {
					int[] temp;
					temp = getindex(h, pos);
					i = temp[0];
					j = temp[1];
				}
				if (h != 5 && pos % 5 != 0 || (i != -1 && j != -1))
					board[i][j] = "O";
			}
		}
		int[] index = getindex(0, 0);
		board[index[0]][index[1]] = "O";
		// showboard();

	}

	public static void flipDiscs(String[][] givenboard, int hex_no1, int pos1, int hex_no2, int pos2) {
		int[] start = getindex(hex_no1, pos1);
		int[] end = getindex(hex_no2, pos2);
		int si = -1;
		int sj = -1;
		int ei = -1;
		int ej = -1;
		if (start[0] + start[1] == end[0] + end[1]) {
			if (start[0] > end[0]) {
				si = start[0];
				sj = start[1];
				ei = end[0];
				ej = end[1];
			} else {
				ei = start[0];
				ej = start[1];
				si = end[0];
				sj = end[1];
			}
			si--;
			sj++;
			ei++;
			ej--;
			for (; si >= ei; si--, sj++) {
				if (givenboard[si][sj].equals("r"))
					givenboard[si][sj] = "b";
				else if (givenboard[si][sj].equals("b"))
					givenboard[si][sj] = "r";
			}
		} else if (start[0] - start[1] == end[0] - end[1]) {
			if (start[0] < end[0]) {
				si = start[0];
				sj = start[1];
				ei = end[0];
				ej = end[1];
			} else {
				ei = start[0];
				ej = start[1];
				si = end[0];
				sj = end[1];
			}
			si++;
			sj++;
			ei--;
			ej--;
			for (; si <= ei; si++, sj++) {
				if (givenboard[si][sj].equals("r"))
					givenboard[si][sj] = "b";
				else if (givenboard[si][sj].equals("b"))
					givenboard[si][sj] = "r";
			}
		} else if (start[1] == end[1]) {
			if (start[0] < end[0]) {
				si = start[0];
				sj = start[1];
				ei = end[0];
				ej = end[1];
			} else {
				ei = start[0];
				ej = start[1];
				si = end[0];
				sj = end[1];
			}
			si += 2;
			ei -= 2;
			for (; si <= ei; si += 2) {
				if (givenboard[si][sj].equals("r"))
					givenboard[si][sj] = "b";
				else if (givenboard[si][sj].equals("b"))
					givenboard[si][sj] = "r";
			}

		}
	}

	public static void placeRing(String[][] gboard, int player_no, int hex_no, int pos) {
		int[] index = getindex(hex_no, pos);
		if (player_no == 1) {
			gboard[index[0]][index[1]] = "R";

		} else {
			gboard[index[0]][index[1]] = "B";
		}
	}

	public static void moveRing(String[][] givenboard, int player_no, int shex, int spos, int fhex, int fpos) {
		int si, sj, fi, fj;
		int[] index = getindex(shex, spos);
		si = index[0];
		sj = index[1];
		index = getindex(fhex, fpos);
		fi = index[0];
		fj = index[1];
		if (player_no == 1) {
			givenboard[si][sj] = "r";
			givenboard[fi][fj] = "R";
			flipDiscs(givenboard, shex, spos, fhex, fpos);
		} else if (player_no == 2) {
			givenboard[si][sj] = "b";
			givenboard[fi][fj] = "B";
			flipDiscs(givenboard, shex, spos, fhex, fpos);

		}
	}

	public static boolean removeRing(String[][] gboard, int player_no, int hex_no, int pos) {
		int[] index = getindex(hex_no, pos);
		if (gboard[index[0]][index[1]].equals("R") && player_no == 1) {
			gboard[index[0]][index[1]] = "O";
			return (true);
		} else if (gboard[index[0]][index[1]].equals("B") && player_no == 2) {
			gboard[index[0]][index[1]] = "O";
			return (true);
		}
		return (false);
	}

	public static void removeRow(String[][] gboard, int shex, int spos, int fhex, int fpos) {
		int si, sj, fi, fj;
		int[] index = getindex(shex, spos);
		si = index[0];
		sj = index[1];
		index = getindex(fhex, fpos);
		fi = index[0];
		fj = index[1];
		int i = -1;
		int j = -1;
		if (si + sj == fi + fj) {
			if (si > fi) {
				i = si;
				j = sj;
			} else {
				i = fi;
				j = fj;
			}
			for (int k = 0; k < 5; k++) {
				gboard[i][j] = "O";
				i -= 1;
				j += 1;
			}
		} else if (si - sj == fi - fj) {
			if (si < fi) {
				i = si;
				j = sj;
			} else {
				i = fi;
				j = fj;
			}
			for (int k = 0; k < 5; k++) {
				gboard[i][j] = "O";
				i += 1;
				j += 1;
			}
		} else if (sj == fj) {
			j = sj;
			if (si < fi)
				i = si;
			else
				i = fi;
			for (int k = 0; k < 5; k++) {
				gboard[i][j] = "O";
				i += 2;
			}
		}
	}

	// return hex_no,pos for the best ring to remove
	static public int[] removingRingGreedly(String[][] curr_board, int player_no) {
		Vector<int[]> ring_arr = new Vector<int[]>();
		int[] hex = new int[2];
		int k = 0;
		if (player_no == 1) {
			for (int i = 0; i < 19; i++) {
				for (int j = 0; j < 11; j++) {
					if (curr_board[i][j].equals("R")) {
						int[] aa = { i, j };
						ring_arr.add(k, aa);
						k++;
					}
				}
			}
			int util = 0;
			String[][] final_state;
			for (int i = 0; i < ring_arr.size(); i++) {
				String[][] ringremovedboard = getCopyOfboard(curr_board);
				ringremovedboard[ring_arr.get(i)[0]][ring_arr.get(i)[1]] = "O";
				int u = Utility(ringremovedboard,player_no);
				if (i == 0) {
					util = u;
					final_state = ringremovedboard;
					hex = idxToHex(ring_arr.get(i)[0], ring_arr.get(i)[1]);
				}
				if (util > u) {
					util = u;
					final_state = ringremovedboard;
					hex = idxToHex(ring_arr.get(i)[0], ring_arr.get(i)[1]);
				}
			}
			// return final_state;
		} else if (player_no == 2) {
			for (int i = 0; i < 19; i++) {
				for (int j = 0; j < 11; j++) {
					if (curr_board[i][j].equals("B")) {
						int[] aa = { i, j };
						ring_arr.add(k, aa);
						k++;
					}
				}
			}
			int util = 0;
			String[][] final_state;
			for (int i = 0; i < ring_arr.size(); i++) {
				String[][] ringremovedboard = getCopyOfboard(curr_board);
				ringremovedboard[ring_arr.get(i)[0]][ring_arr.get(i)[1]] = "O";
				int u = Utility(ringremovedboard,player_no);
				if (i == 0) {
					util = u;
					final_state = ringremovedboard;
					hex = idxToHex(ring_arr.get(i)[0], ring_arr.get(i)[1]);

				}
				if (util > u) {
					util = u;
					final_state = ringremovedboard;
					hex = idxToHex(ring_arr.get(i)[0], ring_arr.get(i)[1]);

				}
			}
		}
		// return final_state;
		return (hex);
	}

	// Gives all runs intersecting and non-intersecting
	public static Vector<int[]> getruns(String[][] gboard, int player_no) {
		Vector<int[]> runs = new Vector<int[]>();
		String[][] temp_board = getCopyOfboard(gboard);
		// vertical
		runs.addAll(FiveDiscRemovalPossible(temp_board, player_no, 3, 1, 15, 1));
		runs.addAll(FiveDiscRemovalPossible(temp_board, player_no, 2, 2, 16, 2));
		runs.addAll(FiveDiscRemovalPossible(temp_board, player_no, 1, 3, 17, 3));
		runs.addAll(FiveDiscRemovalPossible(temp_board, player_no, 0, 4, 18, 4));
		runs.addAll(FiveDiscRemovalPossible(temp_board, player_no, 1, 5, 17, 5));
		runs.addAll(FiveDiscRemovalPossible(temp_board, player_no, 0, 6, 18, 6));
		runs.addAll(FiveDiscRemovalPossible(temp_board, player_no, 1, 7, 17, 7));
		runs.addAll(FiveDiscRemovalPossible(temp_board, player_no, 2, 8, 16, 8));
		runs.addAll(FiveDiscRemovalPossible(temp_board, player_no, 3, 9, 15, 9));

		// rightRun
		runs.addAll(FiveDiscRemovalPossible(temp_board, player_no, 0, 4, 6, 10));
		runs.addAll(FiveDiscRemovalPossible(temp_board, player_no, 1, 3, 8, 10));
		runs.addAll(FiveDiscRemovalPossible(temp_board, player_no, 2, 2, 10, 10));
		runs.addAll(FiveDiscRemovalPossible(temp_board, player_no, 3, 1, 12, 10));
		runs.addAll(FiveDiscRemovalPossible(temp_board, player_no, 5, 1, 13, 9));
		runs.addAll(FiveDiscRemovalPossible(temp_board, player_no, 6, 0, 15, 9));
		runs.addAll(FiveDiscRemovalPossible(temp_board, player_no, 8, 0, 16, 8));
		runs.addAll(FiveDiscRemovalPossible(temp_board, player_no, 10, 0, 17, 7));
		runs.addAll(FiveDiscRemovalPossible(temp_board, player_no, 12, 0, 18, 6));

		// left Runs
		runs.addAll(FiveDiscRemovalPossible(temp_board, player_no, 6, 0, 0, 6));
		runs.addAll(FiveDiscRemovalPossible(temp_board, player_no, 8, 0, 1, 7));
		runs.addAll(FiveDiscRemovalPossible(temp_board, player_no, 10, 0, 2, 8));
		runs.addAll(FiveDiscRemovalPossible(temp_board, player_no, 12, 0, 3, 9));
		runs.addAll(FiveDiscRemovalPossible(temp_board, player_no, 13, 1, 5, 9));
		runs.addAll(FiveDiscRemovalPossible(temp_board, player_no, 15, 1, 6, 10));
		runs.addAll(FiveDiscRemovalPossible(temp_board, player_no, 16, 2, 8, 10));
		runs.addAll(FiveDiscRemovalPossible(temp_board, player_no, 17, 3, 10, 10));
		runs.addAll(FiveDiscRemovalPossible(temp_board, player_no, 18, 4, 12, 10));

		// System.err.println("run"+runs.size());
		return (runs);
	}

	public static int[] idxToHex(int i, int j) {
		int[] idx = new int[2];
		idx[0] = idxToHex_maping[i][j][0];
		idx[1] = idxToHex_maping[i][j][1];
		return (idx);
	}

	public static String[][] getCopyOfboard(String[][] curr_board) {
		String[][] copy_board = new String[curr_board.length][curr_board[0].length];
		for (int i = 0; i < curr_board.length; i++) {
			for (int j = 0; j < curr_board[0].length; j++) {
				copy_board[i][j] = curr_board[i][j];
			}
		}
		return (copy_board);
	}

	public static Vector<int[]> getPositionOfRing(int player_no, String[][] curr_board) {
		Vector<int[]> positions = new Vector<int[]>();
		for (int i = 0; i < curr_board.length; i++) {
			for (int j = 0; j < curr_board[0].length; j++) {
				// if(curr_board[i][j]=="R" || curr_board[i][j]=="B"){
				if (player_no == 1) {
					if (curr_board[i][j].equals("R")) {
						int[] idx = idxToHex(i, j);
						positions.add(idx);
					}

				} else if (player_no == 2) {
					if (curr_board[i][j].equals("B")) {
						int[] idx = idxToHex(i, j);
						positions.add(idx);
					}
				}
				// }
			}

		}
		// return hex_no,pos of the rings of the given player
		return (positions);
	}

	public static Vector<int[]> getValidPosofTheRing(String[][] gboard, int hex_no, int pos) {
		Vector<int[]> validPos = new Vector<int[]>();
		int[] idx = getindex(hex_no, pos);
		// Direction1
		int i = idx[0];
		int j = idx[1];
		boolean overDisc = false;
		i -= 2;
		while (i >= 0) {
			if (gboard[i][j].equals("X")) {
				break;
			}
			if (gboard[i][j].equals("O") && overDisc == false) {
				validPos.add(idxToHex(i, j));
			} else if (gboard[i][j].equals("O") && overDisc == true) {
				validPos.add(idxToHex(i, j));
				break;
			}
			if (gboard[i][j].equals("r") || gboard[i][j].equals("b")) {
				overDisc = true;
			}
			if (gboard[i][j].equals("R") || gboard[i][j].equals("B")) {
				break;
			}
			i -= 2;

		}
		// Direction2
		i = idx[0];
		j = idx[1];
		overDisc = false;
		i--;
		j++;
		while (i >= 0 && j < 11) {
			if (gboard[i][j].equals("X")) {
				break;
			}
			if (gboard[i][j].equals("O") && overDisc == false) {
				validPos.add(idxToHex(i, j));
			} else if (gboard[i][j].equals("O") && overDisc == true) {
				validPos.add(idxToHex(i, j));
				break;
			}
			if (gboard[i][j].equals("r") || gboard[i][j].equals("b")) {
				overDisc = true;
			}
			if (gboard[i][j].equals("R") || gboard[i][j].equals("B")) {
				break;
			}
			i--;
			j++;
		}

		// Direction3
		i = idx[0];
		j = idx[1];
		overDisc = false;
		i++;
		j++;
		while (i < 19 && j < 11) {
			if (gboard[i][j].equals("X")) {
				break;
			}
			if (gboard[i][j].equals("O") && overDisc == false) {
				validPos.add(idxToHex(i, j));
			} else if (gboard[i][j].equals("O") && overDisc == true) {
				validPos.add(idxToHex(i, j));
				break;
			}
			if (gboard[i][j].equals("r") || gboard[i][j].equals("b")) {
				overDisc = true;
			}
			if (gboard[i][j].equals("R") || gboard[i][j].equals("B")) {
				break;
			}
			i++;
			j++;
		}

		// Direction4
		i = idx[0];
		j = idx[1];
		overDisc = false;
		i += 2;
		while (i < 19) {
			if (gboard[i][j].equals("X")) {
				break;
			}
			if (gboard[i][j].equals("O") && overDisc == false) {
				validPos.add(idxToHex(i, j));
			} else if (gboard[i][j].equals("O") && overDisc == true) {
				validPos.add(idxToHex(i, j));
				break;
			}
			if (gboard[i][j].equals("r") || gboard[i][j].equals("b")) {
				overDisc = true;
			}
			if (gboard[i][j].equals("R") || gboard[i][j].equals("B")) {
				break;
			}
			i += 2;

		}

		// Direction5
		i = idx[0];
		j = idx[1];
		overDisc = false;
		i++;
		j--;
		while (i < 19 && j >= 0) {
			if (gboard[i][j].equals("X")) {
				break;
			}
			if (gboard[i][j].equals("O") && overDisc == false) {
				validPos.add(idxToHex(i, j));
			} else if (gboard[i][j].equals("O") && overDisc == true) {
				validPos.add(idxToHex(i, j));
				break;
			}
			if (gboard[i][j].equals("r") || gboard[i][j].equals("b")) {
				overDisc = true;
			}
			if (gboard[i][j].equals("R") || gboard[i][j].equals("B")) {
				break;
			}
			i++;
			j--;
		}

		// Direction6
		i = idx[0];
		j = idx[1];
		overDisc = false;
		i--;
		j--;
		while (i >= 0 && j >= 0) {
			if (gboard[i][j].equals("X")) {
				break;
			}
			if (gboard[i][j].equals("O") && overDisc == false) {
				validPos.add(idxToHex(i, j));
			} else if (gboard[i][j].equals("O") && overDisc == true) {
				validPos.add(idxToHex(i, j));
				break;
			}
			if (gboard[i][j].equals("r") || gboard[i][j].equals("b")) {
				overDisc = true;
			}
			if (gboard[i][j].equals("R") || gboard[i][j].equals("B")) {
				break;
			}
			i--;
			j--;
		}

		return (validPos);
	}

	public static Vector<int[]> FiveDiscRemovalPossible(String[][] gboard, int player_no, int si, int sj, int fi,int fj) {
		Vector<int[]> idcies_for_removal_in_this_row = new Vector<int[]>();
		if (si + sj == fi + fj) {
			int i = -1;
			int j = -1;
			int ii = -1;
			int jj = -1;
			if (si > fi) {
				i = si;
				j = sj;
				ii = fi;
				jj = fj;
			} else {
				ii = si;
				jj = sj;
				i = fi;
				j = fj;
			}
			if (player_no == 1) {
				// red disc
				int count = 0;
				int[] initial_index = new int[2];
				int[] final_index = new int[2];

				for (int sx = i, sy = j; sx >= ii; sx--, sy++) {
					if (gboard[sx][sy].equals("r")) {
						if (count == 0) {
							initial_index[0] = sx;
							initial_index[1] = sy;
						}
						count++;
						if (count == 5) {
							int[] index = new int[4];
							final_index[0] = sx;
							final_index[1] = sy;
							index[0] = (initial_index[0]);
							index[1] = (initial_index[1]);
							index[2] = (final_index[0]);
							index[3] = (final_index[1]);

							idcies_for_removal_in_this_row.add(index);
							sx = initial_index[0] - 1;
							sy = initial_index[1] + 1;
							count = 0;
							// return(index);
						}

					} else {
						count = 0;
					}
				}
			} else if (player_no == 2) {
				// blue disc
				int count = 0;
				int[] initial_index = new int[2];
				int[] final_index = new int[2];

				for (int sx = i, sy = j; sx >= ii; sx--, sy++) {
					if (gboard[sx][sy].equals("b")) {
						if (count == 0) {
							initial_index[0] = sx;
							initial_index[1] = sy;
						}
						count++;
						if (count == 5) {
							int[] index = new int[4];
							final_index[0] = sx;
							final_index[1] = sy;
							index[0] = (initial_index[0]);
							index[1] = (initial_index[1]);
							index[2] = (final_index[0]);
							index[3] = (final_index[1]);

							idcies_for_removal_in_this_row.add(index);
							sx = initial_index[0] - 1;
							sy = initial_index[1] + 1;
							count = 0;

							// return(index);
						}

					} else {
						count = 0;
					}
				}
			}

			// possible_ring_removal.add(idcies_for_removal_in_this_row);
		} else if (si - sj == fi - fj) {
			int i = -1;
			int j = -1;
			int ii = -1;
			int jj = -1;
			if (si < fi) {
				i = si;
				j = sj;
				ii = fi;
				jj = fj;
			} else {
				ii = si;
				jj = sj;
				i = fi;
				j = fj;
			}
			// Vector<int[]> idcies_for_removal_in_this_row=new Vector<int[]>();
			if (player_no == 1) {
				// red disc
				int count = 0;
				int[] initial_index = new int[2];
				int[] final_index = new int[2];

				for (int sx = i, sy = j; sx <= ii; sx++, sy++) {
					if (gboard[sx][sy].equals("r")) {
						if (count == 0) {
							initial_index[0] = sx;
							initial_index[1] = sy;
						}
						count++;
						if (count == 5) {
							int[] index = new int[4];
							final_index[0] = sx;
							final_index[1] = sy;
							index[0] = (initial_index[0]);
							index[1] = (initial_index[1]);
							index[2] = (final_index[0]);
							index[3] = (final_index[1]);

							idcies_for_removal_in_this_row.add(index);
							sx = initial_index[0] + 1;
							sy = initial_index[1] + 1;
							count = 0;

							// return(index);
						}

					} else {
						count = 0;
					}
				}
			} else if (player_no == 2) {
				// blue disc
				int count = 0;
				int[] initial_index = new int[2];
				int[] final_index = new int[2];

				for (int sx = i, sy = j; sx <= ii; sx++, sy++) {
					if (gboard[sx][sy].equals("b")) {
						if (count == 0) {
							initial_index[0] = sx;
							initial_index[1] = sy;
						}
						count++;
						if (count == 5) {
							int[] index = new int[4];
							final_index[0] = sx;
							final_index[1] = sy;
							index[0] = (initial_index[0]);
							index[1] = (initial_index[1]);
							index[2] = (final_index[0]);
							index[3] = (final_index[1]);

							idcies_for_removal_in_this_row.add(index);
							sx = initial_index[0] + 1;
							sy = initial_index[1] + 1;
							count = 0;
						}

					} else {
						count = 0;
					}
				}
			}
			// possible_ring_removal.add(idcies_for_removal_in_this_row);

		} else if (fj == sj) {
			int i = -1;
			int ii = -1;
			if (si < fi) {
				i = si;
				ii = fi;
			} else {
				i = fi;
				ii = si;
			}
			// Vector<int[]> idcies_for_removal_in_this_row=new Vector<int[]>();
			if (player_no == 1) {
				// red disc
				int count = 0;
				int[] initial_index = new int[2];
				int[] final_index = new int[2];
				for (int sx = i, sy = sj; sx <= ii; sx += 2) {
					if (gboard[sx][sy].equals("r")) {
						if (count == 0) {
							initial_index[0] = sx;
							initial_index[1] = sy;
						}
						count++;
						if (count == 5) {
							int[] index = new int[4];
							final_index[0] = sx;
							final_index[1] = sy;
							index[0] = (initial_index[0]);
							index[1] = (initial_index[1]);
							index[2] = (final_index[0]);
							index[3] = (final_index[1]);

							idcies_for_removal_in_this_row.add(index);
							sx = initial_index[0] + 2;
							count = 0;
						}

					} else {
						count = 0;
					}

				}
			} else if (player_no == 2) {
				// blue disc
				int count = 0;
				int[] initial_index = new int[2];
				int[] final_index = new int[2];
				for (int sx = i, sy = sj; sx <= ii; sx += 2) {
					if (gboard[sx][sy].equals("b")) {
						if (count == 0) {
							initial_index[0] = sx;
							initial_index[1] = sy;
						}
						count++;
						if (count == 5) {
							int[] index = new int[4];
							final_index[0] = sx;
							final_index[1] = sy;
							index[0] = (initial_index[0]);
							index[1] = (initial_index[1]);
							index[2] = (final_index[0]);
							index[3] = (final_index[1]);

							idcies_for_removal_in_this_row.add(index);
							sx = initial_index[0] + 2;
							count = 0;
						}

					} else {
						count = 0;
					}

				}

			}
			// possible_ring_removal.add(idcies_for_removal_in_this_row);

		}
		// System.err.println("5disc"+idcies_for_removal_in_this_row.size());
		return idcies_for_removal_in_this_row;
	}

	// keep the moved =false when calling first time
	// moves as "" when calling first time
	public static Vector<Pair<String[][], String>> AllSingleMoves(String[][] gboard, int player_no, boolean moved,String moves) {
		Vector<int[]> runs = getruns(gboard, player_no);
		Vector<Pair<String[][], String>> allmoves = new Vector<Pair<String[][], String>>();
		// Vector<String[][]> allmoves=new Vector<String[][]>();
		if (runs.size() != 0 && getPositionOfRing(player_no,gboard).size()>=3) {
			for (int i = 0; i < runs.size(); i++) {
				String[][] temp_board = getCopyOfboard(gboard);
				String tmp = new String(moves);
				int[] idx = runs.get(i);
				int[] idxhex1=idxToHex(idx[0],idx[1]);
				int[] idxhex2=idxToHex(idx[2],idx[3]);
				idx[0]=idxhex1[0];
				idx[1]=idxhex1[1];
				idx[2]=idxhex2[0];
				idx[3]=idxhex2[1];

				// System.err.println("remove Row from "+ idx[0]+","+idx[1]+" to "+idx[1]+","+idx[2]);
				removeRow(temp_board, idx[0], idx[1], idx[2], idx[3]);
				// System.err.println("before");
				// showboard(temp_board);
				int[] hex_pos = removingRingGreedly(temp_board, player_no);
				temp_board[getindex(hex_pos[0], hex_pos[1])[0]][getindex(hex_pos[0], hex_pos[1])[1]]="O";
				// System.err.println("After");
				// showboard(temp_board);
				tmp += " RS " + idx[0] + " " + idx[1] + " RE " + idx[2] + " " + idx[3] + " X " + hex_pos[0] + " "+ hex_pos[1];
				// break;
				//error in next line no termination
				allmoves.addAll(AllSingleMoves(temp_board, player_no, moved, tmp));
			}
		} 
		else if (moved == false && getPositionOfRing(player_no,gboard).size()>=3) {
			Vector<int[]> pos_of_Rings = getPositionOfRing(player_no,gboard);
			// System.err.println(" pos_of rings for player "+player_no+" " + pos_of_Rings.size());
			Vector<String[][]> neigh_states = new Vector<String[][]>();
			for (int i = 0; i < pos_of_Rings.size(); i++) {
				Vector<int[]> validPos = getValidPosofTheRing(gboard, pos_of_Rings.get(i)[0], pos_of_Rings.get(i)[1]);
				// System.err.println("No of valid moves for player_no "+player_no+" "+validPos.size());
				for (int j = 0; j < validPos.size(); j++) {
					String s = "";
					String[][] temp_board = getCopyOfboard(gboard);
					moveRing(temp_board, player_no, pos_of_Rings.get(i)[0], pos_of_Rings.get(i)[1], validPos.get(j)[0],validPos.get(j)[1]);
					s = "S " + pos_of_Rings.get(i)[0] + " " + pos_of_Rings.get(i)[1] +" M"+ " " + validPos.get(j)[0] + " "+ validPos.get(j)[1];
					allmoves.addAll(AllSingleMoves(temp_board, player_no, true, s));
				}
			}

		} 
		else {
			Pair<String[][], String> temp = new Pair<String[][], String>(getCopyOfboard(gboard), moves);

			allmoves.add(temp);
		}
		// System.err.println("eeww"+allmoves.size());
		return (allmoves);
	}

	public static boolean gameOver(String[][] gboard) {
		int count1 = 0;
        int count2 = 0;
        int count3 = 0;
		for (int i = 0; i < gboard.length; i++) {
			for (int j = 0; j < gboard[0].length; j++) {
                if(gboard[i][j].equals("O")){
                    count3 +=1;
                }
                if (gboard[i][j].equals("R")) {
					count1 += 1;
				}
				if (gboard[i][j].equals("B")) {
					count2 += 1;
				}

			}
        }
        if(count3 == 0){
            return true;
        }  
		else if (count1 >= 3 && count2 >= 3) {
			return (false);
        }
        
        
		return true;
    }

	public static boolean isTerminal(String[][] curr_board) {
		return false;
	}


	// --------------------------------------------------------------------------------
	public static double Gamescore(String[][] curr_board, int player_no){
		int Rring=0;
		int Bring=0;
		int Rmarker=0;
		int Bmarker=0;
		double score=0;
		for(int i =0; i<curr_board.length;i++){
			for(int j =0; j<curr_board[0].length;j++){
				if(curr_board[i][j].equals("R")){
					Rring++;
				}
				if(curr_board[i][j].equals("B")){
					Bring++;
				}
				if(curr_board[i][j].equals("r")){
					Rmarker++;
				}
				if(curr_board[i][j].equals("b")){
					Bmarker++;
				}
			}
		}
		Rring=5-Rring;
		Bring=5-Bring;
		
		if(Rring == 3 && Bring==0){
			score = score+10;
		}
		else if(Rring == 3 && Bring==1){
			score = score + 9;
		}
		else if(Rring == 3 && Bring==2){
			score = score + 8;
		}
		else if(Rring == 2 && Bring==0){
			score = score + 7;
		}
		else if(Rring == 2 && Bring==1){
			score = score + 6;
		}
		else if(Rring == 1 && Bring==0){
			score = score + 6;
		}
		else if(Rring == 2 && Bring==2){
			score = score + 5;
		}
		else if(Rring == 1 && Bring==1){
			score = score + 5;
		}
		else if(Rring ==0 && Bring==0){
			score = score + 5;
		}
		else if(Rring == 0 && Bring==1){
			score = score + 4;
		}
		else if(Rring == 1 && Bring==2){
			score = score + 4;
		}
		else if(Rring == 0 && Bring==2){
			score = score + 3;
		}
		else if(Rring == 2 && Bring==3){
			score = score + 2;
		}
		else if(Rring == 1 && Bring==3){
			score = score + 1;
		}
		else if(Rring == 0 && Bring==3){
			score = score + 0;
		}
		double Rm = Rmarker;
		score=score+ Rm/10;
		if (player_no==opp_player_no) {
			score=-score;
		}
		
		 
		return score;
	}


	public static int evalDiscscore(String[][] curr_board, int player_no){
		int a = 0;
		a = a+ Discinrowver(curr_board, player_no);
		///left run
		
		int[] a1 = {3,1};
		int[] a2 = {0,4};
		// a = a+ Discindiag(curr_board, a1, a2, player_no);
		a1[0]= 6;a1[1]=0 ;
		a2[0]= 0;a2[1]=6 ;
		a = a+ Discindiag(curr_board, a1, a2, player_no);
		a1[0]= 8;a1[1]=0 ;
		a2[0]= 1;a2[1]=7 ;
		a = a+ Discindiag(curr_board, a1, a2, player_no);
		a1[0]= 10;a1[1]=0 ;
		a2[0]= 2;a2[1]=8 ;
		a = a+ Discindiag(curr_board, a1, a2, player_no);
		a1[0]= 12;a1[1]=0 ;
		a2[0]= 3;a2[1]=9 ;
		a = a+ Discindiag(curr_board, a1, a2, player_no);
		a1[0]= 13;a1[1]=1 ;
		a2[0]= 5;a2[1]=9 ;
		a = a+ Discindiag(curr_board, a1, a2, player_no);
		a1[0]= 15;a1[1]=1 ;
		a2[0]= 6;a2[1]=10 ;
		a = a+ Discindiag(curr_board, a1, a2, player_no);
		a1[0]= 16;a1[1]=2 ;
		a2[0]= 8;a2[1]=10 ;
		a = a+ Discindiag(curr_board, a1, a2, player_no);
		a1[0]= 17;a1[1]=3 ;
		a2[0]= 10;a2[1]=10 ;
		a = a+ Discindiag(curr_board, a1, a2, player_no);
		a1[0]= 18;a1[1]=4;
		a2[0]= 12;a2[1]=10 ;
		a = a+ Discindiag(curr_board, a1, a2, player_no);
		a1[0]= 18;a1[1]=6 ;
		a2[0]= 15;a2[1]=9 ;
		// a = a+ Discindiag(curr_board, a1, a2, player_no);
		//right run
		a1[0]= 0;a1[1]=6 ;
		a2[0]= 3;a2[1]=9 ;
		// a = a+ Discindiag(curr_board, a1, a2, player_no);
		a1[0]= 0;a1[1]=4 ;
		a2[0]= 6;a2[1]=10 ;
		a = a+ Discindiag(curr_board, a1, a2, player_no);
		a1[0]= 1;a1[1]=3 ;
		a2[0]= 8;a2[1]=10 ;
		a = a+ Discindiag(curr_board, a1, a2, player_no);
		a1[0]= 2;a1[1]=2 ;
		a2[0]= 10;a2[1]=10 ;
		a = a+ Discindiag(curr_board, a1, a2, player_no);
		a1[0]= 3;a1[1]=1 ;
		a2[0]= 12;a2[1]=10 ;
		a = a+ Discindiag(curr_board, a1, a2, player_no);
		a1[0]= 5;a1[1]=1 ;
		a2[0]= 13;a2[1]=9 ;
		a = a+ Discindiag(curr_board, a1, a2, player_no);
		a1[0]= 6;a1[1]=0 ;
		a2[0]= 15;a2[1]=9 ;
		a = a+ Discindiag(curr_board, a1, a2, player_no);
		a1[0]= 8;a1[1]=0 ;
		a2[0]= 16;a2[1]=8 ;
		a = a+ Discindiag(curr_board, a1, a2, player_no);
		a1[0]= 10;a1[1]=0 ;
		a2[0]= 17;a2[1]=7 ;
		a = a+ Discindiag(curr_board, a1, a2, player_no);
		a1[0]= 12;a1[1]=0 ;
		a2[0]= 18;a2[1]=6 ;
		a = a+ Discindiag(curr_board, a1, a2, player_no);
		a1[0]= 15;a1[1]=1 ;
		a2[0]= 18;a2[1]=4 ;
		// a = a+ Discindiag(curr_board, a1, a2, player_no);
		if (player_no==opp_player_no) {
			a=-a;
		}
		return a;


	}
	public static int Discindiag(String[][] curr_board,int[] a1, int[] a2,int player_no){
		int diag1_score=0;
		if(player_no == 1){
			diag1_score=0;
			int[] n = {0,0,0,0,0};
			int count =-1;
			if(a1[0]+a1[1]==a2[0]+a2[1]){
				//PLAYER 1
				// while(curr_board[])
				
				
				int i=a1[0];
				int j=a1[1];
				
				while(a2[0]<=i && j<=a2[1]){
				//do sth
					
				
					if(a2[0]<=i && j<=a2[1] && curr_board[i][j].equals("r")){
						count++;//0
						
						if(a2[0]<=i-1 && j+1<=a2[1] && curr_board[i-1][j+1].equals("r")){
							
							count++;//1
							// System.out.println("dd"+i+""+j);
							if(a2[0]<=i-2 && j+2<=a2[1] && curr_board[i-2][j+2].equals("r")){
								count++;//2
								if(a2[0]<=i-3 && j+3<=a2[1] && curr_board[i-3][j+3].equals("r")){
									count++;//3
									if(a2[0]<=i-4 && j+4<=a2[1] && curr_board[i-4][j+4].equals("r")){
										count++;//4
										n[count]= n[count]+1;
									}
									else{
										n[count]= n[count]+1;
									}
									

								}
								else{
									n[count]= n[count]+1;
								}
							}
							else{
								n[count]= n[count]+1;
							}
						}
						else{
							n[count]= n[count]+1;
						}
					}
					
					if(count == -1){
						i= i-1;
						j= j+1;
					}
					if(count !=-1){
						
						i = i-count-1;
						j = j+count+1;
						count = -1;
					}
				
				}
				diag1_score = 121*n[4]+40*n[3]+ 13*n[2]+ 4*n[1]+n[0];
			}
			else if(a1[0]-a1[1]==a2[0]-a2[1]){
				int i=a1[0];
				int j=a1[1];
				while(i<= a2[0] && j<=a2[1]){
					//do sth
					if(a2[0]>=i && j<=a2[1] && curr_board[i][j].equals("r")){
						count++;//0
						if(a2[0]>=i+1 && j+1<=a2[1] && curr_board[i+1][j+1].equals("r")){
							count++;//1
							if(a2[0]>=i+2 && j+2<=a2[1] && curr_board[i+2][j+2].equals("r")){
								count++;//2
								if(a2[0]>=i+3 && j+3<=a2[1] && curr_board[i+3][j+3].equals("r")){
									count++;//3
									if(a2[0]>=i+4 && j+4<=a2[1] && curr_board[i+4][j+4].equals("r")){
										count++;//4
										n[count]= n[count]+1;
									}
									else{
										n[count]= n[count]+1;
									}
									

								}
								else{
									n[count]= n[count]+1;
								}
							}
							else{
								n[count]= n[count]+1;
							}
						}
						else{
							n[count]= n[count]+1;
						}
					}
					if(count == -1){
						i= i+1;
						j= j+1;
					}
					if(count!=-1){
						i = i+count+1;
						j = j+count+1;
						count = -1;
					}
				}
			diag1_score = 121*n[4]+40*n[3]+ 13*n[2]+ 4*n[1]+n[0];
			}
		// return diag1_score;
		}
		else{

			diag1_score=0;
			int[] n = {0,0,0,0,0};
			int count =-1;
			if(a1[0]+a1[1]==a2[0]+a2[1]){
				//PLAYER 1
				// while(curr_board[])
				
				
				int i=a1[0];
				int j=a1[1];
				
				while(a2[0]<=i && j<=a2[1]){
				//do sth
					
				
					if(a2[0]<=i && j<=a2[1] && curr_board[i][j].equals("b")){
						count++;//0
						
						if(a2[0]<=i-1 && j+1<=a2[1] && curr_board[i-1][j+1].equals("b")){
							
							count++;//1
							// System.out.println("dd"+i+""+j);
							if(a2[0]<=i-2 && j+2<=a2[1] && curr_board[i-2][j+2].equals("b")){
								count++;//2
								if(a2[0]<=i-3 && j+3<=a2[1] && curr_board[i-3][j+3].equals("b")){
									count++;//3
									if(a2[0]<=i-4 && j+4<=a2[1] && curr_board[i-4][j+4].equals("b")){
										count++;//4
										n[count]= n[count]+1;
									}
									else{
										n[count]= n[count]+1;
									}
									

								}
								else{
									n[count]= n[count]+1;
								}
							}
							else{
								n[count]= n[count]+1;
							}
						}
						else{
							n[count]= n[count]+1;
						}
					}
					
					if(count == -1){
						i= i-1;
						j= j+1;
					}
					if(count !=-1){
						
						i = i-count-1;
						j = j+count+1;
						count = -1;
					}
				
				}
				diag1_score = 121*n[4]+40*n[3]+ 13*n[2]+ 4*n[1]+n[0];
			}
			else if(a1[0]-a1[1]==a2[0]-a2[1]){
				int i=a1[0];
				int j=a1[1];
				while(i<= a2[0] && j<=a2[1]){
					//do sth
					if(a2[0]>=i && j<=a2[1] && curr_board[i][j].equals("b")){
						count++;//0
						if(a2[0]>=i+1 && j+1<=a2[1] && curr_board[i+1][j+1].equals("b")){
							count++;//
							if(a2[0]>=i+2 && j+2<=a2[1] && curr_board[i+2][j+2].equals("b")){
								count++;//2
								if(a2[0]>=i+3 && j+3<=a2[1] && curr_board[i+3][j+3].equals("b")){
									count++;//3
									if(a2[0]>=i+4 && j+4<=a2[1] && curr_board[i+4][j+4].equals("b")){
										count++;//4
										n[count]= n[count]+1;
									}
									else{
										n[count]= n[count]+1;
									}
									

								}
								else{
									n[count]= n[count]+1;
								}
							}
							else{
								n[count]= n[count]+1;
							}
						}
						else{
							n[count]= n[count]+1;
						}
					}
					if(count == -1){
						i= i+1;
						j= j+1;
					}
					if(count!=-1){
						i = i+count+1;
						j = j+count+1;
						count = -1;
					}
				}
			diag1_score = 121*n[4]+40*n[3]+ 13*n[2]+ 4*n[1]+n[0];
			}
		// return diag1_score;
		}	
		return diag1_score;
	}    
	public static int Discinrowver(String[][] curr_board, int player_no){
		int ver_score=0;
		if(player_no ==1 ){
			ver_score=0;
		
			int[] n = {0,0,0,0,0};
		
			int count =-1;
			for(int i =0; i<11;i++){
				for(int j = 0; j<19;j++){
					if(!curr_board[j][i].equals("X")){
						if(curr_board[j][i].equals("r")){
							count++;
							if(j+1<19 && curr_board[j+1][i].equals("r")){
								count++;
								if(j+2<19 && curr_board[j+2][i].equals("r")){
									count++;
									if(j+3<19 && curr_board[j+3][i].equals("r")){

										count++;
										if(j+4<19 && curr_board[j+4][i].equals("r")){
											count++;
											n[count]= n[count]+1;
										}
										else{
											n[count]= n[count]+1;

										}
									}
									else{
										n[count]= n[count]+1;
									}
								}
								else{
									n[count]= n[count]+1;	
								}

							}	
							else{
								n[count]= n[count]+1;
							}
							j = j+count;
							count=-1;
						}
				
					}
				}
			}
			ver_score = 121*n[4]+40*n[3]+ 13*n[2]+ 4*n[1]+n[0];
		
		}
		else{
			ver_score=0;
		
			int[] n = {0,0,0,0,0};
		
			int count =-1;
			for(int i =0; i<11;i++){
				for(int j = 0; j<19;j++){
					if(!curr_board[j][i].equals("X")){
						if(curr_board[j][i].equals("b")){
							count++;
							if(j+1<19 && curr_board[j+1][i].equals("b")){
								count++;
								if(j+2<19 && curr_board[j+2][i].equals("b")){
									count++;
									if(j+3<19 && curr_board[j+3][i].equals("b")){

										count++;
										if(j+4<19 && curr_board[j+4][i].equals("b")){
											count++;
											n[count]= n[count]+1;
										}
										else{
											n[count]= n[count]+1;
										}
										
									}
									else{
										n[count]= n[count]+1;
									}
								}
								else{
									n[count]= n[count]+1;	
								}

							}
							else{
								n[count]= n[count]+1;
							}
							j = j+count;
							count=-1;
						}
				
					}
				}
			}
			ver_score = 121*n[4]+40*n[3]+ 13*n[2]+ 4*n[1]+n[0];
		

		}
		
		return ver_score;
	}
	public static int Centrering(String[][] curr_board,int player_no){
		Vector<int[]> aa = getPositionOfRing(my_player_no, curr_board);
		int count =0;
		for(int i =0;i<aa.size();i++){
			if(aa.get(i)[0]<=3){
				count++;
			}
		}
		aa = getPositionOfRing(opp_player_no, curr_board);
		for(int i =0;i<aa.size();i++){
			if(aa.get(i)[0]<=3){
				count--;
			}
		}
		return count;
	}
	// public static int no_of_Runs(String[][] gboard,int player_no){
	// 	int num_run=getruns(gboard,player_no).size();
	// 	int score=0;
	// 	if (player_no==my_player_no) {
	// 		score=num_run;
	// 	}
	// 	else{
	// 		score=-num_run;
	// 	}
	// 	return(score);
	// }
	//higher score the better for us
	public static int Less_rings_the_better(String[][] gboard,int player_no){
		int no_of_Rings1=getPositionOfRing(my_player_no,gboard).size();
		int no_of_Rings2=getPositionOfRing(opp_player_no,gboard).size();
		int score=0;
		
		score=(5-no_of_Rings1)-(5-no_of_Rings2);
		return score;
	}
	//replace ring with its corresponding marker
	public static int whatifNoRings(String[][] gboard,int player_no){
		String[][] temp_board=getCopyOfboard(gboard);
		Vector<int[]> posOfRing1=getPositionOfRing(my_player_no,temp_board);
		Vector<int[]> posOfRing2=getPositionOfRing(opp_player_no,temp_board);
		int[] idx={-1,-1};
		int score=0;
		for (int i=0;i<posOfRing1.size();i++ ) {
			idx=getindex(posOfRing1.get(i)[0],posOfRing1.get(i)[1]);
			if (player_no==1) {
				temp_board[idx[0]][idx[1]]="r";
			}
			else{
				temp_board[idx[0]][idx[1]]="b";
			}
		}
		for (int i=0;i<posOfRing2.size();i++ ) {
			idx=getindex(posOfRing2.get(i)[0],posOfRing2.get(i)[1]);
			if (player_no==1) {
				temp_board[idx[0]][idx[1]]="r";
			}
			else{
				temp_board[idx[0]][idx[1]]="b";
			}
		}
		int no_of_Runs1=getruns(temp_board,my_player_no).size();
		int no_of_Runs2=getruns(temp_board,opp_player_no).size();
		score=no_of_Runs1- no_of_Runs2;
		
		return(score);

	}
	public static int Utility(String[][] curr_board, int player_no) {
		// for player us
		int util=0;
		int util1=0;
		int cring1=0;
		int runs_count=0;
		int seq1=0;
		int less_rings=0;
		int whatif=0;
	
		util1 = (int) (Gamescore(curr_board, player_no)); //- Gamescore(curr_board, opp_player_no));
		cring1 = Centrering(curr_board, player_no); //- Centrering(curr_board, opp_player_no) ;
		seq1 = evalDiscscore(curr_board, player_no);// - evalDiscscore(curr_board, opp_player_no);
		// runs_count=no_of_Runs(curr_board,player_no);
		less_rings=Less_rings_the_better(curr_board,player_no);
		whatif=whatifNoRings(curr_board,player_no);
	
		// System.err.println("-------------------------------");
		// System.err.println("util1 "+util1);
		// System.err.println("gamescore "+Gamescore(curr_board, my_player_no));
		// System.err.println("seq1 "+seq1);
		// System.err.println("runs_count "+runs_count);
		// System.err.println("less_rings "+less_rings);
		// System.err.println("whatif "+whatif);
		// System.err.println("-------------------------------\n\n");

		
		util = 30*util1+7*cring1+seq1/2+100*less_rings+50*whatif;
		return util;
	}
	
    // --------------------------------------------------------------------------------
	// int alpha = Integer.MIN_VALUE;
	// int beta = Integer.MAX_VALUE;
	static String returnMove;

    // depth of
    static public String minmax(String[][] curr_board, int player_no,int depth, int alpha, int beta,int cutoff){

        returnMove="";
		Vector<int[]> runs = getruns(curr_board, player_no);
		String tmp="";
       	while(runs.size()!=0 && getPositionOfRing(player_no,curr_board).size()>=3){
				int[] idx = runs.get(0);
				int[] idxhex1=idxToHex(idx[0],idx[1]);
				int[] idxhex2=idxToHex(idx[2],idx[3]);
				idx[0]=idxhex1[0];
				idx[1]=idxhex1[1];
				idx[2]=idxhex2[0];
				idx[3]=idxhex2[1];

				// System.err.println("remove Row from "+ idx[0]+","+idx[1]+" to "+idx[1]+","+idx[2]);
				removeRow(curr_board, idx[0], idx[1], idx[2], idx[3]);
				// System.err.println("before");
				// showboard(temp_board);
				int[] hex_pos = removingRingGreedly(curr_board, player_no);
				curr_board[getindex(hex_pos[0], hex_pos[1])[0]][getindex(hex_pos[0], hex_pos[1])[1]]="O";
				// System.err.println("After");
				// showboard(temp_board);
				tmp += "RS " + idx[0] + " " + idx[1] + " RE " + idx[2] + " " + idx[3] + " X " + hex_pos[0] + " "+ hex_pos[1]+" ";
				runs=getruns(curr_board,player_no);
				
       	}
       	if (getPositionOfRing(player_no,curr_board).size()>=3) {
        	miniMaxAlphaBeta(curr_board, player_no, depth, alpha, beta,cutoff);
       	}
        //System.err.println("ee"+returnMove);
        returnMove=tmp+returnMove;
        return returnMove.trim();
    }
	public static  int miniMaxAlphaBeta(String[][] curr_board, int player_no,int depth, int alpha, int beta,int cutoff) {
		// returnMove = "";
		// return 0;
		//System.err.println("ee"+depth);
		if (gameOver(curr_board) == true) {
			return Utility(curr_board,player_no);// game is over
		}

		if(isTerminal(curr_board) == true || depth == cutoff) {
			return Utility(curr_board,player_no);
		}

		else if (player_no == my_player_no) {
			int v = Integer.MIN_VALUE;
			// System.err.println("ee"+depth);
			Vector<Pair<String[][], String>> neighbours = AllSingleMoves(curr_board,my_player_no,false,"");
			// System.err.println("eee"+neighbours.size());
			// System.err.println("eee"+neighbours.get(0).getSecond());
			for (int i = 0; i < neighbours.size(); i++) {
				// System.err.println("eeeq"+depth);
				int u = Math.max(v, miniMaxAlphaBeta(neighbours.elementAt(i).getFirst(), opp_player_no, depth + 1, alpha, beta,cutoff));
				if (u > v) {
					// store state
					// showboard(neighbours.elementAt(i).getFirst());
					// System.err.println("Haha");
					// System.err.println("Move "+neighbours.elementAt(i).getSecond());
					if (depth==0) {
						returnMove = neighbours.elementAt(i).getSecond();
					}

				}
				v = u;
				alpha = Math.max(alpha, v);

				// returnState =
				if (beta <= alpha) {
					break;
				}
			}
			return v;
		} else {
			int v = Integer.MAX_VALUE;
			Vector<Pair<String[][], String>> neighbours = AllSingleMoves(curr_board,my_player_no,false,"");
			for (int i = 0; i < neighbours.size(); i++) {
				v = Math.min(v, miniMaxAlphaBeta(neighbours.elementAt(i).getFirst(), my_player_no, depth + 1, alpha, beta,cutoff));
				beta = Math.min(beta, v);
				if (beta <= alpha) {
					break;
				}
			}
			return v;
		}
	}



	public static void update_move(String move,int player_no){
		String[] movesplit=move.trim().split("\\s+");
		if (movesplit[0].equals("P")) {
			placeRing(board, player_no, Integer.parseInt(movesplit[1]), Integer.parseInt(movesplit[2]));			
		}
		else if (movesplit[0].equals("S")) {
			moveRing(board, player_no, Integer.parseInt(movesplit[1]), Integer.parseInt(movesplit[2]),Integer.parseInt(movesplit[4]), Integer.parseInt(movesplit[5]));
			String remains="";
			for (int i=6;i<movesplit.length;i++ ) {
				remains+=movesplit[i]+" ";
			}
			remains=remains.trim();
			update_move(remains,player_no);

		}
		else if (movesplit[0].equals("RS")) {
			removeRow(board, Integer.parseInt(movesplit[1]), Integer.parseInt(movesplit[2]),
					Integer.parseInt(movesplit[4]), Integer.parseInt(movesplit[5]));
			removeRing(board, player_no, Integer.parseInt(movesplit[7]), Integer.parseInt(movesplit[8]));
			String remains="";
			for (int i=9;i<movesplit.length;i++ ) {
				remains+=movesplit[i]+" ";
			}
			remains=remains.trim();
			update_move(remains,player_no);
		}
	}

	
	
	public static String opening() {
		String move = "P ";
		Random rand = new Random();
		while (true) {
			int hexagon = rand.nextInt(3);
			int position=0;
			if (hexagon!=0) {
				position = rand.nextInt(6 * hexagon);
			}
			int[] index = getindex(hexagon, position);
			if (board[index[0]][index[1]].equals("O")) {
				move = move + hexagon + " " + position;
				return move;
			}
		}

	}

	public static String get_move() {
		// code to get move from initial state to best move.
		String s = minmax(board, my_player_no, 0, Integer.MIN_VALUE, Integer.MAX_VALUE,3);
		// code to get move string from ini to fin state

		return s;
	}

	public static void main(String[] args) {
		
		int player_id, board_size, time_limit;
		Scanner sc = new Scanner(System.in);
		String move="";
		initial_config();
		String[] data = sc.nextLine().trim().split(" ");
		player_id = Integer.parseInt(data[0]);
		board_size = Integer.parseInt(data[1]);
		time_limit = Integer.parseInt(data[2]);
		boolean gameover = false;
		// my_player_no = player_id;

		if (player_id == 2) {
			my_player_no =2;
			opp_player_no =1;
			
			// Get other player's move
			for (int i = 0; i < 5; i++) {
				move = sc.nextLine();
				
				update_move(move, 1);// 1
				move = opening();

				
				update_move(move, 2);
				System.out.println(move);
				

			}
			move = sc.nextLine();
			update_move(move, 1);// 1

			while (!gameover) {
				// to check for game over

				move = get_move();
				update_move(move, 2);
				System.out.println(move);
				// to check for game over
				
				move = sc.nextLine();
				update_move(move, 1);
			}
		} else if (player_id == 1) {
			my_player_no =1;
			opp_player_no=2;
			// System.err.println("f");
			for (int i = 0; i < 5; i++) {

				move = opening();
				update_move(move, 1);
				System.out.println(move);
				move = sc.nextLine();
				update_move(move, 2);// 1
			}
			while (!gameover) {
				move = get_move();
				update_move(move, 1);
				System.out.println(move);
				move = sc.nextLine();
				update_move(move, 2);
			}
		}

	}
}
class Pair<F, S> {
    public F first; //first member of pair
    public S second; //second member of pair

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public void setSecond(S second) {
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }
}
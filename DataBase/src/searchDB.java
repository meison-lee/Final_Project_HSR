
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class searchDB {

	/**
	 * @param train(列車號碼)
	 * @param start
	 * @param end
	 * @param number(票數)
	 * @return
	 * @throws IOException
	 */
	public static String getSeatno(String train, String start, String end,int number) throws IOException {
		String sss = "table";
		BufferedReader rDB = new BufferedReader(new FileReader("C://NTU/" + sss + ".csv"));
		// 找到指定train
		// System.out.println("here");
		String line = rDB.readLine();
//		int lineCount++;
		boolean found = false;
		while (found == false) {

			line = rDB.readLine();
			String[] tt = line.split(",");
			if (tt[0].equals(train)) {
				found = true;
			}

			else {
//				lineCount++;
			}
		}
		// 建一個array元素是各個站的string，預設空間十個車站
		String[] stationSeat = new String[10];

		// 要記錄是哪兩個站
		int st = 0;
		int s = 0, e = 0;
		boolean matchS = false, matchE = false;
		// 找到開始站，一路assign到找到為止
		int sts=0,ste=0;
		try {
			
			while (matchS == false) {
				stationSeat[st] = rDB.readLine();
				if (stationSeat[st].contains(start)) {
					e++;
					sts=st;
					matchS = true;
				} else {
					s++;
					e++;
				}
				st++;
			}
			while (matchE == false) {
				stationSeat[st] = rDB.readLine();
				if (stationSeat[st].contains(end)) {
					ste=st;
					matchE = true;
				} else {
					e++;
				}
				st++;
			}
		} catch (Exception ee) {
			//System.out.println(ee.getMessage());
		}
		if ((sts >= ste)) {
			rDB.close();
			return "wrong direction"; // 方向錯誤
		}

		// 把第一排設定成座位
		String[] num = stationSeat[0].split(",");
		// 把有的位置輸進一個二維的座位array
		String[][] seats = new String[e - s + 1][986];
		for (int n = 0; n <= e - s; n++) {
			seats[n] = stationSeat[n + s].split(",");
		}

		// 直的是相同座位，橫的是相同站
		// 先以一個位子為單位(大圈for)，直的向下加之後(內圈for)，再進入下一次的迴圈
		int col=0;
		String seatno = "";
		String currentSeat="no seat available";
		for(int t=1;t<=number;t++) {
		for (int i = col; i <= 985; i++) {
			int checkseat = 0;

			for (int n = 0; n <= e - s; n++) {
				
				if (seats[n][i].equals("0")) {
				} else {
					checkseat++;
				}
			}
			if (checkseat == 0) {
				currentSeat=num[i];
				seatno = seatno+currentSeat+", "; // 要return的東西
				col=i+1;
				break;
			}

		}
		}
		rDB.close();
		return seatno;
	}

	/**
	 * @param train(列車號碼)
	 * @param start
	 * @param end
	 * @param number(票數)
	 * @param kind(只能填window or aisle 但我沒有寫偵錯)
	 * @return
	 * @throws IOException
	 */
	public static String getSeatnoSpecial(String train, String start, String end,int number,String kind) throws IOException {
		String sss = "table";
		BufferedReader rDB = new BufferedReader(new FileReader("C://NTU/" + sss + ".csv"));
// 找到指定train
		String line = rDB.readLine();
		boolean found = false;
		while (found == false) {
			line = rDB.readLine();
			String[] tt = line.split(",");
			if (tt[0].equals(train)) {
				found = true;
			}
			else {}
		}
//找起始站、終點站
		// 建一個array元素是各個站的string，預設空間十個車站
		String[] stationSeat = new String[10];
		// 要記錄是哪兩個站
		int st = 0;
		int s = 0, e = 0;
		boolean matchS = false, matchE = false;
		// 找到開始站，一路assign到找到為止
		int sts=0,ste=0;
		try {
			
			while (matchS == false) {
				stationSeat[st] = rDB.readLine();
				if (stationSeat[st].contains(start)) {
					e++;
					sts=st;
					matchS = true;
				} else {
					s++;
					e++;
				}
				st++;
			}
			while (matchE == false) {
				stationSeat[st] = rDB.readLine();
				if (stationSeat[st].contains(end)) {
					ste=st;
					matchE = true;
				} else {
					e++;
				}
				st++;
			}
		} catch (Exception ee) {
			//System.out.println(ee.getMessage());
		}
		if ((sts >= ste)) {
			rDB.close();
			return "wrong direction"; // 方向錯誤
		}
		// 把第一排設定成座位
		String[] num = stationSeat[0].split(",");
		// 把有的位置輸進一個二維的座位array
		String[][] seats = new String[e - s + 1][986];
		for (int n = 0; n <= e - s; n++) {
			seats[n] = stationSeat[n + s].split(",");
		}

		// 直的是相同座位，橫的是相同站
		// 先以一個位子為單位(大圈for)，直的向下加之後(內圈for)，再進入下一次的迴圈
		int col=0;
		String seatno = "";
		String currentSeat="no seats available";
		for(int t=1;t<=number;t+=0) {		
		for (int i = col; i <= 985; i++) {
			int checkseat = 0;
			for (int n = 0; n <= e - s; n++) {
				if (seats[n][i].equals("0")) {
				} else {
					checkseat++;
				}
			}
			if (checkseat == 0) {
				currentSeat=num[i];
				if((currentSeat.contains("C")||currentSeat.contains("D"))&&(kind.equals("aisle"))) {
					seatno =seatno+ currentSeat+", "; // 要return的東西
					col=i+1;
					t++;
					break;
				}
				else if((currentSeat.contains("A")||currentSeat.contains("E"))&&(kind.equals("window"))) {
					seatno =seatno+currentSeat+", "; // 要return的東西
					col=i+1;
					t++;
					break;
				}
				else {
					
				}
				
				
			}
		}
		}
		rDB.close();
		if(seatno.equals("")) {
			seatno ="no seats beside "+kind;
		}
		
		return seatno;
	}
}	

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class searchDB {
public static String getSeatno(String train ,String start,String end) throws IOException {
		String sss="shit";
		BufferedReader rDB = new BufferedReader(new FileReader("C://NTU/"+sss+".csv"));
		 //找到指定train
		 //System.out.println("here");
		 String line=rDB.readLine();
		 
		 boolean found=false;
		 while(found==false) {
			 
			 line=rDB.readLine();
			 String[] tt= line.split(",");
			 if (tt[0].equals(train)) {
				 found=true;
			 }
			 else {
			 }
		 }
		 //建一個array元素是各個站的string，預設空間十個車站
		 String[] stationSeat =new String[10];
	
		 //要記錄是哪兩個站
		 int st =0;
		 int s=0,e=0;
		 boolean matchS=false,matchE=false;
		 //找到開始站，一路assign到找到為止
		 while(matchS==false) {
			 stationSeat[st]=rDB.readLine();
			 if (stationSeat[st].contains(start)) {
				 e++;
				 matchS=true;
			 }
			 else {
				 s++;
				 e++;
			 }
			 st++;
		 }	 
		 while(matchE==false) {
			 stationSeat[st]=rDB.readLine();
			 if (stationSeat[st].contains(end)) {
				 matchE=true;
			 }
			 else {
				 e++;
			 }
			 st++;
		 }	
		 //把第一排設定成座位
		 String[] num=stationSeat[0].split(",");
		 //把有的位置輸進一個二維的座位array
		 String[][] seats=new String[e-s+1][986];
		 for(int n=0;n<=e-s;n++) {
			 seats[n]=stationSeat[n+s].split(",");
		 }
		
		 //直的是相同座位，橫的是相同站
		 //先以一個位子為單位(大圈for)，直的向下加之後(內圈for)，再進入下一次的迴圈
		 String seatno="not found";
		 for(int i=1;i<=985;i++) {
			 int checkseat=0;
			 for(int n=0;n<=e-s;n++) {
				 if (seats[n][i].equals("0")) {
				 }
				 else {
					 checkseat++;
				 }
			 }
			 if (checkseat==0) {
				 seatno=num[i];						//要return的東西
				 break;
			 }
		 }
		 rDB.close();
		 return	seatno;
	
	
	}
}

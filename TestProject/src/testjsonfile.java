import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

public class testjsonfile {

	public static void main(String[] args) throws IOException {
		try {
			FileReader h = new FileReader("Data/file.json");
			BufferedReader tr = new BufferedReader(h);
			BufferedWriter bw = new BufferedWriter(new FileWriter("Data/booking.json"));
			String str = null;
			String data = "";
			while ((str = tr.readLine()) != null) {
				data = data + str+"\n";
			}
			
					
			JSONArray dataJSON = new JSONArray(data);
			System.out.println(dataJSON.length());
//			dataJSON.remove(0);
//			dataJSON.getJSONObject(0).remove("code");
			String ws = dataJSON.toString();
			System.out.println(ws);
			bw.write(ws);
			bw.flush();
			tr.close();
			bw.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

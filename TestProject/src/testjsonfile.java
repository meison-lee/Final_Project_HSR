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
			FileReader h = new FileReader("Data/price.json");
			BufferedReader tr = new BufferedReader(h);
			BufferedWriter bw = new BufferedWriter(new FileWriter("Data/test.json"));
			String str = null;
			System.out.println(tr.readLine());
			while ((str = tr.readLine()) != null) {
				System.out.println(str);
			}
			
					
//			JSONArray dataJSON = new JSONArray(data);
//			dataJSON.getJSONObject(0).remove("code");
//			String ws = dataJSON.toString();
//			System.out.println(ws);
//			bw.write(ws);
			bw.flush();
			tr.close();
			bw.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

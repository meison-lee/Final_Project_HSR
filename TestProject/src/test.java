import java.io.*;
import java.util.ArrayList;
import org.json.*;

public class test {
    /*public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>(); 
        list.add("admin");
        //讀取json檔案
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/json/newFile.json")); //new file
	        
	        JSONObject dataJson = new JSONObject("/booking.json");
	        JSONArray  features = dataJson.getJSONArray("features");
	        for (int i = 0; i < features.length(); i++) {
	        	
                JSONObject info = features.getJSONObject(i);				// 獲取features陣列的第i個json物件  
                JSONObject properties = info.getJSONObject("properties");	// 找到properties的json物件  
                String name = properties.getString("name");					// 讀取properties物件裡的name欄位值  
                
                properties.put("NAMEID", list.get(i));						// 新增NAMEID欄位
                // properties.append("name", list.get(i));  啥意思???
                System.out.println(properties.getString("NAMEID"));  
                properties.remove("ISO");									// 刪除ISO欄位
                
                String ws = dataJson.toString();	//將處理完的JSONObject dataJson轉成String type (ws)
                
                bw.write(ws);	//將ws寫入bw當中
                				// bw.newLine();  啥意思???
                bw.flush(); 	//將bw寫入
                bw.close();		//將bw寫入關閉
                
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
		    e.printStackTrace();
		}
    }*/
}
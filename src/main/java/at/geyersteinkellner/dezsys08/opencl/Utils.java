package at.geyersteinkellner.dezsys08.opencl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utils {

    public static String readFile(Class c, String fileName){
    	String text = "";
    	
    	BufferedReader in = new BufferedReader(new InputStreamReader(c.getResourceAsStream(fileName)));
    	String line = null;
    	
    	try {
			while((line = in.readLine()) != null){
				if(!text.isEmpty()) text += "\n";
				text += line;
			}
		} catch (IOException e) {
			System.err.println("Exception when reading from file: " + e);;
//			e.printStackTrace();
		}
    	
    	return text;
    }
}

package practice2nim;

import java.io.*;
import java.util.*;

public class ReadFile {

    public static ArrayList<Integer> readStacks (String file) throws IOException{
        //we read the different stacks of toothpick sticks
        ArrayList<Integer> stacksoftoothpicks = new ArrayList <Integer>();
        String linea;
        BufferedReader br = new BufferedReader (new FileReader (file));
        linea = br.readLine();
        StringTokenizer st = new StringTokenizer (linea);    
        while (st.hasMoreTokens()) {
            stacksoftoothpicks.add(Integer.parseInt(st.nextToken()));
        }
        br.close();
        return stacksoftoothpicks;
    }  
}
  
 
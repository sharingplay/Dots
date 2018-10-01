package socket;
//Parte de este codigo se tomo de ejemplo de http://www.ejbtutorial.com/distributed-systems/hello-world-for-socket-programming-using-java
import java.io.*;
import java.net.*;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;
 
public class Client {
 
   private static Socket socketClient;

public static void main(String argv[])
      {
	   try{
		    socketClient = new Socket("localhost",74253);
		    System.out.println("Client: "+"Connection Established");
		    
		    
 
		    BufferedReader reader = 
		    		new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
 
		    BufferedWriter writer= 
	        		new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
		    String serverMsg;
            class MyValue {
            	public int point1;
            	public int point2;
            }

            public static void main(String[] args) {
            	ObjectMapper mapper = new ObjectMapper();
            	MyValue value = mapper.readValue(new File("01.json"), MyValue.class);
            }

			while((serverMsg = reader.readLine()) != null){
				System.out.println("Client: " + serverMsg);
			}
 
	   }catch(Exception e){e.printStackTrace();}
      }
}
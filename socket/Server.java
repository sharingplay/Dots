package socket;
////Parte de este codigo se tomo de ejemplo de http://www.ejbtutorial.com/distributed-systems/hello-world-for-socket-programming-using-java
import java.io.*;
import java.net.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;


import com.fasterxml.jackson.databind.ObjectMapper;
 
class Server
{
   private static ServerSocket mysocket;

public static void main1(String argv[]) throws Exception
      {
 
   		 System.out.println(" Server is Running  " );
         mysocket = new ServerSocket(74253);
 
         while(true)
         {
            Socket connectionSocket = mysocket.accept();
 
            BufferedReader reader =
            		new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            BufferedWriter writer= 
            		new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));
 
            writer.write("*** Bienvenido al Server ***\r\n");            
            writer.write("*** instrucciones : \n");
            writer.flush();
            String data1 = reader.readLine().trim();
 
 
            class MyValue {
            	public int point1;
            	public int point2;
            }

            public static void main(String[] args) {
            	ObjectMapper mapper = new ObjectMapper();
            	MyValue value = mapper.readValue(new File("01.json"), MyValue.class);
                System.out.println("Hello World!"); // Display the string.         
           
 
            writer.write(getValue());
            writer.flush();
            connectionSocket.close();
         }
      }
}
package socket;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MyValue {
	public int point1;
	public int point2;
}

public static void main(String[] args) {
	ObjectMapper mapper = new ObjectMapper();
	MyValue value = mapper.readValue(new File("01.json"), MyValue.class);
    System.out.println("Hello World!"); // Display the string.
}

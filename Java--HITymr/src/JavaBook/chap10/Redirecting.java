package JavaBook.chap10;
import java.io.*;
public class Redirecting {
	public static void main(String[] args)  throws IOException {
		BufferedInputStream in = new BufferedInputStream(new FileInputStream("Redirecting.java"));
		PrintStream out = new PrintStream( new BufferedOutputStream( new FileOutputStream("out.txt")));
		
		System.setIn(in);
		System.setOut(out);
		System.setErr(out);
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s;
		while((s = br.readLine()) != null)   
		   System.out.println(s);
		   
		out.close(); // Remember this!
  }
  




  /*	
  public static void main(String[] args){
  	try{
  		BufferedInputStream in = new BufferedInputStream(new FileInputStream( "Redirecting.java"));
		PrintStream out = new PrintStream( new BufferedOutputStream( new FileOutputStream("out.java")));		
		System.setIn(in);
		System.setOut(out);
		System.setErr(out);
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s;
		while((s = br.readLine()) != null)   
		   System.out.println(s);
		   
		out.close(); // Remember this!
		
  	}catch(IOException e){
  		e.getStackTrace();
  	}
		
  }*/
} 

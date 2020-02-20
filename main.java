import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.util.HashMap; // import the HashMap class

public class main {

	static int num_book;
	static int num_lib;
	static int num_days;

	static int [] books_scores;
	static HashMap<Integer, Library> libraries = new HashMap<String, String>();


	public static void main(String[] args) throws IOException {

		leggifile("a_example.txt");
		System.out.println(num_book);
		System.err.println(num_lib);
		System.out.println(num_days);
		System.out.println();
		for(int i =0; i<books_scores.length; i++)	
			System.out.println(books_scores[i]);
	}


	public static void leggifile(String inputfile) throws FileNotFoundException
	{
		try  
		{  
			File file=new File(inputfile);    //creates a new file instance  
			FileReader fr=new FileReader(file);   //reads the file  
			BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
			StringBuffer sb=new StringBuffer();    //constructs a string buffer with no characters  
			String line;  

			int count = 0;
			int hashvalues = 0;
			while((line=br.readLine())!=null)  
			{  
				sb.append(line);      //appends line to string buffer  
				if(count == 0 )
				{
					String[] values = (line.split(" "));
					num_book =  Integer.parseInt(values[0]);
					num_lib = Integer.parseInt(values[1]);
					num_days = Integer.parseInt(values[2]);
				}
				else if(count == 1)
				{
					String[] values = (line.split(" "));
					books_scores = new int[values.length];
					for(int i =0; i<values.length; i++)
						books_scores[i]=Integer.parseInt(values[i]);
				}
				else
				{
					String[] values = line.split(" ");
					line=br.readLine();
					String[] values2 = line.split(" ");

					int in0 = Integer.parseInt(values[0]) ;
					int in1 = Integer.parseInt(values[1]);
					int in2 =  Integer.parseInt(values[3]);
					ArrayList<Integer> in3 = new ArrayList<Integer>();
					for(int j = 0 ; j < values2.length; j++)
						in3.add(Integer.parseInt(values2[j]));
					libraries.put(hashvalues++, new Library(in0,in1,in2,in3));
					

				}
				count++;
				sb.append("\n");     //line feed   
			}  
			fr.close();    //closes the stream and release the resources  
			//System.out.println("Contents of File: ");  
			//System.out.println(sb.toString());   //returns a string that textually represents the object  
		}  
		catch(IOException e)  
		{  
			e.printStackTrace();  
		}  
	}  

}


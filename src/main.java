import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.util.HashMap; // import the HashMap class

public class main {

	static int num_book;
	static int num_lib;
	static int num_days;

	static int [] books_scores;
	static HashMap<Integer, Library> libraries = new HashMap<Integer, Library>();

	static List<Integer> libOrder = new ArrayList<>();
	static List<Integer> bookSent = new ArrayList<>();
	
	static ArrayList<Library> libSol = new ArrayList<>();

	static BufferedWriter bw;



	public static void main(String[] args) throws IOException {

		int firstAvailableDay=1;
		leggifile("a_example.txt");
		System.out.println(num_book);
		System.err.println(num_lib);
		System.out.println(num_days);
		System.out.println();
		for(int i =0; i<books_scores.length; i++)	
			System.out.println(books_scores[i]);

		Library l = libraries.get(0);
		List<Integer> u = l.getBookIDs();
		System.out.println("======");

		System.out.println(u);
		/*System.out.println("\n");
		for (Integer i: u
			 ) {
			System.out.println(i);
		}*/

		Library l2;
		for(int i=1; i<=num_days; i++)
		{
			System.out.println("\n\n");

			System.out.println("We are in day "+i+"  ...");

			if(i>= firstAvailableDay && !libraries.isEmpty())
			{
				System.out.println("available!");

				l2 = main.bestLibrary(num_days, i, new ArrayList<>(libraries.values()), bookSent);
				System.out.println("________________");

				System.out.println("BEST");
				System.out.println(l2.toString());
				System.out.println("________________");
				firstAvailableDay += l2.getDaysSignUp();
				libOrder.add(l2.getId());
				bookSent.addAll(l2.getBookIDs());
				libSol.add(l2);
				libraries.remove(l2.id);
				System.out.println("-----\nSTATUS HASHMAP after removing:\n");
				System.out.println(libraries.toString());
				System.out.println("----------------------------------------");
			}
		}
		
		
	//	System.out.println(libOrder.);
		System.out.println("\n__________________\nFILE WRITING");
		File file = new File("output.txt");
		FileWriter fr = new FileWriter(file, true);
		BufferedWriter br = new BufferedWriter(fr);
		PrintWriter pr = new PrintWriter(br);
		
		pr.println(libSol.size());
		
		int cicle = libSol.size();

		for (int i = 0 ; i<cicle; i++)
		{
			
			System.out.println("=====\n Writing in file "+libSol.get(i));
			Library lib = libSol.get(i);
			String s = lib.id + " "+lib.passedBooks;
			pr.println(s);
			
			String tmp = new String();
			for (int prova : lib.bookIDs)
				tmp += prova+" ";
			pr.println(tmp);

		}
		
		
		pr.close();
		br.close();
		fr.close();

	
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
					String[] values = line.split(" ");
					books_scores = new int[values.length];
					for(int i =0; i<values.length; i++)
						books_scores[i]=Integer.parseInt(values[i]);
				}
				else
				{
					String[] values = line.split(" ");
					line=br.readLine();
					for(int j = 0 ; j < values.length; j++)
						System.out.println(values[j]);
					String[] values2 = line.split(" ");

					int in0 = Integer.parseInt(values[0]) ;
					int in1 = Integer.parseInt(values[1]);
					int in2 = Integer.parseInt(values[2]);
					ArrayList<Integer> in3 = new ArrayList<Integer>();
					for(int j = 0 ; j < values2.length; j++)
						in3.add(Integer.parseInt(values2[j]));
					Library l = new Library(hashvalues,in0,in1,in2,in3);
					libraries.put(hashvalues++, l);
					l.sort(books_scores);
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

	private static Library bestLibrary(int dayTot, int startDay, List<Library> all_library, List<Integer> toRemove)
	{
		 Library bestL = null;
		if(!all_library.isEmpty())
			bestL = all_library.get(0);
		
		System.out.println("TOTAL this day");
		for (int i = 0 ;  i < all_library.size() ; i++)
			System.out.println(all_library.get(i).toString());
		int bestScore = 0;
		int scoreTotale = 0;
		
		for (Library library : all_library)
		{
			library.deleteBooks(toRemove);
			scoreTotale = 0;

			/* prende  tutti gli id dei libri della libreria che si suppone che abbiamo gia ordinato */
			List<Integer> all_books_ids_of_library = library.getBookIDs();


			/*ciclo tutti i libri della libri che possiamo mandare */
			/* i dev'essere minore del numero massimo dei libri che fdacenbdo signup possiamo mandare */
			int x;
			

			if(library.getnBooks() < (dayTot - (library.getDaysSignUp() + startDay)) * library.getbPerDay())
			{
				x = library.getnBooks();
				System.out.println("Books (ALL) that i'm going to scan "+ x);

			}
			else
			{
				x = (dayTot - (library.getDaysSignUp() + startDay)) * library.getbPerDay();
				System.out.println("Books (NOT ALL) that i'm going to scan "+ x);

			}
			for (int i=0; i<x; i++)
			{
				/*totale score */
				System.out.println("Libro [Score:"+books_scores[all_books_ids_of_library.get(i)]+"; ID:"+ all_books_ids_of_library.get(i)+ " ]");
				scoreTotale += books_scores[all_books_ids_of_library.get(i)];
			}

			if(scoreTotale > bestScore)
			{
				bestScore=scoreTotale;
				library.setPassedBooks(x);
				bestL = library;
			}
		}
		return bestL;
	}

}


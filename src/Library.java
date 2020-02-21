import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Library
{
    int  id,nBooks, daysSignUp, bPerDay,passedBooks;
    List<Integer> bookIDs;


    public Library(int id, int nBooks, int daysSignUp, int bPerDay, List<Integer> bookIDs) {
        this.id = id;
        this.nBooks = nBooks;
        this.daysSignUp = daysSignUp;
        this.bPerDay = bPerDay;
        this.bookIDs = bookIDs;
    }

    public String toString()
    {
    	String s = "[Lib id: "+id+", nbooks: "+nBooks+", day for Signup: "+daysSignUp+", book x Day: " +bPerDay+", bookIDs"+bookIDs+ " ]"; 
    	return s;
    }
    public void sort(int[] vector)
    {
        bookIDs.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer t1, Integer t2) {
                int result;
                result = vector[t2] - vector[t1];
                return result;
            }
        });
    }

    public void deleteBooks(List<Integer> books)
    {
        bookIDs.removeAll(books);
        nBooks = bookIDs.size();
    }

    public int getnBooks() {
        return nBooks;
    }

    public int getDaysSignUp() {
        return daysSignUp;
    }

    public int getbPerDay() {
        return bPerDay;
    }

    public List<Integer> getBookIDs() {
        return bookIDs;
    }

    public void setPassedBooks(int passedBooks) {
        this.passedBooks = passedBooks;
    }

    public int getId() {
        return id;
    }

    public int getPassedBooks() {
        return passedBooks;
    }
}

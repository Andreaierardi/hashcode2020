import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Library
{
    int  nBooks, daysSignUp, bPerDay,passedBooks;
    List<Integer> bookIDs;


    public Library(int id, int nBooks, int daysSignup, int bPerDay, List<Integer> bookIDs) {
        this.nBooks = nBooks;
        this.daysSignUp = daysSignUp;
        this.bPerDay = bPerDay;
        this.bookIDs = bookIDs;
    }

    public void sort(int[] hash)
    {
        bookIDs.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer t1, Integer t2) {
                int result;
                result = hash[t2] - hash[t1];
                return result;
            }
        });
    }

    public void deleteBooks(List<Integer> books)
    {
        bookIDs.removeAll(books);
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
}

import dao.MySqlQuotesDAO;

import java.util.List;

public class QuotesTest {
    
    public static void main(String[] args) {
        MySqlQuotesDAO quotesDAO = new MySqlQuotesDAO();
        List<String> quotesFromDb = quotesDAO.getQuotes();
        for (String quote : quotesFromDb) {
            System.out.println(quote);
        }
    }


}

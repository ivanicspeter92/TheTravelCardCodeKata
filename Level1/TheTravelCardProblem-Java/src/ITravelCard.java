
import java.util.Date;

/**
 *  Interface for the TravelCard class. Defines the method headers of the travel cards used for participating in the public transportation.
 * 
 * @author Péter Ivanics
 * @date 31.01.2016.
 */
public interface ITravelCard 
{
    /**
     * Purchases a ticket for the travel card. Prefers to use valid seasonal ticket to value ticket.
     * 
     * @return True, if the purchase was successful; false otherwise. 
     * 
     * @author Péter Ivanics
     * @date 31.01.2016.
     */
    public boolean buyTicket();
    
    /**
     * Buys seasonal ticket for the travel card for the given number of days from today.
     * 
     * @param forDays The number of seasonal ticket days to be purchased. 
     * 
     * @author Péter Ivanics
     * @date 31.01.2016.
     */
    public void buySeasonalTicketForDays(int forDays);
    
    /**
     * Buys seasonal ticket for the travel card from the given amount of money.
     * 
     * @param forAmount The amount from which seasonal days should be purchased.
     * 
     * @author Péter Ivanics
     * @date 31.01.2016.
     */
    public void buySeasonalTicketForAmount(double forAmount);
    
    /**
     * Gets the date on which the next seasonal ticket expires.
     * 
     * @return The expiry date of the current season or the date when the last period was expired.
     * 
     * @author Péter Ivanics
     * @date 31.01.2016.
     */
    public Date getSeasonalExpiryDate();
    
    /**
     * Extends the card's balance with the given amount of money.
     * 
     * @param withAmount The amount of money to be put on the card.
     * 
     * @author Péter Ivanics
     * @date 31.01.2016.
     */
    public void extendBalance(double withAmount);
    
    /**
     * Gets the balance for value tickets on the travel card.
     * 
     * @return The balance on the card.
     * 
     * @author Péter Ivanics
     * @date 31.01.2016.
     */
    public double getBalance();
    
    /**
     * Sets the value of the discount for this card. Does nothing if the value of the provided discount is invalid.
     *  
     * @param discountValue The new value of the discount percentage. Should be between [1.0 and 0.0]. 
     * 
     * @author Péter Ivanics
     * @date 31.01.2016.
     */
    public void setDiscount(double discountValue);
}

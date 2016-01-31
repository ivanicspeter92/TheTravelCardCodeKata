
import java.util.Calendar;
import java.util.Date;

/**
 * A travel card class modeling the behavior of public transport travel cards.
 * 
 * @author Péter Ivanics
 * @date 31.01.2016.
 */
public class TravelCard implements ITravelCard 
{
    /// The current balance of the card.
    private double balance = 0;
    /// The basic price of the value ticket.
    private double valueTicketBasicPrice = 2.0;
    /// The amount of discount for this card. Should be between [1.0, 0.0]
    private double discount = 0.0;
    /// The last expiry as a Date of the seasonal ticket.
    private Date nextOrLastExpiry = new Date(0, 0, 0);
   
    /**
     * Purchases a ticket for the travel card. Prefers to use valid seasonal ticket to value ticket.
     * 
     * @return True, if the purchase was successful; false otherwise. 
     * 
     * @author Péter Ivanics
     * @date 31.01.2016.
     */
    @Override
    public boolean buyTicket() 
    {
        if (this.hasValidSeasonalTicket())
                return true;
  
        if (this.balance >= this.getActualTicketPrice())
        {
            this.balance -= this.getActualTicketPrice();
            return true;
        }
        else
            return false;
    }
    
     /**
     * Buys seasonal ticket for the travel card for the given number of days from today.
     * 
     * @param forDays The number of seasonal ticket days to be purchased. 
     * 
     * @author Péter Ivanics
     * @date 31.01.2016.
     */
    @Override
    public void buySeasonalTicketForDays(int forDays) 
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, forDays);
        
        this.nextOrLastExpiry = cal.getTime();
    }

    /**
     * Buys seasonal ticket for the travel card from the given amount of money.
     * 
     * @param forAmount The amount from which seasonal days should be purchased.
     * 
     * @author Péter Ivanics
     * @date 31.01.2016.
     */
    @Override
    public void buySeasonalTicketForAmount(double forAmount) 
    {
        this.buySeasonalTicketForDays((int) (forAmount / this.getActualTicketPrice()));
    }
    
       /**
     * Gets the date on which the next seasonal ticket expires.
     * 
     * @return The expiry date of the current season or the date when the last period was expired.
     * 
     * @author Péter Ivanics
     * @date 31.01.2016.
     */
    @Override
    public Date getSeasonalExpiryDate() 
    {
        return this.nextOrLastExpiry;
    }

    /**
     * Extends the card's balance with the given amount of money.
     * 
     * @param withAmount The amount of money to be put on the card.
     * 
     * @author Péter Ivanics
     * @date 31.01.2016.
     */
    @Override
    public void extendBalance(double withAmount) 
    {
        this.balance += withAmount;
    }
    
   /**
     * Gets the balance for value tickets on the travel card.
     * 
     * @return The balance on the card.
     * 
     * @author Péter Ivanics
     * @date 31.01.2016.
     */
    @Override
    public double getBalance() 
    {
        return this.balance;
    }

   /**
     * Sets the value of the discount for this card. Does nothing if the value of the provided discount is invalid.
     *  
     * @param discountValue The new value of the discount percentage. Should be between [1.0 and 0.0]. 
     * 
     * @author Péter Ivanics
     * @date 31.01.2016.
     */
    @Override
    public void setDiscount(double discountValue) 
    {
        if (discountValue >= 0.0 && discountValue <= 1.0)
            this.discount = discountValue;
    }
    
    /***** Private methods *******/
    /**
     * Gets the actual price of the value ticket.
     * 
     * @return The price of the value ticket for this card, paying attention to the specified discount.
     * 
     * @author: Péter Ivanics
     * @date 30.01.2016.
     */
    private double getActualTicketPrice()
    {
        return this.valueTicketBasicPrice * (1 - this.discount);
    }
    
    /**
     * Tells if the travel card is charged with seasonal ticket.
     * 
     * @return True, if the travel card has valid seasonal ticket; false otherwise.
     * 
     * @author Péter Ivanics
     * @date 30.01.2016.
     */
    private boolean hasValidSeasonalTicket()
    {
        return this.nextOrLastExpiry.after(new Date());
    }
}

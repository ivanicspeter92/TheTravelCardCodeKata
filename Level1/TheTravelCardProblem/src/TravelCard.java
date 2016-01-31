
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
    private Date nextOrLastExpiry = new Date(0, 0, 0);
   
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

    @Override
    public Date getSeasonalExpiryDate() 
    {
        return this.nextOrLastExpiry;
    }

    @Override
    public double getBalance() 
    {
        return this.balance;
    }

    @Override
    public void buySeasonalTicketForDays(int forDays) 
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, forDays);
        
        this.nextOrLastExpiry = cal.getTime();
    }

    @Override
    public void buySeasonalTicketForAmount(double forAmount) 
    {
        this.buySeasonalTicketForDays((int) (forAmount / this.getActualTicketPrice()));
    }

    @Override
    public void extendBalance(double withAmount) 
    {
        this.balance += withAmount;
    }

    @Override
    public void setDiscount(double discountValue) 
    {
        if (discountValue >= 0.0 && discountValue <= 1.0)
            this.discount = discountValue;
    }
    
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

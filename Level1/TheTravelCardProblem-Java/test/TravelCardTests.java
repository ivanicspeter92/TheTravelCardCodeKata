import java.util.Calendar;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the TravelCard class.
 * 
 * @author Péter Ivanics
 * @date 31.01.2016.
 */
public class TravelCardTests 
{
    /// The test TravelCard object to be used for testing.
    ITravelCard testCard;
    
    @Before
    public void setUp() 
    {
        this.testCard = new TravelCard();
    }
    
    /**
     * Test case for buying ticket on a fresh (empty) card.
     * 
     * @author Péter Ivanics
     * @date 30.01.2016.
     */
    @Test
    public void testBuyTicketOnEmptyCard()
    {
        assertFalse(this.testCard.buyTicket());
    }
    
    /**
     * Test case for extending the balance on the travel card.
     * 
     * @author Péter Ivanics
     * @date 30.01.2016.
     */
    @Test
    public void testExtendBalance()
    {
        assertEquals(this.testCard.getBalance(), 0, 0);
        this.testCard.extendBalance(10);
        assertEquals(this.testCard.getBalance(), 10, 0);
    }
    
    /**
     * Tests case for charging the card for two value tickets and the buying multiple value tickets using the card.
     * 
     * @author Péter Ivanics
     * @date 30.01.2016.
     */
    @Test
    public void testBuyValueTickets()
    {
        assertEquals(this.testCard.getBalance(), 0, 0);
        this.testCard.extendBalance(4); // putting money for two value tickets
        assertEquals(this.testCard.getBalance(), 4, 0);
        assertEquals(this.testCard.buyTicket(), true); // buying the first ticket
        assertEquals(this.testCard.getBalance(), 2, 0);
        assertEquals(this.testCard.buyTicket(), true); // buying the second ticket
        assertEquals(this.testCard.getBalance(), 0, 0);
        assertEquals(this.testCard.buyTicket(), false);
        assertEquals(this.testCard.getBalance(), 0, 0);
    }
    
    /**
     * Tests case for charging the card for two value tickets and the buying multiple value tickets using the card.
     * 
     * @author Péter Ivanics
     * @date 30.01.2016.
     */
    @Test
    public void testBuyFiftyPercentValueTickets()
    {
        this.testCard.setDiscount(0.5); // setting the discount to 50%
        assertEquals(this.testCard.getBalance(), 0, 0);
        this.testCard.extendBalance(2); // putting money for two value tickets on 50% discount
        assertEquals(this.testCard.getBalance(), 2, 0);
        assertEquals(this.testCard.buyTicket(), true); // buying the first ticket
        assertEquals(this.testCard.getBalance(), 1, 0);
        assertEquals(this.testCard.buyTicket(), true); // buying the second ticket
        assertEquals(this.testCard.getBalance(), 0, 0);
        assertEquals(this.testCard.buyTicket(), false);
        assertEquals(this.testCard.getBalance(), 0, 0);
    }
    
    /**
     * Test case for purchasing seasonal ticket for specified days.
     * 
     * @author Péter Ivanics
     * @date 30.01.2016.
     */
    @Test
    public void testBuySeasonalTicketForDays()
    {
        assertNotSame(new Date(), this.testCard.getSeasonalExpiryDate());
        assertTrue(this.testCard.getSeasonalExpiryDate().before(new Date())); // tests if the default expiry is set to somewhere in the past - the card must be expired
        this.testCard.buySeasonalTicketForDays(3); // buying seasonal ticket for 3 days from today
        assertTrue(this.testCard.getSeasonalExpiryDate().after(new Date())); // tests if the expiry is in the future
        
        // testing if the expiry is 3 days from today
        Date threeDaysFromToday = this.getDateWithOffsetFromToday(3);
        assertEquals(threeDaysFromToday, this.testCard.getSeasonalExpiryDate()); 
        
        assertEquals(0, this.testCard.getBalance(), 0); // no balance was charged yet
        assertTrue(this.testCard.buyTicket()); // should return true as the card has valid seasonal ticket
        assertEquals(0, this.testCard.getBalance(), 0); // the balance should not change because seasonal ticket was purchased
    }
    
    /**
     * Test case for purchasing seasonal ticket for specified amount of money.
     * 
     * @author Péter Ivanics
     * @date 30.01.2016.
     */
    @Test
    public void testBuySeasonalTicketForAmountOfMoney()
    {
        assertNotSame(new Date(), this.testCard.getSeasonalExpiryDate());
        assertTrue(this.testCard.getSeasonalExpiryDate().before(new Date())); // tests if the default expiry is set to somewhere in the past - the card must be expired
        this.testCard.buySeasonalTicketForAmount(50.0); // buying seasonal ticket for 50 eur from today
        assertTrue(this.testCard.getSeasonalExpiryDate().after(new Date())); // tests if the expiry is in the future
        
        // testing if the expiry is 25 days from today
        Date twentyFiveDaysFromToday = this.getDateWithOffsetFromToday(25);
        assertEquals(twentyFiveDaysFromToday, this.testCard.getSeasonalExpiryDate()); 
        
        assertEquals(0, this.testCard.getBalance(), 0); // no balance was charged yet
        assertTrue(this.testCard.buyTicket()); // should return true as the card has valid seasonal ticket
        assertEquals(0, this.testCard.getBalance(), 0); // the balance should not change because seasonal ticket was purchased
    }
    
    /**
     * Calculates a date with the given offset from today's date.
     * 
     * @param offset The number of days to be added to/substracted from today's date.
     * @return The date with the provided offset from today.
     * 
     * @author Peter Ivanics
     * @date 30.01.2016.
     */
    private Date getDateWithOffsetFromToday(int offset)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, offset);
        
        return cal.getTime();
    }
}
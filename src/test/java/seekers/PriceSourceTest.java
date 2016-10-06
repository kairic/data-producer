package seekers;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.CoreMatchers.*;

public class PriceSourceTest {

    private PriceSource priceSourceToTest = new PriceSource();

    @Test
    public void generatedDataNotNull() {
        Assert.assertThat(priceSourceToTest.generatePriceWithTimestamp(), is(notNullValue()));
    }

    @Test
    public void checkPriceAndTimestampSeparator() {
        Assert.assertThat(priceSourceToTest.generatePriceWithTimestamp(), is(containsString("@")));
    }

    @Test
    public void validatePrice() {
        String value = priceSourceToTest.generatePriceWithTimestamp();
        String[] parts = value.split("@");
        Assert.assertThat(Integer.parseInt(parts[0]), Matchers.lessThan(PriceSource.MAX_PRICE));
    }

    @Test
    public void validateTimestampFormat() {
        String value = priceSourceToTest.generatePriceWithTimestamp();
        String[] parts = value.split("@");
        System.out.println(parts[1]);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        LocalDateTime dateTime = LocalDateTime.parse(parts[1], formatter);
        Assert.assertThat(dateTime, is(notNullValue()));
    }

}

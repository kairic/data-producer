package seekers;

import java.util.Random;
import java.time.LocalDateTime;
import java.time.Clock;
import org.springframework.cloud.stream.messaging.Source;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.InboundChannelAdapter;

@EnableBinding(Source.class)
public class PriceSource {

	public static final int MAX_PRICE = 100;

	private Random randomGenerator = new Random();

	@InboundChannelAdapter(value = Source.OUTPUT)
	public String generatePriceWithTimestamp() {
		int price = randomGenerator.nextInt(MAX_PRICE);
		return price + "@" + LocalDateTime.now(Clock.systemUTC()).toString();
	}

}

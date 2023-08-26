package com.retail.acme.online.basket;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import lombok.extern.slf4j.Slf4j;
import java.math.BigDecimal;
import java.util.Random;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	@Autowired
	private StreamBridge streamBridge;

	@Scheduled(fixedRate = 10000)
	public void scheduledBasketEvent() {
		Basket basketTobePosted = createBasketModel();
		log.info("Posting Basket event :{}", basketTobePosted);
		sendRetailAcmeOnlineBasketVerbV1Id(basketTobePosted, "CREATED", basketTobePosted.getId());
		if (basketTobePosted.getQuantity() > 5 || basketTobePosted.getQuantity() == 0) {
			log.info("Posting RiskyBasket event :{}", basketTobePosted);
			sendRetailAcmeOnlineRiskyBasketVerbV1Id(basketTobePosted, "CREATED", basketTobePosted.getId());
		}
	}

	private Basket createBasketModel() {
		Basket basket = new Basket();
		Random random = new Random();
		basket.setId(Integer.toString(random.nextInt(99999)));
		basket.setPrice(new BigDecimal(20));
		basket.setProduct("Jumper");
		basket.setQuantity(random.nextInt(20));
		basket.setState("PAID");
		basket.setCustomerId(random.nextInt(99999));
		return basket;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}


	public void sendRetailAcmeOnlineBasketVerbV1Id(
			Basket payload, String verb, String id
	) {
		String topic = String.format("retail/acme/online/basket/%s/v1/%s",
				verb, id);
		streamBridge.send(topic, payload);
	}

	public void sendRetailAcmeOnlineRiskyBasketVerbV1Id(Basket payload, String verb, String basketId) {
		String topic = String.format("retail/acme/online/riskyBasket/%s/v1/%s", verb, basketId);
		streamBridge.send(topic, payload);
	}

}

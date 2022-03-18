package com.bk.consumer;

import com.bk.message.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
public class OrderConsumer {

    @Bean
    public Consumer<KStream<String, Order>> orderService() {
        return kstream -> kstream.foreach((key, order) -> log.info("Order consumed {} @ active={}", order.getId(), order.getActive()));
    }

}

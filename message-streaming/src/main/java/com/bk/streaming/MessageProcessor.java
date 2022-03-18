package com.bk.streaming;

import com.bk.message.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Slf4j
@Component
public class MessageProcessor {

    @Bean
    public Function<KStream<String, Order>, KStream<String, Order>> orderProcessor() {
        return kstream -> kstream.filter((key, order) -> {
            if (order.getActive()) {
                log.info("Active order: {}", order.getId());
            } else {
                log.warn("Inactive order: {}", order.getId());
            }

            return order.getActive();
        });
    }

}

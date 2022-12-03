package org.tlc.domain.base.kafka;

public interface KafkaConsumer<E> {

    public void consume (E obj);

}

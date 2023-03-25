package br.com.mksoftware.control.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendMessageMQ {
	
	@Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;
    
    
    public void send(String order) {
        rabbitTemplate.convertAndSend(this.queue.getName(), order);
    }

}

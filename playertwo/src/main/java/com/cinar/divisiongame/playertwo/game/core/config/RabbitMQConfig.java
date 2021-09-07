package com.cinar.divisiongame.playertwo.game.core.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.r2dbc.ConnectionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  @Bean
  DirectExchange deadLetterExchange() {
    return new DirectExchange("deadLetterExchange");
  }

  @Bean
  DirectExchange exchange() {
    return new DirectExchange("playerTwoExchange");
  }

  @Bean
  Queue dlq() {
    return QueueBuilder.durable("deadLetter.queue").build();
  }

  @Bean
  Queue queue() {
    return QueueBuilder
        .durable("playertwogame.queue")
        .withArgument("x-dead-letter-exchange", "deadLetterExchange")
        .withArgument("x-dead-letter-routing-key", "deadLetter")
        .build();
  }

  @Bean
  Binding DLQBinding() {
    return BindingBuilder.bind(dlq())
        .to(deadLetterExchange())
        .with("deadLetter");
  }

  @Bean
  Binding binding() {
    return BindingBuilder.bind(queue())
        .to(exchange())
        .with("playertwogame");
  }


  @Bean
  SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueues(queue());
    return container;
  }

  @Bean
  public MessageConverter jsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }


  public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(jsonMessageConverter());
    return rabbitTemplate;
  }

}

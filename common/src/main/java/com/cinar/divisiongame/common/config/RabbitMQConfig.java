package com.cinar.divisiongame.common.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  @Bean
  DirectExchange deadLetterExchange() {
    return new DirectExchange("deadLetterExchange");
  }

  @Bean
  DirectExchange playerOneExchange() {
    return new DirectExchange("playerOneExchange");
  }

  @Bean
  DirectExchange playerTwoExchange() {
    return new DirectExchange("playerTwoExchange");
  }


  @Bean
  Queue dlq() {
    return QueueBuilder.durable("deadLetter.queue").build();
  }


  @Bean
  Queue playerTwoQueue() {
    return QueueBuilder
        .durable("playertwogame.queue")
        .withArgument("x-dead-letter-exchange", "deadLetterExchange")
        .withArgument("x-dead-letter-routing-key", "deadLetter")
        .build();
  }

  @Bean
  Queue playerOneQueue() {
    return QueueBuilder
        .durable("playeronegame.queue")
        .withArgument("x-dead-letter-exchange", "deadLetterExchange")
        .withArgument("x-dead-letter-routing-key", "deadLetter")
        .build();
  }

  @Bean
  Binding playerTwoBinding() {
    return BindingBuilder.bind(playerTwoQueue())
        .to(playerTwoExchange())
        .with("playertwogame");
  }


  @Bean
  Binding DLQBinding() {
    return BindingBuilder.bind(dlq())
        .to(deadLetterExchange())
        .with("deadLetter");
  }

  @Bean
  Binding playerOneBinding() {
    return BindingBuilder.bind(playerOneQueue())
        .to(playerOneExchange())
        .with("playeronegame");
  }

  @Bean
  SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueues(playerOneQueue(), playerTwoQueue());
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


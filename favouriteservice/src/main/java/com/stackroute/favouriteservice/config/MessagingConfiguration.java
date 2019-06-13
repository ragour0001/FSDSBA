package com.stackroute.favouriteservice.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfiguration {

  private String exchangeName = "user_exchange";
  private String registerQueue = "user_queue";
  private String articleAddQueue = "article_add_queue";
  private String articleDeleteQueue = "article_delete_queue";

  @Bean
  DirectExchange directExchange() {

    return new DirectExchange(exchangeName);
  }

  @Bean
  Queue queueRegister() {

    return new Queue(registerQueue, false);
  }

  @Bean
  Queue queueAddArticle() {

    return new Queue(articleAddQueue, false);
  }

  @Bean
  Queue queueDeleteArticle() {

    return new Queue(articleDeleteQueue, false);
  }

  @Bean
  Binding bindingUser(Queue queueRegister , DirectExchange directExchange) {

    return BindingBuilder.bind(queueRegister()).to(directExchange).with("user_routing");

  }

  @Bean
  Binding bindingAddArticle(Queue queueAddArticle, DirectExchange exchange) {
    return BindingBuilder.bind(queueAddArticle()).to(exchange).with("article_add_routing");

  }


  @Bean
  Binding bindingDeleteArticle(Queue queueDeleteArticle, DirectExchange exchange) {
    return BindingBuilder.bind(queueDeleteArticle()).to(exchange).with("article_delete_routing");

  }

  @Bean
  public Jackson2JsonMessageConverter producerJackson2JsonMessageConverter() {

    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {

    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(producerJackson2JsonMessageConverter());
    return rabbitTemplate;
  }
}

package com.stackroute.userservice.config;



import com.stackroute.rabbitmq.domain.UserDTO;
import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.service.UserServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

  @Autowired
  private UserServiceImpl userService;

  @RabbitListener(queues = "user_queue")
  public void getUserDtoFromRabbitMq(UserDTO userDTO) {
    User user = new User();

    user.setUserName(userDTO.getUserName());
    user.setPassword(userDTO.getPassword());

    userService.saveUser(user);

  }
}

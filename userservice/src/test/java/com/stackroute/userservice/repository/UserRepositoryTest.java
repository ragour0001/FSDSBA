package com.stackroute.userservice.repository;



import com.stackroute.userservice.domain.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;


    @Before
    public void setUp() {
        user = new User();
        user.setUserName("John123");
        user.setPassword("John123");
    }

    @After
    public void tearDown() {

        userRepository.deleteAll();

        user = null;
    }

    @Test
    public void testSaveUserSuccess() {
        userRepository.save(user);
        User userObj = userRepository.findById(user.getUserId()).get();
        Assert.assertEquals(user.getUserName(), userObj.getUserName());
        userRepository.delete(user);

    }// userRepository.save(user);

    @Test
    public void testUserLoginSuccess() {
        userRepository.save(user);
        User userObj = userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
        Assert.assertEquals(user.getUserName(), userObj.getUserName());
        userRepository.delete(user);

    }

}

package com.gamenism;

import com.gamenism.dao.ActiveRecord;
import com.gamenism.model.User;
import org.junit.Assert;
import org.junit.Test;

/**
 * User: halil
 * Date: 9/28/13
 * Time: 10:17 PM
 */
public class UserTest extends AbstractTest {

    @Test
    public void createUser() {
        ActiveRecord activeRecord = new ActiveRecord();

        User user = new User();
        user.setEmail("t-halilkarakose@gmail.com");
        user.setPassword("1111");
        activeRecord.persist(user);
    }

    @Test
    public void findUser() {
        ActiveRecord<User> activeRecord = new ActiveRecord<User>(User.class);
        String emailAddress = "t-halilkarakose@gmail.com";
        createUser(activeRecord, emailAddress);

        User findUserByEmail = activeRecord.getSingleResultFromNamedQuery("findUserByEmail", emailAddress);

        Assert.assertEquals(emailAddress, findUserByEmail.getEmail());
    }

    private void createUser(ActiveRecord<User> activeRecord, String emailAddress) {
        User user = new User();
        user.setEmail(emailAddress);
        user.setPassword("1111");
        activeRecord.persist(user);
    }
}

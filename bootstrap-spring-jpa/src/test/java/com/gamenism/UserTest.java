package com.gamenism;

import com.gamenism.dao.ActiveRecord;
import com.gamenism.model.User;
import com.gamenism.model.UserGroup;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * User: halil
 * Date: 9/28/13
 * Time: 10:17 PM
 */
public class UserTest extends AbstractTest {
    ActiveRecord activeRecord;

    @Test
    public void createUser() {
        ActiveRecord activeRecord = new ActiveRecord();

        User user = getUser(null);

        UserGroup userGroup = UserGroupTest.getUserGroup();
        activeRecord.persist(userGroup);

        user.setUserGroup(userGroup);
        activeRecord.persist(user);
    }

    @Test
    public void createTwoUsers() {
        activeRecord = new ActiveRecord();

        UserGroup userGroup = UserGroupTest.getUserGroup();
        activeRecord.persist(userGroup);

        User user = getUser("t-halilkarakose@gmail.com");
        user.setUserGroup(userGroup);
        activeRecord.persist(user);

        User user2 = getUser("t-halilkarakose@hotmail.com");
        user2.setUserGroup(userGroup);
        activeRecord.persist(user2);

        activeRecord.clear();

        UserGroup group = (UserGroup) activeRecord.find(UserGroup.class, userGroup.getId());
        Assert.assertEquals(2, group.getUsers().size());
    }

    private User getUser(String email) {
        User user = new User();
        user.setEmail(email);
        user.setPassword("1111");
        return user;
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

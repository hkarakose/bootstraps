package com.gamenism;

import com.gamenism.dao.ActiveRecord;
import com.gamenism.model.UserGroup;
import com.gamenism.model.UserGroupRole;
import org.junit.Assert;
import org.junit.Test;

/**
 * User: halil
 * Date: 10/14/13
 * Time: 2:47 PM
 */
public class UserGroupTest extends AbstractTest {
    ActiveRecord<UserGroup> activeRecord;

    @Test
    public void create() {
        activeRecord = new ActiveRecord<UserGroup>(UserGroup.class);

        UserGroup group = getUserGroup();
        activeRecord.persist(group);

        activeRecord.clear();

        UserGroup userGroup = activeRecord.find(group.getId());
        System.out.println("userGroup = " + userGroup);
        Assert.assertEquals(userGroup.getGroupName(), group.getGroupName());
    }

    @Test
    public void createWithOneRole() {
        ActiveRecord<UserGroup> activeRecord = new ActiveRecord<UserGroup>(UserGroup.class);
        UserGroupRole role = UserGroupRoleTest.getRole("ROLE_ADMIN");
        activeRecord.persist(role);

        UserGroup group = getUserGroup();
        group.addRole(role);
        activeRecord.persist(group);
        activeRecord.flush();

        activeRecord.clear();
        UserGroup userGroup = activeRecord.find(group.getId());
        System.out.println("userGroup = " + userGroup);
        Assert.assertEquals(userGroup.getGroupName(), group.getGroupName());
        Assert.assertEquals(group.getRoles().size(), userGroup.getRoles().size());
    }

    @Test
    public void createWithTwoRoles() {
        ActiveRecord<UserGroup> activeRecord = new ActiveRecord<UserGroup>(UserGroup.class);
        UserGroupRole role = UserGroupRoleTest.getRole("ROLE_USER");
        activeRecord.persist(role);

        UserGroupRole role2 = UserGroupRoleTest.getRole("ROLE_ADMIN");
        activeRecord.persist(role2);

        UserGroup group = getUserGroup();
        group.addRole(role);
        group.addRole(role2);
        activeRecord.persist(group);
        activeRecord.flush();

        activeRecord.clear();
        UserGroup userGroup = activeRecord.find(group.getId());
        System.out.println("userGroup = " + userGroup);
        Assert.assertEquals(userGroup.getGroupName(), group.getGroupName());
        Assert.assertEquals(group.getRoles().size(), userGroup.getRoles().size());
    }

    public static UserGroup getUserGroup() {
        UserGroup group = new UserGroup();
        group.setGroupName("junit-test-group");
        group.setGroupDescription("admin group");
        return group;
    }
}

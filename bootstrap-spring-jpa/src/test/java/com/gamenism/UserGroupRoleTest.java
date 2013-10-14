package com.gamenism;

import com.gamenism.dao.ActiveRecord;
import com.gamenism.model.UserGroup;
import com.gamenism.model.UserGroupRole;
import org.junit.Assert;
import org.junit.Test;

/**
 * User: halil
 * Date: 10/14/13
 * Time: 2:37 PM
 */
public class UserGroupRoleTest extends AbstractTest {
    ActiveRecord<UserGroupRole> activeRecord;

    public UserGroupRoleTest() {
        activeRecord = new ActiveRecord<UserGroupRole>(UserGroupRole.class);
    }

    @Test
    public void createRole() {
        UserGroupRole role = getRole("ROLE_ADMIN");

        activeRecord.persist(role);

        activeRecord.clear();

        UserGroupRole userGroupRole = activeRecord.find(role.getId());

        Assert.assertEquals(userGroupRole.getName(), role.getName());
    }

    @Test(expected = org.springframework.orm.jpa.JpaSystemException.class)
    public void createDuplicateRole() {
        activeRecord = new ActiveRecord<UserGroupRole>(UserGroupRole.class);
        UserGroupRole role = UserGroupRoleTest.getRole("ROLE_USER");
        activeRecord.persist(role);

        UserGroupRole role2 = UserGroupRoleTest.getRole("ROLE_USER");
        activeRecord.persist(role2);

    }

    public static UserGroupRole getRole(String roleName) {
        UserGroupRole role = new UserGroupRole();
        role.setName(roleName);
        role.setDescription("Basic role");
        return role;
    }
}

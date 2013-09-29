package com.gamenism;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.PersistenceContext;

/**
 * AbstractTransactionalJUnit4SpringContextTests provides
 * dependency injection and transaction management mechanism in JUnit tests.
 *
 * <br /><br />
 * User: halil
 * Date: 9/18/13
 * Time: 11:43 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/META-INF/spring/applicationContext.xml"})
public abstract class AbstractTest extends AbstractTransactionalJUnit4SpringContextTests {
    @PersistenceContext
    protected javax.persistence.EntityManager em;

}


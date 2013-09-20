package org.bootstrap;

import org.bootstrap.bean.ActiveRecord;
import org.bootstrap.bean.Dummy;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import javax.validation.ConstraintViolationException;
import java.util.Calendar;
import java.util.List;

public class DummyTest extends AbstractTest{

    @Before
    public void initEntityManager() throws Exception {
    }

    @After
    public void closeEntityManager() throws Exception {
    }

    @Test
    public void testPersist() throws Exception {
        Dummy dummy = create();
        System.out.println("dummy = " + dummy);
        Assert.assertNotNull(dummy.getId());
    }

    @Test
    public void testFindAll() {
        createTwo();
        ActiveRecord activeRecord = new ActiveRecord();
        List<Dummy> dummies = activeRecord.findAll(Dummy.class);
        Assert.assertEquals(2, dummies.size());
    }

    @Test
    public void testFind() {
        Dummy dummy = create();
        Long id = dummy.getId();
        long version = dummy.getVersion();
        dummy.clear();

        ActiveRecord activeRecord = new ActiveRecord();
        Dummy dummy1 = activeRecord.find(Dummy.class, id);
        Assert.assertEquals(version, dummy1.getVersion());
    }

    @Test
    public void testFindById() {
        Dummy dummy = create();
        Long id = dummy.getId();
        long version = dummy.getVersion();
        dummy.clear();

        Dummy dummy1 = new Dummy();
        dummy1 = dummy1.find(Dummy.class, id);
        Assert.assertEquals(version, dummy1.getVersion());
    }

    @Test
    public void testUpdate() {
        Dummy dummy = create();
        dummy.setModificationDate(Calendar.getInstance().getTime());
        dummy.merge();
        long creationDate = dummy.getCreationDate().getTime();
        long modificationDate = dummy.getModificationDate().getTime();
        Assert.assertNotSame(creationDate, modificationDate);
        System.out.println("modificationDate = " + modificationDate);
        System.out.println("creationDate = " + creationDate);
    }

    @Test
    public void testCount() {
        createTwo();
        long count = new Dummy().count();
        Assert.assertEquals(2, count);
    }

    @Test
    public void testFindEntities() {
        //clear db
        deleteAll();

        createSix();
        ActiveRecord activeRecord = new ActiveRecord();
        List<Dummy> entries = activeRecord.findEntries(Dummy.class, 3, 3); //selected 3rd, 4th, 5th
        Assert.assertEquals(3, entries.size());
    }

    private void deleteAll() {
        EntityManager entityManager = Dummy.entityManager();
        Query nativeQuery = entityManager.createNativeQuery("delete from dummy");
        int i = nativeQuery.executeUpdate();
        System.out.println("Deleted record count = " + i);
    }

    private void createSix() {
        createTwo();
        createTwo();
        createTwo();
    }

    private void createTwo() {
        create();
        create();
    }

    private Dummy create() {
        Dummy dummy = new Dummy();
        dummy.persist();
        return dummy;
    }

}
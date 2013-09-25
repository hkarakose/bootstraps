package org.bootstrap.reflection;

import org.junit.Test;

/**
 * User: halil
 * Date: 9/19/13
 * Time: 12:20 PM
 */
public class ClassTest {
    @Test
    public void testSubclass() {
        Subclass subclass = new Subclass();
        subclass.printReflectionInformation();

        Super aSuper = new Super();
        aSuper.printReflectionInformation();
    }


}

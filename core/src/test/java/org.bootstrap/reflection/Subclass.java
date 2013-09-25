package org.bootstrap.reflection;

/**
 * User: halil
 * Date: 9/19/13
 * Time: 12:21 PM
 */
public class Subclass extends Super<Subclass> {
    int subInt;

    public Subclass() {
    }

    public int getSubInt() {
        return subInt;
    }

    public void setSubInt(int subInt) {
        this.subInt = subInt;
    }
}

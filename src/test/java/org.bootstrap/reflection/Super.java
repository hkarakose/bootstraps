package org.bootstrap.reflection;

/**
 * User: halil
 * Date: 9/19/13
 * Time: 12:21 PM
 */
public class Super<T> {
    private int superInt;

    public Super() {
    }

    public int getSuperInt() {
        return superInt;
    }

    public void setSuperInt(int superInt) {
        this.superInt = superInt;
    }

    public void printReflectionInformation() {
        Class<? extends Super> aClass = this.getClass();
        System.out.println("aClass = " + aClass);
        System.out.println("aClass.getName() = " + aClass.getCanonicalName());
        System.out.println("aClass.getDeclaringClass() = " + aClass.getDeclaringClass());
        System.out.println("aClass.getEnclosingClass() = " + aClass.getEnclosingClass());


    }
}

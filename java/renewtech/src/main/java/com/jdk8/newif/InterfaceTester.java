package com.jdk8.newif;

public class InterfaceTester implements IFOne, IFTwo {

    public static void main(String[] args) {
        InterfaceTester tester = new InterfaceTester();
        IFOne.testStatic();
        IFTwo.testStatic();
        tester.testDefault();
    }

    @Override
    public void testDefault() {
        // TODO Auto-generated method stub
        IFOne.super.testDefault();
        IFTwo.super.testDefault();
    }

}

package com.jdk8.newif;

public interface IFTwo {

    default void testDefault() {
        System.out.println("default function test in Interface 2nd");
    }
    
    static void testStatic() {
        System.out.println("static method test in Interface 2nd");
    }
    
}

package com.jdk8.newif;

public interface IFOne {
    
    default void testDefault() {
        System.out.println("default function test in Interface 1st");
    }
    
    static void testStatic() {
        System.out.println("static method test in Interface 1st");
    }

}

package com.jdk8.lambda;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.stream.Stream;

public class CheckedExceptionInLambda {
    
    private final int instanceVar = 10;
    
    public CheckedExceptionInLambda() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                foo();
                System.out.println(instanceVar);
            }
            
        }).start();
    }
    
    public void foo() {
        Stream.of("a", "b").forEach(s -> {
            try {
                new FileInputStream(s).close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        CheckedExceptionInLambda cel = new CheckedExceptionInLambda();
//        cel.foo();
    }

}

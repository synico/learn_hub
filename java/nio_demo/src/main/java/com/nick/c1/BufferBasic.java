package com.nick.c1;

import java.nio.ByteBuffer;

public class BufferBasic {
    
    public void fillBuffer() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byteBuffer.put((byte)'H').put((byte)'e');
        System.out.println(byteBuffer.position());
        byte b1st = byteBuffer.get(1);
        System.out.println("Data in 1st byte: " + (char)b1st);
    }
    
    

    public static void main(String[] args) {
        BufferBasic bb = new BufferBasic();
        bb.fillBuffer();
    }

}

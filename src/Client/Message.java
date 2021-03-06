/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.nio.ByteBuffer;

/**
 *
 * @author Lucas
 */
public class Message {
    
    private byte[] byteArray;
    
    private final byte startByte = (byte) 0x78;
    private byte idByte;
    private byte messageCodeByte;
    private byte payloadLengthByte;
    private byte[] messageBytes;
    
    private final byte[][] actionMessages = new byte[][] {
        new byte[] {},
        new byte[] {
            // hello
            (byte)0x68, (byte)0x65, (byte)0x6c, (byte)0x6c, (byte)0x6f
        }, new byte[] {
            // ok
            (byte)0x6f, (byte)0x6b
        }, new byte[] {
            // yes
            (byte)0x79, (byte)0x65, (byte)0x73
        }, new byte[] {
            // no
            (byte)0x6e, (byte)0x6f
        }
    };
    
    Message(byte idByte, byte[] message, boolean toPack) {
        if (toPack) {
            this.idByte = idByte;
            this.messageCodeByte = (byte) 0x72;
            byte[] deleteMe = intToByteArray(message.length + 4);
            this.payloadLengthByte = (byte) deleteMe[deleteMe.length - 1];
        
            this.messageBytes = message;
            packMessage();
        } else {
            unpackMessage(message);
        }
    }
    
    Message(byte idByte, byte messageCodeByte, byte payloadLengthByte, byte[] messageBytes) {
        this.idByte = idByte;
        this.messageCodeByte = messageCodeByte;
        this.payloadLengthByte = payloadLengthByte;
        this.messageBytes = messageBytes;
        
        this.packMessage();
    }
    
    Message(byte idByte, int action) {
        this.idByte = idByte;
        this.messageCodeByte = (byte) 0x72;
        byte[] deleteMe = intToByteArray(actionMessages[action].length + 4);
        this.payloadLengthByte = (byte) deleteMe[deleteMe.length - 1];
        this.messageBytes = actionMessages[action];
        
        this.packMessage();
    }
    
    public byte[] getByteArray() {
        return this.byteArray;
    }
    
    public byte getMessageCodeByte() {
        return this.messageCodeByte;
    }
    
    public byte[] getMessageBytes() {
        return this.messageBytes;
    }
    
    private void packMessage() {
        byteArray =  new byte[this.messageBytes.length + 4];
        this.byteArray[0] = this.startByte;
        this.byteArray[1] = this.idByte;
        this.byteArray[2] = this.messageCodeByte;
        this.byteArray[3] = this.payloadLengthByte;
        
        for (int i = 0; i < this.messageBytes.length; i++) {
            byteArray[i + 4] = this.messageBytes[i];
        }
        System.out.println("");
        
    }
    
    private void unpackMessage(byte[] message) {
        if (this.isMessageFormat(message)) {
            this.byteArray = message;
            this.idByte = message[1];
            this.messageCodeByte = message[2];
            this.payloadLengthByte = message[3];
            
            
            byte[] remainingBytes = new byte[message.length - 4];;
            
            for (int i = 4; i < message.length; i++) {
                remainingBytes[i - 4] = message[i];
            }
            
            this.messageBytes = remainingBytes;
        } else {
            System.out.println("ERROR: Incorrect Message Format");
        }
    }
    
    private boolean isMessageFormat(byte[] message) {
        
        return true;
    }
    
//    private static byte[] hexStringToByteArray(String s) {
//        int len = s.length();
//        byte[] data = new byte[len / 2];
//        for (int i = 0; i < len; i += 2) {
//            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
//                                 + Character.digit(s.charAt(i+1), 16));
//        }
//        return data;
//    }
    
public static final byte[] intToByteArray(int value) {
    return new byte[] {
            (byte)(value >>> 24),
            (byte)(value >>> 16),
            (byte)(value >>> 8),
            (byte)value};
}
}

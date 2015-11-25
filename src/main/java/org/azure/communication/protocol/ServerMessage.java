package org.azure.communication.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Message sent from the server to the client
 */
public class ServerMessage extends Message {
    private static Logger logger = LoggerFactory.getLogger(ServerMessage.class);

    private boolean finalized;

    /**
     * Creates a new ServerMessage with the given opCode
     *
     * @param opCode instructs the client how to handle the data
     */
    public ServerMessage(short opCode) {
        super(opCode, Unpooled.buffer(6));
        this.data.writeInt(0); // length placeholder
        this.data.writeShort(this.opCode);
    }

    /**
     * Write a boolean (1 byte) value to the buffer
     *
     * @param b the state to write
     */
    public void writeBoolean(boolean b) {
        this.data.writeByte(b ? 1 : 0);
    }

    /**
     * Write n integer (4 bytes) to the buffer
     *
     * @param i
     */
    public void writeInt(int i) {
        this.data.writeInt(i);
    }

    /**
     * Write a short (2 bytes) to the buffer
     *
     * @param i
     */
    public void writeShort(int i) {
        this.data.writeShort(i);
    }

    /**
     * Write a string to the buffer, this is formatted as a short then a byte array.
     *
     * @param s the string
     */
    public void writeString(String s) {
        if (s == null) {
            s = "";
        }

        byte[] dat = s.getBytes();
        this.data.writeShort(dat.length);
        this.data.writeBytes(dat);
    }

    /**
     * Write a charater to the buffer as a string
     *
     * @param c the character
     */
    public void writeString(char c) {
        this.writeString(Character.toString(c));
    }

    /**
     * Write a integer to the buffer as a string
     *
     * @param i the integer
     */
    public void writeString(int i) {
        this.writeString(Integer.toString(i));
    }

    /**
     * Write a float to the buffer as a string
     *
     * @param f the float
     */
    public void writeString(float f) {
        this.writeString(Float.toString(f));
    }

    /**
     * The size of the message in bytes
     *
     * @return the current size of the message
     */
    public int length() {
        return this.data.writerIndex() - 4;
    }

    /**
     * Access the buffer directly
     *
     * @return the finalized buffer
     */
    public ByteBuf getBuffer() {
        if (!this.finalized) {
            this.data.setInt(0, this.length());
            this.finalized = true;
        }

        return this.data;
    }
}
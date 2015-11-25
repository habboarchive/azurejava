package org.azure.communication.protocol;

import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;

/**
 * Basic implementation of a 'Message' contains the opCode and the actual buffer.
 */
public abstract class Message {
    public final short opCode;
    protected final ByteBuf data;
    protected Message(short opCode, ByteBuf data) {
        this.opCode = opCode;
        this.data = data;
    }

    public short getOpCode() {
        return opCode;
    }

    /**
     * Format the message for logging methods
     *
     * @return
     */
    public final String toString() {
        String buf = this.data.toString(Charset.defaultCharset());

        for (int i = 0; i <= 13; i++) {
            buf = buf.replace(Character.toString((char) i), "[" + i + "]");
        }

        return String.format("%d [%s]", this.opCode, buf);
    }
}
package org.azure.communication.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * A message received from the client
 */
public class ClientMessage extends Message {
    /**
     * Create a new ClientMessage with the available data
     *
     * @param header tells us how to parse the message
     * @param data   the data to parse
     */
    public ClientMessage(short header, ByteBuf data) {
        super(header, (data != null) ? data : Unpooled.EMPTY_BUFFER);
    }

    /**
     * How many bytes are available (have not been read)
     *
     * @return the available bytes
     */
    public int remaining() {
        return this.data.readableBytes();
    }

    /**
     * Read a boolean value
     *
     * @return the state
     */
    public boolean readBoolean() {
        return (this.data.readByte() == 1);
    }

    /**
     * Read a integer
     *
     * @return the integer
     */
    public int readInt() {
        return this.data.readInt();
    }

    /**
     * Read a short
     *
     * @return the short
     */
    public short readShort() {
        return this.data.readShort();
    }

    /**
     * Read a string
     *
     * @return the parsed string
     */
    public String readString() {
        short length = this.readShort();
        byte[] bytes = new byte[length];

        this.data.readBytes(bytes);

        return new String(bytes);
    }
}
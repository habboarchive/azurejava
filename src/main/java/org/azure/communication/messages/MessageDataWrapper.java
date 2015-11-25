package org.azure.communication.messages;

import io.netty.buffer.ByteBuf;
import org.azure.encoding.HabboEncoding;

import java.nio.charset.Charset;

public class MessageDataWrapper {
    private int offset;

    private final short header;
    private final int length;

    private final ByteBuf buffer;

    public MessageDataWrapper(ByteBuf buffer) {
        this.buffer = buffer;
        this.header = this.readShort();
        this.length = this.readInteger();
        this.offset = 6;
    }

    public short getHeader() {
        return this.header;
    }

    public int getLength() {
        return this.length;
    }

    public byte[] readToEnd() {
        return this.buffer.readBytes(this.offset).array();
    }

    public byte[] readBytes(int size) {
        this.offset += size;
        return this.buffer.readBytes(size).array();
    }

    public String readString() {
        int length = HabboEncoding.decodeInt(this.readBytes(2));
        this.offset += length;
        return new String(this.readBytes(length));
    }

    public int readInteger() {
        this.offset += 2;
        return this.buffer.readInt();
    }

    public Boolean readBoolean() {
        this.offset += 1;
        return this.buffer.readBoolean();
    }

    public short readShort() {
        this.offset += 2;
        return this.buffer.readShort();
    }

    public float readFloat() {
        this.offset += 4;
        return this.buffer.readFloat();
    }

    public String getBody() {
        String str = this.buffer.toString(Charset.defaultCharset());
        for (int i = 0; i < 13; i++) {
            str = str.replace(Character.toString((char)i), "[" + i + "]");
        }
        return str;
    }

    public int getBytesAvailable() {
        return this.buffer.readableBytes();
    }
}

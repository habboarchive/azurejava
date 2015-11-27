package org.azure.communication.messages.outgoing.handshake;

import org.azure.communication.messages.EServerMessage;
import org.azure.communication.protocol.ServerMessage;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
public class UserObjectMessageComposer {
    public static ServerMessage compose() {
        ServerMessage msg = new ServerMessage(EServerMessage.UserObjectMessageComposer);
        msg.writeInt(1); // User ID
        msg.writeString("Scott"); // Username
        msg.writeString("hr-831-1407.hd-190-14.ch-3015-100.lg-285-82.sh-295-64.fa-1201"); // Figure
        msg.writeString("M"); // Gender
        msg.writeString("AzureJAVA - Developer"); // Motto
        msg.writeString(""); // ?
        msg.writeBoolean(false); // ?
        msg.writeInt(0); // Respect
        msg.writeInt(3); // Daily Respect Points
        msg.writeInt(3); // Daily Pet Respect Points
        msg.writeBoolean(true); // ?
        msg.writeString("1448526834"); // Last Online (format?)
        msg.writeBoolean(true); // Can Change Username
        msg.writeBoolean(false); // ?

        return msg;
    }
}

package org.azure.communication.messages.outgoing.unsorted;

import org.azure.communication.messages.EServerMessage;
import org.azure.communication.protocol.ServerMessage;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
public class SendPerkAllowancesMessageComposer {
    public static ServerMessage compose() {
        ServerMessage msg = new ServerMessage(EServerMessage.SendPerkAllowancesMessageComposer);
        msg.writeInt(11);
        msg.writeString("BUILDER_AT_WORK");
        msg.writeString("");
        msg.writeBoolean(true);

        msg.writeString("VOTE_IN_COMPETITIONS");
        msg.writeString("requirement.unfulfilled.helper_level_2");
        msg.writeBoolean(false);

        msg.writeString("USE_GUIDE_TOOL");
        msg.writeString("requirement.unfulfilled.helper_level_4");
        msg.writeBoolean(false);

        msg.writeString("JUDGE_CHAT_REVIEWS");
        msg.writeString("requirement.unfulfilled.helper_level_6");
        msg.writeBoolean(false);

        msg.writeString("NAVIGATOR_ROOM_THUMBNAIL_CAMERA");
        msg.writeString("");
        msg.writeBoolean(true);

        msg.writeString("CALL_ON_HELPERS");
        msg.writeString("");
        msg.writeBoolean(true);

        msg.writeString("CITIZEN");
        msg.writeString("");
        msg.writeBoolean(true);

        msg.writeString("MOUSE_ZOOM");
        msg.writeString("");
        msg.writeBoolean(true);

        msg.writeString("TRADE");
        msg.writeString("");
        msg.writeBoolean(true);

        msg.writeString("CAMERA");
        msg.writeString("");
        msg.writeBoolean(true);

        msg.writeString("NAVIGATOR_PHASE_TWO_2014");
        msg.writeString("");
        msg.writeBoolean(true);

        return msg;
    }
}

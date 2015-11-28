package org.azure.communication.messages.outgoing.newnavigator;

import org.azure.communication.messages.EServerMessage;
import org.azure.communication.protocol.ServerMessage;

/**
 * AzureJava,
 * Edited: 11/27/2015 (Scott Stamp).
 */
public class SearchResultSetComposer {
    public static ServerMessage compose(String name, String junk) {
        ServerMessage msg = new ServerMessage(EServerMessage.SearchResultSetComposer);
        msg.writeString(name);
        msg.writeString(junk);

        switch (name) {
            case "official_view":
                msg.writeInt(2);
                break;
            case "myworld_view":
                msg.writeInt(5);
                break;
            case "hotel_view":
            case "roomads_view":
                msg.writeInt(1);
                break;
            default:
                msg.writeInt(1);
                break;
        }

        return msg;
    }
}
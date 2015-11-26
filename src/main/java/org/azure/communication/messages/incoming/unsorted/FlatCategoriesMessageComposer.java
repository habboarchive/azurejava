package org.azure.communication.messages.incoming.unsorted;

import org.azure.communication.messages.EServerMessage;
import org.azure.communication.protocol.ServerMessage;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
@SuppressWarnings("unused")
public class FlatCategoriesMessageComposer {
    public static ServerMessage compose() {
        String[] categories = {
                "No Category",
                "All Other Rooms",
                "School, Daycare & Adoption Rooms",
                "Help Centre, Guide & Service Rooms",
                "Hair Salons & Modelling Rooms",
                "Gaming & Race Rooms",
                "Trading & Shopping Rooms",
                "Maze & Theme Park Rooms",
                "Chat, Chill & Discussion Rooms",
                "Club & Group Rooms",
                "Restaurant, Bar & Night Club Rooms",
                "Themed & RPG Rooms",
                "Habbo Staff Rooms"
        };

        ServerMessage msg = new ServerMessage(EServerMessage.FlatCategoriesMessageComposer);

        msg.writeInt(categories.length);

        int index = 1;
        for (String category : categories) {
            msg.writeInt(index++);
            msg.writeString(category);
            msg.writeBoolean(true); // show category?
            msg.writeBoolean(false); // no idea
            msg.writeString("NONE");
            msg.writeString("");
            msg.writeBoolean(false);
        }

        return msg;
    }
}
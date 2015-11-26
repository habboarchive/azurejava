package org.azure.communication.messages.outgoing.unsorted;

import org.azure.communication.messages.EServerMessage;
import org.azure.communication.protocol.ServerMessage;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
public class SuperNotificationMessageComposer {
    /*
     * SuperNotificationMessageComposer - create alert message
     * @param icon  the icon to be used
     * @param title  message title
     * @param message  message text
     */
    public static ServerMessage compose(String icon, String title, String message) {
        return compose(icon, title, message, "event:", "OK");
    }

    /*
     * SuperNotificationMessageComposer - create alert message
     * @param icon  the icon to be used
     * @param title  message title
     * @param message  message text
     * @param linkUrl  link url (use "event:" for no link)
     * @param linkTitle  link text
     */
    public static ServerMessage compose(String icon, String title, String message, String linkUrl, String linkTitle) {
        ServerMessage msg = new ServerMessage(EServerMessage.SuperNotificationMessageComposer);
        msg.writeString(icon);
        msg.writeInt(4);
        msg.writeString("title");
        msg.writeString(title);
        msg.writeString("message");
        msg.writeString(message);
        msg.writeString("linkUrl");
        msg.writeString(linkUrl);
        msg.writeString("linkTitle");
        msg.writeString(linkTitle);

        return msg;
    }
}
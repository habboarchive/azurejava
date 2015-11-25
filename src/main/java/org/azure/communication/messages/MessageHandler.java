package org.azure.communication.messages;

import org.azure.communication.protocol.ClientMessage;
import org.azure.communication.messages.MessageEvent;
import org.azure.network.sessions.Session;

import com.netflix.governator.annotations.AutoBindSingleton;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Set;

/**
 * AzureJava,
 * adopted from Morgoth, written by Jordan (GreySyntax)
 * Edited: 11/24/2015
 */

@AutoBindSingleton
public class MessageHandler {
    private static HashMap<Short, Method> messages = new HashMap<Short, Method>();
    private static final Logger logger = LogManager.getLogger(MessageHandler.class);
    private static boolean initialized = false;

    @PostConstruct
    synchronized void init() {
        if (initialized) {
            return;
        }

        initialized = true;
        logger.info("Scanning for MessageEvents");
        String pkg = "org.azure.communication.messages";

        Reflections reflections = new Reflections(new ConfigurationBuilder()
            .setUrls(ClasspathHelper.forPackage(pkg))
            .setScanners(new MethodAnnotationsScanner()));

        Set<Method> methods = reflections.getMethodsAnnotatedWith(MessageEvent.class);
        for (Method method : methods) {
            MessageEvent event = method.getAnnotation(MessageEvent.class);

            if (method.getReturnType().equals(Void.TYPE) && event != null && event.enabled()) {
                if (messages.containsKey(event.messageId())) {
                    logger.error("Message ID collision: {}::{} with {}::{}",
                            messages.get(event.messageId()).getDeclaringClass().getSimpleName(),
                            messages.get(event.messageId()).getName(),
                            method.getDeclaringClass().getSimpleName(),
                            method.getName()
                    );
                    System.exit(1); // Fatal error
                }

                Parameter[] params = method.getParameters();
                if (params.length == 2 && params[0].getType() == Session.class && params[1].getType() == ClientMessage.class) {
                    logger.info("Registered {}::{} to id {}",
                            method.getDeclaringClass().getSimpleName(),
                            method.getName(),
                            event.messageId()
                    );
                    messages.put(event.messageId(), method);
                }
            }
        }
    }

    public static void invoke(Session session, ClientMessage message) {
        if (session == null || message == null) return;
        if (!messages.containsKey(message.opCode)) {
            logger.info("Unknown message (id: " + message.opCode + " session: " + session.getId() + ") " + message.toString());
            return;
        }

        logger.debug("Incoming message (id: " + message.opCode + " session: " + session.getId() + ") " + message.toString());
        try {
            messages.get(message.opCode).invoke(null, session, message);
        } catch (IllegalAccessException e) {
            logger.error("Error", e);
        } catch (InvocationTargetException e) {
            logger.error("Error", e);
        }
    }
}

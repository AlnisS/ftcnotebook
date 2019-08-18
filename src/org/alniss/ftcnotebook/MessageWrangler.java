package org.alniss.ftcnotebook;

import com.google.gson.Gson;
import org.omg.PortableInterceptor.DISCARDING;

import java.io.*;
import java.util.*;

public class MessageWrangler {
    private Map<String, Message> messages;

    public void init() {
        setupMessagesMap();

        //identifyUpdateOrAdd(loadSlackMessages(DataInfo.slackInputFolder.listFiles()));
        identifyUpdateOrAdd(loadSlackMessages(DataInfo.slackDebugInputFileA));
        saveIntermediateFile();

        setupMessagesMap();
        loadIntermediateFile();

        identifyUpdateOrAdd(loadSlackMessages(DataInfo.slackDebugInputFileB));

        printMessages();
    }

    private void printMessages() {
        for (Map.Entry entry : messages.entrySet())
            System.out.println(entry.getKey() + " " + entry.getValue().toString());
    }

    private void setupMessagesMap() {
        messages = new HashMap<>();
    }

    private void loadIntermediateFile() {
        if (DataInfo.intermediateFile == null)
            return;
        Message[] tmp = loadOneMessageFile(DataInfo.intermediateFile);
        if (tmp != null) {
            initMessages(tmp);
            addMessages(tmp);
        }
    }

    private void identifyUpdateOrAdd(Message[] slackMessages) {
        for (Message message : slackMessages) {
            if (messages.containsKey(message.getMessageID()))
                messages.get(message.getMessageID()).setUpdate(message);
            else
                addMessages(message);
        }
    }

    private Message[] loadSlackMessages(File... messageFiles) {
        List<Message> messages = new ArrayList<>();
        for (File file : messageFiles) {
            try {
                messages.addAll(Arrays.asList(loadOneSlackFile(file)));
            } catch (FileNotFoundException e) {
                System.out.println("FileNotFoundException for file " + file.getAbsolutePath());
                e.printStackTrace();
            }
        }
        return messages.toArray(new Message[0]);
    }

    private Message[] loadOneSlackFile(File messageFile) throws FileNotFoundException {
        TmpSlackMessage[] tmpSlackMessages = new Gson()
                .fromJson(new FileReader(messageFile), TmpSlackMessage[].class);
        Message[] result = new Message[tmpSlackMessages.length];
        for (int i = 0; i < result.length; i++) {
            TmpSlackMessage tmp = tmpSlackMessages[i];
            result[i] = new Message(tmp.user, tmp.ts, tmp.text);
        }
        return result;
    }

    private Message[] loadOneMessageFile(File messageFile) {
        try {
            return new Gson().fromJson(new FileReader(messageFile), Message[].class);
        } catch (FileNotFoundException e) {
            System.out.println("No file " + DataInfo.intermediateFile.getAbsolutePath() + " found. " +
                    "Skipping loading of it.");
            return null;
        }
    }

    private void saveIntermediateFile() {
        try (FileWriter writer = new FileWriter(DataInfo.intermediateFile)) {
            new Gson().toJson(messages.values(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initMessages(Message... messages) {
        for (Message message : messages)
            message.init();
    }

    private void addMessages(Message... messages) {
        for (Message message : messages)
            this.messages.put(message.getMessageID(), message);
    }
}

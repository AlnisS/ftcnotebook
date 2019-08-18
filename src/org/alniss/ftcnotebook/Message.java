package org.alniss.ftcnotebook;

public class Message {
    private String authorID;  // Slack ID of message sender
    private String timestamp;  // Slack sent timestamp
    private String currentBase;  // Current most recent "base" message
    private String proposedBase;  // i.e. new message after user edited
    private String editedLog;  // Edited form (by notebook reviewer)
    private String[] manualTags;  // Tags added by notebook reviewer
    private transient String messageID;  // authorID + timestamp
    private transient String[] autoTags;  // i.e. log date
    private transient String nextLog;  // messageID of next log in chain

    private Message update;  // Updated message (if an update exists)

    public Message(String authorID, String timestamp, String currentBase) {
        this.authorID = authorID;
        this.timestamp = timestamp;
        this.currentBase = currentBase;
        this.proposedBase = null;
        this.editedLog = null;
        this.manualTags = new String[0];
        init();

        //temporary
        this.autoTags = new String[0];
        this.nextLog = null;
    }

    public void init() {
        messageID = authorID + timestamp;
    }

    public String toString() {
        //return authorID + " " + timestamp + " " + currentBase + " " + proposedBase + " " + editedLog + " " + update;
        return update != null ? "update" : "no update";
    }

    public void setUpdate(Message update) {
        this.update = update;
    }

    // GETTERS

    public String getAuthorID() {
        return authorID;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getCurrentBase() {
        return currentBase;
    }

    public String getProposedBase() {
        return proposedBase;
    }

    public String getEditedLog() {
        return editedLog;
    }

    public String[] getManualTags() {
        return manualTags;
    }

    public String getMessageID() {
        return messageID;
    }

    public String[] getAutoTags() {
        return autoTags;
    }

    public String getNextLog() {
        return nextLog;
    }

    public Message getUpdate() {
        return update;
    }
}

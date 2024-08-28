package pojo;

import java.time.LocalDateTime;

public class MessagePOJO {
	private int messageId;
    private String senderId;
    private String receiverId;
    private Integer groupId;
    private String content;
    private LocalDateTime sentAt;
//    private AccountPOJO sender; 
//    private AccountPOJO receiver;
//    private GroupPOJO group;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

//    public AccountPOJO getSender() {
//        return sender;
//    }
//
//    public void setSender(AccountPOJO sender) {
//        this.sender = sender;
//    }
//
//    public AccountPOJO getReceiver() {
//        return receiver;
//    }
//
//    public void setReceiver(AccountPOJO receiver) {
//        this.receiver = receiver;
//    }
//
//    public GroupPOJO getGroup() {
//        return group;
//    }
//
//    public void setGroup(GroupPOJO group) {
//        this.group = group;
//    }

    // Optional constructor(s) for convenience
    public MessagePOJO() {
    	
    }

    public MessagePOJO(int messageId, String senderId, String receiverId, Integer groupId, String content, LocalDateTime sentAt) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.groupId = groupId;
        this.content = content;
        this.sentAt = sentAt;
    }
    
    public MessagePOJO(MessagePOJO m) {
        this.messageId = m.messageId;
        this.senderId = m.senderId;
        this.receiverId = m.receiverId;
        this.groupId = m.groupId;
        this.content = m.content;
        this.sentAt = m.sentAt;
    }
}

package pojo;

public class GroupMemberPOJO {

    private int groupMemberId;
    private int groupId;
    private String userId;
//    private GroupPOJO group;
//    private AccountPOJO user;

    public int getGroupMemberId() {
        return groupMemberId;
    }

    public void setGroupMemberId(int groupMemberId) {
        this.groupMemberId = groupMemberId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

//    public GroupPOJO getGroup() {
//        return group;
//    }
//
//    public void setGroup(GroupPOJO group) {
//        this.group = group;
//    }
//
//    public AccountPOJO getUser() {
//        return user;
//    }
//
//    public void setUser(AccountPOJO user) {
//        this.user = user;
//    }

    public GroupMemberPOJO() {
    	
    }
    
    public GroupMemberPOJO(int groupMemberId, int groupId, String userId) {
    	this.groupMemberId = groupMemberId;
        this.groupId = groupId;
        this.userId = userId;
    }

    public GroupMemberPOJO(GroupMemberPOJO gm) {
        this.groupMemberId = gm.groupMemberId;
        this.groupId = gm.groupId;
        this.userId = gm.userId;
    }
}

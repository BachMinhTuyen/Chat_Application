package pojo;

public class GroupPOJO {
	private int groupId;
    private String groupName;
    private String groupType;

    public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public GroupPOJO() {
    }

    public GroupPOJO(int groupId, String groupName, String groupType) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupType = groupType;
    }
    
    public GroupPOJO(GroupPOJO g) {
        this.groupId = g.groupId;
        this.groupName = g.groupName;
        this.groupType = g.groupType;
    }
}
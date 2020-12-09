package Contato;

public class MatchesObject {
    private String userId;
    private String name;
    private String profileImagemUrl;

    public MatchesObject(String userId, String name, String profileImagemUrl) {
        this.userId = userId;
        this.name = name;
        this.profileImagemUrl = profileImagemUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserID(String userID) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getprofileImagemUrl() {
        return profileImagemUrl;
    }

    public void setprofileImagemUrl(String profileImagemUrl) {
        this.profileImagemUrl = profileImagemUrl;
    }
}

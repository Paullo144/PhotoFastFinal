package Cards;

public class Cards {
        private String userId;
        private String name;
        private String profileImagemUrl;

        public Cards (String userId, String name, String profileImagemUrl){
            this.userId = userId;
            this.name = name;
            this.profileImagemUrl = profileImagemUrl;
        }

        public String getUserId(){
            return userId;
        }
        public void setUserID(String userID){
            this.userId = userId;
        }

        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name = name;
        }

        public String getProfileImagemUrl(){
        return profileImagemUrl;
    }
        public void setProfileImagemUrl(String profileImagemUrl){
        this.profileImagemUrl = profileImagemUrl;
    }
    }



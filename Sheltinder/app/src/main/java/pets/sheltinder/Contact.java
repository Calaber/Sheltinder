package pets.sheltinder;

class Contact {

    private String username, password;

    public void setUsername(String username) { this.username = username; }
    public String getUsername() { return this.username; }

    void setPassword(String password) { this.password = password; }
    String getPassword() { return this.password; }

    private boolean isShelter;
    void setAccessType(boolean isShelter) { this.isShelter = isShelter; }
    boolean getAccessType() { return this.isShelter; }

}


package pets.sheltinder;

public class ShelterInfo {
    private String location;

    public ShelterInfo(String loc){
        location = "Shelter Location: "+loc;
    }

    public void setLocation(String loc){
        location = loc;
    }

    public String getLocation(){
        return location;
    }

}

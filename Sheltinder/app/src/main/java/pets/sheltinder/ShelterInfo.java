package pets.sheltinder;

public class ShelterInfo {
    private static String shelterName;
    private static String shelterInfo;
    private static String location;//Change this to work with google maps once we figure that out

    public ShelterInfo(String name){
        shelterName = name;
    }

    public static void UpdateInfo(String info){
        shelterInfo = info;
    }

    public static void UpdateName(String name){
        shelterName = name;
    }

    public static String getShelterName(){
        return shelterName;
    }

    public static String getShelterInfo(){
        return shelterInfo;
    }

    public static void setLocation(String loc){
        location = loc;
    }

    public static String getLocation(){
        return location;
    }

}

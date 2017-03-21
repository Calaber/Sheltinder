package pets.sheltinder;

public class AnimalInfo {
    private static String animalName;
    private static String animalInfo;
    private static AnimalType animType;
    private static ShelterInfo shelter;
    private static boolean adoptedStatus;

    public AnimalInfo(String name,String info,AnimalType type,ShelterInfo sheltInfo){
        animalName = name;
        animalInfo = info;
        animType = type;
        shelter = sheltInfo;
        adoptedStatus = false;
    }

    public static void setAnimalInfo(String info){
        animalInfo = info;
    }

    public static void setAnimalName(String name){
        animalName = name;
    }

    public static void setAdoptedStatus(boolean status){
        adoptedStatus = status;
    }

    public static String getAnimalName(){
        return animalName;
    }

    public static String getAnimalInfo(){
        return animalInfo;
    }

    public static AnimalType getAnimType(){
        return animType;
    }

    public static ShelterInfo getShelter(){
        return shelter;
    }

    public static boolean setAdoptedStatus(){
        return adoptedStatus;
    }
}

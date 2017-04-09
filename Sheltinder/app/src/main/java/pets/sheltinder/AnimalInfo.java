package pets.sheltinder;

public class AnimalInfo {
    private static String animalName;
    private static String animalInfo;
    private static AnimalType animType;
    private static ShelterInfo shelter;

    public AnimalInfo(String name,String info,AnimalType type,ShelterInfo sheltInfo){
        animalName = name;
        animalInfo = info;
        animType = type;
        shelter = sheltInfo;
    }

    public String getAnimalName(){
        return animalName;
    }

    public String getAnimalInfo(){
        return animalInfo;
    }

    public AnimalType getAnimType(){
        return animType;
    }

    public ShelterInfo getShelter(){
        return shelter;
    }
}

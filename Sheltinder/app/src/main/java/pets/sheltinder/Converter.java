package pets.sheltinder;

public class Converter {

    public static String retPetTypeString(String intPetType){
        String type ="";
        int pt = Integer.parseInt(intPetType);

        if(pt == 0){
            type = "Dog";
        } else if(pt == 1){
            type = "Cat";
        } else{
            type = "Other";
        }
        return type;
    }
    public static AnimalType retTypeFromString(String petType){
        return AnimalType.DOG;
    }
}

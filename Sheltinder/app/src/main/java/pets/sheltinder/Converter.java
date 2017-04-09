package pets.sheltinder;

public class Converter {

    public static String retPetTypeStringFromTypeString(String intPetType){
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
        AnimalType type = AnimalType.DOG;
        if(petType.equals("1")){
            type = AnimalType.CAT;
        } else if (petType.equals("2")){
            type = AnimalType.OTHER;
        }
        return type;
    }

    public static String retStringFromType(AnimalType type){
        String pet = "Dog";

        switch (type){
            case CAT:
                pet = "Cat";
                break;
            case OTHER:
                pet = "Other";
                break;
            default:
                break;
        }

        return pet;
    }

    public static String formatLocation(String loc){
        return "Shelter Location: "+loc;
    }

    public static String formatPetTypeDescription(String type,String description){
        return "Pet Type: "+type+"\n" +description;
    }
}

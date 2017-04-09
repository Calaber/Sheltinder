package pets.sheltinder;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class Tests {

    @Test
    public void testAnimalInfoPetTypeDog(){
        String actual = Converter.retPetTypeStringFromTypeString("0");
        String expected = "Dog";

        assertEquals("Conversion of Pet Type failed",expected,actual);
    }

    @Test
    public void testAnimalInfoPetTypeCat(){
        String actual = Converter.retPetTypeStringFromTypeString("1");
        String expected = "Cat";

        assertEquals("Conversion of Pet Type failed",expected,actual);
    }

    @Test
    public void testAnimalInfoPetTypeOther(){
        String actual = Converter.retPetTypeStringFromTypeString("2");
        String expected = "Other";

        assertEquals("Conversion of Pet Type failed",expected,actual);
    }

    @Test
    public void testAnimalInfoPetTypeFromStringDog(){
        AnimalType actual = Converter.retTypeFromString("0");
        AnimalType expected = AnimalType.DOG;

        assertEquals("Conversion of Pet Type failed",expected,actual);
    }

    @Test
    public void testAnimalInfoPetTypeFromStringCat(){
        AnimalType actual = Converter.retTypeFromString("1");
        AnimalType expected = AnimalType.CAT;

        assertEquals("Conversion of Pet Type failed",expected,actual);
    }

    @Test
    public void testAnimalInfoPetTypeFromStringOther(){
        AnimalType actual = Converter.retTypeFromString("2");
        AnimalType expected = AnimalType.OTHER;

        assertEquals("Conversion of Pet Type failed",expected,actual);
    }

    @Test
    public void testAnimalInfoPetStringFromTypeDog(){
        String actual = Converter.retStringFromType(AnimalType.DOG);
        String expected = "Dog";

        assertEquals("Conversion of Pet Type failed",expected,actual);
    }

    @Test
    public void testAnimalInfoPetStringFromTypeCat(){
        String actual = Converter.retStringFromType(AnimalType.CAT);
        String expected = "Cat";

        assertEquals("Conversion of Pet Type failed",expected,actual);
    }

    @Test
    public void testAnimalInfoPetStringFromTypeOther(){
        String actual = Converter.retStringFromType(AnimalType.OTHER);
        String expected = "Other";

        assertEquals("Conversion of Pet Type failed",expected,actual);
    }

    @Test
    public void testFormatLoc(){
        String actual = Converter.formatLocation("123 Hi Lane");
        String expected = "Shelter Location: 123 Hi Lane";

        assertEquals("Format of Location failed",expected,actual);
    }

    @Test
    public void testFormatType(){
        String actual = Converter.formatPetTypeDescription("Dog","Test");
        String expected = "Pet Type: Dog"+"\n"+ "Test";

        assertEquals("Format of Pet Type failed",expected,actual);
    }

    @Test
    public void testShelterInfo(){
        ShelterInfo test = new ShelterInfo("loc");
        assertNotNull(test);

        assertEquals("loc",test.getLocation());

        test.setLocation("l");

        assertEquals("l",test.getLocation());
    }

    @Test
    public void TestAnimalInfoDog(){
        ShelterInfo test = new ShelterInfo("loc");
        assertNotNull(test);

        assertEquals("loc",test.getLocation());

        test.setLocation("l");

        assertEquals("l",test.getLocation());

        AnimalInfo animalInfoTest = new AnimalInfo("n","i",AnimalType.DOG,test);

        assertNotNull(animalInfoTest);
        assertNotNull(animalInfoTest.getShelter());
        assertEquals("n",animalInfoTest.getAnimalName());
        assertEquals("i",animalInfoTest.getAnimalInfo());
        assertEquals(AnimalType.DOG,animalInfoTest.getAnimType());
    }
    @Test
    public void TestAnimalInfoCat(){
        ShelterInfo test = new ShelterInfo("loc");
        assertNotNull(test);

        assertEquals("loc",test.getLocation());

        test.setLocation("l");

        assertEquals("l",test.getLocation());

        AnimalInfo animalInfoTest = new AnimalInfo("n","i",AnimalType.CAT,test);

        assertNotNull(animalInfoTest);
        assertNotNull(animalInfoTest.getShelter());
        assertEquals("n",animalInfoTest.getAnimalName());
        assertEquals("i",animalInfoTest.getAnimalInfo());
        assertEquals(AnimalType.CAT,animalInfoTest.getAnimType());
    }

    @Test
    public void TestAnimalInfoOther(){
        ShelterInfo test = new ShelterInfo("loc");
        assertNotNull(test);

        assertEquals("loc",test.getLocation());

        test.setLocation("l");

        assertEquals("l",test.getLocation());

        AnimalInfo animalInfoTest = new AnimalInfo("n","i",AnimalType.OTHER,test);

        assertNotNull(animalInfoTest);
        assertNotNull(animalInfoTest.getShelter());
        assertEquals("n",animalInfoTest.getAnimalName());
        assertEquals("i",animalInfoTest.getAnimalInfo());
        assertEquals(AnimalType.OTHER,animalInfoTest.getAnimType());
    }

}

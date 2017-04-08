package pets.sheltinder;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class Tests {

    @Test
    public void testAnimalInfoPetTypeDog(){
        String actual = Converter.retPetTypeString("0");
        String expected = "Dog";

        assertEquals("Conversion of Pet Type failed",actual,expected);
    }

    @Test
    public void testAnimalInfoPetTypeCat(){
        String actual = Converter.retPetTypeString("1");
        String expected = "Cat";

        assertEquals("Conversion of Pet Type failed",actual,expected);
    }

    @Test
    public void testAnimalInfoPetTypeOther(){
        String actual = Converter.retPetTypeString("2");
        String expected = "Other";

        assertEquals("Conversion of Pet Type failed",actual,expected);
    }
}

package ma.zs.stocky.integration.core.piecejointe.type-attachement;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class TypeAttachementIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("TypeAttachementHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

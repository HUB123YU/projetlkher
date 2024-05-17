package ma.zs.stocky.integration.core.piecejointe.attachement;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class AttachementIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("AttachementHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

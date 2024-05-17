package ma.zs.stocky.integration.core.stage.stage-attachement;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class StageAttachementIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("StageAttachementHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

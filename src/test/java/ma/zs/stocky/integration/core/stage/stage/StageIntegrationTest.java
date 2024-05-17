package ma.zs.stocky.integration.core.stage.stage;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class StageIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("StageHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

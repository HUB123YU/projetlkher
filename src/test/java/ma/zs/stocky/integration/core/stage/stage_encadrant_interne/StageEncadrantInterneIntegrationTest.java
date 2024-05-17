package ma.zs.stocky.integration.core.stage.stage-encadrant-interne;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class StageEncadrantInterneIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("StageEncadrantInterneHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

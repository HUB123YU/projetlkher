package ma.zs.stocky.integration.core.stage.stage-encadrant-externe;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class StageEncadrantExterneIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("StageEncadrantExterneHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

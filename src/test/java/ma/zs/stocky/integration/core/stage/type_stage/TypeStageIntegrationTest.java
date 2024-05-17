package ma.zs.stocky.integration.core.stage.type-stage;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class TypeStageIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("TypeStageHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

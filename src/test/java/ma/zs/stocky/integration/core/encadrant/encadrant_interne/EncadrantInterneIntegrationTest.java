package ma.zs.stocky.integration.core.encadrant.encadrant-interne;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class EncadrantInterneIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("EncadrantInterneHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

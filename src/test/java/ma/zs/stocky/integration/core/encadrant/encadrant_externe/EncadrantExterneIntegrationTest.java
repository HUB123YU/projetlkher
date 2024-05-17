package ma.zs.stocky.integration.core.encadrant.encadrant-externe;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class EncadrantExterneIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("EncadrantExterneHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

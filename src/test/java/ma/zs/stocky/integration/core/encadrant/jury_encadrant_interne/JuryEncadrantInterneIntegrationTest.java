package ma.zs.stocky.integration.core.encadrant.jury-encadrant-interne;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class JuryEncadrantInterneIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("JuryEncadrantInterneHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

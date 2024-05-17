package ma.zs.stocky.integration.core.jury.jury;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class JuryIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("JuryHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

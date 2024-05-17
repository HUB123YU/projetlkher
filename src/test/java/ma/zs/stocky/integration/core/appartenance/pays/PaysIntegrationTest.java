package ma.zs.stocky.integration.core.appartenance.pays;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class PaysIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("PaysHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

package ma.zs.stocky.integration.core.appartenance.ville;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class VilleIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("VilleHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

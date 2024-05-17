package ma.zs.stocky.integration.core.departement.domaine;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class DomaineIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("DomaineHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

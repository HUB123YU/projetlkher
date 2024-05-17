package ma.zs.stocky.integration.core.departement.filiere;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class FiliereIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("FiliereHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

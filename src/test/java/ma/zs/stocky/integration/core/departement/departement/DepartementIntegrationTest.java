package ma.zs.stocky.integration.core.departement.departement;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class DepartementIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("DepartementHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

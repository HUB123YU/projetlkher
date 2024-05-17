package ma.zs.stocky.integration.core.departement.secteur-activite;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class SecteurActiviteIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("SecteurActiviteHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

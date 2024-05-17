package ma.zs.stocky.integration.core.etudiant.etudiant;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class EtudiantIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("EtudiantHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

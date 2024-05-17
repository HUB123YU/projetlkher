package ma.zs.stocky.integration.core.stage.stage-etudiant;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class StageEtudiantIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("StageEtudiantHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

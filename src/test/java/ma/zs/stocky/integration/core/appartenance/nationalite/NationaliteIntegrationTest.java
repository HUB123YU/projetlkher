package ma.zs.stocky.integration.core.appartenance.nationalite;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class NationaliteIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("NationaliteHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

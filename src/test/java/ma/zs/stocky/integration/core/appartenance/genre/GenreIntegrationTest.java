package ma.zs.stocky.integration.core.appartenance.genre;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class GenreIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("GenreHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

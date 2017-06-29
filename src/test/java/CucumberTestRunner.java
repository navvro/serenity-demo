import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * @author Rafal Nawrocki <rafalnawrocki0@gmail.com>
 */

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        format = {"pretty", "json:target/cucumber.json"})
public class CucumberTestRunner {
}

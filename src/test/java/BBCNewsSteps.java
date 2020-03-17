import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BBCNewsSteps {

    private Logger logger = LoggerFactory.getLogger( BBCNewsSteps.class);

    @Given("^User BBC News Page$")
    public void userBBCNewsPage() {
        TestBase.getDriver().get("https://www.bbc.co.uk/news");
        assertTrue(TestBase.getDriver().getTitle().contains("Home - BBC News"));
        logger.info("BBC News page opened");

    }

    @When("^User Sign in to BBC news$")
    public void userSignInToBBCNews() throws  InterruptedException {
        TestBase.getDriver().findElement(By.id("idcta-username")).click();
        TestBase.getDriver().findElement(By.id("user-identifier-input")).click();
        TestBase.getDriver().findElement(By.id("user-identifier-input")).sendKeys("nafogo5869@mailernam.com" );
        TestBase.getDriver().findElement(By.id("password-input")).click();
        TestBase.getDriver().findElement(By.id("password-input")).sendKeys("test1234" );
        TestBase.getDriver().findElement(By.xpath("//button[@id='submit-button']/span")).click();

    }

    @Then("^Verify user signed in successfully$")
    public void verifyUserSignedInSuccessfully() {

        assertEquals("Your account", TestBase.getDriver().findElement(By.id("idcta-username")).getText());
        TestBase.getDriver().findElement(By.id("idcta-username")).click();
        assertEquals("Your account", TestBase.getDriver().findElement( By.cssSelector(".heading--inline")).getText() );
        assertEquals("Sign out", TestBase.getDriver().findElement( By.xpath("//li[4]/a/span")).getText() );

    }

    @And("^Sign out from BBC news$")
    public void signOutFromBBCNews() {

        TestBase.getDriver().findElement(By.xpath("//li[4]/a/span")).click();
        assertEquals("You've signed out, sorry to see you go.", TestBase.getDriver().findElement( By.cssSelector(".heading")).getText() );

        TestBase.getDriver().findElement(By.linkText("Continue")).click();

        assertEquals("Sign in", TestBase.getDriver().findElement( By.id("idcta-username")).getText() );

    }
    @Then("^Verify user signed out successfully$")
    public void verifyUserSignedOutSuccessfully() {

        assertEquals("Sign in", TestBase.getDriver().findElement( By.id("idcta-username")).getText() );

    }

}

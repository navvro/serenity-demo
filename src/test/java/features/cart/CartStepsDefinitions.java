package features.cart;

import com.demo.nawrot.actions.ChangeSizeOfWindow;
import com.demo.nawrot.actions.RefreshSession;
import com.demo.nawrot.pageobjects.EShopMainPage;
import com.demo.nawrot.questions.ElementVisibility;
import com.demo.nawrot.questions.ItemsAddedToCart;
import com.demo.nawrot.questions.SessionVariableAsString;
import com.demo.nawrot.tasks.cart.AddItemToCartFromTheList;
import com.demo.nawrot.tasks.cart.RemoveItemFromCart;
import com.demo.nawrot.tasks.cart.RemovesAllItemsFromCart;
import com.demo.nawrot.tasks.navigation.ConfirmCookies;
import com.demo.nawrot.tasks.navigation.OpenMainCategoryByNavBarButton;
import com.demo.nawrot.tasks.navigation.OpenSubcategoryOnMainCategoryPage;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;

import static com.demo.nawrot.pageobjects.CartProductList.EMPTY_CART_MESSAGE;
import static com.demo.nawrot.pageobjects.CartProductList.RESTORE_CART_BUTTON;
import static com.demo.nawrot.utils.SessionVariables.ADDED_TO_CART_ITEM_NAME;
import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Rafal Nawrocki <rafal.nawrocki0@gmail.com>
 */
public class CartStepsDefinitions {
    private Actor user = Actor.named("Customer");
    private EShopMainPage eShopMainPage;

    @Managed
    private WebDriver theBrowser;

    @Before
    public void set_the_stage() {
        OnStage.setTheStage(new OnlineCast());
        givenThat(user).can(BrowseTheWeb.with(theBrowser));
    }

    @Given("^User opens e-shop page$")
    public void userOpensEShopPage() {
        user.wasAbleTo(
                Open.browserOn(eShopMainPage),
                RefreshSession.ofBrowser(),
                ChangeSizeOfWindow.to(1920, 1080),
                ConfirmCookies.popUp());
    }

    @Then("^This item is visible on cart page$")
    public void thisItemIsVisibleOnCartPage() {
        String expectedItemInCart = SessionVariableAsString.ofKey(ADDED_TO_CART_ITEM_NAME).answeredBy(user);
        user.should(seeThat(ItemsAddedToCart.list(), hasItem(expectedItemInCart)));
    }

    @Then("^(\\d+) item(?:s|) (?:is|are) visible on cart page$")
    public void thatItemsAreVisibleOnCartPage(int expectedSize) {
        user.should(seeThat(ItemsAddedToCart.list(), hasSize(equalTo(expectedSize))));
    }

    @When("^User opens \"([^\"]*)\" category$")
    public void userOpensCategory(String categoryName) {
        user.attemptsTo(OpenMainCategoryByNavBarButton.named(categoryName));
    }

    @And("^User opens \"([^\"]*)\" subcategory$")
    public void userOpensSubcategory(String subcategoryName) {
        user.attemptsTo(OpenSubcategoryOnMainCategoryPage.named(subcategoryName));
    }

    @And("^User adds (\\d+)(?:st|nd|rd|th) item on the list to his cart$")
    public void userAddsItemOnTheListToHisCart(int positionOnTheList) {
        user.attemptsTo(AddItemToCartFromTheList.onPosition(positionOnTheList));
    }

    @When("^User removes (\\d+)(?:st|nd|rd|th) item from the cart$")
    public void userRemovesItemFromTheCart(int position) {
        user.attemptsTo(RemoveItemFromCart.onPosition(position));
    }

    @Then("^Message about empty cart is visible$")
    public void messageAboutEmptyCartIsVisible() {
        user.should(seeThat(ElementVisibility.of(EMPTY_CART_MESSAGE), is(equalTo(true))));
    }

    @And("^User removes all items from the cart$")
    public void userRemovesAllItemsFromTheCart() {
        user.attemptsTo(RemovesAllItemsFromCart.list());
    }

    @And("^User restores cart items$")
    public void userRestoresCartItems() {
        user.attemptsTo(Click.on(RESTORE_CART_BUTTON));
    }
}

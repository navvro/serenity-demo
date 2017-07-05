package features.cart;

import com.demo.navvrot.actions.ChangeSizeOfWindow;
import com.demo.navvrot.actions.RefreshSession;
import com.demo.navvrot.pageobjects.EShopMainPage;
import com.demo.navvrot.questions.*;
import com.demo.navvrot.tasks.cart.AddItemToCartFromTheList;
import com.demo.navvrot.tasks.cart.RemoveItemFromCart;
import com.demo.navvrot.tasks.cart.RemovesAllItemsFromCart;
import com.demo.navvrot.tasks.cart.SetAmountOfItemInCart;
import com.demo.navvrot.tasks.navigation.ConfirmCookies;
import com.demo.navvrot.tasks.navigation.OpenMainCategoryByNavBarButton;
import com.demo.navvrot.tasks.navigation.OpenSubcategoryOnMainCategoryPage;
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

import static com.demo.navvrot.pageobjects.CartProductList.EMPTY_CART_MESSAGE;
import static com.demo.navvrot.pageobjects.CartProductList.RESTORE_CART_BUTTON;
import static com.demo.navvrot.utils.SessionVariables.ADDED_TO_CART_ITEM_NAME;
import static com.demo.navvrot.utils.SessionVariables.ADDED_TO_CART_ITEM_PRICE;
import static com.demo.navvrot.utils.Utils.parseIntFromString;
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

    /*
        GIVEN and WHEN steps
     */

    @Given("^User opens e-shop page$")
    public void userOpensEShopPage() {
        user.wasAbleTo(
                Open.browserOn(eShopMainPage),
                RefreshSession.ofBrowser(),
                ChangeSizeOfWindow.to(1920, 1080),
                ConfirmCookies.popUp());
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

    @And("^User removes all items from the cart$")
    public void userRemovesAllItemsFromTheCart() {
        user.attemptsTo(RemovesAllItemsFromCart.list());
    }

    @And("^User restores cart items$")
    public void userRestoresCartItems() {
        user.attemptsTo(Click.on(RESTORE_CART_BUTTON));
    }

    @And("^User set amount of (\\d+)(?:st|nd|rd|th) item in cart to (\\d+) units$")
    public void userSetAmountOfItemInCartToOnes(int position, String count) {
        user.attemptsTo(SetAmountOfItemInCart.listedOnPositionToValue(position, count));
    }
    /*
        THEN steps
     */

    @Then("^This item is visible on cart page$")
    public void thisItemIsVisibleOnCartPage() {
        String expectedItemInCart = SessionVariableAsString.ofKey(ADDED_TO_CART_ITEM_NAME).answeredBy(user);
        user.should(seeThat(ItemsAddedToCart.list(), hasItem(expectedItemInCart)));
    }

    @Then("^(\\d+) item(?:s|) (?:is|are) visible on cart page$")
    public void thatItemsAreVisibleOnCartPage(int expectedSize) {
        user.should(seeThat(ItemsAddedToCart.list(), hasSize(equalTo(expectedSize))));
    }

    @Then("^Message about empty cart is visible$")
    public void messageAboutEmptyCartIsVisible() {
        user.should(seeThat(ElementVisibility.of(EMPTY_CART_MESSAGE), is(equalTo(true))));
    }

    @Then("^Price of item in the cart is the same as on the list$")
    public void priceOfItemInTheCartIsTheSameAsOnTheList() {
        String priceOfAddedItem = SessionVariableAsString.ofKey(ADDED_TO_CART_ITEM_PRICE).answeredBy(user);
        int expectedPrice = parseIntFromString(priceOfAddedItem);

        user.should(seeThat(UnitPriceOfItemInCart.onPosition(1), is(equalTo(expectedPrice))));
    }

    @Then("^Overall price of (\\d+)(?:st|nd|rd|th) item on the list in the cart is multiplied (\\d+) times$")
    public void priceSumOfItemInTheCartIsMultipliedTimes(int itemPosition, int multiplier) {
        int expectedValue = UnitPriceOfItemInCart.onPosition(itemPosition).answeredBy(user) * multiplier;

        user.should(seeThat(
                OverallPriceOfItemInCart.onPosition(itemPosition),
                is(equalTo(expectedValue))));
    }

    @Then("^Price sum of items in the cart is correct$")
    public void priceSumOfItemsInTheCartIsCorrect() {
        int priceOfFirstItem = UnitPriceOfItemInCart.onPosition(1).answeredBy(user);
        int priceOfSecondItem = UnitPriceOfItemInCart.onPosition(2).answeredBy(user);
        int expectedSum = priceOfFirstItem + priceOfSecondItem;

        user.should(seeThat(OverallCartPrice.sum(), is(equalTo(expectedSum))));
    }
}

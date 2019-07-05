import checkout.*;
import checkout.offers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CheckoutServiceTest {

    private Product bred_3;
    private Product flour_6;
    private Product milk_7;
    private CheckoutService checkoutService;
    private Trademark trademark;
    LocalDate yesterday;
    LocalDate tomorrow;

    @BeforeEach
    void setUp() {
        checkoutService = new CheckoutService();
        checkoutService.openCheck();

        trademark = new Trademark(1, "FLOUR_CORPORATION");

        bred_3 = new Product(3, "Bred");
        flour_6 = new Product(6, "Flour", trademark);
        milk_7 = new Product(7, "Milk", Category.MILK);

        yesterday = LocalDate.now().minusDays(1);
        tomorrow = LocalDate.now().plusDays(1);
    }

    @Test
    void closeCheck__withOneProduct() {
        checkoutService.addProduct(milk_7);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(7));
    }

    @Test
    void closeCheck__withTwoProducts() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(10));
    }

    @Test
    void addProduct__whenCheckIsClosed__opensNewCheck() {
        checkoutService.addProduct(milk_7);
        Check milkCheck = checkoutService.closeCheck();
        assertThat(milkCheck.getTotalCost(), is(7));

        checkoutService.addProduct(bred_3);
        Check bredCheck = checkoutService.closeCheck();
        assertThat(bredCheck.getTotalCost(), is(3));
    }

    @Test
    void closeCheck__calcTotalPoints() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(10));
    }

    @Test
    void useOffer__addOfferPoints() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        checkoutService.useOffer(new AnyGoodsOffer(tomorrow,6, 2));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(12));
    }

    @Test
    void useOffer__whenCostLessThanRequired__doNothing() {
        checkoutService.addProduct(bred_3);

        checkoutService.useOffer(new AnyGoodsOffer(tomorrow,6, 2));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(3));
    }

    @Test
    void useOffer__factorByCategory() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, tomorrow));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
    }

    @Test
    void useOffer__beforeCloseCheck() {
        checkoutService.addOffer(new FactorByCategoryOffer(Category.MILK, 2, tomorrow));

        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Check check = checkoutService.closeCheck();
        assertThat(check.getTotalPoints(), is(31));
    }

    @Test
    void useOffer_factorByExpiration(){
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, yesterday));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(10));
    }

    @Test
    void useOffer_DiscountByTrademark(){
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(flour_6);
        checkoutService.addProduct(bred_3);

        checkoutService.useOffer(new PercentageDiscountByTrademarkOffer(tomorrow, trademark));

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(13));
    }

    @Test
    void useOffer_DiscountByProduct(){
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(flour_6);
        checkoutService.addProduct(bred_3);

        checkoutService.useOffer(new PercentageDiscountByProductOffer(tomorrow, bred_3));

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(15));
    }

    @Test
    void useOffer_PresentByTrademark(){
        checkoutService.addProduct(flour_6);
        checkoutService.addProduct(flour_6);
        checkoutService.addProduct(flour_6);

        checkoutService.useOffer(new PresentByTrademarkOffer(tomorrow, trademark));

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(13));
    }

    @Test
    void useOffer_PresentByProduct(){
        checkoutService.addProduct(flour_6);
        checkoutService.addProduct(flour_6);
        checkoutService.addProduct(flour_6);

        checkoutService.useOffer(new PresentByProductOffer(tomorrow, flour_6));

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(13));
    }

}

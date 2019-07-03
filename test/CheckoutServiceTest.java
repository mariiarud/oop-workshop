import checkout.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CheckoutServiceTest {

    private Product milk_7;
    private CheckoutService checkoutService;
    private Product bred_3;
    private Product flour_6;

    @BeforeEach
    void setUp() {
        checkoutService = new CheckoutService();
        checkoutService.openCheck();

        milk_7 = new Product(7, "Milk", Category.MILK, Trademark.MILK_CORPORATION);
        bred_3 = new Product(3, "Bred");
        flour_6 = new Product(6, "Flour", Trademark.FLOUR_CORPORATION);
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

        checkoutService.useOffer(new AnyGoodsOffer(6, 2));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(12));
    }

    @Test
    void useOffer__whenCostLessThanRequired__doNothing() {
        checkoutService.addProduct(bred_3);

        checkoutService.useOffer(new AnyGoodsOffer(6, 2));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(3));
    }

    @Test
    void useOffer__factorByCategory() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, new Date(1593766320000L)));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
    }

    @Test
    void useOffer__beforeCloseCheck() {
        checkoutService.addOffer(new FactorByCategoryOffer(Category.MILK, 2, new Date(1593766320000L)));

        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Check check = checkoutService.closeCheck();
        assertThat(check.getTotalPoints(), is(31));
    }

    @Test
    void useOffer_shelfLife(){
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, new Date(1212121212121L)));
        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, new Date(1593766320000L)));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(17));
    }

    @Test
    void useOffer_BonusByTrademark(){
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        checkoutService.addProduct(flour_6);
        checkoutService.addProduct(flour_6);
        checkoutService.addProduct(flour_6);

        checkoutService.useOffer(new BonusByTrademarkOffer("-50%", new Date(1593766320000L), Trademark.MILK_CORPORATION));
        checkoutService.useOffer(new BonusByTrademarkOffer("present", new Date(1593766320000L), Trademark.FLOUR_CORPORATION));

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(19));
    }

    @Test
    void useOffer_BonusByProduct(){
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        checkoutService.addProduct(bred_3);
        checkoutService.addProduct(bred_3);
        checkoutService.addProduct(flour_6);


        checkoutService.useOffer(new BonusByProductOffer("-50%", new Date(1593766320000L), milk_7));
        checkoutService.useOffer(new BonusByProductOffer("present", new Date(1593766320000L), bred_3));

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(20));
    }
}

package Common;

import Pages.AdminPages.AdminDashBoardPage;
import Pages.AdminPages.ProductPages.AddNewProductsPage;
import Pages.SignInPage;
import Pages.UserPages.ShoppingCart.CheckoutPage;
import Pages.UserPages.ShoppingCart.OrderPage;
import Pages.UserPages.UserControlPanel.ProfilePage;
import Pages.UserPages.UserDashBoardPage;

public class LinkedPage {
    public SignInPage signInPage;

    public UserDashBoardPage userDashBoardPage;
    public ProfilePage profilePage;
    public OrderPage orderPage;
    public CheckoutPage checkoutPage;

    public AdminDashBoardPage adminDashBoardPage;
    public AddNewProductsPage addNewProductsPage;


    public SignInPage getSignInPage() {
        if (signInPage == null) {
            signInPage = new SignInPage();
        }
        return signInPage;
    }

    public ProfilePage getProfilePage() {
        if (profilePage == null) {
            profilePage = new ProfilePage();
        }
        return profilePage;
    }

    public OrderPage getOrderPage() {
        if (orderPage == null) {
            orderPage = new OrderPage();
        }
        return orderPage;
    }

    public AddNewProductsPage getAddNewProductsPage() {
        if (addNewProductsPage == null) {
            addNewProductsPage = new AddNewProductsPage();
        }
        return addNewProductsPage;
    }

    public CheckoutPage getCheckoutPage() {
        if (checkoutPage == null) {
            checkoutPage = new CheckoutPage();
        }
        return checkoutPage;
    }


    public AdminDashBoardPage getAdminDashBoardPage() {
        if (adminDashBoardPage == null) {
            adminDashBoardPage = new AdminDashBoardPage();
        }
        return adminDashBoardPage;
    }

    public UserDashBoardPage getUserDashBoardPage() {
        if (userDashBoardPage == null) {
            userDashBoardPage = new UserDashBoardPage();
        }
        return userDashBoardPage;
    }

}







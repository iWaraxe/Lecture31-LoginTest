package com.coherentsolutions.java;

import com.coherentsolutions.java.basiclasses.TestBase;
import com.coherentsolutions.java.basiclasses.TestConfig;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    private static final String USER_NAME = TestConfig.getUsername();
    private static final String PASSWORD = TestConfig.getPassword();
    private static final String LOGIN_PAGE_URL = TestConfig.getUrl();  // Load the login page URL from config

    private static final String EXPECTED_ERROR_MESSAGE_TEXT = "Please provide password.";
    private static final String INCORRECT_PASSWORD_ERROR_MESSAGE_TEXT = "Invalid password. Please try again";
    private static final String EMPTY_USER_NAME_ERROR_MESSAGE_TEXT = "Sorry, we don't recognize this email.";

    private UserAccountPage userAccountPage;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUpTest() {
        // Load the login page URL before each test
        driver.get(LOGIN_PAGE_URL);

        // Initialize the LoginPage object
        loginPage = new LoginPage();
    }

    @Test
    public void loginTest() {
        loginPage.inputUserName(USER_NAME);
        loginPage.clickLoginButton();
        loginPage.inputUserPassword(PASSWORD);
        userAccountPage = loginPage.clickPasswordFormLoginButton();
        Assert.assertTrue(userAccountPage.isDisplayedMailLink(), "User Account page is not displayed");
    }

    @Test
    public void emptyPasswordTest() {
        loginPage.inputUserName(USER_NAME);
        loginPage.clickLoginButton();
        loginPage.inputUserPassword("");
        userAccountPage = loginPage.clickPasswordFormLoginButton();
        String actualErrorText = loginPage.getEmptyPasswordErrorMessage();
        Assert.assertEquals(actualErrorText, EXPECTED_ERROR_MESSAGE_TEXT);
    }

    @Test
    public void incorrectPasswordTest() {
        loginPage.inputUserName(USER_NAME);
        loginPage.clickLoginButton();
        loginPage.inputUserPassword(RandomStringUtils.randomAlphabetic(10));
        userAccountPage = loginPage.clickPasswordFormLoginButton();
        String actualErrorText = loginPage.getIncorrectPasswordErrorMessage();
        Assert.assertEquals(actualErrorText, INCORRECT_PASSWORD_ERROR_MESSAGE_TEXT);
    }

    @Test
    public void emptyUserNameFieldTest() {
        loginPage.clickLoginButton();
        String actualErrorText = loginPage.getInvalidUserNameErrorMessage();
        Assert.assertEquals(actualErrorText, EMPTY_USER_NAME_ERROR_MESSAGE_TEXT);
    }
}

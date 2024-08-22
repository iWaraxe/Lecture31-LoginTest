package com.coherentsolutions.java;

import com.coherentsolutions.java.basiclasses.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends PageBase {
    @FindBy(css = "[name='username']")
    private WebElement userNameField;

    @FindBy(id = "login-signin")
    private WebElement userNameFormLoginButton;

    @FindBy(id = "login-passwd")
    private WebElement userPasswordField;

    @FindBy(id = "login-signin")
    private WebElement passwordFormLoginButton;

    @FindBy (xpath = "//p[@class='error-msg' and @data-error='messages.ERROR_EMPTY_PASSWORD']")
    private WebElement emptyPasswordErrorMessage;

    @FindBy (xpath = "//p[@class='error-msg' and @data-error='messages.ERROR_INVALID_PASSWORD']")
    private WebElement incorrectPasswordErrorMessage;

    @FindBy (xpath = "//p[@id='username-error' and @class='error-msg']")
    private WebElement invalidUserNameErrorMessage;

    public LoginPage() {
        super();
        PageFactory.initElements(driver,this);
    }

    public void inputUserName(String userName) {
        userNameField.sendKeys(userName);
    }

    public void clickLoginButton() {
        userNameFormLoginButton.click();
    }

    public void inputUserPassword(String userPassword) {
        userPasswordField.sendKeys(userPassword);
    }

    public UserAccountPage clickPasswordFormLoginButton() {
        passwordFormLoginButton.click();
        return new UserAccountPage();
    }

    public String getEmptyPasswordErrorMessage() {
        return emptyPasswordErrorMessage.getText();
    }

    public String getIncorrectPasswordErrorMessage() {
        return incorrectPasswordErrorMessage.getText();
    }

    public String getInvalidUserNameErrorMessage() {
        return invalidUserNameErrorMessage.getText();
    }
}

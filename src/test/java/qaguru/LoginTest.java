package qaguru;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginTest {
    String correctUserName = "name";
    String correctLogin = "login@mail.com";
    String correctPassword = "password";
    String idLoginButton = ".xdget-button";

    void setupValue(String email, String password) {
        open("https://qa.guru/cms/system/login");
        $(".login-form").shouldHave(Condition.text("Войти"));
        $("[name=email]").setValue("email");
        $("[name=password]").setValue("password").pressEnter();
    }

    @BeforeAll
    static void configurationTest() {
        Configuration.holdBrowserOpen = true;
        Configuration.browser = "safari";
    }

    @Test
    void successfulLoginTest() {
        setupValue(correctLogin, correctPassword);
        $(".main-header__login").click();
        $(".logined-form").shouldHave(Condition.text("Hello, " + correctUserName));
    }

    @Test
    void unsuccessfulEmailTest() {
        setupValue("incorrectMail", correctPassword);
        $(idLoginButton).shouldHave(Condition.text("Неверный формат e-mail"));
    }
}
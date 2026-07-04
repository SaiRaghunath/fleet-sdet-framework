package com.fleet.framework.playwright.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    private final Page page;

    private static final String USERNAME_INPUT = "#user-name";
    private static final String PASSWORD_INPUT = "#password";
    private static final String LOGIN_BUTTON = "#login-button";
    private static final String ERROR_MESSAGE = "h3[data-test='error']";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void login(String username, String password) {
        page.fill(USERNAME_INPUT, username);
        page.fill(PASSWORD_INPUT, password);
        page.click(LOGIN_BUTTON);
    }

    public String getErrorMessage() {
        return page.locator(ERROR_MESSAGE).innerText().trim();
    }
}

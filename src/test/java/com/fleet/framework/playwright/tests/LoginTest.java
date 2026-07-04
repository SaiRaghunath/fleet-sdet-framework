package com.fleet.framework.playwright.tests;

import com.fleet.framework.base.PlaywrightFactory;
import com.fleet.framework.playwright.pages.InventoryPage;
import com.fleet.framework.playwright.pages.LoginPage;
import com.fleet.framework.utils.ConfigReader;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {
    private PlaywrightFactory playwrightFactory;
    private Page page;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        playwrightFactory = new PlaywrightFactory();
        page = playwrightFactory.initPage(ConfigReader.get("baseUrl"));
        loginPage = new LoginPage(page);
    }

    @AfterEach
    public void tearDown() {
        playwrightFactory.close();
    }

    @Test
    public void validUserCanLogin() {
        loginPage.login(
            ConfigReader.get("standardUsername"),
            ConfigReader.get("standardPassword")
        );

        InventoryPage inventoryPage = new InventoryPage(page);
        Assertions.assertTrue(inventoryPage.isLoaded(), "Inventory page should load after login.");
        Assertions.assertTrue(inventoryPage.getItemCount() > 0, "At least one inventory item should be visible.");
    }

    @Test
    public void invalidPasswordShowsErrorMessage() {
        loginPage.login(
            ConfigReader.get("standardUsername"),
            ConfigReader.get("invalidPassword")
        );

        String errorText = loginPage.getErrorMessage();
        Assertions.assertTrue(errorText.contains("Username and password do not match"), "Expected invalid login error message.");
    }
}

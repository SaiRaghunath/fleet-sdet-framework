package com.fleet.framework.base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    public Page initPage(String baseUrl) {
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "true"));

        playwright = Playwright.create();
        browser = playwright.chromium().launch(
            new BrowserType.LaunchOptions()
                .setChannel("msedge")
                .setHeadless(headless)
        );
        context = browser.newContext();
        page = context.newPage();
        page.navigate(baseUrl);
        return page;
    }

    public void close() {
        if (context != null) {
            context.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}

package com.fleet.framework.playwright.pages;

import com.microsoft.playwright.Page;

public class InventoryPage {
    private final Page page;
    private static final String INVENTORY_LIST = ".inventory_list";
    private static final String INVENTORY_ITEM = ".inventory_item";

    public InventoryPage(Page page) {
        this.page = page;
    }

    public boolean isLoaded() {
        return page.locator(INVENTORY_LIST).isVisible();
    }

    public int getItemCount() {
        return page.locator(INVENTORY_ITEM).count();
    }
}

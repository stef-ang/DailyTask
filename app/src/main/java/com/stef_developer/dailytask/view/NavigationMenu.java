package com.stef_developer.dailytask.view;

/**
 * Created by tsaqova on 5/25/15.
 */
public class NavigationMenu {

    private int icon;
    private String title;private String counter;

    private boolean isGroupHeader = false;

    public NavigationMenu(String title) {
        this(-1,title);
        isGroupHeader = true;
    }
    public NavigationMenu(int icon, String title) {
        super();
        this.icon = icon;
        this.title = title;
    }

    public int getIcon() {
        return this.icon;
    }

    public String getTitle() {
        return this.title;
    }

}

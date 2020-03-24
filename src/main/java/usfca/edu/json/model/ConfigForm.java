package usfca.edu.json.model;

public class ConfigForm {

    private boolean analytics;

    private boolean faves;

    private boolean search;

    private boolean login;

    public boolean isAnalytics() {
        return analytics;
    }

    public void setAnalytics(boolean analytics) {
        this.analytics = analytics;
    }

    public boolean isFaves() {
        return faves;
    }

    public void setFaves(boolean faves) {
        this.faves = faves;
    }

    public boolean isSearch() {
        return search;
    }

    public void setSearch(boolean search) {
        this.search = search;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

}

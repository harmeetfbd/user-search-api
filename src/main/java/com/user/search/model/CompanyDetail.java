package com.user.search.model;

public class CompanyDetail {

    private String name;
    private String catchPhrase;
    private String bs;

    public String getName() {
        return name;
    }

    public CompanyDetail setName(String name) {
        this.name = name;
        return this;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public CompanyDetail setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
        return this;
    }

    public String getBs() {
        return bs;
    }

    public CompanyDetail setBs(String bs) {
        this.bs = bs;
        return this;
    }
}

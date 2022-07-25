package com.example.demo3;

public class Account {
    private String account,company,bookie,bot,channel,state;
    private int rule;

    public Account(){}

    public Account(String account, String company, String bookie, String bot, String channel, int rule, String state) {
        this.account = account;
        this.company = company;
        this.bookie = bookie;
        this.bot = bot;
        this.channel = channel;
        this.rule = rule;
        this.state = state;
    }

    public String getAccount() {

        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBookie() {
        return bookie;
    }

    public void setBookie(String bookie) {
        this.bookie = bookie;
    }

    public String getBot() {
        return bot;
    }

    public void setBot(String bot) {
        this.bot = bot;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public int getRule() {
        return Math.min(rule, 1000);
    }

    public void setRule(int rule) {
        this.rule = rule;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

}

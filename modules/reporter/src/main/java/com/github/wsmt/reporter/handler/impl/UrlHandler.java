package com.github.wsmt.reporter.handler.impl;

import com.github.wsmt.reporter.handler.Handler;


public class UrlHandler implements Handler {

    private UrlHandler() {

    }

    public static UrlHandler create() {
        return new UrlHandler();
    }

    @Override
    public String handle(String inputRow) {
        return inputRow;
    }
}

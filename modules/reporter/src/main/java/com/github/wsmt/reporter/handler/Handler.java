package com.github.wsmt.reporter.handler;

import java.io.Serializable;

public interface Handler extends Serializable {
    String handle(String inputRow);
}

package com.github.wsmt.reporter.util;

import java.io.Serializable;
import java.util.function.Supplier;

public interface SerializableSupplier<T> extends Serializable, Supplier<T> {
}

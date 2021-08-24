package com.zzsong.quarkus.upms.common;

import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.function.Supplier;

/**
 * @author 宋志宗 on 2021/8/24
 */
public class Asserts {

  @Nonnull
  public static CharSequence notBlank(@Nullable CharSequence charSequence,
                                      @Nullable String message) throws IllegalArgumentException {
    if (StringUtils.isBlank(charSequence)) {
      illegalArgument(message);
    }
    return charSequence;
  }

  @Nonnull
  public static CharSequence notBlank(@Nullable CharSequence charSequence,
                                      @Nonnull Supplier<String> messageSupplier) throws IllegalArgumentException {
    if (StringUtils.isBlank(charSequence)) {
      illegalArgument(messageSupplier.get());
    }
    return charSequence;
  }

  @Nonnull
  public static CharSequence ifBlankThrow(@Nullable CharSequence charSequence,
                                          @Nonnull Supplier<? extends RuntimeException> supplier) {
    if (StringUtils.isBlank(charSequence)) {
      throw supplier.get();
    }
    return charSequence;
  }

  @Nonnull
  public static <T> T nonnull(@Nullable T t, @Nullable String message) {
    if (t == null) {
      illegalArgument(message);
    }
    return t;
  }

  @Nonnull
  public static <T> T nonnull(@Nullable T t, @Nonnull Supplier<String> messageSupplier) {
    if (t == null) {
      illegalArgument(messageSupplier.get());
    }
    return t;
  }

  @Nonnull
  public static <T> T ifNullThrow(@Nullable T t,
                                  @Nonnull Supplier<? extends RuntimeException> supplier) {
    if (t == null) {
      throw supplier.get();
    }
    return t;
  }

  public static void maxLength(@Nonnull CharSequence charSequence, int length, @Nullable String message) {
    if (charSequence.length() > length) {
      illegalArgument(message);
    }
  }

  public static void assertTrue(boolean condition, @Nullable String message) {
    if (!condition) {
      throw new IllegalArgumentException(message);
    }
  }

  public static void assertTrue(boolean condition, @Nonnull Supplier<String> messageSupplier) {
    if (!condition) {
      throw new IllegalArgumentException(messageSupplier.get());
    }
  }

  public static void assertFalse(boolean condition, @Nullable String message) {
    if (condition) {
      illegalArgument(message);
    }
  }

  public static void assertFalse(boolean condition, @Nonnull Supplier<String> messageSupplier) {
    if (condition) {
      illegalArgument(messageSupplier.get());
    }
  }

  @Nonnull
  public static <C extends Collection<?>> C notEmpty(@Nullable C collection,
                                                     @Nullable String message) {
    if (collection == null || collection.isEmpty()) {
      illegalArgument(message);
    }
    return collection;
  }

  @Nonnull
  public static <C extends Collection<?>> C notEmpty(@Nullable C collection,
                                                     @Nonnull Supplier<String> messageSupplier) {
    if (collection == null || collection.isEmpty()) {
      illegalArgument(messageSupplier.get());
    }
    return collection;
  }


  public static void range(long value, long minimum, long maximum,
                           @Nullable String message) throws IllegalArgumentException {
    if (value < minimum || value > maximum) {
      illegalArgument(message);
    }
  }

  private static void illegalArgument(@Nullable String message) {
    if (message == null) {
      throw new IllegalArgumentException();
    }
    throw new IllegalArgumentException(message);
  }

  private Asserts() {
  }
}

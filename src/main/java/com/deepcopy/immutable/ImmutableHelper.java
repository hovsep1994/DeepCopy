package com.deepcopy.immutable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.time.*;
import java.util.Currency;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

public class ImmutableHelper {

    private static final Set<Class<?>> IMMUTABLE_TYPES = Set.of(
            // Core Java Types
            String.class, Integer.class, Long.class, Short.class, Byte.class,
            Double.class, Float.class, Boolean.class, Character.class, Void.class,

            // Number-Related
            BigDecimal.class, BigInteger.class,

            // Java 8 Date/Time API
            LocalDate.class, LocalTime.class, LocalDateTime.class, ZonedDateTime.class,
            OffsetDateTime.class, OffsetTime.class, Year.class, YearMonth.class, MonthDay.class,
            Instant.class, Duration.class, Period.class,

            // Other Immutable Types
            UUID.class, Path.class, URI.class, URL.class, Locale.class, Currency.class
    );

    public static boolean isImmutable(Class<?> clazz) {
        return clazz.isEnum() || clazz.isPrimitive() || IMMUTABLE_TYPES.contains(clazz);
    }
}

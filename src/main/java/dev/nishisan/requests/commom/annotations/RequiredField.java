package dev.nishisan.requests.commom.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredField {

    AllowedValue[] allowedValues() default {};

    @interface AllowedValue {
        String stringValue() default "";
        int intValue() default 0;
        long longValue() default 0L;
        double doubleValue() default 0.0;
        float floatValue() default 0.0f;
        boolean booleanValue() default false;
        char charValue() default '\u0000';
        byte byteValue() default 0;
        short shortValue() default 0;
    }
}

package dev.nishisan.requests.commom.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredField {

    AllowedValue[] allowedValues() default {};

    @interface AllowedValue {
        String type();
        String value();
    }
}

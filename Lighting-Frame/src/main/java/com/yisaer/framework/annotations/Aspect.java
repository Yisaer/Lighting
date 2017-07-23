package com.yisaer.framework.annotations;

import java.lang.annotation.*;

/**
 * Created by Yisa on 2017/7/23.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    Class<? extends Annotation> value();
}

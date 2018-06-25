package com.iguangtech.interceptor;

import javax.ws.rs.NameBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation - 需要accessToken
 *
 * @author xingkong1221
 * @date 2015-11-21
 */
@NameBinding
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedToken {
    public boolean bindPhone() default true;
}

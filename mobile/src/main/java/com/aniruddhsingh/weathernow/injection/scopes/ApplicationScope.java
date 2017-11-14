package com.aniruddhsingh.weathernow.injection.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;
import javax.inject.Singleton;

/**
 * Created by taru on 11/15/2017.
 */

@Scope
@Singleton
@Retention(RetentionPolicy.CLASS)
public @interface ApplicationScope {
}

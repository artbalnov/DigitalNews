package artbalnov.digitalnewsrefactored.base.annotations;

import android.support.annotation.LayoutRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Base Annotation for quick defining layout resource id
 * */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface LayoutResourceId {
    @LayoutRes
    int value();
}

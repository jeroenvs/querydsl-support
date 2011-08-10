package org.jeroen.querydsl;

import static org.hamcrest.core.IsNull.notNullValue;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import com.mysema.query.types.Path;

/**
 * Matches based on the current value of a path.
 * 
 * @author Jeroen van Schagen
 * @since 30-06-2011
 *
 * @param <T> type of the path root
 * @param <V> type of value being matched
 */
public class PathMatcher<T, V> extends TypeSafeDiagnosingMatcher<T> {   
    private final PathBasedValueAccessor accessor;
    private final Matcher<? super V> matcher;
    private final Path<V> path;
    
    public PathMatcher(Path<V> path, Matcher<? super V> matcher) {
        this(path, matcher, new PathBasedValueAccessor());
    }
    
    public PathMatcher(Path<V> path, Matcher<? super V> matcher, PathBasedValueAccessor accessor) {
        this.path = path;
        this.matcher = matcher;
        this.accessor = accessor;
    }
    
    @Factory
    public static <T,P> Matcher<T> hasValue(Path<P> path) {
        return new PathMatcher<T,P>(path, notNullValue());
    }
  
    @Factory
    public static <T,P> Matcher<T> hasValue(Path<P> path, Matcher<? super P> matcher) {
        return new PathMatcher<T,P>(path, matcher);
    }
    
    @Override
    protected boolean matchesSafely(T bean, Description mismatchDescription) {
        V value = accessor.getPathValue(bean, path);
        boolean valueMatches = matcher.matches(value);
        if (!valueMatches) {
            mismatchDescription.appendText("value \"" + path.toString() + "\" ");
            matcher.describeMismatch(value, mismatchDescription);
        }
        return valueMatches;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("valueOf(");
        description.appendValue(path.toString());
        description.appendText(", ");
        description.appendDescriptionOf(matcher);
        description.appendText(")");
    }
    
}

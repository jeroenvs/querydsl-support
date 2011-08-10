package org.jeroen.querydsl;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import com.mysema.query.types.Path;

/**
 * Matches the current value of a specific path.
 * 
 * @author Jeroen van Schagen
 * @since 30-06-2011
 *
 * @param <T> type of the path root
 * @param <P> type of value being matched
 */
public class PathMatcher<T,P> extends TypeSafeDiagnosingMatcher<T> {   
    private final PathBasedValueAccessor accessor;
    private final Matcher<? super P> matcher;
    private final Path<P> path;
    
    public PathMatcher(Path<P> path, Matcher<? super P> matcher) {
        this(path, matcher, new PathBasedValueAccessor());
    }
    
    public PathMatcher(Path<P> path, Matcher<? super P> matcher, PathBasedValueAccessor accessor) {
        this.path = path;
        this.matcher = matcher;
        this.accessor = accessor;
    }
  
    @Factory
    public static <T,P> Matcher<T> valueOf(Path<P> path, Matcher<? super P> matcher) {
        return new PathMatcher<T,P>(path, matcher);
    }
    
    @Override
    protected boolean matchesSafely(T bean, Description mismatchDescription) {
        P value = accessor.getPathValue(bean, path);
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

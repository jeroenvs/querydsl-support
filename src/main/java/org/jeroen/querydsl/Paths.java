package org.jeroen.querydsl;

import java.util.LinkedList;
import java.util.List;

import com.mysema.query.types.Expression;
import com.mysema.query.types.Path;

/**
 * Provides helper functionality on paths.
 * 
 * @see com.mysema.query.types.Path
 * 
 * @author Jeroen van Schagen
 * @since 10-08-2011
 */
public final class Paths {
    
    public static String pathToSeperatedString(Path<?> path) {
        return pathToSeperatedString(path, ".");
    }
    
    public static String pathToSeperatedString(Path<?> path, String delim) {
        return pathToSeperatedString(path, delim, "", "");
    }
    
    public static String pathToSeperatedString(Path<?> path, String delim, String prefix, String suffix) {
        if (path == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Path<?> current : collectPaths(path)) {
            switch(current.getMetadata().getPathType()) {
            case PROPERTY:
                if(sb.length() > 0) {
                    sb.append(delim);
                }
                String expression = expressionOf(current).toString();
                sb.append(prefix).append(expression).append(suffix);
                break;
            }
        }
        return sb.toString();
    }
    
    private static List<Path<?>> collectPaths(Path<?> path) {
        List<Path<?>> expressions = new LinkedList<Path<?>>();
        while(path != null) {
            expressions.add(0, path);
            path = path.getMetadata().getParent();
        }
        return expressions;
    }
    
    public static Expression<?> expressionOf(Path<?> path) {
        return path.getMetadata().getExpression();
    }
    
}

package org.jeroen.querydsl;

import java.util.Comparator;

import org.apache.commons.lang3.ObjectUtils;

import com.mysema.query.types.Path;

public class PathComparator<T, V extends Comparable<V>> implements Comparator<T> {
    private final PathBasedValueAccessor accessor;
    private final Path<V> comparingPath;
    
    public PathComparator(Path<V> comparingPath) {
        this(comparingPath, new PathBasedValueAccessor());
    }
    
    public PathComparator(Path<V> comparingPath, PathBasedValueAccessor accessor) {
        this.comparingPath = comparingPath;
        this.accessor = accessor;
    }
    
    public static <T, V extends Comparable<V>> PathComparator<T, V> pathComparator(Path<V> comparingPath) {
        return new PathComparator<T,V>(comparingPath);
    }

    @Override
    public int compare(T leftBean, T rightBean) {
        if(leftBean == rightBean) {
            return 0; // Reference to the seme object should always result in '0'
        } else if (leftBean == null) {
            return -1; // Whenever the reference varies, and left is null, right is not null
        } else if (rightBean == null) {
            return 1; // Whenever the reference varies, and right is null, left is not null
        } else if (leftBean.equals(rightBean)) {
            return 0; // Equal beans should always result in '0'
        }
        return comparePathValues(leftBean, rightBean);
    }
    
    private int comparePathValues(T leftBean, T rightBean) {
        V left = accessor.getPathValue(leftBean, comparingPath);
        V right = accessor.getPathValue(rightBean, comparingPath);
        return ObjectUtils.compare(left, right); // Null safe value comparison
    }

}

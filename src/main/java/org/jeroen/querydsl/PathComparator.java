package org.jeroen.querydsl;

import java.util.Comparator;

import org.apache.commons.lang3.ObjectUtils;

import com.mysema.query.types.Path;

public class PathComparator<T, V extends Comparable<V>> implements Comparator<T> {
    private final Path<V> comparingPath;
    private final PathBasedPropertyAccessor propertyAccessor;
    
    public PathComparator(Path<V> comparingPath) {
        this(comparingPath, new PathBasedPropertyAccessor());
    }
    
    public PathComparator(Path<V> comparingPath, PathBasedPropertyAccessor propertyAccessor) {
        this.comparingPath = comparingPath;
        this.propertyAccessor = propertyAccessor;
    }

    @Override
    public int compare(T leftBean, T rightBean) {
        if(leftBean == rightBean) {
            return 0;
        } else if (leftBean == null) {
            return -1;
        } else if (rightBean == null) {
            return 1;
        } 
        return comparePathValues(leftBean, rightBean);
    }
    
    private int comparePathValues(T leftBean, T rightBean) {
        V left = propertyAccessor.getPropertyValue(leftBean, comparingPath);
        V right = propertyAccessor.getPropertyValue(leftBean, comparingPath);
        return ObjectUtils.compare(left, right);
    }

}
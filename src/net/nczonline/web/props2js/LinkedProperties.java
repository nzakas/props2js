package net.nczonline.web.props2js;

import java.util.*;

/**
 * Pulled from http://stackoverflow.com/questions/1312383/pulling-values-from-a-java-properties-file-in-order
 */
public class LinkedProperties extends Properties {

    private final LinkedHashSet<Object> keys = new LinkedHashSet<Object>();

    @Override
    public Enumeration<?> propertyNames() {
        return Collections.enumeration(keys);
    }

    @Override
    public synchronized Enumeration<Object> elements() {
        return Collections.enumeration(keys);
    }

    public Enumeration<Object> keys() {
        return Collections.enumeration(keys);
    }

    public Set<Object> keySet() {
        return Collections.unmodifiableSet(keys);
    }

    public Object put(Object key, Object value) {
        keys.add(key);
        return super.put(key, value);
    }

    @Override
    public synchronized Object remove(Object key) {
        keys.remove(key);
        return super.remove(key);
    }

    @Override
    public synchronized void clear() {
        keys.clear();
        super.clear();
    }
}

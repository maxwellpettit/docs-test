package org.jumpmind.pos;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class OrderedProperties extends Properties {

    private List<String> orderedKeys = new LinkedList<>();

    public List<String> getOrderedKeys() {
        return orderedKeys;
    }

    @Override
    public Object put(Object key, Object value) {
        orderedKeys.remove(key);
        orderedKeys.add((String)key);
        return super.put(key, value);
    }

    @Override
    public Object remove(Object key) {
        orderedKeys.remove(key);
        return super .remove(key);
    }

}
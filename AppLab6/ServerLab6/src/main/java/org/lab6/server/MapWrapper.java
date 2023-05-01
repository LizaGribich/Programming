package org.lab6.server;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MapWrapper<K, V> {

    private Map<K, V> map;

    public MapWrapper(Class<? extends Map> mapClass) throws Exception {
        map = mapClass.newInstance();
    }

    public void put(K key, V value) {
        map.put(key, value);
    }

    public void remove(K key) {
        map.remove(key);
    }

    public V get(K key) {
        return map.get(key);
    }

    public void clear() {
        map.clear();
    }

    public int size() {
        return map.size();
    }

    public Set<K> keySet() {
        return map.keySet();
    }

    public Set<Map.Entry<K,V>> entrySet() {
        return map.entrySet();
    }

    public Collection<V> values() {
        return map.values();
    }
}
package Tokens;
import java.util.*;
import java.util.function.Function;
public final class Cache<T,V> {
    private Map<T,V> cache = new HashMap<T,V>();

    public V get(T key, Function <? super T, ? extends V> constructor){
    if(!cache.containsKey(key)){ // if the item is not in the map.....
        cache.put(key, constructor.apply(key));
    }
        // return the item with key since item will be in the map any way after if
         return cache.get(key);
    }
}

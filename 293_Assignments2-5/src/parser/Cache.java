package parser;
import java.util.*;
import java.util.function.Function;

final class Cache<T,V> {
	
    private Map<T,V> cache = new HashMap<T,V>();

    public V get(T key, Function <? super T, ? extends V> constructor){
    	
    	Objects.requireNonNull(key, "Input key is null for get method");
    	Objects.requireNonNull(constructor, "Constructor method is null for get method");
    	
        cache.computeIfAbsent(key, constructor);//Adds key to map if non-existent
        return cache.get(key);
    }
    
}

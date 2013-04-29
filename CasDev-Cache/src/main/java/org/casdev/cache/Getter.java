package org.casdev.cache;

import com.google.gson.JsonObject;

public interface Getter<T> {

	T get(Class<T> clazz, JsonObject o);

}

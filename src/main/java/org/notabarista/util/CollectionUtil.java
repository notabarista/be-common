package org.notabarista.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionUtil {

	private CollectionUtil() {

	}

	public static <T> List<T> asList(T object) {
		List<T> retList = new ArrayList<>();
		if (object != null) {
			retList.add(object);
		}
		return retList;
	}

	public static <T> Boolean isEmpty(Collection<T> object) {
		return object == null || object.isEmpty();
	}

	public static <T> Boolean isNotEmpty(Collection<T> object) {
		return object != null && !object.isEmpty();
	}

}

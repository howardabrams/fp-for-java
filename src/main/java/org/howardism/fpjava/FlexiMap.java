/* Copyright (c) 2012, Howard Abrams
 * All rights reserved.
 *
 * The skeleton and ideas for this code was taken from its original at:
 * http://svn.apache.org/repos/asf/commons/proper/functor/trunk/src/test/java/org/apache/commons/functor/example/FlexiMapExample.java
 * 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.howardism.fpjava;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * How can one Map implementation support all these behaviors?
 * Using functors and composition, of course.
 *
 * In order to keep our example small, we'll just consider the
 * primary Map.put and Map.get methods here, although the remaining
 * Map methods could be handled similiarly.
 *
 * @author Howard Abrams (www.howardabrams.com)
 */
public class FlexiMap implements Map<Object, Object> {

    /*
     * Our FlexiMap will accept two BinaryFunctions, one
     * that's used to transform objects being put into the Map,
     * and one that's used to transforms objects being retrieved
     * from the map.
     */
    public FlexiMap(Option<Closure> putfn, Option<Closure> getfn) {
    	if(putfn.present())
    		onPut = putfn.value();
    	if(getfn.present())
    		onGet = getfn.value();
        proxiedMap = new HashMap<Object, Object>();
    }


    /*
     * The arguments to our "onGet" function will be the
     * key and the value associated with that key in the
     * underlying Map.  We'll return whatever the function
     * returns.
     */
    public Object get(Object key) {
    	if (onGet != null)
    		return onGet.apply( key, proxiedMap.get(key) );
    	else
    		return proxiedMap.get(key);
    }

    /*
     * The arguments to our "onPut" function will be the
     * value previously associated with that key (if any),
     * as well as the new value being associated with that key.
     *
     * Since put returns the previously associated value,
     * we'll invoke onGet here as well.
     */
    public Object put(Object key, Object value) {
        Object oldvalue = proxiedMap.get(key);
        if (onPut != null)
        	proxiedMap.put(key, onPut.apply(oldvalue, value));
        else
        	proxiedMap.put(key, value);
        
        if (onGet != null)
        	return onGet.apply(key,oldvalue);
        else
        	return oldvalue;
    }

   /*
    * We'll skip the remaining Map methods for now.
    */

    public void clear() {
        throw new UnsupportedOperationException("Left as an exercise for the reader.");
    }

    public boolean containsKey(Object key) {
        throw new UnsupportedOperationException("Left as an exercise for the reader.");
    }

    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Left as an exercise for the reader.");
    }

    public Set<Map.Entry<Object, Object>> entrySet() {
        throw new UnsupportedOperationException("Left as an exercise for the reader.");
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException("Left as an exercise for the reader.");
    }

    public Set<Object> keySet() {
        throw new UnsupportedOperationException("Left as an exercise for the reader.");
    }

    public void putAll(Map<?, ?> t) {
        throw new UnsupportedOperationException("Left as an exercise for the reader.");
    }

    public Object remove(Object key) {
        throw new UnsupportedOperationException("Left as an exercise for the reader.");
    }

    public int size() {
        throw new UnsupportedOperationException("Left as an exercise for the reader.");
    }

    public Collection<Object> values() {
        throw new UnsupportedOperationException("Left as an exercise for the reader.");
    }

    // private BinaryFunction<Object, Object, Object> onPut = null;
    private Closure onPut = null;
    // private BinaryFunction<Object, Object, Object> onGet = null;
    private Closure onGet = null;
    private Map<Object, Object> proxiedMap = null;
}
/* Most of this code was pilfered and altered from its original at:
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.howardism.options1.None;
import org.junit.Test;

/**
 * Unit tests that validate using <i>composition</i> techniques to build
 * a flexible map based on {@link Closure}s.
 * <p>
 * The original unit tests were taken from
 * <a href="http://svn.apache.org/repos/asf/commons/proper/functor/trunk/src/test/java/org/apache/commons/functor/example/FlexiMapExample.java">
 *   this file
 * </a>, but implemented without the Apache
 * <a href="http://commons.apache.org/functor/index.html">
 *   Commons Functor library</a>.
 * </p><p>  
 * <b>Note:</b> While I find the Functor library useful, it seemed overly
 * complicated for initial discussion and teaching functional programming
 * concepts.
 * </p>
 * 
 * @author Howard Abrams (www.howardabrams.com)
 */
public class FlexiMapTest
{
	/*
	 * In a "test first" style, let's first specify the Map behaviour we'd like
	 * to implement via unit tests.
	 * 
	 * First, let's review the basic Map functionality.
	 * 
     * The basic Map interface lets one associate keys and values:
     */
    @Test
    public void testBasicMap() {
        /* (We'll define these make*Map functions below.) */
        Map<Object, Object> map = makeBasicMap();
        Object key = "key";
        Object value = new Integer(3);
        map.put(key,value);
        assertEquals(value, map.get(key) );
    }

    private Map<Object, Object> makeBasicMap() {
		return new FlexiMap( None.noclosure(), None.noclosure() );
	}

	/*
     * If there is no value associated with a key,
     * the basic Map will return null for that key:
     */
    @Test
    public void testBasicMapReturnsNullForMissingKey() {
        Map<Object, Object> map = makeBasicMap();
        assertNull( map.get("key") );
    }

    /*
     * One can also explicitly store a null value for
     * some key:
     */
    @Test
    public void testBasicMapAllowsNull() {
        Map<Object, Object> map = makeBasicMap();
        Object key = "key";
        Object value = null;
        map.put(key,value);
        assertNull( map.get(key) );
    }

    /*
     * The basic Map deals with Objects--it can store keys
     * and values of multiple or differing types:
     */
    @Test
    public void testBasicMapAllowsMultipleTypes() {
        Map<Object, Object> map = makeBasicMap();
        map.put("key-1","value-1");
        map.put(new Integer(2),"value-2");
        map.put("key-3",new Integer(3));
        map.put(new Integer(4),new Integer(4));

        assertEquals("value-1", map.get("key-1") );
        assertEquals("value-2", map.get(new Integer(2)) );
        assertEquals(new Integer(3), map.get("key-3") );
        assertEquals(new Integer(4), map.get(new Integer(4)) );
    }

    /*
     * Finally, note that putting a second value for a given
     * key will overwrite the first value--the basic Map only
     * stores the most recently put value for each key:
     */
    @Test
    public void testBasicMapStoresOnlyOneValuePerKey() {
        Map<Object, Object> map = makeBasicMap();

        assertNull( map.put("key","value-1") );
        assertEquals("value-1", map.get("key") );
        assertEquals("value-1", map.put("key","value-2"));
        assertEquals("value-2", map.get("key") );
    }

    /*
     * Now let's look at some specializations of the Map behavior.
     */

    /*
     * One common specialization is to forbid null values,
     * like our old friend Hashtable:
     */
    @Test
    public void testForbidNull() {
        Map<Object, Object> map = makeNullForbiddenMap();

        map.put("key","value");
        map.put("key2", new Integer(2) );
        try {
            map.put("key3",null);
            fail("Expected NullPointerException");
        } catch(NullPointerException e) {
            // expected
        }
    }

    private Map<Object, Object> makeNullForbiddenMap() {
		return new FlexiMap( new NoMoreNulls().getOption(),  /* put */ 
							 None.noclosure() );             /* get */
	}

    private class NoMoreNulls extends ClosureOption {

		public Object apply(Object... objects) {
			// All closures used for 'putFn' will have two parameters:
			//    0: old value
			//    1: new value
			assert objects.length == 2;
			
			if (objects[1] == null)
				throw new NullPointerException("This map implementation doesn't allow nulls.");
			else
				return objects[1];
		}
    	
    }
    
	/*
     * Alternatively, we may want to provide a default
     * value to return when null is associated with some
     * key. (This might be useful, for example, when the Map
     * contains a counter--when there's no count yet, we'll
     * want to treat it as zero.):
     */
    @Test
    public void testNullDefaultsToZero() {
        Map<Object, Object> map = makeDefaultValueForNullMap(new Integer(0));
        /*
         * We expect 0 when no value has been associated with "key".
         */
        assertEquals( new Integer(0), map.get("key") );
        /*
         * We also expect 0 when a null value has been associated with "key".
         */
        map.put("key", null);
        assertEquals( new Integer(0), map.get("key") );
    }

    private Map<Object, Object> makeDefaultValueForNullMap(Integer integer) {
		return new FlexiMap( None.noclosure(),                               // put 
					         new DefaultValueForNull(integer).getOption() ); // get
	}

    private class DefaultValueForNull extends ClosureOption {
    	final Object defaultValue;
    	
    	public DefaultValueForNull(Object value) {
    		this.defaultValue = value;
		}
    	
		public Object apply(Object... objects) {
			// All closures used for 'getFn' will have two parameters:
			//    0: key
			//    1: value currently stored in the map
			assert objects.length == 2;
			
			if (objects[1] == null)
				return defaultValue;
			else
				return objects[1];
		}

    }

    
	/*
     * Another common specialization is to constrain the type of values
     * that may be stored in the Map:
     */
    @Test
    public void testIntegerValuesOnly() {
        Map<Object, Object> map = makeTypeConstrainedMap(Integer.class);
        map.put("key", new Integer(2));
        assertEquals( new Integer(2), map.get("key") );
        try {
            map.put("key2","value");
            fail("Expected ClassCastException");
        } catch(ClassCastException e) {
            // expected
        }
    }

    private Map<Object, Object> makeTypeConstrainedMap(Class<Integer> class1) {
		return new FlexiMap( new ConstrainedPut(class1).getOption(), // put
				             None.noclosure() );                     // get
	}

	class ConstrainedPut extends ClosureOption {
		final Class<?> constraint;
		
		public ConstrainedPut(Class<Integer> c) {
			this.constraint = c;
		}
		
		public Object apply(Object... objects) {
			// All closures used for 'putFn' will have two parameters:
			//    0: old value
			//    1: new value
			assert objects.length == 2;
			
			if (objects[1].getClass() != constraint)
				throw new ClassCastException("This map implementation only allows values of type: "+constraint.getName());
			else
				return objects[1];
		}

	}


	/*
     * A more interesting specialization is that used by the
     * Apache Commons Collections MultiMap class, which allows
     * one to associate multiple values with each key.  The put
     * function still accepts a single value, but the get function
     * will return a Collection of values.  Associating multiple values
     * with a key adds to that collection, rather than overwriting the
     * previous value:
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testMultiMap() {
        Map<Object, Object> map = makeMultiMap();

        map.put("key", "value 1");

        {
            Collection<Object> result = (Collection<Object>)(map.get("key"));
            assertEquals(1,result.size());
            assertEquals("value 1", result.iterator().next());
        }

        map.put("key", "value 2");

        {
            Collection<Object> result = (Collection<Object>)(map.get("key"));
            assertEquals(2,result.size());
            Iterator<Object> iter = result.iterator();
            assertEquals("value 1", iter.next());
            assertEquals("value 2", iter.next());
        }

        map.put("key", "value 3");

        {
            Collection<Object> result = (Collection<Object>)(map.get("key"));
            assertEquals(3,result.size());
            Iterator<Object> iter = result.iterator();
            assertEquals("value 1", iter.next());
            assertEquals("value 2", iter.next());
            assertEquals("value 3", iter.next());
        }

    }

    private Map<Object, Object> makeMultiMap() {
		return new FlexiMap( new MultiMapPut().getOption(), // put
	                         None.noclosure() );            // get
	}

	class MultiMapPut extends ClosureOption {
		
		@SuppressWarnings("unchecked")
		public Object apply(Object... objects) {
			// All closures used for 'putFn' will have two parameters:
			//    0: The map for the current key (or null if new)
			//    1: The value to store...
			assert objects.length == 2;
			
			final List<Object> values;
			if (objects[0] == null)
				values = new ArrayList<Object>();
			else
				values = (List<Object>) objects[0];
			
			values.add( objects[1] );
			return values;
		}

	}

	/*
     * Here's another variation on the MultiMap theme.
     * Rather than adding elements to a Collection, let's
     * concatenate String values together, delimited by commas.
     * (Such a Map might be used by the Commons Collection's
     * ExtendedProperties type.):
     */
    @Test
    public void testStringConcatMap() {
        Map<Object, Object> map = makeStringConcatMap();
        map.put("key", "value 1");
        assertEquals("value 1",map.get("key"));
        map.put("key", "value 2");
        assertEquals("value 1, value 2",map.get("key"));
        map.put("key", "value 3");
        assertEquals("value 1, value 2, value 3",map.get("key"));
    }

	private Map<Object, Object> makeStringConcatMap() {
		return new FlexiMap( new MultiStringPut().getOption(), // put
                             None.noclosure() );               // get
	}

	class MultiStringPut extends ClosureOption {
		
		public Object apply(Object... objects) {
			// All closures used for 'putFn' will have two parameters:
			//    0: The string of values (or null if empty)
			//    1: The value to store...
			assert objects.length == 2;
			
			final String slist;
			if (objects[0] == null)          // First time in, we
				slist = (String) objects[1]; // just store the value
			else                             // Next time, we append
				slist = (String) objects[0] + ", " + (String) objects[1];
			
			return slist;
		}

	}

}

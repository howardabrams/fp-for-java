Function Programming Ideas for Java
===================================

The source code in this project (and its accompanying presentation)
demonstrate some principles of *functional programming* using standard
Java.

The concepts fall into the following categories:

Optional Parameters
-------------------

In order to get rid of the dreaded null pointer exceptions, you can
have a function accept parameters of type [Option][]. Only two classes
implement this interface: `None` and `Some`.

 * [None][]: returns `false` when `present()` function is called.
 * [Some][]: returns `true` when `present()` called, and returns results from `values()`

We can then have a function like:
 
    public FlexiMap( Option<Closure> putfn, Option<Closure> getfn ) {
      if(putfn.present())
        onPut = putfn.value();
      if(getfn.present())
        onGet = getfn.value();
      // ...
    }

This process makes it less likely to encounter with `null` values.


Closures
--------

The [Closure][] interface has a single... ahem... function inside:

    Object apply(Object ...objects);

This allows anonymous inner classes to pass simple functions around.
Of course, I find the syntax for anonymous inner classes quite icky,
and prefer to create private inner classes instead:

    private class DefaultValueForNull implements Closure {
	    final Object defaultValue;
    	
      public DefaultValueForNull(Object value) {
        this.defaultValue = value;
      }
    	
      public Object apply(Object... objects) {
        if (objects[0] == null)
          return defaultValue;
        else
          return objects[0];
      }
    }


Composition
-----------

To demonstrate the concept of creating specialized versions of a `Map` through
*composition* (instead of inheritance), we took [these unit tests][1] from
the Apache [Commons Functor library][2], but instead of using their libraries,
we used our own `Closure` classes (see [these this code][Test]).
Same principle, different approach.

  [1]: http://svn.apache.org/repos/asf/commons/proper/functor/trunk/src/test/java/org/apache/commons/functor/example/FlexiMapExample.java
  [2]: http://commons.apache.org/functor/index.html
  
  [Closure]: /howardabrams/fp-for-java/blob/master/src/main/java/org/howardism/fpjava/Closure.java
  [Option]: /howardabrams/fp-for-java/blob/master/src/main/java/org/howardism/fpjava/Option.java
  [None]: /howardabrams/fp-for-java/blob/master/src/main/java/org/howardism/fpjava/None.java
  [Some]: /howardabrams/fp-for-java/blob/master/src/main/java/org/howardism/fpjava/Some.java
  [Test]: /howardabrams/fp-for-java/blob/master/src/test/java/org/howardism/fpjava/FlexiMapTest.java
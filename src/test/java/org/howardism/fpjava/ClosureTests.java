/*
 * Copyright (c) 2012, Howard Abrams All rights reserved.
 * 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.howardism.fpjava;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

/**
 * Validate some basic assumptions for using and extending the {@link Closure}
 * interface.
 * 
 * @author Howard Abrams (www.howardabrams.com)
 */
public class ClosureTests {

    // List the closures we want to test...
    final Closure theTruth   = new TheTruth();
    final Closure conjecture = new Conjecture();

    final Closure sum = new Sum();

    @Test
    public void testBooleans() {
        assertTrue( (Boolean) theTruth.apply() );
        assertTrue( conjecture.apply() == Boolean.FALSE );
    }

    class TheTruth implements Closure {
        public Object apply(Object... objects) {
            return Boolean.TRUE;
        }
    }

    class Conjecture implements Closure {
        public Object apply(Object... objects) {
            return Boolean.FALSE;
        }
    }

    // ------------------------------------------------------------- //

    /**
     * The most standard closure is a <code>sum</code> function that acts
     * recursively on an arbitrary number of arguments.
     */
    @Test
    public void testSum() {
        assertEquals(10, new Closure() {
            public Object apply(Object... objects) {
                int total = 0;
                for (Object n : Arrays.asList(objects)) {
                    total += (Integer) n;
                }
                return total;
            }
        }.apply(1, 2, 3, 4));
    }

    /**
     * Could there be anything more obtuse and ugly than the previous code?
     * Let's see if breaking it out into bite-size morsels helps us out any.
     */
    @Test
    public void testSumAgain() {
        assertEquals(10, sum.apply(1,2,3,4));
    }

    class Sum extends RecursiveClosure {
        public Object apply(Object ... objects) {
            if (objects.length == 0)
                return 0;
            return (Integer) head(objects) +
                   (Integer) apply( tail(objects) );
        }
    }

    /**
     * To make a recursive call dealing with variable length parameter lists,
     * we create this class that has a head and a tail function.
     */
    abstract class RecursiveClosure implements Closure {
        <T> T head(T ... o) {
            return o[0];
        }
        <T> T[] tail(T ... o) {
            return Arrays.copyOfRange(o, 1, o.length-1);
        }
    }
}

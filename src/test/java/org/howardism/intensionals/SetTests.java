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

package org.howardism.intensionals;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * A collection of for playing with the {@link RealSet}.
 *
 */

public SetTests {

    /**
     * We can create a set with a single element in it with this.
     */
    public static Set<Integer> singleton(final Integer i) {
        return new Set<Integer>() {
            public boolean has(final Integer n) { return n == i; }
        };
    }

    /**
     * We can define the set of all even natural numbers with this.
     */
    public static Set<Integer> evens() {
        return new Set<Integer>() {
            public boolean has(final int n) { return n % 2 == 0; }
        };
    }

    /**
     * We can define the set of all even natural numbers with this.
     */
    public static Set<Integer> odds() {
        return new Set<Integer>() {
            public boolean has(final int n) { return n % 2 == 1; }
        };
    }

    @Test
    public void testUnion() {
        final Set<Integer> s1 = singleton(1);
        final Set<Integer> s2 = singleton(2);
        final Set<Integer> sT = SetUtils.union(s1, s2);

        assert (sT.has(1))
        assert (sT.has(2))
        assert (!sT.has(3))
    }
}

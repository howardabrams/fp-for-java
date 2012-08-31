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
}

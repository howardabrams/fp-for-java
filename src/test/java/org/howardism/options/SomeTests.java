/* Copyright (c) 2012, Howard Abrams
 * All rights reserved.
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

package org.howardism.options;

import static org.junit.Assert.*;

import org.howardism.options.None;
import org.howardism.options.Option;
import org.howardism.options.Some;
import org.junit.Test;

/**
 * Let's test and show examples of using the better version
 * of the {@link Option} interface, including both the
 * {@link None} and {@link Some} classes.
 * 
 * @author Howard Abrams (www.howardabrams.com)
 */
public class SomeTests {

	@Test
	public void testNoneDefault() {
		assertEquals(1, someFunc( new None<Integer>() ) );
	}

	@Test
	public void testSomeDefault() {
		assertEquals(6, someFunc( new Some<Integer>(5) ) );
	}

	public int someFunc(Option<Integer> p1) {
		return p1.get(0) + 1;
	}
}

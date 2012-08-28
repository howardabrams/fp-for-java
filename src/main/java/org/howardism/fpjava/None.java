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

package org.howardism.fpjava;

/**
 * A parameter that isn't set is called <b>None</b> (see also the {@link Some}
 * class.
 * 
 * @author Howard Abrams (www.howardabrams.com)
 * @param <T> The type the optional parameter should expect.
 */
public class None<T> implements Option<T>
{
	/**
	 * This singleton is safe to use and pass around.
	 */
	public static None<Object> noparameter = new None<Object>();
	
	/**
	 * Gets an instance of this class in case we really want to pretend were still
	 * living in an imperative world.
	 * @return The <code>noparameter</code> singleton instance.
	 */
	public static None<Closure> noclosure() {
		return new None<Closure>();
	}
	
	/**
	 * Always returns <code>false</code>, since this class means no value available.
	 * @see org.howardism.fpjava.Option#present()
	 */
	public boolean present() {
		return false;
	}

	/**
	 * Always returns <code>null</code> since no value is available.
	 * @see org.howardism.fpjava.Option#value()
	 */
	public T value() {
		return null;
	}

}

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
 * In order to get rid of <code>null</code>s in your code, think of what a null
 * really means... an optional value. So why not call them as such? Two classes
 * implement this {@link Some} and {@link None}.
 * 
 * @author Howard Abrams (www.howardabrams.com)
 */
public interface Option<T>
{
	/**
	 * Returns <code>true</code> if this option has a value.
	 * @return <code>true</code> if {@link #value} will return a value, <code>false</code> otherwise.
	 */
	public boolean present();
	
	/**
	 * Returns the value stored in this parameter.
	 * @return the value stored in this parameter.
	 */
	public T value();
}

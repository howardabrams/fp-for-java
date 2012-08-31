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

import org.howardism.options.Option;
import org.howardism.options.Some;

/**
 * A way to easily create a closure that works better than a
 * simple anonymous class.
 * 
 * @author Howard Abrams (www.howardabrams.com)
 */
public abstract class ClosureOption implements Closure {

	/**
	 * Return this new object wrapped up in an {@link Option}.
	 * @return an {@link Option} containing the current instance.
	 */
	public Option<Closure> getOption() {
		return new Some<Closure>( this );
	}

}

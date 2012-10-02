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

/**
 * This is nearer in concept to a mathematical <i>intensional</i> set.  This is not
 * a fixed collection of numbers, but can contains an infinite number
 * of entries, for instance:
 *
 * <pre>
 * public static Set evens() {
 *   return new Set() {
 *     public boolean has(final int n) { return n % 2 == 0; }
 *   };
 * }
 * </pre>
 *
 * @see SetUtils
 * @author Howard Abrams <howard.abrams@gmail.com>
 */

interface Set<T> {
    boolean has(T i); 
}

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

package org.howardism.options;

/**
 * An optional parameter that contains an actual value.
 * 
 * @author Howard Abrams (www.howardabrams.com)
 */
public class Some<T> implements Option<T>
{
    final T value;

    /**
     * Creating a <code>Some</code> instance requires a value given at
     * construction time.
     * 
     * @param value
     *            A value of some sort.
     */
    public Some(final T value) {
        this.value = value;
    }

    /**
     * If the value originally assigned to this parameter is not
     * <code>null</code>, this returns <code>true</code>.
     * 
     * @return <code>true</code> if a value has been assigned,
     *         <code>false</code> otherwise.
     * @see org.howardism.options.Option#get()
     */
    public boolean isPresent() {
        return value != null;
    }

    /**
     * Returns the value originally assign to this option. Any default value
     * parameter given is ignored.
     * 
     * @see org.howardism.options.Option#get()
     */
    public T get(final T ignoredValue) {
        return value;
    }

    /**
     * Returns the value originally assign to this option.
     * 
     * @see org.howardism.options.Option#get()
     */
    public T get() {
        return value;
    }
}

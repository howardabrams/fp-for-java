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
 * A parameter that isn't set is called <b>None</b> (see also the {@link Some}
 * class.
 * 
 * @author Howard Abrams (www.howardabrams.com)
 * @param <T>
 *            The type the optional parameter should expect.
 */
public class None<T> implements Option<T>
{
    /**
     * This singleton is safe to use and pass around.
     */
    public static None<Object> noparameter = new None<Object>();

    /**
     * Always returns <code>false</code>, since this class means no value
     * available.
     * 
     * @see org.howardism.options.Option#isPresent()
     */
    public boolean isPresent() {
        return false;
    }

    /**
     * Always returns the default value since no other value is available.
     * 
     * @see org.howardism.options.Option#get()
     */
    public T get(final T defaultValue) {
        return defaultValue;
    }

    /**
     * Always returns <code>null</code> since no other value is available. This
     * is dangerous, and should never be called.
     * 
     * @see org.howardism.options.Option#get()
     */
    public T get() {
        return null;
    }

    /**
     * Syntactic sugar for creating a <code>None</code> instance without
     * needing to specify the type.
     * For example, if a function took an {@link Option}, you could pass:
     * <pre>
     *    logMessage ( None.thing() );
     * </pre>
     * The above example is equivalent to:
     * <pre>
     *    logMessage ( new None<String>() );
     * </pre>
     * The other advantage is that seems a little clearer.
     *
     * @return   A None instance
     */
    public static <S> Option<S> thing() {
        return new None<S>();
    }

    /**
     * Convenience object that can be easily used in function calls.
     */
    public static None<Integer> noInteger = new None<Integer>();
    public static None<Double>  noDouble  = new None<Double>();
    public static None<Float>   noFloat   = new None<Float>();
    public static None<Short>   noShort   = new None<Short>();
    public static None<String>  noString  = new None<String>();
    public static None<Object>  noObject  = new None<Object>();

}

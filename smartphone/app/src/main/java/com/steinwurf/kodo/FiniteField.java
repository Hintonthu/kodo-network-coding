// Copyright Steinwurf ApS 2014.
// Distributed under the "STEINWURF RESEARCH LICENSE 1.0".
// See accompanying file LICENSE.rst or
// http://www.steinwurf.com/licensing

package com.steinwurf.kodo;

/**
 * Represents the available finite fields.
 */
public enum FiniteField
{
    BINARY(0),
    BINARY4(1),
    BINARY8(2),
    BINARY16(3),
    PRIME2325(4);

    private final int value;

    private FiniteField(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return this.value;
    }
}

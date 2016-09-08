// Copyright Steinwurf ApS 2014.
// Distributed under the "STEINWURF RESEARCH LICENSE 1.0".
// See accompanying file LICENSE.rst or
// http://www.steinwurf.com/licensing

package com.steinwurf.kodo;

/**
 * Represents the available code types in Kodo.
 */
public enum CodeType
{
    FULL_RLNC(0),
    ON_THE_FLY(1);

    private final int value;

    private CodeType(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return this.value;
    }
}

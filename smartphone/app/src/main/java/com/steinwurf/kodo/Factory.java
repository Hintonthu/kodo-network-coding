// Copyright Steinwurf ApS 2014.
// Distributed under the "STEINWURF RESEARCH LICENSE 1.0".
// See accompanying file LICENSE.rst or
// http://www.steinwurf.com/licensing

package com.steinwurf.kodo;

/**
 * Represents the common interface of Kodo factories.
 */
public class Factory
{
    protected long factoryAddress;

    protected Factory(long factoryAddress)
    {
        this.factoryAddress = factoryAddress;
    }

    /**
     * Sets the number of symbols which should be used for the subsequent
     * encoders / decoders built with the specified factory. The value must
     * be below the max symbols used for the specific factory.
     * @param symbols The number of symbols used for the next encoder/decoder
     *        built with the factory
     */
    public void setSymbols(int symbols)
    {
        KodoJava.factorySetSymbols(factoryAddress, symbols);
    }

    /**
     * Sets the symbol size which should be used for the subsequent
     * encoders / decoders built with the specified factory. The value must
     * be below the max symbols used for the specific factory.
     * @param symbolSize The symbol size used for the next encoder/decoder
     *        built with the factory.
     */
    public void setSymbolSize(int symbolSize)
    {
        KodoJava.factorySetSymbolSize(factoryAddress, symbolSize);
    }

    /**
     * Returns the maximum number of symbols supported by the factory.
     * @return The maximum number of symbols
     */
    public int maxSymbols()
    {
        return KodoJava.factoryMaxSymbols(factoryAddress);
    }

    /**
     * Returns the maximum symbol size supported by the factory.
     * @return The maximum symbol size in bytes
     */
    public int maxSymbolSize()
    {
        return KodoJava.factoryMaxSymbolSize(factoryAddress);
    }

    /**
     * Returns the maximum block size supported by the factory.
     * @return The maximum amount of data to be encoded / decoded in bytes.
     *         This is calculated by multiplying the maximum number of symbols
     *         by the maximum size of a symbol.
     */
    public int maxBlockSize()
    {
        return KodoJava.factoryMaxBlockSize(factoryAddress);
    }

    /**
     * Returns the maximum payload size supported by the factory.
     * @return The maximum payload buffer size in bytes
     */
    public int maxPayloadSize()
    {
        return KodoJava.factoryMaxPayloadSize(factoryAddress);
    }
}

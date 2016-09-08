// Copyright Steinwurf ApS 2011-2013.
// Distributed under the "STEINWURF RESEARCH LICENSE 1.0".
// See accompanying file LICENSE.rst or
// http://www.steinwurf.com/licensing

package com.steinwurf.kodo;

/**
 * Represents the interface of a Kodo encoder.
 */
public class Encoder extends Coder
{
    /**
     * This class can only be instantiated by an encoder factory.
     */
    Encoder(long coderAddress)
    {
        super(coderAddress);
    }

    /**
     * Deallocates and releases the memory consumed by the native encoder.
     */
    protected void finalize() throws Throwable
    {
        KodoJava.deleteEncoder(coderAddress);
    }

    /**
     * Specifies the source data for all symbols. This will specify all
     * symbols also in the case of partial data. If this is not desired,
     * then the symbols should be specified individually. This also
     * means that it is the responsibility of the user to communicate
     * how many of the bytes transmitted are application data.
     * @param data The buffer containing the data to be encoded
     * @param size The size of the buffer to be encoded
    */
    public void setSymbols(byte[] data, int size)
    {
        KodoJava.setSymbols(coderAddress, data, size);
    }

    /**
     * Specifies the source data for a given symbol.
     * @param index The index of the symbol in the coding block
     * @param data The buffer containing the data to be encoded
     * @param size The size of the symbol buffer
    */
    public void setSymbol(int index, byte[] data, int size)
    {
        KodoJava.setSymbol(coderAddress, index, data, size);
    }

    /**
     * Returns whether an encoder has systematic capabilities.
     * @return true if the encoder supports the systematic mode, otherwise false
     */
    public boolean hasSetSystematicOff()
    {
        return KodoJava.hasSetSystematicOff(coderAddress);
    }

    /**
     * Returns whether the encoder is in the systematic mode, i.e. if it will
     * initially send the original source symbols with a simple header.
     * @return true if the encoder is in the systematic mode, otherwise false
     */
    public boolean isSystematicOn()
    {
        return KodoJava.isSystematicOn(coderAddress);
    }

    /**
     * Switches the systematic encoding on
     */
    public void setSystematicOn()
    {
        KodoJava.setSystematicOn(coderAddress);
    }

    /**
     * Switches the systematic encoding off
     */
    public void setSystematicOff()
    {
        KodoJava.setSystematicOff(coderAddress);
    }

    /**
     * Reads the feedback information from the provided feedback buffer.
     * @param feedback The buffer which contains the feedback information
     */
    public void readFeedback(byte[] feedback)
    {
        KodoJava.readFeedback(coderAddress, feedback);
    }
}

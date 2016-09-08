// Copyright Steinwurf ApS 2011-2013.
// Distributed under the "STEINWURF RESEARCH LICENSE 1.0".
// See accompanying file LICENSE.rst or
// http://www.steinwurf.com/licensing

package com.steinwurf.kodo;

/**
 * Represents the interface of a Kodo decoder.
 */
public class Decoder extends Coder
{
    /**
     * This class can only be instantiated by a decoder factory.
     */
    Decoder(long coderAddress)
    {
        super(coderAddress);
    }

    /**
     * Deallocates and releases the memory consumed by the native decoder.
     */
    protected void finalize() throws Throwable
    {
        KodoJava.deleteDecoder(coderAddress);
    }

    /**
     * Reads the coded symbol in the payload buffer. The decoder state is
     * updated during this operation.
     * @param payload The buffer storing the payload of the coded symbol.
     */
    public void readPayload(byte[] data)
    {
        KodoJava.readPayload(coderAddress, data);
    }

    /**
     * Checks whether decoding is complete.
     * @return true if the decoding is complete, otherwise false
     */
    public boolean isComplete()
    {
        return KodoJava.isComplete(coderAddress);
    }

    /**
     * Copies the decoded symbols to the provided buffer.
     * @param data The destination buffer to which the data should be copied
     * @param size The size of the data to be copied
     */
    public void copySymbols(byte[] data, int size)
    {
        KodoJava.copySymbols(coderAddress, data, size);
    }

    /**
     * Copies a specific symbol to the provided buffer.
     * @param index The index of the symbol to copy
     * @param data The destination buffer to which the data should be copied
     * @param size The size of the data to be copied
     */
    public void copySymbol(int index, byte[] data, int size)
    {
        KodoJava.copySymbol(coderAddress, index, data, size);
    }

    /**
     * Check whether the decoder supports partial decoding. This means
     * that the decoder is able to decode symbols on-the-fly.
     * If the decoder supports the partial decoding tracker, then the
     * isPartialComplete() function may be used to determine whether some of
     * the symbols are fully decoded and therefore can be copied out of the
     * decoder.
     * @return true if the decoder supports partial decoding
     */
    public boolean hasPartialDecodingTracker()
    {
        return KodoJava.hasPartialDecodingTracker(coderAddress);
    }

    /**
     * Check whether decoding is partially complete. This means that some
     * symbols in the decoder are fully decoded. You can use the
     * isSymbolUncoded() function to determine which symbols.
     * @return true if the decoding is partially complete, otherwise false
     */
    public boolean isPartialComplete()
    {
        return KodoJava.isPartialComplete(coderAddress);
    }

    /**
     * Indicates whether a symbol is available in an uncoded
     * (i.e. fully decoded) form in an encoder/decoder.
     * @param index Index of the symbol whose state should be checked
     * @return true if the symbol is , otherwise false
     */
    public boolean isSymbolUncoded(int index)
    {
        return KodoJava.isSymbolUncoded(coderAddress, index);
    }

    /**
     * Returns the number of uncoded symbols.
     * @return The number of uncoded symbols in the decoder
     */
    public int symbolsUncoded()
    {
        return KodoJava.symbolsUncoded(coderAddress);
    }


    /**
     * Writes the feedback information into the provided buffer.
     * @param feedback The buffer which should contain the feedback information.
     * @return The total bytes used from the feeback buffer
     */
    public void writeFeedback(byte[] feedback)
    {
        KodoJava.writeFeedback(coderAddress, feedback);
    }
}

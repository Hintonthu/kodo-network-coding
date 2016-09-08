// Copyright Steinwurf ApS 2011-2013.
// Distributed under the "STEINWURF RESEARCH LICENSE 1.0".
// See accompanying file LICENSE.rst or
// http://www.steinwurf.com/licensing

package com.steinwurf.kodo;

/**
 * Represents the common interface of encoders and decoders.
 */
public class Coder
{
    protected long coderAddress;

    /**
     * This class cannot be instantiated directly.
     */
    protected Coder(long coderAddress)
    {
        this.coderAddress = coderAddress;
    }

    /**
     * Returns the block size of an encoder/decoder.
     * @return the block size, i.e. the total size in bytes
     *         that this coder operates on.
     */
    public int blockSize()
    {
        return KodoJava.blockSize(coderAddress);
    }

    /**
     * Returns the payload size of an encoder/decoder, which is the maximum
     * size of a generated payload.
     * @return The required payload buffer size in bytes
     */
    public int payloadSize()
    {
        return KodoJava.payloadSize(coderAddress);
    }

    /**
     * The rank of a decoder indicates how many symbols have been decoded
     * or partially decoded. The rank of an encoder indicates how many symbols
     * are available for encoding.
     * @return The rank of the encoder/decoder
     */
    public int rank()
    {
        return KodoJava.rank(coderAddress);
    }

    /**
     * Returns the symbol size of an encoder/decoder.
     * @return The size of a symbol in bytes
     */
    public int symbolSize()
    {
        return KodoJava.symbolSize(coderAddress);
    }

    /**
     * Returns the number of symbols in a block (i.e. the generation size).
     * @return The number of symbols
     */
    public int symbols()
    {
        return KodoJava.symbols(coderAddress);
    }

    /**
     * Indicates whether a symbol is defined in the coding matrix
     * of an encoder/decoder. A symbol with a pivot element might not be fully
     * decoded in the coding matrix of a decoder, therefore use the
     * Decoder.isSymbolUncoded() function to check if a symbol is fully decoded.
     * @param index Index of the symbol whose state should be checked
     * @return true if the symbol is defined, otherwise false
     */
    public boolean isSymbolPivot(int index)
    {
        return KodoJava.isSymbolPivot(coderAddress, index);
    }

    /**
     * Returns whether the encoder/decoder has trace capabilities
     * @return true if tracing is supported, otherwise false
     */
    public boolean hasTrace()
    {
        return KodoJava.hasTrace(coderAddress);
    }

    /**
     * Enables the default trace function of the encoder/decoder, which prints
     * to the standard output.
     */
    public void trace()
    {
        KodoJava.trace(coderAddress);
    }

    /**
     * Checks whether the encoder or decoder can use/provide feedback
     * information. The encoder can disregard some symbols if the feedback
     * from the decoder indicates that those symbols were already decoded.
     * @return true if feedback is supported, otherwise false
     */
    public boolean hasFeedbackSize()
    {
        return KodoJava.hasFeedbackSize(coderAddress);
    }

    /**
     * Returns the feedback size of an encoder/decoder.
     * @return The size of the required feedback buffer in bytes
     */
    public int feedbackSize()
    {
        return KodoJava.feedbackSize(coderAddress);
    }

    /**
     * Writes a systematic/coded symbol into the provided payload buffer.
     * @param payload The buffer which should contain the (re/en)coded
     *        symbol.
     * @return The total bytes written to the payload buffer
     */
    public int writePayload(byte[] payload)
    {
        return KodoJava.writePayload(coderAddress, payload);
    }
}

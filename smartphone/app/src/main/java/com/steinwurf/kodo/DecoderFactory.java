// Copyright Steinwurf ApS 2014.
// Distributed under the "STEINWURF RESEARCH LICENSE 1.0".
// See accompanying file LICENSE.rst or
// http://www.steinwurf.com/licensing

package com.steinwurf.kodo;

/**
 * A decoder factory is used to build decoders with the specified parameters.
 */
public class DecoderFactory extends Factory
{
    /**
     * @param code The decoder type.
     * @param field The finite field that should be used by the decoder.
     * @param maxSymbols The maximum number of symbols supported by decoders
     *        built with this factory.
     * @param maxSymbolSize The maximum symbol size in bytes supported by
     *        decoders built with this factory.
     * @param traceEnabled Determines if tracing is enabled or not.
     */
    public DecoderFactory(
        CodeType code, FiniteField field, int maxSymbols,
        int maxSymbolSize, boolean traceEnabled)
    {
        super(KodoJava.newDecoderFactory(
            code.getValue(), field.getValue(), maxSymbols,
            maxSymbolSize, traceEnabled));
    }

    /**
     * @param code The decoder type.
     * @param field The finite field that should be used by the decoder.
     * @param maxSymbols The maximum number of symbols supported by decoders
     *        built with this factory.
     * @param maxSymbolSize The maximum symbol size in bytes supported by
     *        decoders built with this factory.
     */
    public DecoderFactory(
        CodeType code, FiniteField field, int maxSymbols,
        int maxSymbolSize)
    {
        super(KodoJava.newDecoderFactory(
            code.getValue(), field.getValue(), maxSymbols,
            maxSymbolSize, false));
    }

    /**
     * Deallocates and releases the memory consumed by the native factory.
     */
    protected void finalize() throws Throwable
    {
        KodoJava.deleteDecoderFactory(factoryAddress);
    }

    /**
     * Builds a new decoder using the specified parameters of the factory.
     */
    public Decoder build()
    {
        return new Decoder(KodoJava.factoryNewDecoder(factoryAddress));
    }
}

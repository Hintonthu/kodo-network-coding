// Copyright Steinwurf ApS 2014.
// Distributed under the "STEINWURF RESEARCH LICENSE 1.0".
// See accompanying file LICENSE.rst or
// http://www.steinwurf.com/licensing

package com.steinwurf.kodo;

/**
 * An encoder factory is used to build encoders with the specified parameters.
 */
public class EncoderFactory extends Factory
{
    /**
     * @param code The encoder type.
     * @param field The finite field that should be used by the encoder.
     * @param maxSymbols The maximum number of symbols supported by encoders
     *        built with this factory.
     * @param maxSymbolSize The maximum symbol size in bytes supported by
     *        encoders built with this factory.
     * @param traceEnabled Determines if tracing is enabled or not.
     */
    public EncoderFactory(
        CodeType code, FiniteField field, int maxSymbols,
        int maxSymbolSize, boolean traceEnabled)
    {
        super(KodoJava.newEncoderFactory(
            code.getValue(), field.getValue(), maxSymbols,
            maxSymbolSize, traceEnabled));
    }

    /**
     * @param code The encoder type.
     * @param field The finite field that should be used by the encoder.
     * @param maxSymbols The maximum number of symbols supported by encoders
     *        built with this factory.
     * @param maxSymbolSize The maximum symbol size in bytes supported by
     *        encoders built with this factory.
     */
    public EncoderFactory(
        CodeType code, FiniteField field, int maxSymbols,
        int maxSymbolSize)
    {
        super(KodoJava.newEncoderFactory(
            code.getValue(), field.getValue(), maxSymbols,
            maxSymbolSize, false));
    }

    /**
     * Deallocates and releases the memory consumed by the native factory.
     */
    protected void finalize() throws Throwable
    {
        KodoJava.deleteEncoderFactory(factoryAddress);
    }

    /**
     * Builds a new encoder using the specified parameters of the factory.
     */
    public Encoder build()
    {
        return new Encoder(KodoJava.factoryNewEncoder(factoryAddress));
    }
}

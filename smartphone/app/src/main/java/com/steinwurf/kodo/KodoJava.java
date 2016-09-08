// Copyright Steinwurf ApS 2014.
// Distributed under the "STEINWURF RESEARCH LICENSE 1.0".
// See accompanying file LICENSE.rst or
// http://www.steinwurf.com/licensing

package com.steinwurf.kodo;

// Internal class for mapping Java functions to C++ with JNI
class KodoJava
{
    static
    {
        System.loadLibrary("kodo_java");
    }

    // Factory methods

    public static native long newEncoderFactory(
        int code, int field, int maxSymbols, int maxSymbolSize,
        boolean traceEnabled);

    public static native long newDecoderFactory(
        int code, int field, int maxSymbols, int maxSymbolSize,
        boolean traceEnabled);

    public static native void deleteEncoderFactory(long factory);
    public static native void deleteDecoderFactory(long factory);

    public static native long factoryNewDecoder(long factory);
    public static native long factoryNewEncoder(long factory);

    public static native void deleteEncoder(long coder);
    public static native void deleteDecoder(long coder);

    public static native void factorySetSymbols(long factory, int symbols);

    public static native void factorySetSymbolSize(
        long factory, int symbolSize);

    public static native int factoryMaxSymbols(long factory);

    public static native int factoryMaxSymbolSize(long factory);

    public static native int factoryMaxBlockSize(long factory);

    public static native int factoryMaxPayloadSize(long factory);


    // Coder methods

    public static native int blockSize(long coder);

    public static native int payloadSize(long coder);

    public static native int rank(long coder);

    public static native int symbolSize(long coder);

    public static native int symbols(long coder);

    public static native boolean isSymbolPivot(long coder, int index);

    public static native boolean hasTrace(long coder);

    public static native void trace(long coder);

    public static native boolean hasFeedbackSize(long coder);

    public static native int feedbackSize(long coder);

    public static native int writePayload(long coder, byte[] payload);


    // Encoder methods

    public static native void setSymbols(
        long coder, byte[] data, int size);

    public static native void setSymbol(
        long coder, int index, byte[] data, int size);

    public static native boolean hasSetSystematicOff(long coder);

    public static native boolean isSystematicOn(long coder);

    public static native void setSystematicOn(long coder);

    public static native void setSystematicOff(long coder);

    public static native void readFeedback(long coder, byte[] feedback);


    // Decoder methods

    public static native void readPayload(long coder, byte[] data);

    public static native boolean isComplete(long coder);

    public static native void copySymbols(long coder, byte[] data, int size);

    public static native void copySymbol(
        long coder, int index, byte[] data, int size);

    public static native boolean hasPartialDecodingTracker(long coder);

    public static native boolean isPartialComplete(long coder);

    public static native boolean isSymbolUncoded(long coder, int index);

    public static native int symbolsUncoded(long coder);

    public static native void writeFeedback(long coder, byte[] feedback);
}

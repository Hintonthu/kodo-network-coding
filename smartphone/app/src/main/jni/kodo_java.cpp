// Copyright Steinwurf ApS 2014.
// Distributed under the "STEINWURF RESEARCH LICENSE 1.0".
// See accompanying file LICENSE.rst or
// http://www.steinwurf.com/licensing

#include <jni.h>

#include <kodoc/kodoc.h>

#ifdef __cplusplus
extern "C"
{
#endif

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM* vm, void* /*reserved*/)
{
    JNIEnv* env;
    if (vm->GetEnv((void**)&env, JNI_VERSION_1_6) != JNI_OK)
    {
        return -1;
    }

    // Get jclass with env->FindClass.
    // Register methods with env->RegisterNatives.

    return JNI_VERSION_1_6;
}

//------------------------------------------------------------------
// FACTORY API
//------------------------------------------------------------------

JNIEXPORT jlong JNICALL Java_com_steinwurf_kodo_KodoJava_newEncoderFactory
    (JNIEnv* /*env*/, jclass /*_this*/, jint code, jint field,
    jint max_symbols, jint max_symbol_size, jboolean trace_enabled)
{
    return (long) kodoc_new_encoder_factory(
        code, field, max_symbols, max_symbol_size);
}

JNIEXPORT jlong JNICALL Java_com_steinwurf_kodo_KodoJava_newDecoderFactory
    (JNIEnv* /*env*/, jclass /*_this*/, jint code, jint field,
    jint max_symbols, jint max_symbol_size, jboolean trace_enabled)
{
    return (long) kodoc_new_decoder_factory(
        code, field, max_symbols, max_symbol_size);
}

JNIEXPORT void JNICALL Java_com_steinwurf_kodo_KodoJava_deleteEncoderFactory
    (JNIEnv* /*env*/, jclass /*_this*/, jlong factory)
{
    kodoc_delete_factory((kodoc_factory_t)factory);
}

JNIEXPORT void JNICALL Java_com_steinwurf_kodo_KodoJava_deleteDecoderFactory
    (JNIEnv* /*env*/, jclass /*_this*/, jlong factory)
{
    kodoc_delete_factory((kodoc_factory_t)factory);
}

JNIEXPORT jlong JNICALL Java_com_steinwurf_kodo_KodoJava_factoryNewEncoder
    (JNIEnv* /*env*/, jclass /*_this*/, jlong factory)
{
    return (long) kodoc_factory_build_coder((kodoc_factory_t)factory);
}

JNIEXPORT jlong JNICALL Java_com_steinwurf_kodo_KodoJava_factoryNewDecoder
    (JNIEnv* /*env*/, jclass /*_this*/, jlong factory)
{
    return (long) kodoc_factory_build_coder((kodoc_factory_t)factory);
}

JNIEXPORT void JNICALL Java_com_steinwurf_kodo_KodoJava_deleteEncoder
    (JNIEnv* /*env*/, jclass /*_this*/, jlong coder)
{
    kodoc_delete_coder((kodoc_coder_t)coder);
}

JNIEXPORT void JNICALL Java_com_steinwurf_kodo_KodoJava_deleteDecoder
    (JNIEnv* /*env*/, jclass /*_this*/, jlong coder)
{
    kodoc_delete_coder((kodoc_coder_t)coder);
}

JNIEXPORT void JNICALL Java_com_steinwurf_kodo_KodoJava_factorySetSymbols
  (JNIEnv* /*env*/, jclass /*_this*/, jlong factory, jint symbols)
{
    kodoc_factory_set_symbols((kodoc_factory_t)factory, symbols);
}

JNIEXPORT void JNICALL Java_com_steinwurf_kodo_KodoJava_factorySetSymbolSize
  (JNIEnv* /*env*/, jclass /*_this*/, jlong factory, jint symbol_size)
{
    kodoc_factory_set_symbol_size((kodoc_factory_t)factory, symbol_size);
}

JNIEXPORT jlong JNICALL Java_com_steinwurf_kodo_KodoJava_factoryMaxSymbols
  (JNIEnv* /*env*/, jclass /*_this*/, jlong factory)
{
    return kodoc_factory_max_symbols((kodoc_factory_t)factory);
}

JNIEXPORT jlong JNICALL Java_com_steinwurf_kodo_KodoJava_factoryMaxSymbolSize
  (JNIEnv* /*env*/, jclass /*_this*/, jlong factory)
{
    return kodoc_factory_max_symbol_size((kodoc_factory_t)factory);
}

JNIEXPORT jlong JNICALL Java_com_steinwurf_kodo_KodoJava_factoryMaxBlockSize
  (JNIEnv* /*env*/, jclass /*_this*/, jlong factory)
{
    return kodoc_factory_max_block_size((kodoc_factory_t)factory);
}

JNIEXPORT jlong JNICALL Java_com_steinwurf_kodo_KodoJava_factoryMaxPayloadSize
  (JNIEnv* /*env*/, jclass /*_this*/, jlong factory)
{
    return kodoc_factory_max_payload_size((kodoc_factory_t)factory);
}

//------------------------------------------------------------------
// CODER API
//------------------------------------------------------------------

JNIEXPORT jlong JNICALL Java_com_steinwurf_kodo_KodoJava_blockSize
  (JNIEnv* /*env*/, jclass /*_this*/, jlong coder)
{
    return kodoc_block_size((kodoc_coder_t)coder);
}

JNIEXPORT jlong JNICALL Java_com_steinwurf_kodo_KodoJava_payloadSize
  (JNIEnv* /*env*/, jclass /*_this*/, jlong coder)
{
    return kodoc_payload_size((kodoc_coder_t)coder);
}

JNIEXPORT jlong JNICALL Java_com_steinwurf_kodo_KodoJava_rank
  (JNIEnv* /*env*/, jclass /*_this*/, jlong coder)
{
    return kodoc_rank((kodoc_coder_t)coder);
}

JNIEXPORT jlong JNICALL Java_com_steinwurf_kodo_KodoJava_symbolSize
  (JNIEnv* /*env*/, jclass /*_this*/, jlong coder)
{
    return kodoc_symbol_size((kodoc_coder_t)coder);
}

JNIEXPORT jlong JNICALL Java_com_steinwurf_kodo_KodoJava_symbols
  (JNIEnv* /*env*/, jclass /*_this*/, jlong coder)
{
    return kodoc_symbols((kodoc_coder_t)coder);
}

JNIEXPORT jboolean JNICALL Java_com_steinwurf_kodo_KodoJava_isSymbolPivot
  (JNIEnv* /*env*/, jclass /*_this*/, jlong coder, jint index)
{
    if (kodoc_is_symbol_pivot((kodoc_coder_t)coder, index) != 0)
    {
        return JNI_TRUE;
    }
    else
    {
        return JNI_FALSE;
    }
}

JNIEXPORT jboolean JNICALL Java_com_steinwurf_kodo_KodoJava_hasTrace
  (JNIEnv* /*env*/, jclass /*_this*/, jlong coder)
{
    if (kodoc_has_trace_interface((kodoc_coder_t)coder) != 0)
    {
        return JNI_TRUE;
    }
    else
    {
        return JNI_FALSE;
    }
}

JNIEXPORT void JNICALL Java_com_steinwurf_kodo_KodoJava_trace
  (JNIEnv* /*env*/, jclass /*_this*/, jlong coder)
{
    kodoc_set_trace_stdout((kodoc_coder_t)coder);
}

JNIEXPORT jboolean JNICALL Java_com_steinwurf_kodo_KodoJava_hasFeedbackSize
  (JNIEnv* /*env*/, jclass /*_this*/, jlong coder)
{
    if (kodoc_has_feedback_size((kodoc_coder_t)coder) != 0)
    {
        return JNI_TRUE;
    }
    else
    {
        return JNI_FALSE;
    }
}

JNIEXPORT jlong JNICALL Java_com_steinwurf_kodo_KodoJava_feedbackSize
  (JNIEnv* /*env*/, jclass /*_this*/, jlong coder)
{
    return kodoc_feedback_size((kodoc_coder_t)coder);
}

JNIEXPORT jlong JNICALL Java_com_steinwurf_kodo_KodoJava_writePayload
  (JNIEnv* env, jclass /*_this*/, jlong encoder, jbyteArray payload)
{
    jbyte* payloadPtr = env->GetByteArrayElements(payload, NULL);
    long result = 0;

    if (payloadPtr != NULL)
    {
        result =
            kodoc_write_payload((kodoc_coder_t)encoder, (uint8_t*)payloadPtr);
        env->ReleaseByteArrayElements(payload, payloadPtr, 0);
    }

    return result;
}

//------------------------------------------------------------------
// ENCODER API
//------------------------------------------------------------------

JNIEXPORT void JNICALL Java_com_steinwurf_kodo_KodoJava_setSymbols
  (JNIEnv* env, jclass /*_this*/, jlong encoder, jbyteArray data, jint size)
{
    jbyte* dataPtr = env->GetByteArrayElements(data, NULL);

    if (dataPtr != NULL)
    {
        kodoc_set_const_symbols((kodoc_coder_t)encoder, (uint8_t*)dataPtr, size);
        env->ReleaseByteArrayElements(data, dataPtr, JNI_ABORT);
    }
}

JNIEXPORT void JNICALL Java_com_steinwurf_kodo_KodoJava_setSymbol
  (JNIEnv* env, jclass /*_this*/, jlong encoder, jint index, jbyteArray data,
   jint size)
{
    jbyte* dataPtr = env->GetByteArrayElements(data, NULL);

    if (dataPtr != NULL)
    {
        kodoc_set_const_symbol((kodoc_coder_t)encoder, index, (uint8_t*)dataPtr, size);
        env->ReleaseByteArrayElements(data, dataPtr, JNI_ABORT);
    }
}

JNIEXPORT jboolean JNICALL Java_com_steinwurf_kodo_KodoJava_isSystematicOn
  (JNIEnv* /*env*/, jclass /*_this*/, jlong encoder)
{
    if (kodoc_is_systematic_on((kodoc_coder_t)encoder) != 0)
    {
        return JNI_TRUE;
    }
    else
    {
        return JNI_FALSE;
    }
}

JNIEXPORT void JNICALL Java_com_steinwurf_kodo_KodoJava_setSystematicOn
  (JNIEnv* /*env*/, jclass /*_this*/, jlong encoder)
{
    kodoc_set_systematic_on((kodoc_coder_t)encoder);
}

JNIEXPORT void JNICALL Java_com_steinwurf_kodo_KodoJava_setSystematicOff
  (JNIEnv* /*env*/, jclass /*_this*/, jlong encoder)
{
    kodoc_set_systematic_off((kodoc_coder_t)encoder);
}

JNIEXPORT void JNICALL Java_com_steinwurf_kodo_KodoJava_readFeedback
  (JNIEnv* env, jclass /*_this*/, jlong encoder, jbyteArray feedback)
{
    jbyte* feedbackPtr = env->GetByteArrayElements(feedback, NULL);

    if (feedbackPtr != NULL)
    {
        kodoc_read_feedback((kodoc_coder_t)encoder, (uint8_t*)feedbackPtr);
        env->ReleaseByteArrayElements(feedback, feedbackPtr, JNI_ABORT);
    }
}

//------------------------------------------------------------------
// DECODER API
//------------------------------------------------------------------

JNIEXPORT void JNICALL Java_com_steinwurf_kodo_KodoJava_readPayload
  (JNIEnv* env, jclass /*_this*/, jlong decoder, jbyteArray data)
{
    jbyte* dataPtr = env->GetByteArrayElements(data, NULL);

    if (dataPtr != NULL)
    {
        kodoc_read_payload((kodoc_coder_t)decoder, (uint8_t*)dataPtr);
        env->ReleaseByteArrayElements(data, dataPtr, JNI_ABORT);
    }
}

JNIEXPORT jboolean JNICALL Java_com_steinwurf_kodo_KodoJava_isComplete
  (JNIEnv* /*env*/, jclass /*_this*/, jlong decoder)
{
    if (kodoc_is_complete((kodoc_coder_t)decoder) != 0)
    {
        return JNI_TRUE;
    }
    else
    {
        return JNI_FALSE;
    }
}

JNIEXPORT jboolean JNICALL
Java_com_steinwurf_kodo_KodoJava_hasPartialDecodingTracker
  (JNIEnv* /*env*/, jclass /*_this*/, jlong decoder)
{
    if (kodoc_has_partial_decoding_interface((kodoc_coder_t)decoder) != 0)
    {
        return JNI_TRUE;
    }
    else
    {
        return JNI_FALSE;
    }
}

JNIEXPORT jboolean JNICALL Java_com_steinwurf_kodo_KodoJava_isPartialComplete
  (JNIEnv* /*env*/, jclass /*_this*/, jlong decoder)
{
    if (kodoc_is_partially_complete((kodoc_coder_t)decoder) != 0)
    {
        return JNI_TRUE;
    }
    else
    {
        return JNI_FALSE;
    }
}

JNIEXPORT jboolean JNICALL Java_com_steinwurf_kodo_KodoJava_isSymbolUncoded
  (JNIEnv* /*env*/, jclass /*_this*/, jlong decoder, jint index)
{
    if (kodoc_is_symbol_uncoded((kodoc_coder_t)decoder, index) != 0)
    {
        return JNI_TRUE;
    }
    else
    {
        return JNI_FALSE;
    }
}

JNIEXPORT jlong JNICALL Java_com_steinwurf_kodo_KodoJava_symbolsUncoded
  (JNIEnv* /*env*/, jclass /*_this*/, jlong decoder)
{
    return kodoc_symbols_uncoded((kodoc_coder_t)decoder);
}

JNIEXPORT void JNICALL Java_com_steinwurf_kodo_KodoJava_writeFeedback
  (JNIEnv* env, jclass /*_this*/, jlong decoder, jbyteArray data)
{
    jbyte* dataPtr = env->GetByteArrayElements(data, NULL);

    if (dataPtr != NULL)
    {
        kodoc_write_feedback((kodoc_coder_t)decoder, (uint8_t*)dataPtr);
        env->ReleaseByteArrayElements(data, dataPtr, 0);
    }
}

#ifdef __cplusplus
}
#endif

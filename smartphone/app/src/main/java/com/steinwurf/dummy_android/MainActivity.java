package com.steinwurf.dummy_android;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.steinwurf.kodo.*;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends ActionBarActivity {

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    static {
        System.loadLibrary("android_test");
    }

    public native String runKodo();

    TextView mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mResult = (TextView)findViewById(R.id.textResult);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                run();
                //mResult.setText(runKodo());
            }
        });
    }

    public void run()
    {
        Random random = new Random();

        // Set the number of symbols (i.e. the generation size in RLNC
        // terminology) and the size of a symbol in bytes
        int maxSymbols = 10;
        int maxSymbolSize = 1;

        boolean traceFlag = false;

        CodeType code = CodeType.ON_THE_FLY;
        FiniteField field = FiniteField.BINARY;

        // Initilization of encoder and decoder
        EncoderFactory encoderFactory = new EncoderFactory(
                code, field, maxSymbols, maxSymbolSize, traceFlag);

        Encoder encoder = encoderFactory.build();

        DecoderFactory decoderFactory = new DecoderFactory(
                code, field, maxSymbols, maxSymbolSize, traceFlag);

        Decoder decoder = decoderFactory.build();

        System.out.format("Encoder payloadSize: %d%n", encoder.payloadSize());
        System.out.format("Decoder payloadSize: %d%n", decoder.payloadSize());

        // Allocate some storage for a "payload" the payload is what we would
        // eventually send over a network
        byte[] payload = new byte[encoder.payloadSize()];

        // Allocate some data to encode. In this case we make a buffer
        // with the same size as the encoder's block size (the max.
        // amount a single encoder can encode)
        byte[] dataIn = new byte[encoder.blockSize()];

        // Just for fun - fill the data with random data
        random.nextBytes(dataIn);

        // Assign the data buffer to the encoder so that we can start
        // to produce encoded symbols
        encoder.setSymbols(dataIn, encoder.blockSize());

        while (!decoder.isComplete())
        {

            // Encode the packet into the payload buffer
            encoder.writePayload(payload);
            System.out.format("Decoder payloadSize: %d%n", decoder.payloadSize());
            System.out.format("Payload size: %d%n", payload.length);
            System.out.format("Payload: %s%n", bytesToHex(payload));

            // Pass the packet to the decoder
            decoder.readPayload(payload);
            System.out.format("Encoder payloadSize: %d%n", encoder.payloadSize());

        }

        System.out.format("Decoder rank: %d%n", decoder.rank());

        // The decoder is complete, now copy the symbols from the decoder
        byte[] dataOut = new byte[encoder.blockSize()];
        decoder.copySymbols(dataOut, decoder.blockSize());

        // Check if we properly decoded the data
        if (Arrays.equals(dataIn, dataOut))
        {
            System.out.println("Data decoded correctly");
        }
        else
        {
            System.out.println("Unexpected failure to decode, " +
                    "please file a bug report :)");
        }
    }

    public String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}

package com.clover.tools;

import biz.source_code.dsp.filter.FilterPassType;

import biz.source_code.dsp.sound.IirFilterAudioInputStreamExstrom;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Demo {
    public static void main(String[] args) {
//        double[] time = new double[150];
//        double[] valueA = new double[150];
//        for (int i = 0; i < 50 * 3; i++) {
//            time[i] = i / 50.0;
//            valueA[i] = Math.sin(2 * Math.PI * 5 * time[i])+Math.sin(2 * Math.PI * 15 * time[i]);
//        }
//        IirFilterCoefficients iirFilterCoefficients;
//        iirFilterCoefficients = IirFilterDesignExstrom.design(FilterPassType.lowpass, 10,
//                10.0 / 50, 10.0 / 50);
//        valueA = IIRFilter(valueA, iirFilterCoefficients.a, iirFilterCoefficients.b);
        SourceDataLine auline=null;
        try {
            AudioInputStream audioInputStream = IirFilterAudioInputStreamExstrom.getAudioInputStream(
                    AudioSystem.getAudioInputStream(new File("C:\\Users\\Clover\\Desktop\\from3aligned.wav")),
                    FilterPassType.bandpass,10,200,1000);
//            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\Clover\\Desktop\\from3aligned.wav"));
            AudioFormat format= audioInputStream.getFormat();
            DataLine.Info info=new DataLine.Info(SourceDataLine.class,format);

            try {
                auline=(SourceDataLine)AudioSystem.getLine(info);
                auline.open(format);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return;
            }
            auline.start();
            int nByteRead=0;
            byte[] abData=new byte[512];
            try {
                while(nByteRead!=-1)
                {
                    nByteRead=audioInputStream.read(abData,0,abData.length);
                    if(nByteRead>=0)
                    {
                        auline.write(abData, 0, nByteRead);
                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            finally
            {
                auline.drain();
                auline.close();
            }

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static synchronized double[] IIRFilter(double[] signal, double[] a, double[] b) {

        double[] in = new double[b.length];
        double[] out = new double[a.length-1];

        double[] outData = new double[signal.length];

        for (int i = 0; i < signal.length; i++) {

            System.arraycopy(in, 0, in, 1, in.length - 1);
            in[0] = signal[i];

            //calculate y based on a and b coefficients
            //and in and out.
            float y = 0;
            for(int j = 0 ; j < b.length ; j++){
                y += b[j] * in[j];

            }

            for(int j = 0;j < a.length-1;j++){
                y -= a[j+1] * out[j];
            }

            //shift the out array
            System.arraycopy(out, 0, out, 1, out.length - 1);
            out[0] = y;

            outData[i] = y;


        }
        return outData;
    }

}

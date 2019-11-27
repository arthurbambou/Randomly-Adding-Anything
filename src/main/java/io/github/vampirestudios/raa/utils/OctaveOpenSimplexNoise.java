package io.github.vampirestudios.raa.utils;

import java.util.Random;

//Code kindly taken from Terraform. Thank you, coderbot, Prospector, and Valoeghese!
public final class OctaveOpenSimplexNoise {

    protected OpenSimplexNoise[] samplers;
    private double clamp;
    private double frequency, amplitudeLow, amplitudeHigh;

    public OctaveOpenSimplexNoise(Random rand, int octaves, double frequency, double amplitudeHigh, double amplitudeLow) {
        samplers = new OpenSimplexNoise[octaves];
        clamp = 1D / (1D - (1D / Math.pow(2, octaves)));

        for (int i = 0; i < octaves; ++i) {
            samplers[i] = new OpenSimplexNoise(rand.nextLong());
        }

        this.frequency = frequency;
        this.amplitudeLow = amplitudeLow;
        this.amplitudeHigh = amplitudeHigh;
    }

    public double sample(double x, double y) {
        double amplFreq = 0.5D;
        double result = 0;
        for (OpenSimplexNoise sampler : samplers) {
            result += (amplFreq * sampler.sample(x / (amplFreq * frequency), y / (amplFreq * frequency)));

            amplFreq *= 0.5D;
        }

        result = result * clamp;
        return result > 0 ? result * amplitudeHigh : result * amplitudeLow;
    }
    
    public double sample(double x, double y, double z) {
        double amplFreq = 0.5D;
        double result = 0;
        for (OpenSimplexNoise sampler : samplers) {
        	double freq = amplFreq * frequency;
            result += (amplFreq * sampler.sample(x / freq, y / freq, z / freq));

            amplFreq *= 0.5D;
        }

        result = result * clamp;
        return result > 0 ? result * amplitudeHigh : result * amplitudeLow;
    }
    
    public double sampleCustom(double x, double y, double z, double freqModifier, double amplitudeHMod, double amplitudeLMod, int octaves) {
    	 double amplFreq = 0.5D;
         double result = 0;
         
         double sampleFreq = frequency * freqModifier;
         
         for (int i = 0; i < octaves; ++i) {
        	 OpenSimplexNoise sampler = samplers[i];
        	 
        	 double freq = amplFreq * sampleFreq;
             result += (amplFreq * sampler.sample(x / freq, y / freq, z / freq));

             amplFreq *= 0.5D;
         }
         
         double sampleClamp = 1D / (1D - (1D / Math.pow(2, octaves)));
         result = result * sampleClamp;
         return result > 0 ? result * amplitudeHigh * amplitudeHMod : result * amplitudeLow * amplitudeLMod;
    }

}
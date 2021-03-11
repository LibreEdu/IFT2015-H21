/*
 * Copyright 2020 Miklós Csűrös.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pedigree;


import java.util.Arrays;
import java.util.Random;

/**
 *
 * Gompertz-Makeham distribution for lifespan.
 * 
 * Parametrized by accident, death rate and scale.
 *
 * @author Miklós Csűrös
 */
public class AgeModel 
{
    private final double death_rate;
    private final double accident_rate;
    private final double age_factor;
    private final int smp_size; 
    private final double[] lifespan;
    
    private static final double DEFAULT_ACCIDENT_RATE = 0.01; // 1% chance of dying per year
    private static final double DEFAULT_DEATH_RATE = 12.5;
    private static final double DEFAULT_SCALE = 100.0; // "maximum" age [with death rate 1]
    private static final int DEFAULT_SMP_SIZE = 1000; // this many random values
    
    public AgeModel(double accident_rate, double death_rate, double age_scale, int smp_size)
    {
        this.death_rate = death_rate;
        this.age_factor = Math.exp(age_scale/death_rate);
        this.accident_rate = accident_rate;
        this.smp_size = smp_size;
        this.lifespan = new double[smp_size];
    }
    
    /**
     * Instantiation with default values (human).
     */
    public AgeModel()
    {
        this(DEFAULT_ACCIDENT_RATE, DEFAULT_DEATH_RATE, DEFAULT_SCALE, DEFAULT_SMP_SIZE);
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder(getClass().getName());
        sb.append("[acc ").append(accident_rate).append(", age ").append(death_rate).append(", agefactor ").append(age_factor);
        sb.append("]");
        return sb.toString();
    }
    
    /**
     * Probability of surviving past the given age
     * 
     * @param age 
     * @return probability of dying after the given age
     */
    public double getSurvival(double age)
    {
       return Math.exp(-accident_rate*age -death_rate*Math.expm1(age/death_rate)/age_factor);
    }
    
    /**
     * Expected time span (TS) for mating: average number of children will be TS/matingrate.
     * 
     * @param min_age minimum age of sexual maturity
     * @param max_age maximum age of parenting
     * @return 
     */
    public double expectedParenthoodSpan(double min_age, double max_age)
    {
        
        // integration of the survival function over the mating age
        
        // numerical integration with simpson's rule, dynamic setting of resolution
        
        int n = 1; // number of intervals along the range
        double d = (max_age-min_age)/n;
        
        double st = d*0.5*(getSurvival(min_age)+getSurvival(max_age)); 
        
        
        double espan = 0.0;
        double old_espan = -1.0; // does not matter much
        
        for (int iter=1; iter<20;iter++)
        {
            double x0=min_age+d*0.5;
            double s2=0.0;
            for (int i=0;i<n;i++)
            {
                double x = x0+i*d;
                s2 += getSurvival(x);
            }
            double old_st = st;
            st = 0.5*(st+d*s2); // simple trapezoidal 
            espan = (4.0*st-old_st)/3.0; // Simpson's ... better than st

            n = n*2;
            d=d*0.5;
            
            if (iter>5 // first five iteration kept 
                    && (Math.abs(old_espan-espan)<1e-7*old_espan
                    || (espan==0.0 && old_espan==0.0) ))
                break;
            old_espan = espan;
        }         
        return espan;
    }
    
    /**
     * Exponentially distributed random variable.
     * 
     * 
     * @param RND random number generator
     * @param rate inverse of the mean
     * @return Exponential(rate)
     */
    public static double randomWaitingTime(Random RND, double rate)
    {
        return -Math.log(RND.nextDouble())/rate;
    }

    /**
     * A random value with the specified lifespan distribution.
     * 
     * @param RND Psudorandom number generator for uniform[0,1]
     * 
     * @return a random value distributed by Gomperz-Makeham
     */
    public double randomAge(Random RND)
    {
        // psudorandom by exponential for accident-related death
        double accidental_death = -Math.log(RND.nextDouble())/accident_rate;
        // pseudorandom by Gompertz for old-age
        double u = RND.nextDouble();
        double age_death = death_rate*Math.log1p(-Math.log(u)/death_rate*age_factor);
        
        return Math.min(age_death, accidental_death);
    }
    
    /**
     * Test for tabulating random lifespans from command line.
     * 
     * @param args accident-rate death-rate [scale]
     */
    public void lifeSpan(String[] args)
    {
    	Random RND = new Random();
    	
        for (int r=0; r<smp_size; r++)
        {
            double d = randomAge(RND);
            avg += d;
            lifespan[r] = d;
        }
        avg /= smp_size;
        Arrays.sort(lifespan);
        }
    
}
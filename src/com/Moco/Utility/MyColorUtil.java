package com.Moco.Utility;

public class MyColorUtil {
	
	public static int[] getRGB(int fromColor, int endColor, int freq, int n) {
		int[] rgb = new int[3];
		
		int r0 = fromColor>>16&0xff;
		int r1 = endColor>>16&0xff;
		
		int g0 = fromColor>>8&0xff;
		int g1 = endColor>>8&0xff;
		
		int b0 = fromColor&0xff;
		int b1 = endColor&0xff;

		int f;
		
		for(int i = 0; i<freq; i++) {
			f = (i<<16)/freq;
			rgb[0] = r0+((f*(r1-r0))>>16);
			rgb[1] = g0+((f*(g1-g0))>>16);
			rgb[2] = b0+((f*(b1-b0))>>16);
			if(i == n)break;
		}
		
		return rgb;
	}
}

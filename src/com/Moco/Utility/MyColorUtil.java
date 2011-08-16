package com.Moco.Utility;

import android.graphics.Color;

public class MyColorUtil {
	
	public static int[] getRGB(String fromColor, String endColor, int freq, int n) {
		int[] rgb = new int[3];
		
		int r0 = Color.red(Color.parseColor(fromColor));
		int r1 = Color.red(Color.parseColor(endColor));
		
		int g0 = Color.green(Color.parseColor(fromColor));
		int g1 = Color.green(Color.parseColor(endColor));
		
		int b0 = Color.blue(Color.parseColor(fromColor));
		int b1 = Color.blue(Color.parseColor(endColor));

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

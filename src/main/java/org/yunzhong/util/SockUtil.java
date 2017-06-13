package org.yunzhong.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SockUtil {

	/**
	 * @param value
	 * @return
	 */
	public static double format(double value) {
		BigDecimal bg = new BigDecimal(value).setScale(2, RoundingMode.UP);
		return bg.doubleValue();
	}

	public static boolean upStaying(Double old, Double current, Double percentage) {
		if (old == null || current == null) {
			return false;
		}
		return format(old * (1 + (percentage == null ? 0 : percentage))) <= current;
	}
}

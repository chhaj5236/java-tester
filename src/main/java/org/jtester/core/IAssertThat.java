package org.jtester.core;

import org.jtester.unitils.jmock.fluent.IBooleanAssert;
import org.jtester.unitils.jmock.fluent.IIntegerAssert;
import org.jtester.unitils.jmock.fluent.IStringAssert;
import org.jtester.unitils.jmock.fluent.impl.BooleanAssert;
import org.jtester.unitils.jmock.fluent.impl.IntegerAssert;
import org.jtester.unitils.jmock.fluent.impl.StringAssert;

public interface IAssertThat {
	// BigDecimalAssert assertThat(BigDecimal actual);
	//
	// BooleanArrayAssert assertThat(boolean[] actual);
	//
	// ImageAssert assertThat(BufferedImage actual);
	//
	// ByteAssert assertThat(byte actual);
	//
	// ByteArrayAssert assertThat(byte[] actual);
	//
	// CharAssert assertThat(char actual);
	//
	// CharArrayAssert assertThat(char[] actual);
	//
	// CollectionAssertEx assertThat(Collection<?> actual);
	//
	// DoubleAssert assertThat(double actual);
	//
	// DoubleArrayAssert assertThat(double[] actual);
	//
	// FileAssert assertThat(File actual);
	//
	// FloatAssert assertThat(float actual);
	//
	// FloatArrayAssert assertThat(float[] actual);

	//
	// IntArrayAssert assertThat(int[] actual);
	//
	// CollectionAssertEx assertThat(Iterator<?> actual);
	//
	// LongAssert assertThat(long actual);
	//
	// LongArrayAssert assertThat(long[] actual);
	//
	// MapAssert assertThat(Map<?, ?> actual);
	//
	// ObjectAssertEx assertThat(Object actual);
	//
	// ObjectArrayAssertEx assertThat(Object[] actual);
	//
	// ShortAssert assertThat(short actual);
	//
	// ShortArrayAssert assertThat(short[] actual);
	//
	// StringAssert assertThat(String actual);
	//
	// <T extends AssertExtension> T assertThat(T assertion);
	//
	// ThrowableAssert assertThat(Throwable actual);

	public static class want {
		public static IStringAssert string(String value) {
			return new StringAssert(value, StringAssert.class);
		}

		public static IIntegerAssert integer(Integer value) {
			return new IntegerAssert(value, IntegerAssert.class);
		}

		public static IBooleanAssert bool(Boolean value) {
			return new BooleanAssert(value, BooleanAssert.class);
		}
	}
}
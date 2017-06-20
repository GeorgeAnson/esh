package com.esh.test;

import java.math.BigInteger;
import java.util.Random;

import org.junit.Test;

public class TokenTest {

	@Test
	public void create()
	{
		Random random=new Random();
		String value=new BigInteger(165, random).toString(36).toUpperCase();
		System.out.println("JSSESSIONID  "+value+"  "+value.length());
	}
}

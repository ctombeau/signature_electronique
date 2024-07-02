package com.banking.digital_signature_demo.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

public  class KeyPairGeneratorUtil {
	
	private KeyPair keyPair;
	
	public KeyPairGeneratorUtil() throws NoSuchAlgorithmException{
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(2048);
		this.keyPair = keyGen.generateKeyPair();
	}
	
	public PrivateKey getPrivateKey() {
		return keyPair.getPrivate();
	}
	
	public PublicKey getPublicKey() {
		return keyPair.getPublic();
	}
	
	/*
	public static void main(String[] args) throws NoSuchAlgorithmException{
		KeyPairGeneratorUtil keyPairGenUtil = new KeyPairGeneratorUtil();
		
		System.out.println("Private key: "+keyPairGenUtil.getPrivateKey());
		System.out.println("Public key: "+keyPairGenUtil.getPublicKey());
	}
  */
}

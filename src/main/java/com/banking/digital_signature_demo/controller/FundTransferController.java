package com.banking.digital_signature_demo.controller;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.digital_signature_demo.utils.DigitalSignatureUtil;
import com.banking.digital_signature_demo.utils.KeyPairGeneratorUtil;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@RestController
@RequestMapping("/api")
public class FundTransferController {
     private final KeyPair keyPair;
     
     public FundTransferController() throws NoSuchAlgorithmException{
    	 KeyPairGeneratorUtil keyPairGenUtil = new KeyPairGeneratorUtil();
    	 this.keyPair = new KeyPair(keyPairGenUtil.getPublicKey(),keyPairGenUtil.getPrivateKey());
     }
     
     @PostMapping("/sign-transfer")
     public String signTransfer(@RequestBody TransferRequest request) throws Exception{
    	 String data = request.toString();
    	 byte[] signature = DigitalSignatureUtil.signData(data.getBytes(), keyPair.getPrivate());
    	 return Base64.getEncoder().encodeToString(signature);
     }
     
     @PostMapping("/verify-transfer")
     public boolean verifyTrnasfer(@RequestBody TransferVerificationRequest request) throws Exception{
    	 byte [] signature = Base64.getDecoder().decode(request.getSignature());
    	 return DigitalSignatureUtil.verifyData(
    			 request.getTransferRequest().toString().getBytes(), signature, keyPair.getPublic());
     }
     
     @Data
     @Getter
     @Setter
     static class TransferRequest{
    	 private String fromAccount;
    	 private String toAccount;
    	 private double amount;
    	 
    	 @Override
    	 public String toString() {
    		 return "TransferRequest{" +
    	                "fromAccount='" + fromAccount + '\'' +
    	                ", toAccount='" + toAccount + '\'' +
    	                ", amount=" + amount +
    	                '}';
    	 }
    	 
     }
     
     @Data
     @Getter
     @Setter
     static class TransferVerificationRequest{
    	 private TransferRequest transferRequest;
    	 private String signature;
     }
}

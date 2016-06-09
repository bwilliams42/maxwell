package com.zendesk.maxwell;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MaxwellDecryptionClassLoader extends ClassLoader {
	static final Logger LOGGER = LoggerFactory.getLogger(MaxwellDecryptionClassLoader.class);
	
    private Object decryptor = null;
	public void initDecryptorClass(String className){
		String decrypted = null;
		try {
			ClassLoader classLoader = this.getClass().getClassLoader();
	        Class loadedMyClass = classLoader.loadClass(className);
	        LOGGER.info("Loaded Decryption class name: " + loadedMyClass.getName());
	        Constructor constructor = loadedMyClass.getConstructor();
	        this.decryptor = constructor.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String decrypt(String encrypted) {
		LOGGER.debug("Decrypting: " + encrypted);
		Method method = decryptor.getMethod("decrypt");
        decrypted = (String) method.invoke(myClassObject);
		return decrypted;
	}
}

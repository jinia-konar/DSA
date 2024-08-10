package com.example.java_syntax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaSyntaxApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaSyntaxApplication.class, args);
		ListImpl listImpl = new ListImpl();
		listImpl.threadSafe();
	}

}

package com.example.java_syntax;

import com.example.java_syntax.dataTypes.DataTypeHandler;
import com.example.java_syntax.dataTypes.ListImpl;
import com.example.java_syntax.dataTypes.MapImpl;
import com.example.java_syntax.dataTypes.SetImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaSyntaxApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaSyntaxApplication.class, args);
		DataTypeHandler handler = new MapImpl();
		handler.handleImplementations();
	}

}

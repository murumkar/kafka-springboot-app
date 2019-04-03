package com.adobe.ids.dim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;

import java.util.Properties;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;




@SpringBootApplication
public class canaryApplication {


	final static Logger logger = Logger.getLogger(canaryApplication.class);
	@Value("${spring.application.name:canaryApplication}")
	String name;
	@Value("${bootstrap.url:'localhost:9092'}")
	String bootstrap;
	@Value("${registry.url:'localhost:8081'}")
	String registry;

	public static void main(String[] args) {
		//SpringApplication.run(CanaryApplication.class, args);
		final Options options = new Options();

		options.addOption("p", "producer", false, "Run Producer Application")
				.addOption("c", "consumer", false, "Run Consumer Application");

		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("canaryApplication", options);

	}

}

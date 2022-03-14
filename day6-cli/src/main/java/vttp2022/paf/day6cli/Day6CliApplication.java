package vttp2022.paf.day6cli;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day6CliApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Day6CliApplication.class, args);
	}

	@Override
	public void run(String[] args) {
		System.out.println("args: " + args);
		if (args.length <= 0) {
			System.err.println("Please pass in a JSON file to parse");
			System.exit(-1);
		}

		for (String a: args)
			System.out.println(">>> a: " + a);

	}


}

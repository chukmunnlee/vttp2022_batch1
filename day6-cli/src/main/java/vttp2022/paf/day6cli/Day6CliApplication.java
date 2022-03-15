package vttp2022.paf.day6cli;

import java.io.FileInputStream;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import vttp2022.paf.day6cli.respositories.GameRepository;
import vttp2022.paf.day6cli.services.GameService;

@SpringBootApplication
public class Day6CliApplication implements CommandLineRunner {

	@Autowired
	private GameService gameSvc;

	@Autowired
	private GameRepository gameRepo;

	public static void main(String[] args) {
		SpringApplication.run(Day6CliApplication.class, args);
	}

	@Override
	public void run(String[] args) {

		if (args.length <= 0) {
			System.err.println("Please pass in a JSON file to parse");
			System.exit(-1);
		}

		JsonArray games = null;
		try (FileInputStream fis = new FileInputStream(args[0])) {
			games = gameSvc.loadData(fis);
			System.out.println("size: " + games.size());
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		games.stream()
			.map(v -> (JsonObject)v)
			.forEach((JsonObject v) -> {
				gameRepo.save(v);
			});

		System.out.println("completed!");

		Set<String> keys = gameRepo.findKeys("*12*");
		for (String k: keys)
			System.out.printf(">> %s\n", k);
	}


}

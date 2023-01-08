package eu.przemyslawrutkowski.pokolei;

import eu.przemyslawrutkowski.pokolei.entity.Train;
import eu.przemyslawrutkowski.pokolei.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private TrainRepository trainRepository;

	@Override
	public void run (String... args) throws Exception {

		Train train1 = Train.builder()
				.brand("R")
				.trainNumber("8985")
				.name("Vojak Švejk")
				.route("Medzilaborce 10:31, Łupków 10:54-10:55, Sanok 12:08")
				.runningDates("02.02.2012 - 02.05.2012")
				.carriers("PKP Intercity")
				.build();

		Train train2 = Train.builder()
				.brand("TLK")
				.trainNumber("51102/3")
				.name("Biebrza")
				.route("Gdynia Gł. 8:00 - Gdańsk Gł. - Tczew - Malbork - Elbląg - Olsztyn Gł. - Szczytno - Ełk - Białystok - Hajnówka - Siedlce - Warszawa Wsch. 19:45")
				.runningDates("02.02.2012 - 02.05.2012")
				.carriers("PKP Intercity")
				.build();

		trainRepository.save(train1);
		trainRepository.save(train2);
	}
}

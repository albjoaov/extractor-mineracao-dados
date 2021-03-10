package com.example.extractor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@SpringBootApplication
public class ExtractorApplication implements CommandLineRunner {

	private final JdbcTemplate jdbcTemplate;
	private final ExcelWriter excelWriter;

	public ExtractorApplication (JdbcTemplate jdbcTemplate, ExcelWriter excelWriter) {
		this.jdbcTemplate = jdbcTemplate;
		this.excelWriter = excelWriter;
	}

	public static void main(String[] args) {
		SpringApplication.run(ExtractorApplication.class, args);
	}

	@Override
	public void run (String... args) {

		ObjectMapper mapper = new ObjectMapper();

		List<Log> logList = jdbcTemplate.query("SELECT g.id as gameId, g.name as gameName, l.uuid, l.data " +
						"FROM logs l INNER JOIN games g on g.id = l.fk_game ",
//						"LIMIT 10",
				(resultSet, rowNum) -> {
					int gameId = resultSet.getInt("gameId");
					String gameName = resultSet.getString("gameName");
					String uuid = resultSet.getString("uuid");
					String json = resultSet.getString("data");
					Log log = new Log();
					log.setGameId(gameId);
					log.setGameName(gameName);
					log.setUuid(uuid);
					boolean success = this.processJSON(json, mapper, log);
					if (!success) {
						return null;
					}
					return log;
				});

		List<Log> logs = logList.stream().filter(Objects::nonNull).collect(Collectors.toList());
		this.excelWriter.write(logs);

		System.out.println("ACABOU");
	}

	// duvidas gender? limpei valores nulos ou valores sem emocao
	private boolean processJSON (String json, ObjectMapper mapper, Log log) {

		// example
		// d:{"e":[{"emotion":"angry","value":0.08495773461377512},{"emotion":"disgusted","value":0.09287702083369649},{"emotion":"fear","value":0.03218940094105824},{"emotion":"sad","value":0.05993173506165729},{"emotion":"surprised","value":0.054032595527500206},{"emotion":"happy","value":0.18562819815754616}],"g":[{"emotion":"female","value":0.550643615757293},{"emotion":"male","value":0.44935638424270696}],"p":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]}

		Map jsonMap;
		Object d;
		try {
			jsonMap = mapper.readValue(json, Map.class);
			d = jsonMap.get("d");
			if (isNull(d)) {
				return false;
			}
			Long t = (Long) jsonMap.get("t");
			if (isNull(d)) {
				return false;
			}
			log.setTimestamp(t);
		} catch (JsonProcessingException e) {
			return false;
		}

		Map data;
		try {
			data = mapper.readValue((String) d, Map.class);
		} catch (Exception exception) {
			System.out.println("data fudido");
			return false;
		}

		Object e = data.get("e");
		if (isNull(e)) {
			return false;
		}

		try {
			List emotionList = (List) e;
			emotionList.forEach(emotion -> {
				Map emotionMap = (Map) emotion;
				String emotionName = (String) emotionMap.get("emotion");
				Double emotionValue = (Double) emotionMap.get("value");
				if (isNull(emotionValue)) throw new RuntimeException();
				switch (emotionName) {
					case "angry":
						log.setAngry(emotionValue);
						break;
					case "disgusted":
						log.setDisgusted(emotionValue);
						break;
					case "fear":
						log.setFear(emotionValue);
						break;
					case "sad":
						log.setSad(emotionValue);
						break;
					case "surprised":
						log.setSurprised(emotionValue);
						break;
					case "happy":
						log.setHappy(emotionValue);
				}
			});
		} catch (Exception exception) {
			System.out.println("casa caiu no emotionList");
			return false;
		}

		try {
			Object p = data.get("p");
			if (isNull(p)) {
				return false;
			}

			Object[] pontosEuclidianos = ((List) p).toArray();
			try {
				log.setP1((Double) pontosEuclidianos[0]);
				log.setP2((Double) pontosEuclidianos[1]);
				log.setP3((Double) pontosEuclidianos[2]);
				log.setP4((Double) pontosEuclidianos[3]);
				log.setP5((Double) pontosEuclidianos[4]);
				log.setP6((Double) pontosEuclidianos[5]);
				log.setP7((Double) pontosEuclidianos[6]);
				log.setP8((Double) pontosEuclidianos[7]);
				log.setP9((Double) pontosEuclidianos[8]);
				log.setP10((Double) pontosEuclidianos[9]);
				log.setP11((Double) pontosEuclidianos[10]);
				log.setP12((Double) pontosEuclidianos[11]);
				log.setP13((Double) pontosEuclidianos[12]);
				log.setP14((Double) pontosEuclidianos[13]);
				log.setP15((Double) pontosEuclidianos[14]);
				log.setP16((Double) pontosEuclidianos[15]);
				log.setP17((Double) pontosEuclidianos[16]);
				log.setP18((Double) pontosEuclidianos[17]);
				log.setP19((Double) pontosEuclidianos[18]);
				log.setP20((Double) pontosEuclidianos[19]);
				log.setP21((Double) pontosEuclidianos[20]);
				log.setP22((Double) pontosEuclidianos[21]);
				log.setP23((Double) pontosEuclidianos[22]);
				log.setP24((Double) pontosEuclidianos[23]);
			} catch (ClassCastException classCastException) {
				log.setP1(((Integer) pontosEuclidianos[0]).doubleValue());
				log.setP2(((Integer) pontosEuclidianos[1]).doubleValue());
				log.setP3(((Integer) pontosEuclidianos[2]).doubleValue());
				log.setP4(((Integer) pontosEuclidianos[3]).doubleValue());
				log.setP5(((Integer) pontosEuclidianos[4]).doubleValue());
				log.setP6(((Integer) pontosEuclidianos[5]).doubleValue());
				log.setP7(((Integer) pontosEuclidianos[6]).doubleValue());
				log.setP8(((Integer) pontosEuclidianos[7]).doubleValue());
				log.setP9(((Integer) pontosEuclidianos[8]).doubleValue());
				log.setP10(((Integer) pontosEuclidianos[9]).doubleValue());
				log.setP11(((Integer) pontosEuclidianos[10]).doubleValue());
				log.setP12(((Integer) pontosEuclidianos[11]).doubleValue());
				log.setP13(((Integer) pontosEuclidianos[12]).doubleValue());
				log.setP14(((Integer) pontosEuclidianos[13]).doubleValue());
				log.setP15(((Integer) pontosEuclidianos[14]).doubleValue());
				log.setP16(((Integer) pontosEuclidianos[15]).doubleValue());
				log.setP17(((Integer) pontosEuclidianos[16]).doubleValue());
				log.setP18(((Integer) pontosEuclidianos[17]).doubleValue());
				log.setP19(((Integer) pontosEuclidianos[18]).doubleValue());
				log.setP20(((Integer) pontosEuclidianos[19]).doubleValue());
				log.setP21(((Integer) pontosEuclidianos[20]).doubleValue());
				log.setP22(((Integer) pontosEuclidianos[21]).doubleValue());
				log.setP23(((Integer) pontosEuclidianos[22]).doubleValue());
				log.setP24(((Integer) pontosEuclidianos[23]).doubleValue());
			}

		} catch (Exception exception) {
			System.out.println("casa caiu nos pontos euclidianos");
			return false;
		}

		return true;
	}
}

package com.example.extractor;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class ExcelWriter {

	public void write (List<Log> logs) {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("logs");

		this.createHeaders(sheet);

		int rowCount = 1;

		for (Log log : logs) {
			Row row = sheet.createRow(rowCount);
			this.fillRow(row, log);
			rowCount = rowCount + 1;
		}

		try (FileOutputStream outputStream = new FileOutputStream("game-logs-completo.xlsx")) {
			workbook.write(outputStream);
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createHeaders (XSSFSheet sheet) {
		Row row = sheet.createRow(0);

		Cell cell = row.createCell(0);
		cell.setCellValue("game_id");

		cell = row.createCell(1);
		cell.setCellValue("game_name");

		cell = row.createCell(2);
		cell.setCellValue("uuid");

		cell = row.createCell(3);
		cell.setCellValue("timestamp");

		cell = row.createCell(4);
		cell.setCellValue("angry");

		cell = row.createCell(5);
		cell.setCellValue("disgusted");

		cell = row.createCell(6);
		cell.setCellValue("fear");

		cell = row.createCell(7);
		cell.setCellValue("sad");

		cell = row.createCell(8);
		cell.setCellValue("surprised");

		cell = row.createCell(9);
		cell.setCellValue("happy");

		cell = row.createCell(10);
		cell.setCellValue("p1");

		cell = row.createCell(11);
		cell.setCellValue("p2");

		cell = row.createCell(12);
		cell.setCellValue("p3");

		cell = row.createCell(13);
		cell.setCellValue("p4");

		cell = row.createCell(14);
		cell.setCellValue("p5");

		cell = row.createCell(15);
		cell.setCellValue("p6");

		cell = row.createCell(16);
		cell.setCellValue("p7");

		cell = row.createCell(17);
		cell.setCellValue("p8");

		cell = row.createCell(18);
		cell.setCellValue("p9");

		cell = row.createCell(19);
		cell.setCellValue("p10");

		cell = row.createCell(20);
		cell.setCellValue("p11");

		cell = row.createCell(21);
		cell.setCellValue("p12");

		cell = row.createCell(22);
		cell.setCellValue("p13");

		cell = row.createCell(23);
		cell.setCellValue("p14");

		cell = row.createCell(24);
		cell.setCellValue("p15");

		cell = row.createCell(25);
		cell.setCellValue("p16");

		cell = row.createCell(26);
		cell.setCellValue("p17");

		cell = row.createCell(27);
		cell.setCellValue("p18");

		cell = row.createCell(28);
		cell.setCellValue("p19");

		cell = row.createCell(29);
		cell.setCellValue("p20");

		cell = row.createCell(30);
		cell.setCellValue("p21");

		cell = row.createCell(31);
		cell.setCellValue("p23");

		cell = row.createCell(32);
		cell.setCellValue("p24");

		cell = row.createCell(33);
		cell.setCellValue("p24");
	}

	private void fillRow (Row row, Log log) {
		Cell cell = row.createCell(0);
		cell.setCellValue(log.getGameId());

		cell = row.createCell(1);
		cell.setCellValue(log.getGameName());

		cell = row.createCell(2);
		cell.setCellValue(log.getUuid());

		cell = row.createCell(3);
		cell.setCellValue(log.getTimestamp());

		cell = row.createCell(4);
		cell.setCellValue(log.getAngry());

		cell = row.createCell(5);
		cell.setCellValue(log.getDisgusted());

		cell = row.createCell(6);
		cell.setCellValue(log.getFear());

		cell = row.createCell(7);
		cell.setCellValue(log.getSad());

		cell = row.createCell(8);
		cell.setCellValue(log.getSurprised());

		cell = row.createCell(9);
		cell.setCellValue(log.getHappy());


		cell = row.createCell(10);
		cell.setCellValue(log.getP1());


		cell = row.createCell(11);
		cell.setCellValue(log.getP2());


		cell = row.createCell(12);
		cell.setCellValue(log.getP3());


		cell = row.createCell(13);
		cell.setCellValue(log.getP4());


		cell = row.createCell(14);
		cell.setCellValue(log.getP5());


		cell = row.createCell(15);
		cell.setCellValue(log.getP6());


		cell = row.createCell(16);
		cell.setCellValue(log.getP7());

		cell = row.createCell(17);
		cell.setCellValue(log.getP8());


		cell = row.createCell(18);
		cell.setCellValue(log.getP9());


		cell = row.createCell(19);
		cell.setCellValue(log.getP10());


		cell = row.createCell(20);
		cell.setCellValue(log.getP11());


		cell = row.createCell(21);
		cell.setCellValue(log.getP12());


		cell = row.createCell(22);
		cell.setCellValue(log.getP13());


		cell = row.createCell(23);
		cell.setCellValue(log.getP14());


		cell = row.createCell(24);
		cell.setCellValue(log.getP15());


		cell = row.createCell(25);
		cell.setCellValue(log.getP16());


		cell = row.createCell(26);
		cell.setCellValue(log.getP17());


		cell = row.createCell(27);
		cell.setCellValue(log.getP18());


		cell = row.createCell(28);
		cell.setCellValue(log.getP19());


		cell = row.createCell(29);
		cell.setCellValue(log.getP20());


		cell = row.createCell(30);
		cell.setCellValue(log.getP21());


		cell = row.createCell(31);
		cell.setCellValue(log.getP22());

		cell = row.createCell(32);
		cell.setCellValue(log.getP23());

		cell = row.createCell(33);
		cell.setCellValue(log.getP24());
	}
}

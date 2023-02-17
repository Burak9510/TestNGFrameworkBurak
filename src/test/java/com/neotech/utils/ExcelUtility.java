package com.neotech.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	private static Workbook book;
	private static Sheet sheet;

	private static void openExcel(String filePath) {
		try {
			FileInputStream fis = new FileInputStream(filePath);
			book = new XSSFWorkbook(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void loadSheet(String sheetName) {
		sheet = book.getSheet(sheetName);
	}

	private static int rowCount() {
		return sheet.getPhysicalNumberOfRows();
	}

	private static int colCount(int rowIndex) {
		return sheet.getRow(rowIndex).getLastCellNum();
	}

	private static String cellData(int rowIndex, int colIndex) {
		return sheet.getRow(rowIndex).getCell(colIndex).toString();
	}

	// Return a 2D-Object Array
	public static Object[][] excelIntoArray(String filePath, String sheetName) {

		openExcel(filePath);
		loadSheet(sheetName);

		int rows = rowCount();
		int cols = colCount(0);

		// I used row - 1 to skip the header
		Object[][] data = new Object[rows - 1][cols];

		// Iterating rows
		// Started from 1 to skip the header
		for (int row = 1; row < rows; row++) {

			// Iterating columns
			for (int col = 0; col < cols; col++) {

				// Read from excel into 2D-Object Array
				data[row - 1][col] = cellData(row, col);
			}

		}

		// return the object that has been filled
		return data;
	}

}

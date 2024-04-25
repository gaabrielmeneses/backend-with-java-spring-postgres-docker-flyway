package br.com.menesic.GranjaApp.domain.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Slf4j
public class ExcelExporter {

    public static byte[] getXlsxReport(List<Map<String, String>> jsonList) throws IOException {
        try (Workbook workbook = new HSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Relatório de gerenciamento de patos");

            // Cabeçalho "GERENCIAMENTO DE PATOS"
            CellRangeAddress region = new CellRangeAddress(1, 1, 1, 5);
            sheet.addMergedRegion(region);
            Row titleRow = sheet.createRow(1);
            Cell titleCell = titleRow.createCell(1);
            titleCell.setCellValue("GERENCIAMENTO DE PATOS");
            titleCell.setCellStyle(createTitleCellStyle(workbook));

            // Cabeçalho das Colunas
            Row headerRow = sheet.createRow(2);
            String[] headers = {"Nome", "Status", "Cliente", "Tipo do cliente", "Valor"};
            CellStyle headerCellStyle = createHeaderCellStyle(workbook);
            IntStream.range(0, headers.length)
                    .forEach(i -> {
                        Cell cell = headerRow.createCell(i + 1);
                        cell.setCellValue(headers[i]);
                        cell.setCellStyle(headerCellStyle);
                    });


            // Preenchimento dos dados
            int rowNum = 3;
            for (Map<String, String> jsonMap : jsonList) {
                Row row = sheet.createRow(rowNum++);
                int colNum = 1;
                for (String header : headers) {
                    Cell cell = row.createCell(colNum++);
                    Object value = jsonMap.get(header.toLowerCase().replace(" ", "_"));
                    if (value == null) {
                        cell.setCellValue("-");
                    } else if (value instanceof String) {
                        cell.setCellValue(((String) value));
                    } else {
                        cell.setCellValue(value.toString());
                    }
                }
            }

            // Ajusta automaticamente a largura das colunas
            IntStream.range(0, headers.length)
                    .forEach(i -> sheet.autoSizeColumn(i + 4));

            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                workbook.write(outputStream);
                log.info("Arquivo .xls criado com sucesso!");
                return outputStream.toByteArray();
            } catch (Exception e) {
                log.error("Erro ao gerar arquivo .xls!");
                return new byte[0];
            }
        }
    }

    private static CellStyle createHeaderCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }

    private static CellStyle createTitleCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 16);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }
}


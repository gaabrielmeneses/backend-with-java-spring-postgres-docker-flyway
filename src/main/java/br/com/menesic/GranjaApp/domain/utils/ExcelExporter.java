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
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i+1);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
            }

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
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i+4);
            }            // Escreve no arquivo Excel
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                workbook.write(outputStream);
                log.info("Arquivo .xlsx criado com sucesso!");
                return outputStream.toByteArray();
            } catch (Exception e) {
                log.error("Erro ao gerar arquivo .xlsx!", e.getStackTrace());
                return new byte[0];
            }
        }
    }

    public void export(List<Map<String, String>> jsonList, String excelFilePath) throws IOException {
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
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i+1);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
            }

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
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i+4);
            }

            // Escreve no arquivo Excel
            try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
                workbook.write(outputStream);
            }
            System.out.println("Arquivo Excel criado com sucesso!");
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
/*
    public static void main(String[] args) {
        List<Map<String, String>> jsonList = new ArrayList<>();

        // Objeto JSON 1
        Map<String, String> json1 = new HashMap<>();
        json1.put("nome", "Pato Mae 1.0");
        json1.put("status", "Vendido");
        json1.put("cliente", "Cliente 1");
        json1.put("tipo_do_cliente", "com Desconto");
        json1.put("valor", "40.00");
        jsonList.add(json1);

        // Objeto JSON 2
        Map<String, String> json2 = new HashMap<>();
        json2.put("nome", "Pato Mae");
        json2.put("status", "Disponivel");
        json2.put("cliente", null);
        json2.put("tipo_do_cliente", null);
        json2.put("valor", "50.00");
        jsonList.add(json2);

        String excelFilePath = "C:/Users/gmsn/Desktop/desafios_estudos/backend-with-java-spring-postgres-docker-flyway/teste.xlsx";

        try {
            export(jsonList, excelFilePath);
            System.out.println("Lista exportada para Excel com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao exportar lista para Excel: " + e.getMessage());
        }
    }*/
}


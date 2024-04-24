package br.com.menesic.GranjaApp.domain.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ExcelToPdfConverter {

    public static byte[] convertExcelToPdf(byte[] excelBytes) throws IOException {
        try (Workbook workbook = new HSSFWorkbook(new ByteArrayInputStream(excelBytes))) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try (PDDocument document = new PDDocument()) {
                for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                    Sheet sheet = workbook.getSheetAt(i);
                    PDPage page = new PDPage();
                    document.addPage(page);

                    try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(100, 700);

                        for (Row row : sheet) {
                            for (Cell cell : row) {
                                contentStream.showText(cell.toString());
                                contentStream.newLineAtOffset(100, 0);
                            }
                            contentStream.newLineAtOffset(-sheet.getRow(0).getPhysicalNumberOfCells() * 100, -20);
                        }

                        contentStream.endText(); // <- Adiciona esta linha para corrigir o erro
                    }
                }
                document.save(out);
            }
            return out.toByteArray();
        }
    }
}

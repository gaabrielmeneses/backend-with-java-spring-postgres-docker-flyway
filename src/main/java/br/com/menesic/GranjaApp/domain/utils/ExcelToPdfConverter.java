package br.com.menesic.GranjaApp.domain.utils;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.IntStream;


public class ExcelToPdfConverter {

    public static byte[] convertXlsToPdf(byte[] xlsBytes) throws IOException {
        try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(xlsBytes));ByteArrayOutputStream pdfStream = new ByteArrayOutputStream()) {
            PdfDocument pdf = new PdfDocument(new PdfWriter(pdfStream));
            Document document = new Document(pdf);

            IntStream.range(0, workbook.getNumberOfSheets())
                    .mapToObj(workbook::getSheetAt)
                    .map(sheet -> {
                        Table table = new Table(getMaxColumns(sheet));
                        IntStream.range(sheet.getFirstRowNum(), sheet.getLastRowNum() + 1)
                                .mapToObj(sheet::getRow)
                                .filter(Objects::nonNull)
                                .forEach(row -> IntStream.range(0, getMaxColumns(sheet))
                                        .mapToObj(row::getCell)
                                        .map(cell -> cell != null ? cell.toString() : "")
                                        .forEach(table::addCell));
                        return table;
                    })
                    .forEach(document::add);
            document.close();
            return pdfStream.toByteArray();
        }
    }

    private static int getMaxColumns(Sheet sheet) {
        return IntStream.range(sheet.getFirstRowNum(), sheet.getLastRowNum() + 1)
                .mapToObj(sheet::getRow)
                .filter(Objects::nonNull)
                .mapToInt(Row::getLastCellNum)
                .max()
                .orElse(0);
    }
}

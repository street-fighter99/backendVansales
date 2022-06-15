package com.ciferz.demo.services;

import com.ciferz.demo.inventory.ReturnEntity;
import com.ciferz.demo.model.StockChk;
import com.ciferz.demo.reposetries.sales.Entity.SalesEntity;
import com.ciferz.demo.reposetries.sales.SalesRepo;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.jfunc.json.impl.JSONArray;
import top.jfunc.json.impl.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PDFSalesReportPerodic {

    @Autowired
    SalesRepo salesRepo;


    public void PdfGenerator(HttpServletResponse response, String stdate, String eddate, int userID) throws IOException {

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA);
        fontTitle.setSize(25);

        Paragraph paragraph = new Paragraph("SALES REPORT B/W Dates", fontTitle);

        paragraph.setAlignment(Element.ALIGN_CENTER);

        Font fontParagraph2 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph2.setSize(12);

        Paragraph paragraph2 = new Paragraph("Date: ", fontParagraph2);
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph2.setSpacingBefore(20);


        Paragraph paragraph1 = new Paragraph("Signature of sales Rep................", fontParagraph2);
        paragraph1.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph1.setSpacingBefore(50);

        Paragraph paragraph12 = new Paragraph("Date :..............", fontParagraph2);
        paragraph12.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph12.setSpacingBefore(20);
        document.add(paragraph);
        document.add(paragraph2);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(12);


        writeTableHeader(table);
        writeTableData(table, stdate, eddate, userID);

        document.add(table);
        document.add(paragraph1);
        document.add(paragraph12);
        document.close();
    }

    public void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(8);

        cell.setPhrase(new Phrase("S.No", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("DATE", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("INV. NO", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("AMOUNT", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("VAT 15%", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("NET AMOUNT", font));
        table.addCell(cell);


    }

    public void writeTableData(PdfPTable table, String stdate, String eddate, int userId) {
        List<SalesEntity> list = salesRepo.getSalesBWDates(stdate, eddate, userId);
        double TotalAmount = 0.0;
        double TVat = 0.0;
        double TotalNAmount = 0.0;


        int i = 1;
        for (SalesEntity sales : list) {

            table.addCell(String.valueOf(i));
            table.addCell(String.valueOf(sales.getTdate()));
            table.addCell(String.valueOf(sales.getSaleId()));
//            table.addCell(sales.getItemList());
            table.addCell(String.format("%.2f",sales.getTotalAmount()));
            table.addCell(String.format("%.2f",sales.getVat()));
            table.addCell(String.format("%.2f",sales.getNetAmount()));

            TotalAmount += sales.getTotalAmount();
            TVat += sales.getVat();
            TotalNAmount += sales.getNetAmount();
            i++;


        }

        Font font = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE);
        font.setSize(13);


        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" Net Amount : ");
        table.addCell(String.format("%.2f",TotalAmount));
        table.addCell(String.format("%.2f",TVat));
        table.addCell(String.format("%.2f",TotalNAmount));


    }


}
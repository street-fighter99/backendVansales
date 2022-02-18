package com.ciferz.demo.services;

import com.ciferz.demo.reposetries.sales.Entity.SalesEntity;
import com.ciferz.demo.reposetries.sales.SalesRepo;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.lang.Math.round;

@Service
public class PDFGenerateService {

    @Autowired
    SalesRepo salesRepo;

    public void PdfGenerator(HttpServletResponse response, String date, int userId) throws IOException {

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document,response.getOutputStream());

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA);
        fontTitle.setSize(25);

        Paragraph paragraph = new Paragraph("Sales Report", fontTitle);

        paragraph.setAlignment(Element.ALIGN_CENTER);

        Font fontParagraph2 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph2.setSize(12);

        Paragraph paragraph2 = new Paragraph("Date: "+date, fontParagraph2);
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

        PdfPTable table = new PdfPTable(11);
        table.setWidthPercentage(100);
        table.setSpacingBefore(12);



        writeTableHeader(table);
        writeTableData(table,date,userId);

        document.add(table);
        document.add(paragraph1);
        document.add(paragraph12);
        document.close();



    }

    public void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(8);

        cell.setPhrase(new Phrase("S.No",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Bill NO.",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Customer ID",font));
        table.addCell(cell);
//        cell.setPhrase(new Phrase("Item List",font));
//        table.addCell(cell);
        cell.setPhrase(new Phrase("Vat",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Discount",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Aft Discount",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Total Amount",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Net Amount",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Recieved Amount",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Balance",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Total Balance",font));
        table.addCell(cell);


    }

    public void writeTableData(PdfPTable table, String date, int userId){
        List<SalesEntity> list = salesRepo.SDatess(date,userId);
        double totalAmnt =0.0;
        double totalNetAmnt =0.0;
        double totalReceivedAmnt =0.0;
        double totalBalance =0.0;
        double totalTBalance =0.0;

        int i = 1;
        for (SalesEntity sales : list){

            table.addCell(String.valueOf(i));
            table.addCell(String.valueOf(sales.getSaleId()));
            table.addCell(String.valueOf(sales.getCustomerId()));
//            table.addCell(sales.getItemList());
            table.addCell(String.valueOf(sales.getVat()));
            table.addCell(String.valueOf(sales.getDiscount()));
            table.addCell(String.valueOf(sales.getAftDiscount()));
            table.addCell(String.valueOf(sales.getTotalAmount()));
            table.addCell(String.valueOf(sales.getNetAmount()));
            table.addCell(String.valueOf(sales.getRecievedAmount()));
            table.addCell(String.valueOf(sales.getBalance()));
            table.addCell(String.valueOf(sales.getTotalBalance()));


            totalAmnt = totalAmnt + sales.getTotalAmount();
            totalNetAmnt = totalNetAmnt + sales.getNetAmount();
            totalReceivedAmnt = totalReceivedAmnt + sales.getRecievedAmount();
            totalBalance = totalBalance + sales.getBalance();
            totalTBalance = totalTBalance + sales.getTotalBalance();


            i++;
        }

        Font font = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE);
        font.setSize(13);



        PdfPCell cells = new PdfPCell(new Phrase("Total",font));
        cells.setColspan(6);
        cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cells.setVerticalAlignment(Element.ALIGN_CENTER);
        cells.setPaddingRight(10);
        table.addCell(cells);
        table.addCell(String.format("%.2f",totalAmnt));
        table.addCell(String.format("%.2f",totalNetAmnt));
        table.addCell(String.format("%.2f",totalReceivedAmnt));
        table.addCell(String.format("%.2f",totalBalance));
        table.addCell(String.format("%.2f",totalTBalance));

    }

}

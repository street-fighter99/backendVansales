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
import java.sql.Date;
import java.util.List;

@Service
public class PDFGenerateService {

    @Autowired
    SalesRepo salesRepo;

    public void PdfGenerator(HttpServletResponse response, String date) throws IOException {

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document,response.getOutputStream());

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA);
        fontTitle.setSize(25);

        Paragraph paragraph = new Paragraph("Sales Report", fontTitle);

        paragraph.setAlignment(Element.ALIGN_CENTER);

        Font fontParagraph2 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph2.setSize(12);

        Paragraph paragraph2 = new Paragraph("Date: ", fontParagraph2);
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);



        document.add(paragraph);
        document.add(paragraph2);

        PdfPTable table = new PdfPTable(10);
        table.setWidthPercentage(100);
        table.setSpacingBefore(12);



        writeTableHeader(table);
        writeTableData(table,date);

        document.add(table);

        document.close();



    }

    public void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(8);

        cell.setPhrase(new Phrase("Id",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Customer ID",font));
        table.addCell(cell);
//        cell.setPhrase(new Phrase("Item List",font));
//        table.addCell(cell);
        cell.setPhrase(new Phrase("Total Amount",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Discount",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Aft Discount",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Net Amount",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Recieved Amount",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Balance",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Total Balance",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Vat",font));
        table.addCell(cell);

    }

    public void writeTableData(PdfPTable table, String date){
        List<SalesEntity> list = salesRepo.SDatess(date);
        double totalAmnt =0.0;
        double totalNetAmnt =0.0;
        double totalReceivedAmnt =0.0;
        double totalBalance =0.0;
        double totalTBalance =0.0;

        for (SalesEntity sales : list){
            table.addCell(String.valueOf(sales.getId()));
            table.addCell(String.valueOf(sales.getCustomerId()));
//            table.addCell(sales.getItemList());
            table.addCell(String.valueOf(sales.getTotalAmount()));
            table.addCell(String.valueOf(sales.getDiscount()));
            table.addCell(String.valueOf(sales.getAftDiscount()));
            table.addCell(String.valueOf(sales.getNetAmount()));
            table.addCell(String.valueOf(sales.getRecievedAmount()));
            table.addCell(String.valueOf(sales.getBalance()));
            table.addCell(String.valueOf(sales.getTotalBalance()));
            table.addCell(String.valueOf(sales.getVat()));

            totalAmnt = totalAmnt + sales.getTotalAmount();
            totalNetAmnt = totalNetAmnt + sales.getNetAmount();
            totalReceivedAmnt = totalReceivedAmnt + sales.getRecievedAmount();
            totalBalance = totalBalance + sales.getBalance();
            totalTBalance = totalTBalance + sales.getTotalBalance();


        }

        PdfPTable table1 = new PdfPTable(10);
        table.setWidthPercentage(100);
        table.setSpacingBefore(12);
        PdfPCell cell = new PdfPCell(new Phrase("Total"));
        cell.setColspan(5);
        table1.addCell(cell);
        table.addCell(cell);



    }

}

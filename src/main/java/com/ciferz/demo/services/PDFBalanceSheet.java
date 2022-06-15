package com.ciferz.demo.services;

import com.ciferz.demo.reposetries.Customer.CustomerRepo;
import com.ciferz.demo.reposetries.Customer.Entity.CustomerEntity;
import com.ciferz.demo.reposetries.sales.Entity.SalesEntity;
import com.ciferz.demo.reposetries.sales.SalesRepo;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class PDFBalanceSheet {

    @Autowired
    CustomerRepo customerRepo;


    public void PdfGenerator(HttpServletResponse response, int userID) throws IOException {

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA);
        fontTitle.setSize(25);

        Paragraph paragraph = new Paragraph("CUSTOMER BALANCE SHEET", fontTitle);

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

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setSpacingBefore(12);


        writeTableHeader(table);
        writeTableData(table, userID);

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
        cell.setPhrase(new Phrase("NAME OF THE CUSTOMER", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("VAT NO.", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("AMOUNT", font));
        table.addCell(cell);


    }

    public void writeTableData(PdfPTable table, int userId) {
        List<CustomerEntity> list = customerRepo.getByUserId(userId);
        double TotalAmount = 0.0;

        int i = 1;
        for (CustomerEntity customer : list) {


            table.addCell(String.valueOf(i));
            table.addCell(customer.getName());
            table.addCell(customer.getVatNo());
            table.addCell(String.format("%.2f",customer.getCbalance()));
            TotalAmount += customer.getCbalance();
            i++;


        }

        Font font = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE);
        font.setSize(13);


        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" TOTAL AMOUNT : ");
        table.addCell(String.format("%.2f",TotalAmount));

    }
}

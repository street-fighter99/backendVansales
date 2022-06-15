package com.ciferz.demo.services;

import com.ciferz.demo.reposetries.Customer.CustomerRepo;
import com.ciferz.demo.reposetries.Customer.Entity.CustomerEntity;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PDFCustomerBal {

    @Autowired
    CustomerRepo customerRepo;

    public void PdfGenerator(HttpServletResponse response, int id, int userID) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document,response.getOutputStream());

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA);
        fontTitle.setSize(25);

        Paragraph paragraph = new Paragraph("Customer Balance Report", fontTitle);

        paragraph.setAlignment(Element.ALIGN_CENTER);

        Font fontParagraph2 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph2.setSize(12);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String currentDateTime= dateFormat.format(new Date());

        Paragraph paragraph2 = new Paragraph("Date: "+currentDateTime, fontParagraph2);
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
        writeTableData(table,id,userID);

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
        cell.setPhrase(new Phrase("Customer ID",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("name",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Balance",font));
        table.addCell(cell);
    }

    public void writeTableData(PdfPTable table, int id, int userID){
        List<CustomerEntity> list = customerRepo.getbyCsId(id,userID);
        double totalBalance =0.0;


        int i = 1;
        for (CustomerEntity customer : list){
            i=i++;
            table.addCell(String.valueOf(i));
            table.addCell(String.valueOf(customer.getCsId()));
            table.addCell(String.valueOf(customer.getName()));
            table.addCell(String.valueOf(customer.getCbalance()));

            totalBalance = totalBalance + customer.getCbalance();

        }

        Font font = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE);
        font.setSize(13);



        PdfPCell cells = new PdfPCell(new Phrase("Total",font));
        cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cells.setVerticalAlignment(Element.ALIGN_CENTER);
        cells.setPaddingRight(10);
        table.addCell("");
        table.addCell("");
        table.addCell(cells);
        table.addCell(String.valueOf(totalBalance));



    }

}

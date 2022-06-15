package com.ciferz.demo.services;

import com.ciferz.demo.reposetries.Customer.CustomerRepo;
import com.ciferz.demo.reposetries.Customer.Entity.CustomerEntity;
import com.ciferz.demo.reposetries.purchase.Entity.PurchaseEntity;
import com.ciferz.demo.reposetries.purchase.PurchaseRepo;
import com.ciferz.demo.reposetries.sales.Entity.SalesEntity;
import com.ciferz.demo.reposetries.sales.SalesRepo;
import com.ciferz.demo.reposetries.supplier.Entity.SupplierEntity;
import com.ciferz.demo.reposetries.supplier.SupplierRepo;
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
public class NewPeriodicPurchase {
    @Autowired
    PurchaseRepo purchaseRepo;

    @Autowired
    SupplierRepo supplierRepo;


    public void PdfGenerator(HttpServletResponse response, String stdate, String eddate, int userID) throws IOException {

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA);
        fontTitle.setSize(25);

        Paragraph paragraph = new Paragraph("PURCHASE REPORT B/W Dates", fontTitle);

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

        PdfPTable table = new PdfPTable(8);
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
        cell.setPhrase(new Phrase("NAME OF THE CUSTOMER", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("VAT NO.", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("AMOUNT", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("VAT 15%", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("NET AMOUNT", font));
        table.addCell(cell);


    }

    public void writeTableData(PdfPTable table, String stdate, String eddate, int userId) {
        List<PurchaseEntity> list = purchaseRepo.getpurchaseBWDates(stdate, eddate, userId);
        double TotalAmount = 0.0;
        double TVat = 0.0;
        double TotalNAmount = 0.0;


        int i = 1;
        for (PurchaseEntity purchase : list) {

            SupplierEntity supplier = supplierRepo.getSuppierByID(purchase.getSupplierId(),purchase.getUserId());
            table.addCell(String.valueOf(i));
            table.addCell(String.valueOf(purchase.getTdate()));
            table.addCell(String.valueOf(purchase.getPurchaseId()));
            table.addCell(supplier.getName());
            table.addCell(supplier.getVatNo());
//            table.addCell(sales.getItemList());
            table.addCell(String.format("%.2f",purchase.getAftDiscount()));
            table.addCell(String.format("%.2f",purchase.getVat()));
            table.addCell(String.format("%.2f",purchase.getNetAmount()));

            TotalAmount += purchase.getAftDiscount();
            TVat += purchase.getVat();
            TotalNAmount += purchase.getNetAmount();
            i++;


        }

        Font font = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE);
        font.setSize(13);


        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" Net Amount : ");
        table.addCell(String.format("%.2f",TotalAmount));
        table.addCell(String.format("%.2f",TVat));
        table.addCell(String.format("%.2f",TotalNAmount));


    }
}

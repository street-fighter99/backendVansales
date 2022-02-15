package com.ciferz.demo.controller;

import com.ciferz.demo.inventory.SoldItemStocks;
import com.ciferz.demo.services.PDFCustomerBal;
import com.ciferz.demo.services.PDFGenerateService;
import com.ciferz.demo.services.PDFStockListService;
import com.ciferz.demo.services.PDFVatReport;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("pdf")
public class PDFGenerateController {


    @Autowired
    PDFGenerateService pdfGenerateService;

    @Autowired
    PDFCustomerBal pdfCustomerBal;

    @Autowired
    PDFStockListService pdfStockListService;

    @Autowired
    PDFVatReport pdfVatReport;


    @GetMapping("/generate/pdf/sales/{pdate}/{userID}")
    public void generatePDF(HttpServletResponse response, @PathVariable("pdate") String pdate,@PathVariable("userID") int userID) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd:mm:ss");
        String currentDateTime= dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=SalesSummaryPDF_"+ currentDateTime +".pdf";
        response.setHeader(headerKey,headerValue);

        pdfGenerateService.PdfGenerator(response, pdate,userID);
    }

    @GetMapping("/generate/pdf/customer/{id}/{userID}")
    public void generateCustomerBalPDF(HttpServletResponse response,@PathVariable("id") int id,@PathVariable("userID") int userID ) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd:mm:ss");
        String currentDateTime= dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=CustomerBalancePDF_"+ currentDateTime +".pdf";
        response.setHeader(headerKey,headerValue);

        pdfCustomerBal.PdfGenerator(response, id,userID);
    }

    @GetMapping("/generate/stocklist/{date}/{userID}")
    public void generateCustomerBalsPDF(HttpServletResponse response,@PathVariable("date") String date,@PathVariable("userID") int userID) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd:mm:ss");
        String currentDateTime= dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=StockListPDF_"+ currentDateTime +".pdf";
        response.setHeader(headerKey,headerValue);

        pdfStockListService.PdfGenerator(response, date, userID);
    }

    @GetMapping("/generate/vatreport/{userId}")
    public void generateVatReport(HttpServletResponse response,@PathVariable int userId) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd:mm:ss");
        String currentDateTime= dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=VatReportPDF_"+ currentDateTime +".pdf";
        response.setHeader(headerKey,headerValue);

        pdfVatReport.PdfGenerator(response,currentDateTime,userId);
    }


}

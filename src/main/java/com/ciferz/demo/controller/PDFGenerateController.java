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


    @GetMapping("/generate/pdf/sales")
    public void generatePDF(HttpServletResponse response, @RequestBody pdfgetdata pdfdata) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd:mm:ss");
        String currentDateTime= dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=SalesSummaryPDF_"+ currentDateTime +".pdf";
        response.setHeader(headerKey,headerValue);

        pdfGenerateService.PdfGenerator(response, pdfdata.getPdate(),pdfdata.getUserId());
    }

    @GetMapping("/generate/pdf/customer/{id}")
    public void generateCustomerBalPDF(HttpServletResponse response,@PathVariable int id) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd:mm:ss");
        String currentDateTime= dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=CustomerBalancePDF_"+ currentDateTime +".pdf";
        response.setHeader(headerKey,headerValue);

        pdfCustomerBal.PdfGenerator(response, id);
    }

    @GetMapping("/generate/stocklist/{date}")
    public void generateCustomerBalPDF(HttpServletResponse response,@RequestBody pdfgetdata pdfgetdata) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd:mm:ss");
        String currentDateTime= dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=StockListPDF_"+ currentDateTime +".pdf";
        response.setHeader(headerKey,headerValue);

        pdfStockListService.PdfGenerator(response, pdfgetdata.getPdate(), pdfgetdata.getUserId());
    }

    @GetMapping("/generate/vatreport")
    public void generateVatReport(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd:mm:ss");
        String currentDateTime= dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=VatReportPDF_"+ currentDateTime +".pdf";
        response.setHeader(headerKey,headerValue);

        pdfVatReport.PdfGenerator(response,currentDateTime);
    }


}

@Data
class pdfgetdata {

    private int id;
    private int userId;
    private String pdate;


}
package com.ciferz.demo.controller;

import com.ciferz.demo.services.PDFGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @GetMapping("/generate/{date}")
    public void generatePDF(HttpServletResponse response,@PathVariable String date) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd:mm:ss");
        String currentDateTime= dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=SalesSummaryPDF_"+ currentDateTime +".pdf";
        response.setHeader(headerKey,headerValue);

        pdfGenerateService.PdfGenerator(response, date);
    }

}

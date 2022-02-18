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
public class PDFStockListService {


    @Autowired
    SalesRepo salesRepo;

    public void PdfGenerator(HttpServletResponse response, String date, int userID) throws IOException {

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document,response.getOutputStream());

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA);
        fontTitle.setSize(25);

        Paragraph paragraph = new Paragraph("Sold Stock List", fontTitle);

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

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(12);



        writeTableHeader(table);
        writeTableData(table,date,userID);

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
        cell.setPhrase(new Phrase("Code",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Name",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Qty",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Price",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Total Price",font));
        table.addCell(cell);



    }

    public void writeTableData(PdfPTable table, String date, int userId){
        List<StockChk> list = getalltheitem(date,userId);
        double TotalQty =0.0;
        double TPrice =0.0;
        double TotalTPrice =0.0;


        int i = 1;
        for (StockChk stock : list){

            table.addCell(String.valueOf(i));
            table.addCell(String.valueOf(stock.getId()));
            table.addCell(String.valueOf(stock.getName()));
//            table.addCell(sales.getItemList());
            table.addCell(String.valueOf(stock.getStock()));
            table.addCell(String.valueOf(stock.getPrice()));
            double totalPrice = stock.getPrice()*stock.getStock();
            table.addCell(String.valueOf(totalPrice));

            TotalQty = TotalQty + stock.getStock();
            TPrice = TPrice + stock.getPrice();
            TotalTPrice = TotalTPrice + totalPrice;

            i++;
        }

        Font font = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE);
        font.setSize(13);




        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" TOTAL : ");
        table.addCell(String.valueOf(TotalQty));
        table.addCell(String.valueOf(TPrice));
        table.addCell(String.valueOf(TotalTPrice));



    }



    public List<StockChk> getalltheitem(String date,int userId) {

        List<SalesEntity> list = salesRepo.SDatess(date,userId);
        int stock = 0;
        List<StockChk> list1 = new ArrayList<>();

        for (SalesEntity sales : list) {

            String data = sales.getItemList();
            JSONArray jsonArray1 = new JSONArray(data);

            for (int i = 0; i < jsonArray1.size(); i++) {

                JSONObject jsonObj = (JSONObject) jsonArray1.getJsonObject(i);
                int chk = Integer.parseInt(String.valueOf(jsonObj.get("id")));
                ReturnEntity returnEntity = ChkTheExistence(list1,chk);
                if (returnEntity.isActive())
                {
                    System.out.println("the data is already "+returnEntity.getIndex());
                    stock = stock+Integer.parseInt(String.valueOf(jsonObj.get("quantity")));
//                    sales1.setId(Integer.parseInt(String.valueOf(jsonObj.get("id"))));
//                    sales1.setStock(stock);
                    int index = returnEntity.getIndex();
                    System.out.println("    "+index);

                    list1.get(index).setId(Integer.parseInt(String.valueOf(jsonObj.get("id"))));
                    list1.get(index).setStock(stock);
                    list1.get(index).setName(String.valueOf(jsonObj.get("name")));


                } else {
                    StockChk sales1 = new StockChk();
                    sales1.setId((Integer) jsonObj.get("id"));
                    stock = Integer.parseInt(String.valueOf(jsonObj.get("quantity")));
                    sales1.setStock(stock);
                    sales1.setName(String.valueOf(jsonObj.get("name")));
                    sales1.setPrice(Double.parseDouble(String.valueOf(jsonObj.get("price"))));
                    list1.add(sales1);
                    System.out.println(list1);



                }
            }
        }
        System.out.println("/////////////////////////////////\n"+list1+"\n\n\n");

        return list1;
    }







    private ReturnEntity ChkTheExistence(List<StockChk> list1, int chk) {
//        int ida = list1.get(0).getId();
        boolean ans =false;
        int index=0;
        for (int i = 0;i<list1.size();i++){
            if (list1.get(i).getId()==chk){
                ans = true;
                index =i;
                break;
            }else{
                ans = false;
            }
        }

        ReturnEntity returnEntity = new ReturnEntity();
        returnEntity.setActive(ans);
        returnEntity.setIndex(index);

        System.out.println("\n\n"+returnEntity+"   "+chk+"\n\n\n");
        return returnEntity;

    }
}

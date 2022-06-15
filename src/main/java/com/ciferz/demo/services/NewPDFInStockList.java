package com.ciferz.demo.services;

import com.ciferz.demo.inventory.ReturnEntity;
import com.ciferz.demo.model.StockChk;
import com.ciferz.demo.reposetries.item.Entity.ItemEntity;
import com.ciferz.demo.reposetries.item.ItemRepo;
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
public class NewPDFInStockList {



    @Autowired
    ItemRepo itemRepo;

    public void PdfGenerator(HttpServletResponse response, int userID) throws IOException {

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document,response.getOutputStream());

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA);
        fontTitle.setSize(25);

        Paragraph paragraph = new Paragraph("Stock List", fontTitle);

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

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100);
        table.setSpacingBefore(12);



        writeTableHeader(table);
        writeTableData(table,userID);

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
        cell.setPhrase(new Phrase("ITEM ID",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("ITEM NAME",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Qty",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("COST",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Total COST",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("PRICE",font));
        table.addCell(cell);



    }

    public void writeTableData(PdfPTable table, int userId){
        java.util.List<StockChk> list = getalltheitem(userId);
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
            table.addCell(String.format("%.2f",stock.getPrice()));
            double totalPrice = stock.getPrice()*stock.getStock();
            table.addCell(String.format("%.2f",totalPrice));
            table.addCell(String.valueOf(stock.getSprice()));

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
        table.addCell(String.format("%.2f",TotalQty));
        table.addCell(String.format("%.2f",TPrice));
        table.addCell(String.format("%.2f",TotalTPrice));



    }



    public java.util.List<StockChk> getalltheitem(int userId) {

        java.util.List<ItemEntity> list = itemRepo.getByUserId(userId);
        int stock = 0;
        java.util.List<StockChk> list1 = new ArrayList<>();

        for (ItemEntity item : list) {

            String data = item.getSuppliers();
            JSONArray jsonArray1 = new JSONArray(data);

            for (int i = 0; i < jsonArray1.size(); i++) {

                JSONObject jsonObj = (JSONObject) jsonArray1.getJsonObject(i);
//                int chk = Integer.parseInt(String.valueOf(jsonObj.get("id")));
//                ReturnEntity returnEntity = ChkTheExistence(list1,chk);
//                if (returnEntity.isActive())
//                {
//                    System.out.println("the data is already "+returnEntity.getIndex());
//                    stock = stock+Integer.parseInt(String.valueOf(jsonObj.get("quantity")));
////
//                    int index = returnEntity.getIndex();
//                    System.out.println("    "+index);
//
//                    list1.get(index).setId(item.getItemId()));
//                    list1.get(index).setStock(stock);
//                    list1.get(index).setName(String.valueOf(jsonObj.get("name")));
//
//
//                } else {
                    StockChk sales1 = new StockChk();
                    sales1.setId(item.getItemId());
                    sales1.setStock(item.getStock());
                    sales1.setName(item.getName());
                    sales1.setPrice(Double.parseDouble(String.valueOf(jsonObj.get("bprice"))));
                    sales1.setSprice(Double.parseDouble(String.valueOf(jsonObj.get("sprice"))));
                    list1.add(sales1);
                    System.out.println(sales1.getPrice());



//                }
            }
        }
        System.out.println("/////////////////////////////////\n"+list1+"\n\n\n");

        return list1;
    }







//    private ReturnEntity ChkTheExistence(List<StockChk> list1, int chk) {
////        int ida = list1.get(0).getId();
//        boolean ans =false;
//        int index=0;
//        for (int i = 0;i<list1.size();i++){
//            if (list1.get(i).getId()==chk){
//                ans = true;
//                index =i;
//                break;
//            }else{
//                ans = false;
//            }
//        }
//
//        ReturnEntity returnEntity = new ReturnEntity();
//        returnEntity.setActive(ans);
//        returnEntity.setIndex(index);
//
//        System.out.println("\n\n"+returnEntity+"   "+chk+"\n\n\n");
//        return returnEntity;
//
//    }


}

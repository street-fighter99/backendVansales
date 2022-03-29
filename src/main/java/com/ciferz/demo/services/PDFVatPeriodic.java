package com.ciferz.demo.services;

import com.ciferz.demo.reposetries.purchase.PurchaseRepo;
import com.ciferz.demo.reposetries.sales.SalesRepo;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class PDFVatPeriodic {

    @Autowired
    SalesRepo salesRepo;

    @Autowired
    PurchaseRepo purchaseRepo;

    private final EntityManagerFactory emf;

    public PDFVatPeriodic(EntityManagerFactory emf) {
        this.emf = emf;
    }


    public void PdfGenerator(HttpServletResponse response, String stdate, String eddate, int userID) throws IOException {

        Document document = new Document(PageSize.A4);
//        PdfWriter.getInstance(document,response.getOutputStream());
        PdfWriter.getInstance(document,response.getOutputStream());

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA);
        fontTitle.setSize(25);

        Paragraph paragraph = new Paragraph("VAT Report", fontTitle);

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
        writeTableData(table,stdate,eddate,userID);

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
        cell.setPhrase(new Phrase("Month",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Net Sales",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Net Sales Vat",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Net Purchase",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Net Purchase Vat",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Vat Payable",font));
        table.addCell(cell);



    }

    public void writeTableData(PdfPTable table, String stdate, String eddate, int userId){



        List<Object[]> list  = getVatByDate(stdate,eddate,userId);

        int i = 1;
        for (Object[] obj : list ){

            table.addCell(String.valueOf(i));
            table.addCell(String.valueOf(obj[0]));
            table.addCell(String.valueOf(obj[1]));
            table.addCell(String.valueOf(obj[2]));
            table.addCell(String.valueOf(obj[3]));
            table.addCell(String.valueOf(obj[4]));
            table.addCell(String.valueOf(obj[5]));
            i++;
        }












//        List<MSales> Slist = getalldatafromamonth(stdate,eddate,userId);
//        List<MPurchase> pList = getalldatafromamonthPurhase(stdate,eddate,userId);
////        double TotalQty =0.0;
////        double TPrice =0.0;
////        double TotalTPrice =0.0;
//
//
//        int i = 1;
//        int listcount = Slist.size();
//        System.out.println("size of the list = "+listcount);
//        for (int j=0; j<listcount;j++){
//
//            table.addCell(String.valueOf(i));
//            table.addCell(String.valueOf(Slist.get(j).getMonth()));
//            table.addCell(String.valueOf(Slist.get(j).getNetAmount()));
//            table.addCell(String.valueOf(Slist.get(j).getNetVat()));
//            table.addCell(String.valueOf(pList.get(j).getNetAmount()));
//            double vat = pList.get(j).getNetVat();
//            table.addCell(String.format("%.2f",vat));
////            table.addCell(String.valueOf(pList.get(j).getNetVat()));
//        }
//
//        Font font = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE);
//        font.setSize(13);
//
//


//        table.addCell(" ");
//        table.addCell(" ");
//        table.addCell(" TOTAL : ");
//        table.addCell(String.valueOf(TotalQty));
//        table.addCell(String.valueOf(TPrice));
//        table.addCell(String.valueOf(TotalTPrice));



    }

//    public List<MSales> getalldatafromamonth(String stdate, String eddate, int userId){
//
//        String arrofstd[] = stdate.split("-");
//        int stDay = Integer.parseInt(arrofstd[2]);
//        int stmonth = Integer.parseInt(arrofstd[1])-1;
//        int styear = Integer.parseInt(arrofstd[0]);
//
//        String arrOfEnd[] = eddate.split("-");
//        int edDay = Integer.parseInt(arrOfEnd[2]);
//        int edMonth = Integer.parseInt(arrOfEnd[1]);
//        int edYear = Integer.parseInt(arrOfEnd[0]);
//        List<MSales> list = new ArrayList<>();
//
//        long monthCount = ChronoUnit.MONTHS.between(
//                LocalDate.parse(stdate).withDayOfMonth(1),
//                LocalDate.parse(eddate).withDayOfMonth(30));
//
//        for (int i=0;i<=monthCount;i++){
//            if (stmonth>=12){
//                stmonth = 1;
//                styear++;
//            }else {
//                System.out.println("s - "+stmonth);
//                stmonth++;
//                System.out.println("s2 - "+stmonth);
//
//            }
//
//           List<SalesEntity> salesMonthList = salesRepo.getbydate(stmonth,styear);
//
//            double totalSalesAmonth = 0;
//            double totalSalesVatAmonth = 0;
//            for (SalesEntity sales: salesMonthList){
//
//                totalSalesAmonth = totalSalesAmonth + sales.getRecievedAmount();
//                totalSalesVatAmonth = totalSalesVatAmonth + sales.getVat();
//
//            }
//            MSales sales = new MSales();
//
//            sales.setNetAmount(totalSalesAmonth);
//            sales.setNetVat(totalSalesVatAmonth);
//            String[] monthString = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
//
//            System.out.println(stmonth);
//            sales.setMonth(monthString[stmonth-1]);
//
//            list.add(sales);
//            System.out.println(sales.getMonth());
//
//
//        }
//        return list;
//    }


//    public List<MPurchase> getalldatafromamonthPurhase(String stdate, String eddate, int userId){
//
//        String arrofstd[] = stdate.split("-");
//        int stDay = Integer.parseInt(arrofstd[2]);
//        int stmonth = Integer.parseInt(arrofstd[1])-1;
//        int styear = Integer.parseInt(arrofstd[0]);
//
//        String arrOfEnd[] = eddate.split("-");
//        int edDay = Integer.parseInt(arrOfEnd[2]);
//        int edMonth = Integer.parseInt(arrOfEnd[1]);
//        int edYear = Integer.parseInt(arrOfEnd[0]);
//        List<MPurchase> list = new ArrayList<>();
//
//        long monthCount = ChronoUnit.MONTHS.between(
//                LocalDate.parse(stdate).withDayOfMonth(1),
//                LocalDate.parse(eddate).withDayOfMonth(30));
//
//        for (int i=0;i<=monthCount;i++){
//            if (stmonth>=12){
//                stmonth = 1;
//                styear++;
//            }else {
//                System.out.println("s - "+stmonth);
//                stmonth++;
//                System.out.println("s2 - "+stmonth);
//
//            }
//
//            List<PurchaseEntity> purchaseMonthList = purchaseRepo.getbydate(stmonth,styear);
//
//            double totalSalesAmonth = 0;
//            double totalSalesVatAmonth = 0;
//            for (PurchaseEntity purchase: purchaseMonthList){
//
//                totalSalesAmonth = totalSalesAmonth + purchase.getPaidAmount();
//                totalSalesVatAmonth = totalSalesVatAmonth + Double.parseDouble(purchase.getVat());
//
//            }
//            MPurchase purchase = new MPurchase();
//
//            purchase.setNetAmount(totalSalesAmonth);
//            purchase.setNetVat(totalSalesVatAmonth);
//            String[] monthString = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
//
//            System.out.println(stmonth);
//            purchase.setMonth(monthString[stmonth-1]);
//
//            list.add(purchase);
//            System.out.println(purchase.getMonth());
//
//
//        }
//        return list;
//    }


    public List<Object[]> getVatByDate(String stDate,String edDate,int userID) {
        EntityManager entityManager = emf.createEntityManager();
        Query query = entityManager
                .createNativeQuery("select m.monthyear, sum(m.sales) as total_sales,sum(m.sales_vat) as sales_vat, sum(m.purchase) as total_purchase ,sum(m.purchase_vat) as purchase_vat, (m.sales_vat - m.purchase_vat) as net_vat from \n" +
                        "(select date_format(s_date, '%Y-%M') as monthyear, \n" +
                        "round(sum(net_amount),2) as sales,\n" +
                        "round(sum(vat),2) as sales_vat,\n" +
                        " 0 purchase,\n" +
                        " 0 purchase_vat\n" +
                        " from sales where s_date between '"+stDate+"' and '"+edDate+"' and (user_id = "+String.valueOf(userID)+") group by date_format(s_date, '%Y-%M') union all\n" +
                        "select date_format(p_date, '%Y-%M'), \n" +
                        "0 sales,\n" +
                        "0 sales,\n" +
                        " round(sum(net_amount),2) as purchase ,\n" +
                        " round(sum(vat),2) as purchase_vat\n" +
                        " from purchase where p_date between \'"+stDate+"\' and \'"+edDate+"\' and (user_id = "+String.valueOf(userID)+") group by date_format(p_date, '%Y-%M')) as m\n" +
                        "group by m.monthyear order by 1");

        return query.getResultList();
    }

}

//@Data
//class MSales{
//    private String month;
//    private double netAmount;
//    private double netVat;
//
//}
//
//@Data
//class MPurchase{
//    private String month;
//    private double netAmount;
//    private double netVat;
//
//}
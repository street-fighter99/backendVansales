package com.ciferz.demo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;

@Service
public class TestService {

//    @Autowired
//    ReportRepository reportRepository;

   private final EntityManagerFactory emf;

    public TestService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<Object[]> find(String stDate,String edDate,int userID) {
        EntityManager entityManager = emf.createEntityManager();
        Query query = entityManager
                .createNativeQuery("select m.monthyear, sum(m.sales) as total_sales,sum(m.sales_vat) as sales_vat, sum(m.purchase) as total_purchase ,sum(m.purchase_vat) as purchase_vat from \n" +
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
    }}

//    public void getTheMonth(){
//        String stDate = "2021-01-31";
//        String stDatearr[] = "2021-01-31".split("-");
//        String edDate = "2022-03-30";
//        long monthCount = ChronoUnit.MONTHS.between(
//                LocalDate.parse(stDate).withDayOfMonth(1),
//                LocalDate.parse(edDate).withDayOfMonth(1));
//        System.out.println(monthCount);
//        System.out.println("------------ month count -------------------- "+monthCount);
//
//        int stMonth =Integer.parseInt(stDatearr[1]);
//        int styear = Integer.parseInt(stDatearr[0]);
//        System.out.println(stMonth+"   "+styear);
//
//
//        for (int i=0;i<monthCount;i++){
//
//            if (stMonth >=12){
//                stMonth = 1;
//                styear++;
//
//            }else{
//                 stMonth++;
//            }
//
////           List<SalesEntity> salesEntity =  salesRepo.getbydate(stMonth,styear);
////            double total = 0;
////            for (SalesEntity sales : salesEntity){
////
////                total = total+sales.getNetAmount();
////                System.out.println("NetSales = "+total);
////
////            }
////            System.out.println(salesEntity+" "+styear+" "+stMonth);
////        }
//    }
//
//
//    }

//    public ResponseEntity testt(){
//        List<Object[]> list = find();
//        for (Object[] a: list){
//            System.out.println("Year_Month = "+a[0]);
//            System.out.println("totalSales = "+a[1]);
//            System.out.println("Sales_vat = "+a[2]);
//            System.out.println("totalpurchase = "+a[3]);
//            System.out.println("vat_purchase = "+a[4]);
//        }
//        return new ResponseEntity(list, HttpStatus.ACCEPTED);
//    }
//}

//@Entity
//class modelclass{
//
//    @Column(name ="monthyear")
//    private String monthyear;
//    @Column(name = "total_sales")
//    private double totalSales;
//    @Column(name = "sales_vat")
//    private double salesVat;
//    @Column(name = "total_purchase")
//    private double totalPurchase;
//    @Column(name = "purchase_vat")
//    private double purchaseVat;
//
//
//
//}
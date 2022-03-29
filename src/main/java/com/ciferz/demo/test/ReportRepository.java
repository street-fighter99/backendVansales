package com.ciferz.demo.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;


public class ReportRepository {

    private  final EntityManagerFactory emf;

    public  ReportRepository(EntityManagerFactory emf){

        this.emf = emf;
    }

    public List<Object[]> find() {
        EntityManager entityManager = emf.createEntityManager();
        Query query = entityManager
                .createQuery("select m.monthyear, sum(m.sales) as total_sales,sum(m.sales_vat) as sales_vat, sum(m.purchase) as total_purchase ,sum(m.purchase_vat) as purchase_vat from \n" +
                        "(select date_format(s_date, '%Y-%M') as monthyear, \n" +
                        "round(sum(net_amount),2) as sales,\n" +
                        "round(sum(vat),2) as sales_vat,\n" +
                        " 0 purchase,\n" +
                        " 0 purchase_vat\n" +
                        " from sales where s_date between '2021-03-23' and '2022-03-23' group by date_format(s_date, '%Y-%M') union all\n" +
                        "select date_format(p_date, '%Y-%M'), \n" +
                        "0 sales,\n" +
                        "0 sales,\n" +
                        " round(sum(net_amount),2) as purchase ,\n" +
                        " round(sum(vat),2) as purchase_vat\n" +
                        " from purchase where p_date between '2021-03-23' and '2022-03-23' group by date_format(p_date, '%Y-%M')) as m\n" +
                        "group by m.monthyear order by 1");

        return query.getResultList();
    }

}

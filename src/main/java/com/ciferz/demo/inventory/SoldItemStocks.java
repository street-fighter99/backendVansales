package com.ciferz.demo.inventory;

import com.ciferz.demo.model.StockChk;
import com.ciferz.demo.reposetries.sales.Entity.SalesEntity;
import com.ciferz.demo.services.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.jfunc.json.impl.JSONArray;
import top.jfunc.json.impl.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Service
public class SoldItemStocks {

    @Autowired
    SalesService salesService;


    public List<StockChk> getalltheitem() {

        List<SalesEntity> list = salesService.showAllTheData();
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


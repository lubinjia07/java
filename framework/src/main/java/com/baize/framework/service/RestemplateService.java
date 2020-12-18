package com.baize.framework.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author lubinjia
 * @create 2020/11/22 18:47
 */
@Service
public class RestemplateService {
    @Autowired
    private RestTemplate restTemplate;

    private final static String appKey = "D69GOsp1ThsJqBOc";
    private final static String appSecret = "uwcsk0pefh7zzjty";
    private final static String callbackUrl = "193.112.11.62:8002/wx/invoice/callback";
    private final static String invoiceUrl = "https://fapiao-api.easyapi.com/invoice/make";

    public InvoiceResponse testPost() {
        String content = "{\n" +
                "\t\"purchaserBank\": \"\",\n" +
                "\t\"purchaserBankAccount\": \"\",\n" +
                "\t\"purchaserPhone\": \"15600624736\",\n" +
                "\t\"mobile\": \"15600624736\",\n" +
                "\t\"taxNumber\": \"20201117531843\",\n" +
                "\t\"remark\": \"\",\n" +
                "\t\"type\": \"个人\",\n" +
                "\t\"outOrderNo\": \"20201117531843\",\n" +
                "\t\"purchaserAddress\": \"\",\n" +
                "\t\"purchaserName\": \"个人发票\",\n" +
                "\t\"taxPlate\": \"\",\n" +
                "\t\"property\": \"电子\",\n" +
                "\t\"appKey\": \"D69GOsp1ThsJqBOc\",\n" +
                "\t\"appSecret\": \"uwcsk0pefh7zzjty\",\n" +
                "\t\"callbackUrl\": \"193.112.11.62:8002/wx/invoice/callback\",\n" +
                "\t\"category\": \"增值税普通发票\",\n" +
                "\t\"items\": [{\n" +
                "\t\t\"number\": 1,\n" +
                "\t\t\"unit\": \"箱\",\n" +
                "\t\t\"price\": 229.00,\n" +
                "\t\t\"name\": \"新西兰佳沛金奇异果27#\",\n" +
                "\t\t\"model\": \"标准\"\n" +
                "\t}, {\n" +
                "\t\t\"number\": 1,\n" +
                "\t\t\"unit\": \"箱\",\n" +
                "\t\t\"price\": 269.00,\n" +
                "\t\t\"name\": \"泰国椰皇24个装\",\n" +
                "\t\t\"model\": \"标准\"\n" +
                "\t}],\n" +
                "\t\"purchaserTaxpayerNumber\": \"\",\n" +
                "\t\"email\": \"540171689@qq.com\",\n" +
                "\t\"username\": 3\n" +
                "}";

        MultiValueMap<String, Object> headers = new LinkedMultiValueMap<String, Object>();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");

        HttpEntity request = new HttpEntity(content, headers);
        ResponseEntity<InvoiceResponse> responseEntity = restTemplate.exchange(invoiceUrl, HttpMethod.POST, request, InvoiceResponse.class);
        InvoiceResponse invoiceResponse = responseEntity.getBody();
        return invoiceResponse;
    }

    @Data
    public static class InvoiceResponse {
        private String code;
        private String message;
        private String content;
    }


}

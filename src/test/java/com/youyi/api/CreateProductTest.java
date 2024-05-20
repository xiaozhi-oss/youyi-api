package com.youyi.api;

import com.youyi.api.model.entity.Product;
import com.youyi.api.model.entity.UserInfo;
import com.youyi.api.service.ProductService;
import com.youyi.api.service.UserInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaozhi
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateProductTest {
    
    @Resource
    private ProductService productService;
    
    @Resource
    private UserInfoService userInfoService;
    
    @Test
    void contextLoads() {
        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        for (int i = 0; i < 20; i++) {
            product.setName("手工穿戴甲 长款高级线条感、轻奢 - " + i);
            product.setMiaoshu("低调奢华");
            product.setPrice(new BigDecimal(26 + i));
            product.setSize("S码-M码-L码");
            product.setUrl("https://aoao-jiao.oss-cn-guangzhou.aliyuncs.com/iamge/3.png");
            product.setKucun(100 + i);
            productList.add(product);
        }
        productService.saveBatch(productList);
    }
    
    @Test
    public void saveUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUrl("sdfds");
        userInfo.setUsername("sdfsd");
        userInfo.setStatus(0);
        userInfo.setSex(2);
        userInfoService.save(userInfo);
        System.out.println(userInfo.getId());
    }

    public static void main(String[] args) throws FileNotFoundException {
        String path = "json\\ExpressJson\\01.order.json";
        String jsonString = "";
        File file = new File(path);
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());
        try {
            InputStream is = Files.newInputStream(Paths.get(path));
            byte[] bs = new byte[is.available()];
            is.read(bs);
            jsonString = new String(bs);
        } catch (Exception var4) {
        }
        System.out.println(jsonString);
    }
}

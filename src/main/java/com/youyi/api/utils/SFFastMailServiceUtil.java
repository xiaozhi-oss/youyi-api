package com.youyi.api.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sf.csim.express.service.CallExpressServiceTools;
import com.sf.csim.express.service.HttpClientUtil;
import com.sf.csim.express.service.IServiceCodeStandard;
import com.sf.csim.express.service.code.ExpressServiceCodeEnum;
import com.youyi.api.model.sfobj.RouteObj;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author xiaozhi
 */
@Slf4j
public class SFFastMailServiceUtil {


    /**
     * 顾客编码  YQUPV2VR
     * 校验码  ogBKtVXnuwrxj0rusAgjkiOFcZDwFUgs
     **/
    private static final String CLIENT_CODE = "YQUPV2VR";  // 此处替换为您在丰桥平台获取的顾客编码
    private static final String CHECK_WORD = "ogBKtVXnuwrxj0rusAgjkiOFcZDwFUgs";// 此处替换为您在丰桥平台获取的校验码


    // 沙箱环境的地址 -PRO
    private static final String CALL_URL_BOX = "https://sfapi-sbox.sf-express.com/std/service";
    // 生产环境的地址 -PRO
    private static final String CALL_URL_PROD = "https://sfapi.sf-express.com/std/service";

    /**
     * ExpressServiceCodeEnum     对应速运类-快递APIs
     * POSTServiceCodeEnum        对应速运类-驿站APIs
     * YJTServiceCodeEnum         对应解决方案-医寄通APIs
     * EPSServiceCodeEnum         对应解决方案-快递管家APIs
     * HZTServiceCodeEnum         对应解决方案-函证通APIs    2022-2-24 新增
     * 详情见code目录下枚举类，客户可自行修改引用的该类
     **/

    // IServiceCodeStandard standardService = ExpressServiceCodeEnum.EXP_RECE_CREATE_ORDER; // 下订单
    // IServiceCodeStandard standardService = ExpressServiceCodeEnum.EXP_RECE_SEARCH_ORDER_RESP; // 查订单
    //  IServiceCodeStandard standardService = ExpressServiceCodeEnum.EXP_RECE_UPDATE_ORDER;//订单取消
    // 	IServiceCodeStandard standardService = ExpressServiceCodeEnum.EXP_RECE_FILTER_ORDER_BSP;//订单筛选
    //  IServiceCodeStandard standardService = ExpressServiceCodeEnum.EXP_RECE_SEARCH_ROUTES;//查路由
    //	IServiceCodeStandard standardService = ExpressServiceCodeEnum.EXP_RECE_GET_SUB_MAILNO;//子单号
    //	IServiceCodeStandard standardService = ExpressServiceCodeEnum.EXP_RECE_QUERY_SFWAYBILL;//查运费
    //	IServiceCodeStandard standardService = ExpressServiceCodeEnum.EXP_RECE_REGISTER_ROUTE;//注册路由	
    //	IServiceCodeStandard standardService = ExpressServiceCodeEnum.EXP_RECE_CREATE_REVERSE_ORDER;//退货下单
    //	IServiceCodeStandard standardService = ExpressServiceCodeEnum.EXP_RECE_CANCEL_REVERSE_ORDER;//退货消单
    //	IServiceCodeStandard standardService = ExpressServiceCodeEnum.EXP_RECE_WANTED_INTERCEPT;//截单转寄
    // 	IServiceCodeStandard standardService = ExpressServiceCodeEnum.EXP_RECE_QUERY_DELIVERTM;//时效标准及价格查询
    //  IServiceCodeStandard standardService = ExpressServiceCodeEnum.COM_RECE_CLOUD_PRINT_WAYBILLS;//面单打印
    //  IServiceCodeStandard standardService = ExpressServiceCodeEnum.EXP_RECE_UPLOAD_ROUTE;//路由上传
    //   IServiceCodeStandard standardService = ExpressServiceCodeEnum.EXP_RECE_SEARCH_PROMITM;//预计派送时间查询
    //  IServiceCodeStandard standardService = ExpressServiceCodeEnum.EXP_EXCE_CHECK_PICKUP_TIME;//揽件服务时间查询
    //  IServiceCodeStandard standardService = ExpressServiceCodeEnum.EXP_RECE_VALIDATE_WAYBILLNO;//运单号合法性校验	

    /**
     * 根据 orderId 创建订单
     *
     * @param standardService
     * @return
     */
    public static String requestResult(IServiceCodeStandard standardService) {
        String msgData = CallExpressServiceTools.packageMsgData(standardService);
        try {
            // set common header
            Map<String, String> params = new HashMap<>();
            String timeStamp = java.lang.String.valueOf(System.currentTimeMillis());
            params.put("partnerID", CLIENT_CODE);  // 顾客编码 ，对应丰桥上获取的clientCode
            params.put("requestID", UUID.randomUUID().toString().replace("-", ""));
            params.put("serviceCode", standardService.getCode());// 接口服务码
            params.put("timestamp", timeStamp);
            params.put("msgData", msgData);
            params.put("msgDigest", CallExpressServiceTools.getMsgDigest(msgData, timeStamp, CHECK_WORD));

            // System.out.println(params.get("requestID"));
            long startTime = System.currentTimeMillis();

            //  System.out.println("====调用请求：" + params.get("msgData"));
            log.info("====调用实际请求：" + params);
            String result = HttpClientUtil.post(CALL_URL_BOX, params);

            log.info("====调用丰桥的接口服务代码：" + java.lang.String.valueOf(standardService.getCode()) + " 接口耗时：" + java.lang.String.valueOf(System.currentTimeMillis() - startTime) + "====");
            log.info("===调用地址 ===" + CALL_URL_BOX);
            log.info("===顾客编码 ===" + CLIENT_CODE);
            log.info("===返回结果：" + result);
            return result;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public static boolean createOrder() {
        IServiceCodeStandard standardService = ExpressServiceCodeEnum.EXP_RECE_CREATE_ORDER; // 下订单
        String result = requestResult(standardService);
        return true;
    }

    /**
     * 查询订单
     *
     * @return 运单号
     */
    public static String obtainWaybillNumber() {
        String waybillNo = "";
        try {
            // set common header
            String result = requestResult(ExpressServiceCodeEnum.EXP_RECE_SEARCH_ORDER_RESP);
            ObjectMapper jsonMapper = new ObjectMapper();
            JsonNode jsonNode = jsonMapper.readTree(result);
            JsonNode apiResultData = jsonNode.get("apiResultData");
            // 将apiResultData字段的值从JSON字符串转换为另一个JsonNode对象
            JsonNode resultDataNode = jsonMapper.readTree(apiResultData.asText());
            // 获取msgData字段的值
            JsonNode msgDataNode = resultDataNode.get("msgData");
            // 获取waybillNoInfoList字段的值
            JsonNode waybillNoInfoListNode = msgDataNode.get("waybillNoInfoList");
            // 获取waybillNoInfoList中的第一个元素
            JsonNode waybillNoInfoNode = waybillNoInfoListNode.get(0);
            // 获取waybillNo字段的值
            waybillNo = waybillNoInfoNode.get("waybillNo").asText();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return waybillNo;
    }

    public static List<RouteObj> getLogisticsInfoRoute() {
        String result = requestResult(ExpressServiceCodeEnum.EXP_RECE_SEARCH_ROUTES);
        ObjectMapper mapper = new ObjectMapper();
        try {
            // 先解析外层JSON
            JsonNode outerNode = mapper.readTree(result);
            // 获取内层的apiResultData字段
            JsonNode innerNode = outerNode.get("apiResultData");
            // 解析内层的JSON字符串
            JsonNode dataNode = mapper.readTree(innerNode.asText());
            // 获取msgData字段
            JsonNode msgDataNode = dataNode.get("msgData");
            // 获取routeResps字段
            JsonNode routeRespsNode = msgDataNode.get("routeResps");

            // 遍历routeResps中的每个元素
            List<RouteObj> objList = new ArrayList<>();
            for (JsonNode routeRespNode : routeRespsNode) {
                JsonNode routesNode = routeRespNode.get("routes");
                for (JsonNode routeNode : routesNode) {
                    String acceptAddress = routeNode.get("acceptAddress").asText();
                    String acceptTime = routeNode.get("acceptTime").asText();
                    String remark = routeNode.get("remark").asText();
                    RouteObj routeObj = new RouteObj();
                    routeObj.setAcceptAddress(acceptAddress);
                    routeObj.setAcceptTime(acceptTime);
                    routeObj.setRemark(remark);
                    objList.add(routeObj);
                }
            }
            return objList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getLogisticsInfoRoute());

    }
}

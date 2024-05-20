package com.youyi.api.model.sfobj;

import lombok.Data;

import java.util.List;

@Data
public class ApiResponse {
    private String apiErrorMsg;
    private String apiResponseID;
    private String apiResultCode;
    private ApiResultData apiResultData;

    // getters and setters
    @Data
    public static class ApiResultData {
        private boolean success;
        private String errorCode;
        private String errorMsg;
        private MsgData msgData;

        // getters and setters
        @Data
        public static class MsgData {
            private List<RouteResp> routeResps;

            // getters and setters
            @Data
            public static class RouteResp {
                private String mailNo;
                private List<Route> routes;

                // getters and setters
                @Data
                public static class Route {
                    private String acceptAddress;
                    private String acceptTime;
                    private String remark;
                    private String opCode;

                    // getters and setters
                }
            }
        }
    }
}
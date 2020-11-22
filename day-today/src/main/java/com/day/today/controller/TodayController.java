package com.day.today.controller;

import com.day.api.config.DubboNacosGroup;
import com.day.api.provider.yesterday.YesterdayProvider;
import com.day.common.base.JsonResult;
import com.day.today.persistence.entity.Order;
import com.day.today.persistence.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/8/19 10:11
 */
@RestController
@Api(tags = "今天的控制层信息")
@RequestMapping("/today")
public class TodayController {
    private static final Logger log = LoggerFactory.getLogger(TodayController.class);

    @DubboReference(group = DubboNacosGroup.YESTERDAY_DUBBO_NACOS)
    private YesterdayProvider yesterdayProvider;

    @Autowired
    private IOrderService orderService;

    @ApiOperation(value = "测试分布式事务")
    @RequestMapping(value = "testDistributedTransaction", method = RequestMethod.POST)
    public JsonResult testDistributedTransaction() {
        Order order = new Order();
        order.setUserId("1001");
        order.setAmount(300d);
        return JsonResult.success(orderService.saveOrder(order));
    }

    private static final int BUFFER = 1024 * 8;

    private void downloadFileByPath(String fileName, HttpServletResponse response) {
        String fName = null;
        try {
            fName = URLEncoder.encode(fileName, "utf8");
        } catch (UnsupportedEncodingException e) {
            log.error("转换文件名失败！",e);
        }
        response.addHeader("Access-Control-Expose-Headers", "ajax-mimeType, ajax-filename");
        response.setHeader("Content-Disposition", "attachment; filename=" + fName);
        response.setHeader("ajax-mimeType", "application/vnd.ms-excel");
        response.setHeader("ajax-filename", fName);
        response.setContentType("application/octet-stream; charset=utf-8");
        InputStream inputStream = this.getClass().getResourceAsStream("/excel/"+fileName);
        OutputStream os = null;
        try{
            os = response.getOutputStream();
            byte[] b = new byte[BUFFER];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (Exception e){
            log.error("下载失败！",e);
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("关闭流失败！",e);
                }
            }
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    log.error("关闭流失败！",e);
                }
            }
        }
    }
    @ApiOperation(value = "下载模板")
    @RequestMapping(value = "download", method = RequestMethod.POST)
    public String download(String name, HttpServletResponse response) {
        return yesterdayProvider.getYesterDayTime();
    }


}
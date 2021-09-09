package com;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

public class Log4jTest {

    @Test
    public  void 基本测试(){
        BasicConfigurator.configure(); //初始化配置，不要配置文件

        Logger logger = Logger.getLogger(this.getClass());
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        logger.fatal("fatal");
        logger.debug("debug");
        logger.trace("trace");
    }

    @Test
    public  void 根据配置文件(){
        //LogLog.setInternalDebugging(true); //打印内部原始信息 日志出问题时候调试可以打开
        Logger logger = Logger.getLogger(this.getClass());
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        logger.fatal("fatal");
        logger.debug("debug");
        logger.trace("trace");
    }
}

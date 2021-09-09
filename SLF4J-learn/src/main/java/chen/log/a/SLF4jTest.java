package chen.log;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SLF4jTest {

    /**
     * 具体实现类需要看 pom中对应的实现类
     * */
    @Test
    public void 基本测试(){
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.error("error");
        logger.warn("warn");
        logger.info("info");
        logger.debug("debug");
        logger.trace("trace");
    }
}

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;

public class JCLTest {

    /**
     * 这个的实现是jdk14logger
     */
    @Test
    public void 基本测试(){
        Log log = LogFactory.getLog(JCLTest.class);
        log.info("info");
        log.fatal("fatal");
        log.error("erorr");
        log.warn("warn");
        log.debug("debug");
    }
}

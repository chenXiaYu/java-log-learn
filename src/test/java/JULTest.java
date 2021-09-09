import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.logging.*;

/**
 * java 自带Java Util Logger
 */
public class JULTest {

    /**
     * 参考结果
     * 九月 08, 2021 8:56:48 下午 JULTest java自带日志测试
     * 严重: msg server
     * 九月 08, 2021 8:56:48 下午 JULTest java自带日志测试
     * 警告: msg warning
     * 九月 08, 2021 8:56:48 下午 JULTest java自带日志测试
     * 信息: log info
     * 九月 08, 2021 8:56:48 下午 JULTest java自带日志测试
     * 警告: warning msg: hello:,,chenxiayu
     */
    @Test
    public void java自带日志测试(){
        Logger logger = Logger.getLogger(JULTest.class.getName());

        logger.severe("msg server");
        logger.warning("msg warning");
        logger.info("log info");
        //默认级别info

        logger.config("msg config");
        logger.fine("msg fine");
        logger.finer("msg finer");
        logger.finest("msg finest");

        logger.log(Level.ALL,"level all");

        //使用占位符的方式输出
        logger.log(Level.WARNING,"warning msg: {0},{1}",new Object[]{"hello:,","chenxiayu"});
    }

    @Test
    public void 自定义日志级别() throws IOException {
        Logger logger = Logger.getLogger(this.getClass().getName());

        //关闭系统设置
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.ALL);

        SimpleFormatter simpleFormatter = new SimpleFormatter();
        //控制台输出
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(simpleFormatter);
        consoleHandler.setLevel(Level.ALL);
        //文件输出
        FileHandler fileHandler = new FileHandler("/Users/chenxiayu/IdeaProjects/java-log-learn/src/test/logout/jul.log");
        fileHandler.setLevel(Level.ALL);
        fileHandler.setFormatter(simpleFormatter);

        logger.addHandler(consoleHandler);
        logger.addHandler(fileHandler);

        logger.severe("msg server");
        logger.warning("msg warning");
        logger.info("log info");
        logger.config("msg config");
        logger.fine("msg fine");
        logger.finer("msg finer");
        logger.finest("msg finest");

    }


    @Test
    public void logger的层级关系(){
        Logger comChen = Logger.getLogger("com.chen");
        Logger comChenBean = Logger.getLogger("com.chen.bean");
        Logger parent = comChenBean.getParent();

        System.out.println("comChen == parent:"+(comChen == parent));
        System.out.println("comchen父级名称:"+comChen.getParent().getName());
        System.out.println("comCHenBean父级名称:"+comChenBean.getParent().getName());


        //更改父级子级也会受到影响
        comChen.setUseParentHandlers(false);  //注意应定要屏蔽掉继承的父类否则部分设置无效
        comChen.setLevel(Level.ALL);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        consoleHandler.setFormatter(simpleFormatter);
        comChen.addHandler(consoleHandler);

        comChenBean.severe("msg server");
        comChenBean.warning("msg warning");
        comChenBean.info("log info");
        comChenBean.config("msg config");
        comChenBean.fine("msg fine");
        comChenBean.finer("msg finer");
        comChenBean.finest("msg finest");
    }

    @Test
    public void 从配置文件初始化logger() throws IOException {

        LogManager logManager = LogManager.getLogManager();
        InputStream resourceAsStream = JULTest.class.getResourceAsStream("logging.properties");
        logManager.readConfiguration(resourceAsStream);

        //注意上面的loggerManager只是用于初始化实际上logger的获取还是调用Logger 而不是 logManager
        //Logger.getLogger其实是Logmanager调用demadLogger获取

        Logger logger = Logger.getLogger("com.chen.bean");
        logger.severe("msg server");
        logger.warning("msg warning");
        logger.info("log info");
        logger.config("msg config");
        logger.fine("msg fine");
        logger.finer("msg finer");
        logger.finest("msg finest");

        System.out.println("-------");
        //这个继承了root所以也是有输出的
        Logger test = Logger.getLogger("test");
        test.fine("test fine");

        System.out.println("--------");
        //这个是全局
        Logger global = Logger.getGlobal();
        global.fine("global fine");
    }

}

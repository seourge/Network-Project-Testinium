import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Log {
    static Logger logger = Logger.getLogger(Log.class);

    public Log(){
        DOMConfigurator.configure("log4j.xml");
    }
    public void infoLog(String message){
        logger.info(message);
    }
    public void errorLog(String message){
        logger.error(message);
    }

}

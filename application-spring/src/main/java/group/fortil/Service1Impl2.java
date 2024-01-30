package group.fortil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Service1Impl2 implements Service1 {

    private final static Logger LOGGER = LoggerFactory.getLogger(Service1Impl2.class);

    public Service1Impl2() {
    }

    ;

    @Override
    public void test() {
        LOGGER.info("test 2");
    }
}

package examples;

import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MainVerticle extends AbstractVerticle {

  private static final Logger logger = LoggerFactory.getLogger(MainVerticle.class);

  public void start() {
    logger.debug("Start ServiceVerticle");

    vertx.deployVerticle("examples.ServiceVerticle", ar -> {
      if (ar.failed()) {
        // this is not printed, if the future of ServiceVerticle fails
        logger.error("Could not start serviceVerticle", ar.cause());
      } else {
        logger.error("........................................");
        logger.error("ar: ", ar.result());
      }
    });

    logger.info("All verticles deployed");

  }
}

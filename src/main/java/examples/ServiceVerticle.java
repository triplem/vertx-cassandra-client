package examples;

import io.vertx.cassandra.CassandraClient;
import io.vertx.cassandra.CassandraClientOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceVerticle extends AbstractVerticle {

  private static final Logger logger = LoggerFactory.getLogger(ServiceVerticle.class);

  private static final String CASSANDRA_KEYSPACE = "dummy";

  public void start(Future<Void> startFuture) {
    CassandraClientOptions options = new CassandraClientOptions();
    CassandraClient client = CassandraClient.createShared(vertx, options);

    client.connect(CASSANDRA_KEYSPACE, ar -> {
      if (ar.failed()) {
        logger.error("ALL IS BAD....");
        if (client.isConnected()) {
          client.disconnect();
        }
        // does not work
        startFuture.fail(ar.cause());

        // does work
        //startFuture.complete();
      } else {
        logger.error("THIS IS NEVER REACHED ON ERROR");

        startFuture.complete();
      }
    });

    logger.info("Connecting cassandra");
  }
}

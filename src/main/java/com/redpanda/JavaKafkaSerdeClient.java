package com.redpanda;

import org.apache.log4j.Logger;

/**
 * Class used to test the Java Kafka serde library
 */
public class JavaKafkaSerdeClient {

  /**
   * Supported protocols
   */
  public enum Protocol { AVRO, PROTOBUF }

  private final Protocol protocol;
  private final String brokers;
  private final String topic;
  private final String srAddr;
  private final String consumerGroup;
  private final SecuritySettings securitySettings;

  private final Logger log;

  /**
   * Creates a new instance of the testing client
   *
   * @param brokers Comma separated list of brokers
   * @param topic The topic to communicate with
   * @param srAddr The URL of the schema registry
   * @param consumerGroup The consumer group to use
   * @param protocol The protocol to use for serialization/deserialization
   * @param securitySettings The security settings
   * @param log The logger to use
   *
   * @see SecuritySettings
   * @see org.apache.log4j.Logger
   */
  public JavaKafkaSerdeClient(
      String brokers, String topic, String srAddr, String consumerGroup,
      Protocol protocol, SecuritySettings securitySettings, Logger log) {
    this.brokers = brokers;
    this.topic = topic;
    this.srAddr = srAddr;
    this.consumerGroup = consumerGroup;
    this.protocol = protocol;
    this.securitySettings = securitySettings;
    this.log = log;
  }

  /**
   * Executes the test
   *
   * @param count Number of messages to produce and consume
   * @throws RuntimeException Exception thrown during run
   */
  public void run(int count) throws RuntimeException {
    KafkaMessagingInterface test_interface = null;

    switch (this.protocol) {
                case AVRO -> {
                  test_interface = new AvroMessaging();
                }
                case PROTOBUF -> {
                  test_interface = new ProtobufMessaging();
                }
              }

    log.info("Starting produce");
    test_interface.produce(this.log,
                            test_interface.getProducerProperties(this.brokers,
                                    this.srAddr,
                                    this.securitySettings,
                    true), this.topic, count);

    log.info("Starting consume");
    test_interface.consume(this.log,
            test_interface.getConsumerProperties(this.brokers,
                    this.srAddr,
                    this.securitySettings,
                    this.consumerGroup), this.topic, count);

    log.info("Done!");
  }
}

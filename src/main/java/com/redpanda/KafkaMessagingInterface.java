package com.redpanda;

import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * Interface used to test serialization and deserialization of Kafka messages
 */
public interface KafkaMessagingInterface {
  /**
   * Generates properties for a Kafka producer
   *
   * @param brokers The comma separated list of broker addresses
   * @param srAddr The URL of the schema registry
   * @param securitySettings The security settings (may be null)
   * @param autoRegisterSchema True to automatically register schemas
   * @return Instance of Properties
   *
   * @see java.util.Properties
   * @see SecuritySettings
   */
  Properties getProducerProperties(
      String brokers, String srAddr, SecuritySettings securitySettings,
      boolean autoRegisterSchema);

  /**
   * Generates properties for a Kafka consumer
   *
   * @param brokers The comma separated list of brokers
   * @param srAddr The URL of the schema registry
   * @param securitySettings The security settings (may be null)
   * @param consumerGroup The consumer group to use
   * @return Instance of Properties
   *
   * @see java.util.Properties
   * @see SecuritySettings
   */
  Properties getConsumerProperties(
      String brokers, String srAddr, SecuritySettings securitySettings,
      String consumerGroup);

  /**
   * Tests production of messages
   *
   * @param log The logger to use
   * @param props The producer configuration
   * @param topic The topic to communicate with
   * @param count The number of messages to send
   *
   * @see org.apache.log4j.Logger
   */
  void produce(Logger log, Properties props, String topic, int count);

  /**
   * Tests consumption of messages
   *
   * @param log The logger to use
   * @param props The consumer configuration
   * @param topic The topic to communicate with
   * @param count The number of messages to consume
   *
   * @see org.apache.log4j.Logger
   */
  void consume(Logger log, Properties props, String topic, int count);
}

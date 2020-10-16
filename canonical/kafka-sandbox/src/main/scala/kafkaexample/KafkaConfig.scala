package kafkaexample

import java.util.Properties

import com.typesafe.config.{Config, ConfigFactory}
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.config.SslConfigs

case class SslStore(location: String, password: String)

case class KafkaClientSettings(
  bootStrapServers: String,
  sslTrustStore: SslStore,
  sslKeyStore: SslStore,
  keyPassword: String
) {
  def toClientProps: Properties = {
    val props = new Properties()
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers)
    props.put("security.protocol", "SSL")
    props.put(SslConfigs.SSL_KEYSTORE_TYPE_CONFIG, "PKCS12")
    props.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, sslKeyStore.location)
    props.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, sslKeyStore.password)
    props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, sslTrustStore.location)
    props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, sslTrustStore.password)
    props.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, keyPassword)
    props
  }
}

object KafkaConfig {
  val config: Config = ConfigFactory.load()

  val kafkaClientConfig = {
    val clientConfig = config.getConfig("kafka.client")

    KafkaClientSettings(
      bootStrapServers = clientConfig.getString(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG),
      sslTrustStore = SslStore(
        clientConfig.getString(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG),
        clientConfig.getString(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG)
      ),
      sslKeyStore = SslStore(
        clientConfig.getString(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG),
        clientConfig.getString(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG)
      ),
      keyPassword = clientConfig.getString(SslConfigs.SSL_KEY_PASSWORD_CONFIG)
    )
  }
}

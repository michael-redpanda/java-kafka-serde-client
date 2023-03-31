package com.redpanda;

import java.util.Properties;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Defines the security settings for the application
 */
public class SecuritySettings {
  private SecuritySettings() {}

  private String saslMechanism;
  private String saslPlainPassword;

  private String saslPlainUsername;

  private String securityProtocol;

  /**
   * @return The SASL mechanism
   */
  public String getSaslMechanism() { return saslMechanism; }

  /**
   * @return The SASL password
   */
  public String getSaslPlainPassword() { return saslPlainPassword; }

  /**
   * @return The SASL username
   */
  public String getSaslPlainUsername() { return saslPlainUsername; }

  /**
   * @return The security protocol
   */
  public String getSecurityProtocol() { return securityProtocol; }

  /**
   * Creates a Security settings from a JSONObject
   *
   * @param obj The object to parse
   * @return Instance of security settings
   *
   * @see org.json.JSONObject
   */
  public static SecuritySettings fromJSON(JSONObject obj) {
    SecuritySettings securitySettings = new SecuritySettings();
    try {
      securitySettings.saslMechanism = obj.getString("sasl_mechanism");
    } catch (JSONException ignored) {
    }

    try {
      securitySettings.securityProtocol = obj.getString("security_protocol");
    } catch (JSONException ignored) {
    }

    try {
      securitySettings.saslPlainUsername = obj.getString("sasl_plain_username");
    } catch (JSONException ignored) {
    }

    try {
      securitySettings.saslPlainPassword = obj.getString("sasl_plain_password");
    } catch (JSONException ignored) {
    }

    return securitySettings;
  }

  /**
   * @return A Properties instance containig the values of this class
   * @see java.util.Properties
   */
  public Properties toProperties() {
    Properties prop = new Properties();

    prop.put("sasl.mechanism", saslMechanism);
    prop.put("sasl.password", saslPlainPassword);
    prop.put("sasl.username", saslPlainUsername);
    prop.put("security.protocol", securityProtocol);

    return prop;
  }

  /**
   * @return The properties as a string
   */
  public String toString() { return this.toProperties().toString(); }
}

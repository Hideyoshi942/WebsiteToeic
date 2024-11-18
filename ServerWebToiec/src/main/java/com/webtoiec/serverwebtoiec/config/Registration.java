package com.webtoiec.serverwebtoiec.config;

public class Registration {
  private final String registrationId;
  private final String uri;
  private final String clientName;

  public Registration(String registrationId, String uri, String clientName) {
    this.registrationId = registrationId;
    this.uri = uri;
    this.clientName = clientName;
  }

  public String getRegistrationId() {
    return registrationId;
  }

  public String getUri() {
    return uri;
  }

  public String getClientName() {
    return clientName;
  }

  @Override
  public String toString() {
    return "Registration{" +
        "registrationId='" + registrationId + '\'' +
        ", uri='" + uri + '\'' +
        ", clientName='" + clientName + '\'' +
        '}';
  }
}

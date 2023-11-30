package com.mass.media.dto;

public class OperationResponse {

  private String returnType;
  private String returnMessage;

  public OperationResponse() {
  }

  public OperationResponse(String returnType, String returnMessage) {
    this.returnType = returnType;
    this.returnMessage = returnMessage;
  }

  public String getReturnType() {
    return returnType;
  }

  public void setReturnType(String returnType) {
    this.returnType = returnType;
  }

  public String getReturnMessage() {
    return returnMessage;
  }

  public void setReturnMessage(String returnMessage) {
    this.returnMessage = returnMessage;
  }
}

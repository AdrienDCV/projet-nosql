package com.fisa.producerapi.models;

import lombok.Getter;

@Getter
public class ShardInfo {
  private final String businessId;
  private final int port;
  private final String shardFile;

  public ShardInfo(String businessId, int port, String shardFile) {
    this.businessId = businessId;
    this.port = port;
    this.shardFile = shardFile;
  }

}

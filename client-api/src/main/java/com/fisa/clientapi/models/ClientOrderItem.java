package com.fisa.clientapi.models;


import com.fisa.clientapi.models.enums.UniteMesure;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientOrderItem {

  private String productId;
  private String businessId;
  private String image;
  private String label;
  private Double unitPrice;
  private Integer quantity;
  private UniteMesure uniteMesure;

}

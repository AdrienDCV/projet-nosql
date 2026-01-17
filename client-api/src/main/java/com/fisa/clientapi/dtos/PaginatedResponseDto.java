package com.fisa.clientapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginatedResponseDto<T> {

  private List<T> content;
  private Integer pageNumber;
  private Integer pageSize;
  private Long totalElement;
  private Integer totalPages;

}

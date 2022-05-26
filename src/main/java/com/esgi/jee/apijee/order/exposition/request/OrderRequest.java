package com.esgi.jee.apijee.order.exposition.request;
import com.esgi.jee.apijee.item.exposition.payload.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequest {
    private Long userId;
    private List<ItemDto> products;
}

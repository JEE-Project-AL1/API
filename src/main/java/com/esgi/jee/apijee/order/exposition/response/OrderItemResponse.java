package com.esgi.jee.apijee.order.exposition.response;

import com.esgi.jee.apijee.item.exposition.payload.reponse.ItemResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {
    private Long id;
    private Long amount;
    private Long quantity;
    private ItemResponse item;
}

package com.esgi.jee.apijee.user.exposition.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleToUserDtoRequest {

    private Long id;
    private String roleName;
}

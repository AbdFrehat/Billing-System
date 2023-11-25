package com.selling.system.data.shared.module.entites;

import com.selling.system.shared.module.models.entities.AbstractCustomer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.TextIndexed;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDocument implements AbstractCustomer {

    private String gender;

    private int age;

    @TextIndexed
    private String email;

    private int satisfaction;
}

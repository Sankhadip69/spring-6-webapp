package guru.springframework.spring6restmvc.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class CustomerDto {

    private UUID id;
    private String name;
    private String email;
    private Integer version;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}

package com.softifyo.mgmt.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CaseDto {
    private Integer id;
    @NotNull(message = "Title cannot be null or empty")
    private String title;
    @NotNull(message = "Description cannot be null or empty")
    private String description;
    @NotNull(message = "Status cannot be null or empty")
    private String status;
}

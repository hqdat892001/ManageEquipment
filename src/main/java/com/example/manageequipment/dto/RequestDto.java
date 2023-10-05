package com.example.manageequipment.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestDto {
    public Long id;
    public Long userId;
    public boolean isPending;
    public String description;
    public Long requestEquipmentTypeId;
}

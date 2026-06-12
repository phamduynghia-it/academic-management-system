package com.duynghia.Academic.Management.System.common;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImportResponse {
    int successfulCount;
    int failedCount;
    List<String> errorMessages;
}
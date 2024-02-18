package com.freeuni.coursewhisperer.data.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChangePasswordDTO {
    String oldPassword;
    String newPassword;
    String confirmNewPassword;
}

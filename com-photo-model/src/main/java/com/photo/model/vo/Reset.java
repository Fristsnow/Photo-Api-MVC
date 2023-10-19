package com.photo.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * reset password
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reset {

    private String original_password;

    private String new_password;

    private String repeat_password;

}

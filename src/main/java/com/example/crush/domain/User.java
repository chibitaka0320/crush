package com.example.crush.domain;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private Integer id;
    @NotBlank(message = "名前を入力してください")
    private String name;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

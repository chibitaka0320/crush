package com.example.crush.domain;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Article {
    private Integer id;

    @NotNull
    private Integer userId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

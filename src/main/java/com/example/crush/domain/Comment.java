package com.example.crush.domain;

import java.time.LocalDateTime;

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
public class Comment {

    private Integer id;
    private Integer userId;
    private Integer articleId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

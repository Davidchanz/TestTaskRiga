/**
 * Author: Giorgi Nodia
 * User:davidchanzz
 * Date:9/24/24
 * Time:11:43 AM
 */

package com.gn.testtaskriga.dto.article;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@Setter
public class ArticleDto {

    @NotBlank
    @Size(max = 100, message = "Title should be less than 100 symbols!")
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String author;

    @NotNull
    private ZonedDateTime publishedDate;

}

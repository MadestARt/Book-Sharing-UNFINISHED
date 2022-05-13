package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    private int id;
    private String name;
    private LocalDateTime yearOfPublish;
    private int pageCount;
    private Author author;

}

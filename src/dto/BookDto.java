package dto;

import lombok.Builder;
import lombok.Value;


import java.time.LocalDateTime;

@Value
@Builder
public class BookDto {
        int pageCount;
        LocalDateTime yearOfPublish;
        String name;
}

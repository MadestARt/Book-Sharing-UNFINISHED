package dto;

import lombok.*;

@Value
@Builder
public class AuthorDto {

    private final int id;
    private final String name;
    private final String secondName;
    private final char gender;


}

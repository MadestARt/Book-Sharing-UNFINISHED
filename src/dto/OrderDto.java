package dto;


import lombok.Builder;
import lombok.Value;

@Value
@Builder
public final class OrderDto {

    private long price;
    private String info;


}

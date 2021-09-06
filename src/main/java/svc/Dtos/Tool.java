package svc.Dtos;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Tool {
    private String type;
    private String brand;
    private String code;
    private Prices price;
}

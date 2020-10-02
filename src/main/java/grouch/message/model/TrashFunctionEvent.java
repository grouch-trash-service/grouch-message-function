package grouch.message.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrashFunctionEvent {
    @JsonProperty("date")
    private String date;
}

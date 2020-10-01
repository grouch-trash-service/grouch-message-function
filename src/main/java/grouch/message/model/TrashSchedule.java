package grouch.message.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TrashSchedule.class, name = "default"),
        @JsonSubTypes.Type(value = HolidayTrashSchedule.class, name = "holiday")
})
public class TrashSchedule {
    @JsonProperty("type")
    private String type;

    @JsonProperty("schedule")
    private String schedule;
}

package grouch.message.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class HolidayTrashSchedule extends TrashSchedule {
    @JsonProperty("holiday")
    private String holiday;
}

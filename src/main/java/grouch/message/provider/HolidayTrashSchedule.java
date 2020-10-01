package grouch.message.provider;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HolidayTrashSchedule extends TrashSchedule {
    @JsonProperty("holiday")
    private String holiday;
}

package grouch.message.function;

import com.amazonaws.services.lambda.invoke.LambdaFunction;
import grouch.message.model.TrashFunctionEvent;
import grouch.message.model.TrashSchedule;

public interface TrashFunction {
    @LambdaFunction(functionName = "TrashFunction")
    TrashSchedule getTrashSchedule(TrashFunctionEvent date);
}

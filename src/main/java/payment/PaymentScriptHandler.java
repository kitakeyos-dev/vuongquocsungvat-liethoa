package payment;

import script.NativeFunction;
import script.ScriptContext;

public final class PaymentScriptHandler implements NativeFunction {
    private int commandId;
    private SmsPaymentScreen paymentScreen;

    public PaymentScriptHandler(SmsPaymentScreen var1, int var2) {
        this.paymentScreen = var1;
        this.commandId = var2;
    }

    public final int execute(ScriptContext context, int var2) throws Exception {
        return this.paymentScreen.handleScriptCall(this.commandId, context, var2);
    }
}

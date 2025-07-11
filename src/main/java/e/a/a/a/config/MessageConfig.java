package e.a.a.a.config;

public final class MessageConfig {
    private String freeNumber = "";
    private String freeContent = "";
    private String paidNumber = "";
    private String paidContent = "";
    private String paidReminder = "";
    private int paidCondition;
    private int paidPrice;
    private boolean someFlag;
    private int counter1 = 0;
    private int counter2 = 0;
    private long timestamp;

    public final int getPaidCondition() {
        return this.paidCondition;
    }

    public final String getPaidContent() {
        return this.paidContent;
    }

    public final String getPaidNumber() {
        return this.paidNumber;
    }

    public final int getPaidPrice() {
        return this.paidPrice;
    }

    public final String getFreeContent() {
        return this.freeContent;
    }

    public final String getFreeNumber() {
        return this.freeNumber;
    }

    public final String getPaidReminder() {
        return this.paidReminder;
    }

    public final void setFreeNumber(String var1) {
        this.freeNumber = var1;
    }

    public final void setFreeContent(String var1) {
        this.freeContent = var1;
    }

    public final void setPaidPrice(int var1) {
        this.paidPrice = var1;
    }

    public final void setPaidNumber(String var1) {
        this.paidNumber = var1;
    }

    public final void setPaidContent(String var1) {
        this.paidContent = var1;
    }

    public final void setPaidCondition(int var1) {
        this.paidCondition = var1;
    }

    public final void setPaidReminder(String var1) {
        this.paidReminder = var1;
    }

    public final boolean getSomeFlag() {
        return this.someFlag;
    }

    public final void setSomeFlag(boolean var1) {
        this.someFlag = false;
    }

    public final int getCounter1() {
        return this.counter1;
    }

    public final void setCounter1(int var1) {
        this.counter1 = var1;
    }

    public final int getCounter2() {
        return this.counter2;
    }

    public final void setCounter2(int var1) {
        this.counter2 = var1;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final void setTimestamp(long var1) {
        this.timestamp = var1;
    }

    public final String toString() {
        return "Miễn phí dãy số:" + this.freeNumber + "\n" +
                "Miễn phí nội dung:" + this.freeContent + "\n" +
                "Thu phí dãy số:" + this.paidNumber + "\n" +
                "Thu phí nội dung:" + this.paidContent + "\n" +
                "Thu phí điều vài:" + this.paidCondition + "\n" +
                "Thu phí đơn giá:" + this.paidPrice + "\n" +
                "Thu phí nhắc nhở ngữ:" + this.paidReminder + "\n\n";
    }
}

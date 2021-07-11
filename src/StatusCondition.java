public class StatusCondition extends Exception{
    @Override
    public String getMessage() {
        return "Trạng thái không hợp lệ. Vui lòng nhập lại!";
    }
}

public class NameCondition extends Exception{
    @Override
    public String getMessage() {
        return "Tên đã tồn tại. Vui lòng sử dụng tên khác";
    }
}

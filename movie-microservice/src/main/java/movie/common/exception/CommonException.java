package movie.common.exception;

/**
 * @Description: 自定义异常
 */
public class CommonException extends RuntimeException {

    public CommonException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;

    public void setCode(int code) {
        this.code = code;
    }
    public void setMsg(String mgs) {
        this.msg = msg;
    }
    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}

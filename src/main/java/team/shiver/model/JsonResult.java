package team.shiver.model;


import lombok.Data;

@Data
public class JsonResult<T> {

    private T data;// 请求数据，对象或数组均可
    private int code = CodeMsg.SUCCESS.getCode();; // 业务自定义状态码
    private String msg = CodeMsg.SUCCESS.getMsg();// 请求状态描述，调试用

    /**
     * 若没有数据返回，默认状态码为 0，提示信息为“操作成功！”
     */
    public JsonResult() {
        this(CodeMsg.SUCCESS);
    }

    /**
     * 若没有数据返回，可以人为指定状态码和提示信息
     * @param code
     * @param msg
     */
    public JsonResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JsonResult(CodeMsg codeMsg) {
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();
    }

    /**
     * 有数据返回时，状态码为 0，默认提示信息为“操作成功！”
     * @param data
     */
    public JsonResult(T data) {
        this(data,CodeMsg.SUCCESS);
    }

    /**
     * 有数据返回，状态码为 0，人为指定提示信息
     * @param data
     * @param msg
     */
    public JsonResult(T data, String msg) {
        this.data = data;
        this.msg = msg;
    }

    public JsonResult(T data,CodeMsg codeMsg){
        this.data = data;
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();
    }
}

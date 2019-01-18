package interception;

/**
 * 作为责任链上一直被传递的对象，从构造开始，一直传递到最后一个拦截器并被依次捕获和处理
 */
public class Request {
    public int i = 0;
    public int j = 1;
}

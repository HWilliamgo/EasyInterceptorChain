package interception;

/**
 * 在最后一个拦截器中发生构造，并被依次返回到责任链的递归，并最终返回给应用层读取。
 */
public class Response {
    public String param1;
    public String param2;
}

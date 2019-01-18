package interception;

public interface Interceptor {

    //拦截
    Response intercept(Chain chain);

    interface Chain {
        //获取请求
        Request request();

        //处理请求，该方法由拿到了Chain对象引用的拦截器来调用。
        Response proceed(Request request);
    }
}
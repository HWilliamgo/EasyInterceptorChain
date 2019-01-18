import interception.Interceptor;
import interception.RealInterceptorChain;
import interception.Request;
import interception.Response;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //1.构造Request
        //2.构造拦截器容器
        //3.添加内部的或外部的拦截器
        //4.构造第一个Chain对象，交给第一个拦截器，发起责任两从上到下的处理。
        //5.拿到处理后的Response，开发者进行处理。

        //1
        Request request = new Request();

        //2
        List<Interceptor> interceptors = new ArrayList<>();

        //3
        interceptors.add(chain -> {
            //修改Request
            Request r = chain.request();
            r.i = 100;
            r.j = 99;
            //将request向责任链下部传递。
            return chain.proceed(r);
        });
        interceptors.add(chain -> {
            Request r = chain.request();
            //最后一个Chain对象只有request方法起作用，用来获取Request，而不会调用proceed，在最后一个拦截器中生成Response直接返回而不再递归
            Response response = new Response();
            if (r.i == 100) {
                response.param1 = String.valueOf(r.i);
            }
            if (r.j == 99) {
                response.param2 = String.valueOf(r.j);
            }
            return response;
        });

        //4
        RealInterceptorChain chain = new RealInterceptorChain(interceptors, 0, request);
        Response response = chain.proceed(request);

        //5
        System.out.println(response.param1);
        System.out.println(response.param2);

    }
}

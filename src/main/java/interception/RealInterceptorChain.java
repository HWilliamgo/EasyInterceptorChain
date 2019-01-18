package interception;

import java.util.List;

/**
 * 实际上只是对以下3个变量的一个封装。
 */
public class RealInterceptorChain implements Interceptor.Chain {
    //自始至终被传递的请求
    private Request mRequest;
    //拦截器容器
    private List<Interceptor> mInterceptors;
    //拦截器容器的下标。
    private int mIndex;

    public RealInterceptorChain(List<Interceptor> interceptors, int index, Request request) {
        mRequest = request;
        mInterceptors = interceptors;
        mIndex = index;
    }

    @Override
    public Request request() {
        return mRequest;
    }

    @Override
    public Response proceed(Request request) {
        //该index处不再有拦截器，异常
        if (mIndex >= mInterceptors.size()) throw new AssertionError();

        //构造责任链，将下标+1，其余不变。
        RealInterceptorChain next = new RealInterceptorChain(mInterceptors, mIndex + 1, mRequest);
        //获取当前下标的拦截器
        Interceptor interceptor = mInterceptors.get(mIndex);
        //将变量交给拦截器处理，由拦截器自己转发给下一个拦截器
        return interceptor.intercept(next);
    }
}

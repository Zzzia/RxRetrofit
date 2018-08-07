##### 定义的接口类
~~~java
/**
 * 接口类
 */
public interface Api {
    @GET("id={id}")
    Call<ResponseBody> getById(@Path("id") int id);
}

/**
* Main类
*/
public class Base {
    public static void main(String[] args) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://zzzia.net:8080/api/")
                .build();
        Api api = retrofit.create(Api.class);
        System.out.println(api.getById(2016211541).execute().body().string());
    }
}
~~~

##### Retrofit.build()方法干了什么
~~~java
class Retrofit{
    
    //注解分析后之的方法（ServiceMethod）缓存
    private final Map<Method, ServiceMethod<?, ?>> serviceMethodCache = new ConcurrentHashMap();
    
    //网络请求工厂
    final Factory callFactory;
    
    //目标网址的封装
    final HttpUrl baseUrl;
    
    //数据转换适配器，这里将json数据转换成ResponseBody（默认），一般会用Gson转换成bean类
    final List<retrofit2.Converter.Factory> converterFactories;
    
    //接口返回值的适配器，这里是Call（默认），一般我们会结合RxJava转换成Observable
    final List<retrofit2.CallAdapter.Factory> callAdapterFactories;
    
    @Nullable
    final Executor callbackExecutor;//回调执行器
    
    final boolean validateEagerly;//是否提前处理注解
    
    public Retrofit build() {
                if (this.baseUrl == null) {
                    throw new IllegalStateException(BaseDemo);
                } else {
                    //检查参数是否为空，如果为空就改成默认的
                    Factory callFactory = this.callFactory;
                    if (callFactory == null) {//默认使用OkHttpClient网络请求
                        callFactory = new OkHttpClient();
                    }
    
                    Executor callbackExecutor = this.callbackExecutor;
                    if (callbackExecutor == null) {
                        //针对Java，Android等不同平台有不同的处理方法
                        callbackExecutor = this.platform.defaultCallbackExecutor();
                    }
    
                    //接口返回值适配器，是一个责任链模式，可以添加多个适配器，第一个不行就换其它的
                    List<retrofit2.CallAdapter.Factory> callAdapterFactories = new ArrayList(this.callAdapterFactories);
                    //这里除了用户自定义，添加了一个默认callAdapter，处理顺序当然是用户优先
                    callAdapterFactories.add(this.platform.defaultCallAdapterFactory(callbackExecutor));
                    //同理，也是多个数据适配器的集合
                    List<retrofit2.Converter.Factory> converterFactories = new ArrayList(1 + this.converterFactories.size());
                    converterFactories.add(new BuiltInConverters());
                    converterFactories.addAll(this.converterFactories);
                    //Builder模式，将所有参数补充完整后生成一个Retrofit对象
                    return new Retrofit((Factory)callFactory, this.baseUrl, Collections.unmodifiableList(converterFactories), Collections.unmodifiableList(callAdapterFactories), callbackExecutor, this.validateEagerly);
                }
    }
}
~~~

##### Retrofit.create(Api.class)方法干了什么
~~~java
/**
 * Retrofit.create(Api.class)
 */
class Retrofit{
    public <T> T create(final Class<T> service) {
        //验证class是否是接口，以及是否含继承关系
        Utils.validateServiceInterface(service);

        if (this.validateEagerly) {//默认跳过
            //提前loadServiceMethod，这个过程一次性加载完所有注解，保存到map中
            this.eagerlyValidateMethods(service);
        }

        //返回一个动态代理的类
        return Proxy.newProxyInstance(service.getClassLoader(), new Class[]{service}, new InvocationHandler() {
            private final Platform platform = Platform.get();

            //拦截器。当调用方法的时候，拦截方法，动态代理。没有调用方法时不会调用！！
            public Object invoke(Object proxy, Method method, @Nullable Object[] args) throws Throwable {
                if (method.getDeclaringClass() == Object.class) {//默认跳过
                    return method.invoke(this, args);

                } else if (this.platform.isDefaultMethod(method)) {//默认跳过
                    return this.platform.invokeDefaultMethod(method, service, proxy, args);
                } else {

                    //获取要使用的方法的注解，保存在serviceMethod中，同时传入了之前自定义的参数
                    //调用了方法(new retrofit2.ServiceMethod.Builder(this, method)).build();
                    ServiceMethod<Object, Object> serviceMethod = Retrofit.this.loadServiceMethod(method);

                    //将接口类的参数信息(serviceMethod)和动态代理的args封装成okHttpCall
                    OkHttpCall<Object> okHttpCall = new OkHttpCall(serviceMethod, args);

                    //使用指定的适配器将okHttpCall封装成接口中的返回类型
                    //这里适配成了Call<ResponseBody>
                    return serviceMethod.adapt(okHttpCall);
                }
            }
        });
    }
}
~~~

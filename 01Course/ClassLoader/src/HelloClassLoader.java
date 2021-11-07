import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

public class HelloClassLoader extends ClassLoader {
   public static void main(String[] args) throws Exception {
       Class helloClass= new HelloClassLoader().loadClass("Hello");
       for( Method method:helloClass.getDeclaredMethods()){
           System.out.println(helloClass.getSimpleName()+"."+method.getName());
       }
       Object hello=helloClass.newInstance();
       Method method=helloClass.getDeclaredMethod("hello");
       method.invoke(hello);
   }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 如果支持包名, 则需要进行路径转换
        String resourcePath = name.replace(".", "/");
        final String suffix = ".xlass";
        byte[] bytes = null;
        int byteLength=0;
       // try(InputStream in = this.getClass().getClassLoader().getResourceAsStream(resourcePath + suffix)) {
        try(InputStream in = new FileInputStream(resourcePath + suffix)) {
            byteLength= in.available();
            bytes=new byte[byteLength];
            in.read(bytes);
        } catch (Exception e1) {
           throw new ClassNotFoundException();
        }
        for(int i=0;i<byteLength;i++){
            bytes[i]= (byte) (255-bytes[i]);
        }
       return defineClass(name,bytes,0,byteLength);
    }

}

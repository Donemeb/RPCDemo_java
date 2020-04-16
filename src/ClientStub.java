
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class ClientStub{
	private static String server_ip = "127.0.0.1";
	private static int server_port = 23333;
	
	public <T> T ClientProxy(final Class<?> class_interface) {
		T server_class = getRemoteProxyObj(class_interface,new InetSocketAddress("localhost", 23333));
		return (T) server_class;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getRemoteProxyObj(final Class<?> class_interface, final InetSocketAddress addr) {
		return (T) Proxy.newProxyInstance(class_interface.getClassLoader(), new Class<?>[]{class_interface}, new InvocationHandler() {
			public Object invoke(Object proxy, Method method, Object[] args) throws UnknownHostException, IOException, ClassNotFoundException{	
				Socket socket = new Socket(server_ip, server_port);
				ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
				output.writeUTF(class_interface.getName());
				output.writeUTF(method.getName());
				output.writeObject(method.getParameterTypes());
				output.writeObject(args);
			 
				ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
				return input.readObject();
			}
		});
	}
}

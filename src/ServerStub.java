
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ServerStub implements Runnable{
	private static final HashMap<String, Class> serviceRegistry = new HashMap<String, Class>();
	private static int server_port = 23333;
	
	@Override
	public void run(){
		try {
			ServerSocket server_socket = new ServerSocket(server_port);
			System.out.println("Server: start");
			while(true) {
				Socket socket = server_socket.accept();
				ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
		        String class_name = input.readUTF();
		        String method_name = input.readUTF();
				Class<?>[] parameter_types = (Class<?>[]) input.readObject();
		        Object[] arguments = (Object[]) input.readObject();
				
		        Class server_class = serviceRegistry.get(class_name);
		        if (server_class == null) {
		        	throw new ClassNotFoundException("Server stub: "+class_name + " not found");
		        }
		        Method method = server_class.getMethod(method_name, parameter_types);
		        Object result = method.invoke(server_class.newInstance(), arguments);

				System.out.println("Server: result "+result.getClass());
				
				ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
				output.writeObject(result);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
	  
	 
	public void register(Class class_interface, Class class_impl) {
		serviceRegistry.put(class_interface.getName(), class_impl);
	}
}

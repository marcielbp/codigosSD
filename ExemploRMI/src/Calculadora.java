import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

// Implementacao de fato da interface remota e seus metodos e funcoes
public class Calculadora  implements ICalculadora {

	private static final long serialVersionUID = 1L;
	
	private static int chamadas = 0;
	// metodos da calculadora
	public double soma(double a, double b) throws RemoteException {
		System.out.println("Metodo soma chamado " + chamadas++);
		return a + b;
	}
	public double subtracao(double a, double b) throws RemoteException {
		System.out.println("Metodo subtracao chamado " + chamadas++);
		return a - b;
	}
	public double multiplicacao(double a, double b) throws RemoteException {
		System.out.println("Metodo multiplicacao chamado " + chamadas++);
		return a * b;
	}
	public double divisao(double a, double b) throws RemoteException {
		System.out.println("Metodo divisao chamado " + chamadas++);
		return a / b;
	}

	public static void main(String[] args) throws AccessException, RemoteException, AlreadyBoundException  {
		// instanciamento de um novo objeto
		Calculadora calculadora = new Calculadora();	
		// instancia de um novo registro
		Registry reg = null;
		
		// Implementacao de um novo objeto servidor da classe UnicastRemoteObject
		// exportObject exporta o novo stub contendo os objetos remotos que podem agora ser acessados
		ICalculadora stub = (ICalculadora) UnicastRemoteObject.
				exportObject(calculadora, 1100);
		try {
			System.out.println("Creating registry...");
			// Cria um novo registro para objetos na porta 1099
			reg = LocateRegistry.createRegistry(1099);
		} catch (Exception e) {
			try {
				reg = LocateRegistry.getRegistry(1099);
			} catch (Exception e1) {
				System.exit(0);
			}
		}
		System.out.println("DONE!");
		// bind dos objetos da interface ICalculadora para porta registrada em reg
		reg.rebind("calculadora", stub);
	}
}

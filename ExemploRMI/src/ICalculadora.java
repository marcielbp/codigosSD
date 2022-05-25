import java.rmi.Remote;
import java.rmi.RemoteException;

// Definicao da interface remota para invocacao dos objetos RMI
public interface ICalculadora extends Remote{
	
	// Definicao de excessoes dos objetos remotos caso os objetos nao estejam disponiveis por problemas de rede
	
	public double soma(double a, double b) throws RemoteException;
	
	public double subtracao(double a, double b) throws RemoteException;
	
	public double multiplicacao(double a, double b) throws RemoteException;
	
	public double divisao(double a, double b) throws RemoteException;
	
}

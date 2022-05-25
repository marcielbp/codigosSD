import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

// Cliente RMI local
public class CalculadoraCliente {
	
	public static void main(String[] args) {
		Registry reg = null;
		ICalculadora calc;		
		try {
			// Registro de objetos remotos na porta 1099
			reg = LocateRegistry.getRegistry(1099);
			calc = (ICalculadora) reg.lookup("calculadora");
	
			double oper1 = 32.6;
			double oper2 = 11.7;
			//System.out.println("Operandos:"+Double.toString(oper1)+" e "+Double.toString(oper2));
			//System.out.println("Teste operacao Soma:");
			System.out.println(+calc.soma(oper1,oper2));
			
			//System.out.println("Teste operacao Subtracao");
			System.out.println(+calc.subtracao(oper1,oper2));
			
			//System.out.println("Teste operacao Produto");
			System.out.println(calc.multiplicacao(oper1,oper2));
			
			//System.out.println("Teste operacao Divisao");
			System.out.println(calc.divisao(oper1,oper2));
			
			
		} catch (RemoteException | NotBoundException e) {
				System.out.println(e);
				System.exit(0);
		}
	}		

}

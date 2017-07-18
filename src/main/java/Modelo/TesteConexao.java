package Modelo;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteConexao {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Golf");
		emf.close();
		System.out.println("tudo ok");
	}

}

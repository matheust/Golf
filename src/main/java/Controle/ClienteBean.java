package Controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Modelo.Cliente;

@ManagedBean
@SessionScoped
public class ClienteBean {

	private Cliente cliente;
	private List<Cliente> clientes;
	
	public ClienteBean(){
		cliente = new Cliente();
		clientes = listarClientes();
	}
	
	//Selecionar
	public void selecionar(Cliente cliente){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Golf");
		EntityManager em = emf.createEntityManager();
		
		this.cliente = em.find(Cliente.class, cliente.getCodigo());
		System.out.println(cliente.getNome());
		System.out.println(cliente.getCodigo());
		em.close();
		emf.close();
		//return "Principal";
	}
	
	//Salvar
	public void salvar(){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Golf");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.merge(cliente);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		cliente = new Cliente();
		listarClientes();
		System.out.println("Salvar");
	}
	
	//Listar Todos
	public List<Cliente> listarClientes(){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Golf");
		EntityManager em =emf.createEntityManager();
		
		
		clientes = em.createQuery("select c from Cliente c order by c.nome",Cliente.class).getResultList();
		
		em.close();
		emf.close();
		
		
		return clientes;
	}
	
	public void ecluir(Cliente cliente){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Golf");
		EntityManager em = emf.createEntityManager();
		
		this.cliente = em.find(Cliente.class, cliente.getCodigo());
		
		em.getTransaction().begin();
		em.remove(this.cliente);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		this.cliente = new Cliente();
		listarClientes();
	}


	//get set
	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public List<Cliente> getClientes() {
		return clientes;
	}


	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	
	
}

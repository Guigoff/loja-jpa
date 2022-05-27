package br.com.furla.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.furla.loja.dao.CategoriaDao;
import br.com.furla.loja.dao.ProdutoDao;
import br.com.furla.loja.modelo.Categoria;
import br.com.furla.loja.modelo.Produto;
import br.com.furla.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		cadastrarProduto();
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		Produto p = produtoDao.buscarPorId(1l);
		System.out.println(p.getPreco());
		
		List<Produto> todos = produtoDao.buscarPorNomeDaCategoria("CELULARES");
		todos.forEach(p2 -> System.out.println(p2.getNome()));
		
		BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("S10 Plus");
		System.out.println(precoDoProduto);
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto("S10 Plus", "Samsung S10 Plus", new BigDecimal("3000"), celulares);

		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em); 
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		
		
		em.getTransaction().begin();
			
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		em.getTransaction().commit();
		em.close();
	}

}

package br.com.furla.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.furla.loja.dao.CategoriaDao;
import br.com.furla.loja.dao.ProdutoDao;
import br.com.furla.loja.modelo.Categoria;
import br.com.furla.loja.modelo.Produto;
import br.com.furla.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
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

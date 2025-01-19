package com.BirdSoftware.Loja_Virtual;

import com.BirdSoftware.Loja_Virtual.controller.AcessoController;
import com.BirdSoftware.Loja_Virtual.model.Acesso;
import com.BirdSoftware.Loja_Virtual.service.AcessoService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class LojaVirtualApplicationTests {


	@Autowired
	private AcessoService acessoService;
	@Autowired
	private AcessoController acessoController;

	@Test
	void testCadastroAcesso() {

		Acesso acesso = new Acesso();
		acesso.setDescricao("ROLE_ADMIN");
		acessoController.salvarAcesso(acesso);
	}

}

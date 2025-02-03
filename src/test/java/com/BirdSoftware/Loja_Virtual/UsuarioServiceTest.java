package com.BirdSoftware.Loja_Virtual;

import com.BirdSoftware.Loja_Virtual.model.Acesso;
import com.BirdSoftware.Loja_Virtual.model.PessoaFisica;
import com.BirdSoftware.Loja_Virtual.model.Usuario;
import com.BirdSoftware.Loja_Virtual.repository.AcessoRepository;
import com.BirdSoftware.Loja_Virtual.repository.PessoaFisicaRepository;
import com.BirdSoftware.Loja_Virtual.repository.UsuarioRepository;
import com.BirdSoftware.Loja_Virtual.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class senhaBancoCript {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;

	@Autowired
	private AcessoRepository acessoRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UsuarioService usuarioService;

	private Usuario usuario;
	private PessoaFisica pessoaFisica;

	@BeforeEach
	public void setUp() {
		//instancia obj pessoa fisica
		pessoaFisica = new PessoaFisica();
		pessoaFisica.setNome("Joao Pedro");
		pessoaFisica.setCpf("0520520520");
		pessoaFisica.setDataNascimento(new Date());
		pessoaFisica.setEmail("joaopedro@example.com");
		pessoaFisica.setTelefone("1197777777");

		pessoaFisica = pessoaFisicaRepository.save(pessoaFisica);

		Acesso acesso = new Acesso();
		acesso.setDescricao("Usuario");
		acesso = acessoRepository.save(acesso);


		this.usuario = new Usuario();
		usuario.setLogin("joao pedro");
		usuario.setSenha(passwordEncoder.encode("senha567"));
		usuario.setDataAtualSenha(new Date());
		usuario.setPessoa(pessoaFisica);


		usuario.setAcessos(new ArrayList<>());
		usuario.getAcessos().add(acesso);

		this.usuario = usuarioRepository.save(usuario);

	}

	@Test
	@Transactional // limpa o banco depois do teste
	//@Commit //salva no banco de dados
	void testCasdastraUser() {
	//buscar usuario salvo no banco de dados
		Usuario usuarioBanco =  usuarioRepository.findById(usuario.getId()).orElseThrow();

		//verificar se os dados foram salvos corretamente
		assertNotNull(usuarioBanco);
		assertEquals("joao pedro",usuarioBanco.getLogin());
		assertTrue(passwordEncoder.matches("senha567",usuarioBanco.getSenha()));


	}



}



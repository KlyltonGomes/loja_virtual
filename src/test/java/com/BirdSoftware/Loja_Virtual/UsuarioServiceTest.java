package com.BirdSoftware.Loja_Virtual;

import com.BirdSoftware.Loja_Virtual.model.Acesso;
import com.BirdSoftware.Loja_Virtual.model.PessoaFisica;
import com.BirdSoftware.Loja_Virtual.model.Usuario;
import com.BirdSoftware.Loja_Virtual.repository.AcessoRepository;
import com.BirdSoftware.Loja_Virtual.repository.PessoaFisicaRepository;
import com.BirdSoftware.Loja_Virtual.repository.UsuarioRepository;
import com.BirdSoftware.Loja_Virtual.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

//	@BeforeEach
//	public void setUp() {


	@Test
	@Transactional
		//@Commit
	void testCadastraUser() {
		// Criando um objeto Acesso
		Acesso acesso = new Acesso();
		acesso.setDescricao("ROLE_USER"); // Defina a descrição do acesso

		// Salvando o acesso no banco
		acesso = acessoRepository.save(acesso);

		// Criando uma pessoa para associar ao usuário
		PessoaFisica pessoaFisica = new PessoaFisica();
		pessoaFisica.setNome("João Pedro");
		pessoaFisica.setCpf("123.456.789-00"); // CPF válido
		pessoaFisica.setDataNascimento(new Date());
		pessoaFisica.setEmail("joao@email.com");
		pessoaFisica.setTelefone("11999999999");

		// Salvando a pessoa no banco
		pessoaFisica = pessoaFisicaRepository.save(pessoaFisica);

		// Criando um usuário para teste
		Usuario usuario = new Usuario();
		usuario.setLogin("joao pedro");
		usuario.setSenha(passwordEncoder.encode("senha567")); // Senha criptografada
		System.out.println("Senha criptografada:"+usuario.getSenha());
		usuario.setDataAtualSenha(new Date()); // Definir a data atual
		usuario.setPessoa(pessoaFisica); // Associando a pessoa ao usuário

		// Associando o acesso ao usuário
		List<Acesso> acessos = new ArrayList<>();
		acessos.add(acesso);
		usuario.setAcessos(acessos);

		// Salvando o usuário no banco
		usuario = usuarioRepository.save(usuario);

		// Buscar usuário salvo no banco de dados
		Usuario usuarioBanco = usuarioRepository.findById(usuario.getId()).orElseThrow();

		// Verificar se os dados foram salvos corretamente
		assertNotNull(usuarioBanco);
		assertEquals("joao pedro", usuarioBanco.getLogin());
		assertNotNull(usuarioBanco.getPessoa()); // Verificar se a pessoa está associada
		assertEquals("João Pedro", usuarioBanco.getPessoa().getNome());
		assertFalse(usuarioBanco.getAcessos().isEmpty()); // Verificar se há acessos associados
		assertEquals("ROLE_USER", usuarioBanco.getAcessos().get(0).getDescricao()); // Verificar o acesso

		// Comparar senha usando passwordEncoder.matches()
		assertTrue(passwordEncoder.matches("senha567", usuarioBanco.getSenha()));
	}

}


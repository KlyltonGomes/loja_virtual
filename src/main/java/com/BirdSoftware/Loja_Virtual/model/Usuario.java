package com.BirdSoftware.Loja_Virtual.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "usuario")
@SequenceGenerator(name = "seq_usuario",sequenceName = "seq_usuario",allocationSize = 1,initialValue = 1)
public class Usuario implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate dataAtualSenha;

//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "usuarios_acesso", uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id","acesso_id"} ,
//    name = "unique_acesso_user"),
//    joinColumns = @JoinColumn(name = "usuario_id",referencedColumnName = "id", table = "usuario",
//            unique = false, foreignKey = @ForeignKey(name = "usuario_fk", value = ConstraintMode.CONSTRAINT)),
//    inverseJoinColumns = @JoinColumn(name = "acesso_id", unique = false, referencedColumnName = "id", table = "acesso",
//    foreignKey = @ForeignKey(name = "acesso_fk", value = ConstraintMode.CONSTRAINT)))
//   private List<Acesso> acessos ;
    // Alteração aqui: relacionamento ManyToMany
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_acesso",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "acesso_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "acesso_id"},
                    name = "unique_acesso_user"))
    private List<Acesso> acessos;

    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(name = "pessoa_id", nullable = true,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,name = "pessoa_fk"))
    private Pessoa pessoa;

    // Pode adicionar campos que ajudem a identificar o tipo de pessoa: Física ou Jurídica
    @Column(nullable = true)
    private Boolean isPessoaJuridica; // Se for Pessoa Jurídica, essa flag pode ser marcada como 'true'


    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    /* Autoridades = são os acesso, ou seja ROLE_ADMIN, ROLE_SECRETARIO,ROLE_FINANCEIRO*/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.acessos;
    }

    @Override
    public String getPassword() {
        return senha;  // Retorna a senha criptografada do banco
    }

    @Override
    public String getUsername() {
        return login;  // Retorna o login do usuário
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataAtualSenha() {
        return dataAtualSenha;
    }

    public void setDataAtualSenha(LocalDate dataAtualSenha) {
        this.dataAtualSenha = dataAtualSenha;
    }

    public List<Acesso> getAcessos() {
        return acessos;
    }

    public void setAcessos(List<Acesso> acessos) {
        this.acessos = acessos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

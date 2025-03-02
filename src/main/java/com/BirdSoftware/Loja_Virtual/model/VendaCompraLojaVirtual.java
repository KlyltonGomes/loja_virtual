package com.BirdSoftware.Loja_Virtual.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "vd_cp_loja_virt")
@SequenceGenerator(name = "seq_vd_cp_loja_virt",sequenceName = "seq_vd_cp_loja_virt",allocationSize = 1,initialValue = 1)
public class VendaCompraLojaVirtual implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_vd_cp_loja_virt")
    private long id;

    @ManyToOne(targetEntity = Pessoa.class) //abstracao usamos target
    @JoinColumn(name = "pessoa_id", nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,name = "pessoa_fk"))
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "endereco_entrega_id", nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,name = "endereco_entrega_fk"))
    private Endereco enderecoEntrega;

    @ManyToOne
    @JoinColumn(name = "endereco_cobranca_id", nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,name = "endereco_cobranca_fk"))
    private Endereco enderecoCobranca;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    private BigDecimal valorDesconto;

    @ManyToOne
    @JoinColumn(name = "forma_pagamento_id", nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,name = "forma_pagamento_fk"))
    private FormaPagamento formaPagamento;

    @ManyToOne
    @JoinColumn(name = "nota_fiscal_venda_id", nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,name = "nota_fiscal_venda_fk"))
    private NotaFiscalVenda notaFiscalVenda;

    @ManyToOne
    @JoinColumn(name = "cupom_desc_id",
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,name = "cupom_desc_fk"))
    private CupDesconto cupDesconto;

    @Column(nullable = false)
    private BigDecimal valorFrete;

    @Column(nullable = false)
    private Integer diaEntrega;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataEntrega;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataVenda;

    @ManyToOne(targetEntity = PessoaJuridica.class)
    @JoinColumn(name = "empresa_id", nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,name = "empresa_fk"))
    private PessoaJuridica empresa;

    public PessoaJuridica getEmpresa() {
        return empresa;
    }

    public void setEmpresa(PessoaJuridica empresa) {
        this.empresa = empresa;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(Endereco enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public Endereco getEnderecoCobranca() {
        return enderecoCobranca;
    }

    public void setEnderecoCobranca(Endereco enderecoCobranca) {
        this.enderecoCobranca = enderecoCobranca;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public NotaFiscalVenda getNotaFiscalVenda() {
        return notaFiscalVenda;
    }

    public void setNotaFiscalVenda(NotaFiscalVenda notaFiscalVenda) {
        this.notaFiscalVenda = notaFiscalVenda;
    }

    public CupDesconto getCupDesconto() {
        return cupDesconto;
    }

    public void setCupDesconto(CupDesconto cupDesconto) {
        this.cupDesconto = cupDesconto;
    }

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(BigDecimal valorFrete) {
        this.valorFrete = valorFrete;
    }

    public Integer getDiaEntrega() {
        return diaEntrega;
    }

    public void setDiaEntrega(Integer diaEntrega) {
        this.diaEntrega = diaEntrega;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendaCompraLojaVirtual that = (VendaCompraLojaVirtual) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

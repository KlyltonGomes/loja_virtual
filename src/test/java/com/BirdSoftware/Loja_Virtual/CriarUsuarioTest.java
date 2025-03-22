package com.BirdSoftware.Loja_Virtual;

import com.BirdSoftware.Loja_Virtual.DTO.EnderecoDTO;
import com.BirdSoftware.Loja_Virtual.enums.TipoEndereco;
import com.BirdSoftware.Loja_Virtual.model.Endereco;
import com.BirdSoftware.Loja_Virtual.repository.EnderecoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class) // Usa Mockito para mockar os componentes
class SaveAndress{

    @Mock
    private EnderecoRepository enderecoRepository;

    @Test
    void salvarEndereco(){

        //populando o dto do controller
        EnderecoDTO enderecoDTO = new EnderecoDTO();

        enderecoDTO.setLogradouro("Rua alto são joão");
        enderecoDTO.setBairro("vila ré");
        enderecoDTO.setNumero("71");
        enderecoDTO.setEstado("sp");
        enderecoDTO.setCidade("são paulo");
        enderecoDTO.setCep("01006-000");
        enderecoDTO.setComplemento("casa 1");

        /*criar obj a parti dos dados do DTO*/
        Endereco endereco = new Endereco();

        endereco.setRuaLogra(enderecoDTO.getLogradouro());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setBairro(enderecoDTO.getCep());
        endereco.setUf(enderecoDTO.getEstado());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setComplemento(enderecoDTO.getComplemento());
        endereco.setTipoEndereco(TipoEndereco.ENTREGA);


        enderecoRepository.save(endereco);






    }








}

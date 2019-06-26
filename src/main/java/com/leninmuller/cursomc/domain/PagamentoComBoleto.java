package com.leninmuller.cursomc.domain;

import javax.persistence.Entity;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leninmuller.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComBoleto extends Pagamento{
	
	private static final long serialVersionUID = 1L;	
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataVencimento;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataPagamento;	
	
	public PagamentoComBoleto() {
		
	}

	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataPagamento, Date dataVencimento) {
		super(id, estado, pedido);
		this.dataPagamento = dataPagamento;
		this.dataVencimento =  dataVencimento;
	}

	public Date getDateVencimento() {
		return dataVencimento;
	}

	public void setDateVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDatePagamento() {
		return dataPagamento;
	}

	public void setDatePagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	
}

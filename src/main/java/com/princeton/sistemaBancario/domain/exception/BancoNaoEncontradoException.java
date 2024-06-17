package com.princeton.sistemaBancario.domain.exception;

public class BancoNaoEncontradoException extends EntidadeNaoEncontradaException{
	private static final long serialVersionUID = 1L;

	public BancoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public BancoNaoEncontradoException(Long bancoId) {
		this(String.format("Não existe um cadastro de banco com código %d", bancoId));
	}
}

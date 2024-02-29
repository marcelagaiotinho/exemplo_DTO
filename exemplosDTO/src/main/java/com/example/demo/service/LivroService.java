package com.example.demo.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LivroDTO;
import com.example.demo.entities.Livro;
import com.example.demo.repository.Livrorepository;

@Service
public class LivroService {
	private final Livrorepository livroRepository;

	@Autowired
	public LivroService(Livrorepository livroRepository) {
		this.livroRepository = livroRepository;
	}

	public List<Livro> buscaTodos() {
		return livroRepository.findAll();
	}

	public Livro buscaPorId(Long id) {
		return livroRepository.findById(id).orElse(null);
	}

	public LivroDTO salvar(Livro livro) {
		Livro salvarLivro = livroRepository.save(livro);
		return new LivroDTO(salvarLivro.getId(), salvarLivro.getTitulo(), salvarLivro.getAutor());
	}

	public LivroDTO atualizar(Long id, Livro livro) {
		Livro exiteLivro = livroRepository.findById(id).orElseThrow(() -> new RuntimeException("livro" + "não encontrado"));
		exiteLivro.setTitulo(livro.getTitulo());
		exiteLivro.setAutor(livro.getAutor());
		Livro updateLivro = livroRepository.save(exiteLivro);
		return new LivroDTO(updateLivro.getId(), updateLivro.getTitulo(), updateLivro.getAutor());
	}

	public boolean deletar(Long id) {
		Optional<Livro> exiteLivro = livroRepository.findById(id);
		if (exiteLivro.isPresent()) {
			livroRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
}
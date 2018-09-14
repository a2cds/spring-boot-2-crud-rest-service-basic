package br.com.a2cds.springboot.crudrest.example.endereco;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class EnderecoResource {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@GetMapping("/enderecos")
	public List<Endereco> retrieveAllEnderecos() {
		return enderecoRepository.findAll();
	}
	
	@GetMapping("/enderecos/{id}")
	public Endereco retrieveEndereco(@PathVariable long id) {
		Optional<Endereco> endereco = enderecoRepository.findById(id);
		
		if (!endereco.isPresent())
			throw new EnderecoNotFoundException("id-" + id);
		
		return endereco.get();
	}

	@DeleteMapping("/enderecos/{id}")
	public void deleteEndereco(@PathVariable long id) {
		enderecoRepository.deleteById(id);
	}
	
	@PostMapping("/enderecos")
	public ResponseEntity<Object> createEndereco(@RequestBody Endereco endereco) {
		Endereco savedEndereco = enderecoRepository.save(endereco);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedEndereco.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/enderecos/{id}")
	public ResponseEntity<Object> updateEndereco(@RequestBody Endereco endereco, @PathVariable long id) {
		Optional<Endereco> enderecoOptional = enderecoRepository.findById(id);
		
		if (!enderecoOptional.isPresent())
			return ResponseEntity.notFound().build();
		
		endereco.setId(id);
		
		enderecoRepository.save(endereco);
		
		return ResponseEntity.noContent().build();
	}
	
}

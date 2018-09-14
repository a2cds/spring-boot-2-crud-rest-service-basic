package br.com.a2cds.springboot.crudrest.example.endereco;

import java.util.ArrayList;
import java.util.List;

public class EnderecoService {
	
	private static List<Endereco> enderecos = new ArrayList<>();
	
	static {
		Endereco endereco0 = new Endereco((long) 0,"R. Henrique Braglia", "257", "Ap. 46", "02244-000", "Parada Inglesa", "São Paulo", "SP");
		Endereco endereco1 = new Endereco((long) 1,"Av. Braz Leme", "1000", null, "02511-000", "Santana", "São Paulo", "SP");
		Endereco endereco2 = new Endereco((long) 2,"Av. Braz Leme", "1631", "Bl. A", "02068-030", "Santana", "São Paulo", "SP");
		
		enderecos.add(endereco0);
		enderecos.add(endereco1);
		enderecos.add(endereco2);
	}
	
	public List<Endereco> retrieveAllEnderecos() {
		return enderecos;
	}

	public Endereco retrieveEndereco(Long enderecoId) {
		for (Endereco endereco : enderecos) {
			if (endereco.getId().equals(enderecoId)) {
				return endereco;
			}
		}
		return null;
	}


	public Endereco addEndereco(Endereco endereco) {

		if (endereco == null) {
			return null;
		}

		enderecos.add(endereco);

		return endereco;
	}
}

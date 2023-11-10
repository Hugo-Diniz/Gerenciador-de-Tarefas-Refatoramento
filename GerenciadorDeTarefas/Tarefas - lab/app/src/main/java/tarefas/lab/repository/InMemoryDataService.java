package tarefas.lab.repository;

import tarefas.lab.domain.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryDataService implements DataService {
    protected List<Usuario> usuarios = new ArrayList<>();

    @Override
    public void add(Usuario user) {
        usuarios.add(user);
    }

    @Override
    public Optional<Usuario> getUsuarioAtual(String nome, String senha) {
        Optional<Usuario> result = usuarios.stream().filter(u -> u.getNomeDeUsuario().equals(nome) && u.getSenhaDeUsuario().equals(senha)).findFirst();
        if (result.isPresent()) {
            return result;
        }
        return result;
    }

    @Override
    public List<Usuario> getAll() {
        return usuarios;
    }

}

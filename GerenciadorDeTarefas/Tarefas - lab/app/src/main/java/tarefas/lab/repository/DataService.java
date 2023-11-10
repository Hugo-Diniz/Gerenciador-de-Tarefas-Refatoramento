package tarefas.lab.repository;

import java.util.List;
import java.util.Optional;

import tarefas.lab.domain.*;

public interface DataService {
    void add(Usuario user);
    Optional<Usuario> getUsuarioAtual(String nome, String senha);
    List<Usuario> getAll();
}

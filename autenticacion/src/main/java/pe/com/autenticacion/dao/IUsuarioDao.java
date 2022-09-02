package pe.com.autenticacion.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.autenticacion.model.Usuario;

import java.util.Optional;

public interface IUsuarioDao extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsuario(String usuario);
}

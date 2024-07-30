package Unad.telecom_fase5.servicios.user;

import Unad.telecom_fase5.entity.consultaVerifik.UserEntity;
import Unad.telecom_fase5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    // Crear o actualizar un usuario
    public UserEntity saveOrUpdateUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    // Obtener un usuario por ID
    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Eliminar un usuario por ID
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    // Obtener todos los usuarios
    public Iterable<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}

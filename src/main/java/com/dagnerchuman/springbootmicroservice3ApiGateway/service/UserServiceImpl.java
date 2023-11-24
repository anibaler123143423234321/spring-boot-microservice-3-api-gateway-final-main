package com.dagnerchuman.springbootmicroservice3ApiGateway.service;

import com.dagnerchuman.springbootmicroservice3ApiGateway.model.Role;
import com.dagnerchuman.springbootmicroservice3ApiGateway.model.User;
import com.dagnerchuman.springbootmicroservice3ApiGateway.repository.UserRepository;
import com.dagnerchuman.springbootmicroservice3ApiGateway.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {

        // Continuar con la creación del usuario
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setFechaCreacion(LocalDateTime.now());
        User userCreated = userRepository.save(user);
        String jwt = jwtProvider.generateToken(userCreated);
        userCreated.setToken(jwt);
        return userCreated;

    }


    @Override
    public Optional<User> findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {return userRepository.findByEmail(email);}

    @Transactional
    @Override
    public void changeRole(Role newRole, String username)
    {
        userRepository.updateUserRole(username, newRole);
    }

    @Override
    public User findByUsernameReturnToken(String username)
    {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario no existe:" + username));

        String jwt = jwtProvider.generateToken(user);
        user.setToken(jwt);
        return user;
    }

    @Override
    public User findUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @Override
    public void deleteUserByUsername(String username) {
        // Lógica para eliminar el usuario por nombre de usuario
        userRepository.findByUsername(username).ifPresent(userRepository::delete);
    }

    @Override
    public User updateUser(Long id, User updateUser) {
        Optional<User> existingUserOptional = userRepository.findById(id);

        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();

            // Llamar al nuevo método para actualizar los atributos
            User updatedUser = updateUserAttributes(existingUser, updateUser);

            return userRepository.save(updatedUser);
        } else {
            return null;
        }
    }


    // Nuevo método para actualizar atributos de manera más genérica
    private User updateUserAttributes(User existingUser, User updateUser) {
        if (updateUser.getNombre() != null) {
            existingUser.setNombre(updateUser.getNombre());
        }
        if (updateUser.getApellido() != null) {
            existingUser.setApellido(updateUser.getApellido());
        }
        if (updateUser.getUsername() != null) {
            existingUser.setUsername(updateUser.getUsername());
        }
        if (updateUser.getTelefono() != null) {
            existingUser.setTelefono(updateUser.getTelefono());
        }
        if (updateUser.getEmail() != null) {
            existingUser.setEmail(updateUser.getEmail());
        }
        if (updateUser.getNegocioId() != null) {
            existingUser.setNegocioId(updateUser.getNegocioId());
        }
        if (updateUser.getTipoDoc() != null) {
            existingUser.setTipoDoc(updateUser.getTipoDoc());
        }
        if (updateUser.getPicture() != null) {
            existingUser.setPicture(updateUser.getPicture());
        }
        if (updateUser.getDepartamento() != null) {
            existingUser.setDepartamento(updateUser.getDepartamento());
        }
        if (updateUser.getProvincia() != null) {
            existingUser.setProvincia(updateUser.getProvincia());
        }
        if (updateUser.getDistrito() != null) {
            existingUser.setDistrito(updateUser.getDistrito());
        }

        return existingUser;
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

}

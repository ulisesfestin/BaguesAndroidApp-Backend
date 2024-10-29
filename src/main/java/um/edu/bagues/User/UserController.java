package um.edu.bagues.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import um.edu.bagues.DTO.UserDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Retorna 204 si no hay usuarios
            }
            List<UserDTO> userDTOs = users.stream().map(this::convertToDTO).collect(Collectors.toList());
            return new ResponseEntity<>(userDTOs, HttpStatus.OK);  // Retorna 200 OK con la lista de usuarios
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);  // Retorna 500 si hay un error
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        Optional<User> userData = userService.getUserById(id);

        if (userData.isPresent()) {
            UserDTO userDTO = convertToDTO(userData.get());
            return new ResponseEntity<>(userDTO, HttpStatus.OK);  // Retorna 200 OK con el usuario
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Retorna 404 si no se encuentra el usuario
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody User user) {
        Optional<User> userData = userService.getUserById(id);

        if (userData.isPresent()) {
            User existingUser = userData.get();
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setPhoneNumber(user.getPhoneNumber());
            existingUser.setRole(user.getRole());
            // Actualiza otros campos según sea necesario

            User updatedUser = userService.updateUser(existingUser);
            UserDTO userDTO = convertToDTO(updatedUser);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);  // Retorna 200 OK con el usuario actualizado
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Retorna 404 si no se encuentra el usuario
        }
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRole(user.getRole());
        return userDTO;
    }
}
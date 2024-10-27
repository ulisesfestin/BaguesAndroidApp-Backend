package um.edu.bagues.DTO;

import lombok.Data;
import um.edu.bagues.User.Role;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Role role;
}
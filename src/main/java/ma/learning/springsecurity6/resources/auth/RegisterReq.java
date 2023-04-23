package ma.learning.springsecurity6.resources.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RegisterReq {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
}

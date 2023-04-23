package ma.learning.springsecurity6.resources.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthReq {
    private String email;
    private String password;
}

package retail.orders.MakeMyOrder.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import retail.orders.MakeMyOrder.Entity.User;
import retail.orders.MakeMyOrder.Repository.UserRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TokenServiceImp implements TokenService {

    @Autowired
    private JwtEncoder encoder;
    @Autowired
    private UserRepository userRepository;

    //Authenticated User
    //Returns a String of a token value
    @Override
    public String generateToken(Authentication authentication){
        //Time stamp
        Instant now = Instant.now();

        String id = "";
        Optional<User> user = userRepository.findByUsername(authentication.getName());
        if(user.isPresent()){
            id += Long.toString(user.get().getUserId());
        }

        //Gets the granted Authorities and creates a scope
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        //This checks creates a claim
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self") //Self signing JWTs
                .issuedAt(now) //When was it issued at
                .expiresAt(now.plus(1, ChronoUnit.HOURS)) //Expires in 1 hour
                .subject(authentication.getName())
                .claim("scope",scope) //Is the users scope
                .id(id)
                .build();
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue(); //Encodes the claim
    }
}

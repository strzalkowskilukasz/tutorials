package org.baeldung.config;

import java.security.Principal;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

  @PreAuthorize("#oauth2.clientHasRole('DUPA')")
  @RequestMapping(method = RequestMethod.GET, value = "/users/extra")
  @ResponseBody
  public void getExtraInfo(Authentication auth, Principal principal) {
    OAuth2AuthenticationDetails oauthDetails = (OAuth2AuthenticationDetails) auth.getDetails();
//    Map<String, Object> details = (Map<String, Object>) oauthDetails.getDecodedDetails();
//    System.out.println("User organization is " + details.get("organization"));
    System.out.println(oauthDetails);
    System.out.println(principal);
    System.out.println(oauthDetails.getDecodedDetails());
    System.out.println(auth.getAuthorities().stream().map(GrantedAuthority::getAuthority)
        .collect(Collectors.toSet()));
    System.out.println(auth.getAuthorities().size());
    System.out.println(auth.getAuthorities());
  }
}

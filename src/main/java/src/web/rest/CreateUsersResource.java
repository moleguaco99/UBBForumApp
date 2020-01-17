package src.web.rest;

import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import src.service.dto.UserDTO;
import src.service.dto.UserEmailDTO;
import src.web.rest.vm.ManagedUserVM;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/ourApi")
public class CreateUsersResource {

    private final String url = "http://localhost:8080/api/register";

    @PostMapping("/create-some-users")
    @Transactional
    public ResponseEntity<?> createSomeUsers(@RequestBody UserEmailDTO userEmailDTO) throws URISyntaxException {
        List<UserDTO> userDTOList = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<?> responseEntity1 = create1(userEmailDTO, userDTOList, restTemplate);
//        if (Objects.nonNull(responseEntity1)) {
//            return responseEntity1;
//        }
//        ResponseEntity<?> responseEntity2 = create2(userEmailDTO, userDTOList, restTemplate);
//        if (Objects.nonNull(responseEntity2)) {
//            return responseEntity2;
//        }
//        ResponseEntity<?> responseEntity3 = create3(userEmailDTO, userDTOList, restTemplate);
//        if (Objects.nonNull(responseEntity3)) {
//            return responseEntity3;
//        }
        ResponseEntity<?> responseEntity4 = create4(userEmailDTO, userDTOList, restTemplate);
        if (Objects.nonNull(responseEntity4)) {
            return responseEntity4;
        }
        ResponseEntity<?> responseEntity5 = create5(userEmailDTO, userDTOList, restTemplate);
        if (Objects.nonNull(responseEntity5)) {
            return responseEntity5;
        }
        return new ResponseEntity<>(userDTOList, HttpStatus.CREATED);
    }

    private ResponseEntity<?> create1(UserEmailDTO userEmailDTO, List<UserDTO> userDTOList, RestTemplate restTemplate) throws URISyntaxException {
        if (Objects.nonNull(userEmailDTO.email1)) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            ManagedUserVM managedUserVM1 = new ManagedUserVM();
            managedUserVM1.setLogin("fred");
            managedUserVM1.setFirstName("Fred");
            managedUserVM1.setLastName("Ramey");
            managedUserVM1.setEmail(userEmailDTO.email1);
            managedUserVM1.setLangKey("en");
            managedUserVM1.setCreatedBy("anonymousUser");
            Instant instant = Instant.now();
            managedUserVM1.setCreatedDate(instant);
            managedUserVM1.setLastModifiedBy("anonymousUser");
            managedUserVM1.setLastModifiedDate(instant);
            managedUserVM1.setPassword("fred");
            HttpEntity<ManagedUserVM> request1 = new HttpEntity<>(managedUserVM1, httpHeaders);
            URI uri1 = new URI(url);
            ResponseEntity<Void> result1 = restTemplate.postForEntity(uri1, request1, Void.class);
            if (result1.getStatusCode().equals(HttpStatus.CREATED)) {
                userDTOList.add(managedUserVM1);
            } else {
                return new ResponseEntity<>("Fred Ramey was not created.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("email1", HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    private ResponseEntity<?> create2(UserEmailDTO userEmailDTO, List<UserDTO> userDTOList, RestTemplate restTemplate) throws URISyntaxException {
        if (Objects.nonNull(userEmailDTO.email2)) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            ManagedUserVM managedUserVM1 = new ManagedUserVM();
            managedUserVM1.setLogin("janette");
            managedUserVM1.setFirstName("Janette");
            managedUserVM1.setLastName("Marshall");
            managedUserVM1.setEmail(userEmailDTO.email2);
            managedUserVM1.setLangKey("en");
            managedUserVM1.setCreatedBy("anonymousUser");
            Instant instant = Instant.now();
            managedUserVM1.setCreatedDate(instant);
            managedUserVM1.setLastModifiedBy("anonymousUser");
            managedUserVM1.setLastModifiedDate(instant);
            managedUserVM1.setPassword("janette");
            HttpEntity<ManagedUserVM> request1 = new HttpEntity<>(managedUserVM1, httpHeaders);
            URI uri1 = new URI(url);
            ResponseEntity<Void> result1 = restTemplate.postForEntity(uri1, request1, Void.class);
            if (result1.getStatusCode().equals(HttpStatus.CREATED)) {
                userDTOList.add(managedUserVM1);
            } else {
                return new ResponseEntity<>("Janette Marshall was not created.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("email2", HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    private ResponseEntity<?> create3(UserEmailDTO userEmailDTO, List<UserDTO> userDTOList, RestTemplate restTemplate) throws URISyntaxException {
        if (Objects.nonNull(userEmailDTO.email3)) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            ManagedUserVM managedUserVM1 = new ManagedUserVM();
            managedUserVM1.setLogin("julie");
            managedUserVM1.setFirstName("Julie");
            managedUserVM1.setLastName("Scherer");
            managedUserVM1.setEmail(userEmailDTO.email3);
            managedUserVM1.setLangKey("en");
            managedUserVM1.setCreatedBy("anonymousUser");
            Instant instant = Instant.now();
            managedUserVM1.setCreatedDate(instant);
            managedUserVM1.setLastModifiedBy("anonymousUser");
            managedUserVM1.setLastModifiedDate(instant);
            managedUserVM1.setPassword("julie");
            HttpEntity<ManagedUserVM> request1 = new HttpEntity<>(managedUserVM1, httpHeaders);
            URI uri1 = new URI(url);
            ResponseEntity<Void> result1 = restTemplate.postForEntity(uri1, request1, Void.class);
            if (result1.getStatusCode().equals(HttpStatus.CREATED)) {
                userDTOList.add(managedUserVM1);
            } else {
                return new ResponseEntity<>("Julie Scherer was not created.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("email3", HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    private ResponseEntity<?> create4(UserEmailDTO userEmailDTO, List<UserDTO> userDTOList, RestTemplate restTemplate) throws URISyntaxException {
        if (Objects.nonNull(userEmailDTO.email4)) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            ManagedUserVM managedUserVM1 = new ManagedUserVM();
            managedUserVM1.setLogin("claudiu");
            managedUserVM1.setFirstName("Claudiu");
            managedUserVM1.setLastName("Isopescu");
            managedUserVM1.setEmail(userEmailDTO.email4);
            managedUserVM1.setLangKey("en");
            managedUserVM1.setCreatedBy("anonymousUser");
            Instant instant = Instant.now();
            managedUserVM1.setCreatedDate(instant);
            managedUserVM1.setLastModifiedBy("anonymousUser");
            managedUserVM1.setLastModifiedDate(instant);
            managedUserVM1.setPassword("claudiu");
            HttpEntity<ManagedUserVM> request1 = new HttpEntity<>(managedUserVM1, httpHeaders);
            URI uri1 = new URI(url);
            ResponseEntity<Void> result1 = restTemplate.postForEntity(uri1, request1, Void.class);
            if (result1.getStatusCode().equals(HttpStatus.CREATED)) {
                userDTOList.add(managedUserVM1);
            } else {
                return new ResponseEntity<>("Claudiu Isopescu was not created.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("email4", HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    private ResponseEntity<?> create5(UserEmailDTO userEmailDTO, List<UserDTO> userDTOList, RestTemplate restTemplate) throws URISyntaxException {
        if (Objects.nonNull(userEmailDTO.email5)) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            ManagedUserVM managedUserVM1 = new ManagedUserVM();
            managedUserVM1.setLogin("cristina");
            managedUserVM1.setFirstName("Cristina");
            managedUserVM1.setLastName("Bazgan");
            managedUserVM1.setEmail(userEmailDTO.email5);
            managedUserVM1.setLangKey("en");
            managedUserVM1.setCreatedBy("anonymousUser");
            Instant instant = Instant.now();
            managedUserVM1.setCreatedDate(instant);
            managedUserVM1.setLastModifiedBy("anonymousUser");
            managedUserVM1.setLastModifiedDate(instant);
            managedUserVM1.setPassword("cristina");
            HttpEntity<ManagedUserVM> request1 = new HttpEntity<>(managedUserVM1, httpHeaders);
            URI uri1 = new URI(url);
            ResponseEntity<Void> result1 = restTemplate.postForEntity(uri1, request1, Void.class);
            if (result1.getStatusCode().equals(HttpStatus.CREATED)) {
                userDTOList.add(managedUserVM1);
            } else {
                return new ResponseEntity<>("Cristina Bazgan was not created.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("email5", HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}

package com.app.ecom.service;

import com.app.ecom.dto.AddressDTO;
import com.app.ecom.dto.UserRequest;
import com.app.ecom.dto.UserResponse;
import com.app.ecom.model.Address;
import com.app.ecom.repository.UserRepository;
import com.app.ecom.model.User;
import com.app.ecom.model.UserRole;
import org.hibernate.boot.model.process.internal.UserTypeResolution;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
//    private List<User> userlist = new ArrayList<>();
//    private long userid = 1L;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> fetchuser () {
        return userRepository.findAll().stream().map(this::maptoUserResponse).toList();
    }

    public Optional<UserResponse> fetchuser (Long id) {
        return userRepository.findById(id).map(this::maptoUserResponse);
    }

    public void adduser (UserRequest userRequest) {
//        user.setId(userid++);
//        userlist.add(user);
//        return userlist;
        User user = new User();
        maptoUserRequest(user, userRequest);
        if (user.getRole()==null){
            user.setRole(UserRole.CUSTOMER);
        }
        userRepository.save(user);
    }

    public boolean updateuser (long id, UserRequest userRequest) {
//        return userlist.stream()
//                .filter(u -> u.getId() == id)
//                .findFirst()
        return userRepository.findById(id)
                .map(u -> {
                    maptoUserRequest(u, userRequest);
                    userRepository.save(u);
                    return true;
                }).orElse(false);
    }

    private void maptoUserRequest(User user, UserRequest userRequest) {
        user.setEmail(userRequest.getEmail());
        user.setFirstName(userRequest.getFirstName());
        user.setLastname(userRequest.getLastname());
        user.setPhone(userRequest.getPhone());

        if (userRequest.getAddress()!=null) {
            Address address = new Address();
            address.setCity(userRequest.getAddress().getCity());
            address.setCountry(userRequest.getAddress().getCountry());
            address.setState(userRequest.getAddress().getState());
            address.setStreet(userRequest.getAddress().getStreet());
            address.setZipcode(userRequest.getAddress().getZipcode());
            user.setAddress(address);
        }
    }

    public UserResponse maptoUserResponse (User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastname(user.getLastname());
        userResponse.setPhone(user.getPhone());
        userResponse.setRole(user.getRole());

        if (user.getAddress()!=null) {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setCountry(user.getAddress().getCountry());
            addressDTO.setState(user.getAddress().getState());
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setZipcode(user.getAddress().getZipcode());
            userResponse.setAddress(addressDTO);
        }
        return userResponse;
    }
}

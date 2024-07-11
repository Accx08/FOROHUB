package com.aluracursos.foro_hub.api.domain.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserNameService {
    @Autowired
UserNameRepository userNameRepository;

    public UserName updateUserName(Long id, Long tokenId) {
        UserName user = userNameRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setTokenId(tokenId);

        return userNameRepository.save(user);
    }
}
